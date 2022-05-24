package com.moon.design.template;

/**
 * 实现类
 *
 * @author MooNkirA
 * @version 1.0
 * @date 2022-05-16 22:04
 * @description
 */
public class ConcreteClass extends AbstractClass {

    @Override
    public void abstractMethod1() {
        System.out.println("抽象方法1的实现被调用...");
    }

    @Override
    public void abstractMethod2() {
        System.out.println("抽象方法2的实现被调用...");
    }

    @Override
    public void hookMethod1() {
        System.out.println("钩子方法1被重写...");
    }

    @Override
    public boolean hookMethod2() {
        // 改变抽象类中原有方法逻辑
        return false;
    }
}
