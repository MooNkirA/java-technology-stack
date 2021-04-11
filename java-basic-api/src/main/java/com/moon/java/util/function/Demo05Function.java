package com.moon.java.util.function;

import org.junit.Test;

import java.util.function.Function;

/**
 * JDK 内置函数式接口 - Function 基础使用示例
 *
 * @author MooNkirA
 * @version 1.0
 * @date 2020-9-25 16:38
 * @description
 */
public class Demo05Function {

    @Test
    public void functionTest() {
        System.out.println("程序开始!");
        // 使用Lambda表达式将字符串转成数字
        stringToInteger(str -> Integer.parseInt(str));
        System.out.println("程序结束!!");
    }

    private void stringToInteger(Function<String, Integer> function) {
        System.out.println("stringToInteger()方法执行开始...");
        // 调用“转换型”接口Function，处理传入的字符串转成数字类型
        Integer num = function.apply("8");
        System.out.println("字符串转数字类型结果：" + num);
        System.out.println("stringToInteger()方法执行结束...");
    }

}
