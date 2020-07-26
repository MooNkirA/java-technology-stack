package com.moon.jvm;

/**
 * 栈溢出错误测试
 *
 * @author MooNkirA
 * @version 1.0
 * @date 2020-7-26 08:59
 * @description
 */
public class StackError {

    public static void main(String[] args) {
        A();
    }

    public static void A() {
        A();
    }

}
