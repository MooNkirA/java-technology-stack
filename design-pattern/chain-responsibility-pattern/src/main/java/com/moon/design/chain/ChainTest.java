package com.moon.design.chain;

import com.alibaba.fastjson2.JSON;
import com.moon.design.chain.handler.AbstractCheckHandler;
import com.moon.design.chain.handler.ProductCheckHandlerConfig;
import com.moon.design.chain.vo.ProductVO;
import com.moon.design.chain.vo.Result;
import org.junit.jupiter.api.Test;
import org.junit.platform.commons.util.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.Map;
import java.util.Objects;

/**
 * 责任链测试
 *
 * @author MooNkirA
 * @version 1.0
 * @date 2023-10-07 22:17
 * @description
 */
@SpringBootTest(classes = ChainTest.class)
@SpringBootApplication
public class ChainTest {

    public static void main(String[] args) {
        SpringApplication.run(ChainTest.class);
    }

    /**
     * 使用 Spring 注入:所有继承了AbstractCheckHandler抽象类的Spring Bean都会注入进来。Map的Key对应Bean的name,Value是name对应相应的Bean
     */
    @Resource
    private Map<String, AbstractCheckHandler> handlerMap;


    // 测试
    @Test
    public void createProduct() {
        // 模拟请求上送的数据
        ProductVO param = ProductVO.builder()
                .skuId(1L).skuName("华为手机").Path("http://...")
                .price(new BigDecimal(-999))
                .stock(1)
                .build();
        // 参数校验，使用责任链模式
        Result paramCheckResult = this.paramCheck(param);
        if (!paramCheckResult.isSuccess()) {
            System.out.println("参数校验不通过：" + paramCheckResult.getMsg());
        }

        // 创建商品
        this.saveProduct(param);
    }


    private Result paramCheck(ProductVO param) {

        // 获取处理器配置：通常配置使用统一配置中心存储，支持动态变更
        ProductCheckHandlerConfig handlerConfig = this.getHandlerConfigFile();

        // 获取处理器
        AbstractCheckHandler handler = this.getHandler(handlerConfig);

        // 通过客户端，执行处理器链路
        Result executeChainResult = HandlerClient.executeChain(handler, param);
        if (!executeChainResult.isSuccess()) {
            System.out.println("创建商品 失败...");
            return executeChainResult;
        }

        // 处理器链路全部成功
        return Result.success();
    }

    /**
     * 获取处理器配置：通常配置使用统一配置中心存储，支持动态变更。（这里示例模拟从配置中心读取json字符串）
     */
    private ProductCheckHandlerConfig getHandlerConfigFile() {
        // 模拟配置中心存储的配置
        String configJson = "{\"handler\":\"nullValueCheckHandler\",\"down\":true,\"next\":{\"handler\":\"priceCheckHandler\",\"next\":{\"handler\":\"stockCheckHandler\",\"next\":null}}}";
        // 转成Config对象
        ProductCheckHandlerConfig handlerConfig = JSON.parseObject(configJson, ProductCheckHandlerConfig.class);
        return handlerConfig;
    }

    /**
     * 获取处理器
     *
     * @param config
     * @return
     */
    private AbstractCheckHandler getHandler(ProductCheckHandlerConfig config) {
        // 配置检查：没有配置处理器链路，则不执行校验逻辑
        if (Objects.isNull(config)) {
            return null;
        }
        // 配置错误
        String handler = config.getHandler();
        if (StringUtils.isBlank(handler)) {
            return null;
        }
        // 配置了不存在的处理器
        AbstractCheckHandler abstractCheckHandler = handlerMap.get(config.getHandler());
        if (Objects.isNull(abstractCheckHandler)) {
            return null;
        }

        // 处理器设置配置 Config
        abstractCheckHandler.setConfig(config);

        // 递归设置链路处理器
        abstractCheckHandler.setNextHandler(this.getHandler(config.getNext()));

        return abstractCheckHandler;
    }

    private Result saveProduct(ProductVO productVO) {
        System.out.println("模拟保存产品");
        return Result.success();
    }
}
