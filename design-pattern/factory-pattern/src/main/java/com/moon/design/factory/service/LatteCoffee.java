package com.moon.design.factory.service;

/**
 * 拿铁咖啡
 *
 * @author MooNkirA
 * @version 1.0
 * @date 2023-09-29 18:45
 * @description
 */
public class LatteCoffee implements Coffee {
    /**
     * 获取名字
     *
     * @return
     */
    @Override
    public String getName() {
        return "latteCoffee";
    }

    /**
     * 加牛奶
     */
    @Override
    public void addMilk() {
        System.out.println("LatteCoffee...addMilk...");
    }

    /**
     * 加糖
     */
    @Override
    public void addSuqar() {
        System.out.println("LatteCoffee...addSuqar...");
    }
}
