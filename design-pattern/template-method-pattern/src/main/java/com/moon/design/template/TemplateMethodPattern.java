package com.moon.design.template;

/**
 * 模板方法设计模式
 *
 * @author MooNkirA
 * @version 1.0
 * @date 2022-05-16 21:59
 * @description
 */
public class TemplateMethodPattern {

    public static void main(String[] args) {
        AbstractClass tm = new ConcreteClass();
        tm.TemplateMethod();
    }

}
