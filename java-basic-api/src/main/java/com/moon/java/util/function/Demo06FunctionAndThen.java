package com.moon.java.util.function;

import org.junit.Test;

import java.util.function.Function;

/**
 * JDK 内置函数式接口 - Function 的 andThen() 方法基础使用示例
 *
 * @author MooNkirA
 * @version 1.0
 * @date 2020-9-25 16:48
 * @description
 */
public class Demo06FunctionAndThen {

    @Test
    public void functionTest() {
        System.out.println("程序开始!");
        // 使用Lambda表达式先将字符串解析成为int数字，再操作数字乘以10
        stringToInteger(str -> Integer.parseInt(str), i -> i * 10);
        System.out.println("程序结束!!");
    }

    private void stringToInteger(Function<String, Integer> f1, Function<Integer, Integer> f2) {
        System.out.println("stringToInteger()方法执行开始...");
        // 实现方式一：选择调用“转换型”接口Function，先后处理不同的转换逻辑
        // Integer num = f1.apply("8");
        // Integer result = f2.apply(num);

        // 实现方式二：使用Function接口的andThen方法，实现先后执行不同的Function接口实现
        Integer result = f1.andThen(f2).apply("8");

        System.out.println("字符串转数字类型再乘10后结果：" + result);
        System.out.println("stringToInteger()方法执行结束...");
    }

}
