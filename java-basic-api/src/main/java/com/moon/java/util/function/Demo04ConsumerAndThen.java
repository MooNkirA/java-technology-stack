package com.moon.java.util.function;

import org.junit.Test;

import java.util.function.Consumer;

/**
 * JDK 内置函数式接口 - Consumer 的 andThen() 方法基础使用示例
 *
 * @author MooNkirA
 * @version 1.0
 * @date 2020-9-25 16:11
 * @description
 */
public class Demo04ConsumerAndThen {

    @Test
    public void consumerTest() {
        System.out.println("程序开始!");
        /* 使用Lambda表达式先将一个字符串转成小写的字符串,再转成大写 */
        printString(str -> System.out.println(str.toLowerCase()),
                str -> System.out.println(str.toUpperCase()));
        System.out.println("程序结束!!");
    }

    private void printString(Consumer<String> c1, Consumer<String> c2) {
        System.out.println("printString()方法执行开始...");
        // 待处理字符串
        String str = "Hello Consumer";
        // 实现方式一：先后调用两个“消费型”接口Consumer，处理不同的逻辑
        // c1.accept(str);
        // c2.accept(str);

        // 实现方式二：使用Consumer接口的andThen方法，实现先后执行不同的Consumer接口实现
        c1.andThen(c2).accept(str);
        System.out.println("printString()方法执行结束...");
    }

}
