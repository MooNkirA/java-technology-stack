package com.moon.design.chain.enums;

import lombok.Getter;

/**
 * 错误码
 *
 * @author MooNkirA
 * @version 1.0
 * @date 2023-10-07 20:44
 * @description
 */
@Getter
public enum ErrorCode {

    PARAM_NULL_ERROR("001", "参数为空"),
    PARAM_SKU_NULL_ERROR("002", "SkuId商品主键参数为空"),
    PARAM_PRICE_NULL_ERROR("003", "Price 价格参数为空"),
    PARAM_STOCK_NULL_ERROR("004", "Stock 库存参数为空"),
    PARAM_PRICE_ILLEGAL_ERROR("005", "非法价格"),
    PARAM_STOCK_ILLEGAL_ERROR("005", "非法库存");

    ErrorCode(String code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    private final String code;
    private final String msg;

}
