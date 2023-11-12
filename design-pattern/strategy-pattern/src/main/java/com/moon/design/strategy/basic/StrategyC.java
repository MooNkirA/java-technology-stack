package com.moon.design.strategy.basic;

/**
 * 为圣诞准备的促销活动C
 *
 * @author MooNkirA
 * @version 1.0
 * @date 2023-09-30 08:38
 * @description
 */
public class StrategyC implements Strategy {

    // 为圣诞准备的促销活动C
    @Override
    public void show() {
        System.out.println("满1000元加一元换购任意200元以下商品");
    }

}