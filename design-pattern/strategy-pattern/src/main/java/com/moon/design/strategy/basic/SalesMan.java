package com.moon.design.strategy.basic;

/**
 * 环境角色（Context）：用于连接上下文
 *
 * @author MooNkirA
 * @version 1.0
 * @date 2023-09-30 08:43
 * @description
 */

public class SalesMan {
    // 持有抽象策略角色的引用
    private Strategy strategy;

    public SalesMan(Strategy strategy) {
        this.strategy = strategy;
    }

    // 向客户展示促销活动
    public void salesManShow() {
        strategy.show();
    }

    // 测试
    public static void main(String[] args) {
        SalesMan salesMan = new SalesMan(new StrategyB());
        salesMan.salesManShow();
    }
}