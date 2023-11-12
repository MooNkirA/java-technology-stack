package com.moon.design.factory.method;

import com.moon.design.factory.service.Coffee;

/**
 * 咖啡工厂接口
 *
 * @author MooNkirA
 * @version 1.0
 * @date 2023-09-29 23:14
 * @description
 */
public interface CoffeeFactory {

    /**
     * 创建咖啡
     */
    Coffee createCoffee();

}
