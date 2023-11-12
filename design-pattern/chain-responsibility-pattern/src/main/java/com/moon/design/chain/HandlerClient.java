package com.moon.design.chain;

import com.moon.design.chain.handler.AbstractCheckHandler;
import com.moon.design.chain.vo.ProductVO;
import com.moon.design.chain.vo.Result;

/**
 * 客户端：执行处理器链路
 *
 * @author MooNkirA
 * @version 1.0
 * @date 2023-10-07 20:53
 * @description
 */
public class HandlerClient {

    public static Result executeChain(AbstractCheckHandler handler, ProductVO param) {
        // 执行处理器
        Result handlerResult = handler.handle(param);
        if (!handlerResult.isSuccess()) {
            System.out.println("HandlerClient 责任链执行失败返回：" + handlerResult.toString());
            return handlerResult;
        }
        return Result.success();
    }

}
