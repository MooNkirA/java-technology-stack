package com.moon.design.strategy.basic;

/**
 * 为春节准备的促销活动A
 *
 * @author MooNkirA
 * @version 1.0
 * @date 2023-09-30 08:38
 * @description
 */
public class StrategyA implements Strategy {

    @Override
    public void show() {
        System.out.println("买一送一");
    }

}