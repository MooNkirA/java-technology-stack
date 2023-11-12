package com.moon.design.factory.simple;

import com.moon.design.factory.service.AmericanCoffee;
import com.moon.design.factory.service.Coffee;
import com.moon.design.factory.service.LatteCoffee;

/**
 * 简单工厂
 *
 * @author MooNkirA
 * @version 1.0
 * @date 2023-09-29 19:17
 * @description
 */
public class SimpleCoffeeFactory {

    public static Coffee createCoffee(String type) {
        Coffee coffee = null;
        if ("american".equals(type)) {
            coffee = new AmericanCoffee();
        } else if ("latte".equals(type)) {
            coffee = new LatteCoffee();
        }
        return coffee;
    }

}
