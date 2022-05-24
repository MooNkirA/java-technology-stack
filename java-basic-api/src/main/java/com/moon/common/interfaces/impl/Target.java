package com.moon.common.interfaces.impl;

/**
 * 用于 CGlib 模拟动态代理测试
 *
 * @author MooNkirA
 * @version 1.0
 * @date 2022-05-23 11:25
 * @description
 */
public class Target {

    public void save() {
        System.out.println("被代理类 Target.save() 方法执行...");
    }

    public void save(int i) {
        System.out.println("被代理类 Target.save(int) 方法执行，参数：" + i);
    }

    public void save(long j) {
        System.out.println("被代理类 Target.save(long) 方法执行，参数：" + j);
    }

}
