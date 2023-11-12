package com.moon.design.chain.handler;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * 处理器配置类
 *
 * @author MooNkirA
 * @version 1.0
 * @date 2023-10-07 20:40
 * @description
 */
@AllArgsConstructor
@Data
public class ProductCheckHandlerConfig {

    /**
     * 处理器Bean名称
     */
    private String handler;
    /**
     * 下一个处理器
     */
    private ProductCheckHandlerConfig next;
    /**
     * 是否降级
     */
    private Boolean down = Boolean.FALSE;

}
