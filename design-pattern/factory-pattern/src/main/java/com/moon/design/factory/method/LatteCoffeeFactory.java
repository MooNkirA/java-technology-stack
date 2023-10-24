package com.moon.design.factory.method;

import com.moon.design.factory.service.Coffee;
import com.moon.design.factory.service.LatteCoffee;

/**
 * 拿铁咖啡工厂类
 *
 * @author MooNkirA
 * @version 1.0
 * @date 2023-09-29 23:17
 * @description
 */
public class LatteCoffeeFactory implements CoffeeFactory {

    /**
     * 创建拿铁咖啡
     */
    @Override
    public Coffee createCoffee() {
        return new LatteCoffee();
    }
}
