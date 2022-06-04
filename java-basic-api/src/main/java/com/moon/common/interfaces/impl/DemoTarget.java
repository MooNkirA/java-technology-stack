package com.moon.common.interfaces.impl;

import com.moon.common.interfaces.DemoInterface;

/**
 * 示例接口实现，用于测试代理
 *
 * @author MooNkirA
 * @version 1.0
 * @date 2022-05-23 10:10
 * @description
 */
public class DemoTarget implements DemoInterface {

    @Override
    public void foo() {
        System.out.println("被代理类 DemoTarget.foo() 方法执行了...");
    }

    @Override
    public int bar() {
        System.out.println("被代理类 DemoTarget.bar() 方法执行了...");
        return 99;
    }

}
