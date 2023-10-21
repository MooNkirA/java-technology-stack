package com.moon.design.chain.handler;

import com.moon.design.chain.enums.ErrorCode;
import com.moon.design.chain.vo.ProductVO;
import com.moon.design.chain.vo.Result;
import org.springframework.stereotype.Component;

/**
 * 库存校验处理器
 *
 * @author MooNkirA
 * @version 1.0
 * @date 2023-10-07 20:52
 * @description
 */
@Component
public class StockCheckHandler extends AbstractCheckHandler {
    @Override
    public Result handle(ProductVO param) {
        System.out.println("库存校验 Handler 开始...");

        // 非法库存校验
        boolean illegalStock = param.getStock() < 0;
        if (illegalStock) {
            return Result.failure(ErrorCode.PARAM_STOCK_ILLEGAL_ERROR);
        }
        // 其他校验逻辑..

        System.out.println("库存校验 Handler 通过...");

        // 执行下一个处理器
        return super.next(param);
    }
}
