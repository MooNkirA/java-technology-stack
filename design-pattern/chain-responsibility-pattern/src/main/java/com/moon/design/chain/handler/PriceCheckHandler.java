package com.moon.design.chain.handler;

import com.moon.design.chain.enums.ErrorCode;
import com.moon.design.chain.vo.ProductVO;
import com.moon.design.chain.vo.Result;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

/**
 * 价格校验处理器
 *
 * @author MooNkirA
 * @version 1.0
 * @date 2023-10-07 20:51
 * @description
 */
@Component
public class PriceCheckHandler extends AbstractCheckHandler {
    @Override
    public Result handle(ProductVO param) {
        System.out.println("价格校验 Handler 开始...");

        // 非法价格校验
        boolean illegalPrice = param.getPrice().compareTo(BigDecimal.ZERO) <= 0;
        if (illegalPrice) {
            return Result.failure(ErrorCode.PARAM_PRICE_ILLEGAL_ERROR);
        }
        // 其他校验逻辑...

        System.out.println("价格校验 Handler 通过...");

        // 执行下一个处理器
        return super.next(param);
    }
}
