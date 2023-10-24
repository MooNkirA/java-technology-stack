package com.moon.design.factory.method;

import com.moon.design.factory.service.AmericanCoffee;
import com.moon.design.factory.service.Coffee;

/**
 * 美式咖啡工厂类
 *
 * @author MooNkirA
 * @version 1.0
 * @date 2023-09-29 23:17
 * @description
 */
public class AmericanCoffeeFactory implements CoffeeFactory {

    /**
     * 创建美式咖啡
     */
    @Override
    public Coffee createCoffee() {
        return new AmericanCoffee();
    }
}

