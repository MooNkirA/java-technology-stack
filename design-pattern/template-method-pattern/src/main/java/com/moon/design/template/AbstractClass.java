package com.moon.design.template;

/**
 * 抽象类，类中定义主要的模板方法与抽象方法
 *
 * @author MooNkirA
 * @version 1.0
 * @date 2022-05-16 22:00
 * @description
 */
public abstract class AbstractClass {

    // 模板方法。定义了方法逻辑的骨架，按某种顺序调用其包含的基本方法。
    public void TemplateMethod() {
        abstractMethod1(); // 抽象方法
        hookMethod1();
        if (hookMethod2()) { // 抽象类已实现，但子类可以进行修改
            SpecificMethod(); // 具体方法
        }
        abstractMethod2(); // 抽象方法
    }

    // 具体方法
    public void SpecificMethod() {
        System.out.println("抽象类中的具体方法被调用...");
    }

    // 钩子方法1，方法无处理逻辑，由子类来重写
    public void hookMethod1() {
    }

    // 钩子方法2
    public boolean hookMethod2() {
        return true;
    }

    // 抽象方法1，由实现类来处理
    public abstract void abstractMethod1();

    // 抽象方法2，由实现类来处理
    public abstract void abstractMethod2();
}
