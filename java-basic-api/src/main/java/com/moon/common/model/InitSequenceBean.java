package com.moon.common.model;

/**
 * Java类初始化顺序测试
 *
 * @author MooNkirA
 * @version 1.0
 * @date 2021-3-9 22:49
 * @description
 */
public class InitSequenceBean {

    private static final String staticStr = initStaticMember();
    private final String str = initOrdinaryMember();

    static {
        System.out.println("静态代码块执行了....");
    }

    {
        System.out.println("初始化代码块执行了....");
    }

    public InitSequenceBean() {
        System.out.println("无参构造函数执行了....");
    }

    private static String initStaticMember() {
        System.out.println("静态成员变量初始化....");
        return "123";
    }

    private String initOrdinaryMember() {
        System.out.println("普通成员变量初始化....");
        return "abc";
    }

}
