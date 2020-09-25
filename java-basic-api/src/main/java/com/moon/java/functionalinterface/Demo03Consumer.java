package com.moon.java.functionalinterface;

import org.junit.Test;

import java.util.function.Consumer;

/**
 * JDK 内置函数式接口 - Consumer 基础使用示例
 *
 * @author MooNkirA
 * @version 1.0
 * @date 2020-9-25 16:05
 * @description
 */
public class Demo03Consumer {

    @Test
    public void consumerTest() {
        System.out.println("程序开始!");
        // 使用Lambda表达式将一个字符串转成大写的字符串
        printString(str -> System.out.println(str.toUpperCase()));
        System.out.println("程序结束!!");
    }

    private void printString(Consumer<String> consumer) {
        System.out.println("printString()方法执行开始...");
        // 调用“消费型”接口Consumer，处理传入的字符串
        consumer.accept("Hello Consumer");
        System.out.println("printString()方法执行结束...");
    }

}
