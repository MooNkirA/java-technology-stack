package com.moon.design.chain.basic;

/**
 * 职责链模式测试
 *
 * @author MooNkirA
 * @version 1.0
 * @date 2022-05-25 14:27
 * @description
 */
public class ChainOfResponsibilityPatternTest {

    public static void main(String[] args) {
        // 初始化责任链
        Handler handler1 = new ConcreteHandler1();
        Handler handler2 = new ConcreteHandler2();
        Handler handler3 = new ConcreteHandler3();
        handler1.setNext(handler2);
        handler2.setNext(handler3);

        // 调用键进行处理
        handler1.handleRequest("three");
    }

}
