package com.moon.design.strategy.basic;

/**
 * 为中秋准备的促销活动B
 *
 * @author MooNkirA
 * @version 1.0
 * @date 2023-09-30 08:38
 * @description
 */
public class StrategyB implements Strategy {

    @Override
    public void show() {
        System.out.println("满200元减50元");
    }

}