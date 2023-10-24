package com.moon.design.factory.service;

import com.moon.design.factory.service.Coffee;

/**
 * 美式咖啡
 *
 * @author MooNkirA
 * @version 1.0
 * @date 2023-09-29 18:45
 * @description
 */
public class AmericanCoffee implements Coffee {

    /**
     * 获取名字
     *
     * @return
     */
    @Override
    public String getName() {
        return "americanCoffee";
    }

    /**
     * 加牛奶
     */
    @Override
    public void addMilk() {
        System.out.println("AmericanCoffee...addMilk...");
    }

    /**
     * 加糖
     */
    @Override
    public void addSuqar() {
        System.out.println("AmericanCoffee...addSuqar...");
    }
}
