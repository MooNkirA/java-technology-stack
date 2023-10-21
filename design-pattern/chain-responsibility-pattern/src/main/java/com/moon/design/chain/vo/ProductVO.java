package com.moon.design.chain.vo;

import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;

/**
 * 商品对象
 *
 * @author MooNkirA
 * @version 1.0
 * @date 2023-10-07 20:27
 * @description
 */
@Data
@Builder
public class ProductVO {
    /**
     * 商品SKU，唯一
     */
    private Long skuId;
    /**
     * 商品名称
     */
    private String skuName;
    /**
     * 商品图片路径
     */
    private String Path;
    /**
     * 价格
     */
    private BigDecimal price;
    /**
     * 库存
     */
    private Integer stock;
}
