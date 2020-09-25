package com.moon.java.functionalinterface;

import org.junit.Test;

import java.util.function.Predicate;

/**
 * JDK 内置函数式接口 - Predicate 的 and()、or()、negate() 方法基础使用示例
 *
 * @author MooNkirA
 * @version 1.0
 * @date 2020-9-25 17:27
 * @description
 */
public class Demo08PredicateAndOrNegate {

    @Test
    public void predicateTest() {
        System.out.println("程序开始!");
        test("Hello World", str -> str.contains("W"), str -> str.contains("H"));
        System.out.println("程序结束!!");
    }

    private void test(String str, Predicate<String> p1, Predicate<String> p2) {
        System.out.println("test()方法执行开始...");
        // 使用Lambda表达式判断一个字符串中既包含W,也包含H
        // and方法，相当于 p1.test(str) && p2.test(str)
        boolean b1 = p1.and(p2).test(str);
        if (b1) {
            System.out.println("既包含W,也包含H");
        }

        // 使用Lambda表达式判断一个字符串中包含W或者包含H
        // or方法，相当于 p1.test(str) || p2.test(str)
        boolean b2 = p1.or(p2).test(str);
        if (b2) {
            System.out.println("包含W或者包含H");
        }

        // 使用Lambda表达式判断一个字符串中不包含W
        // negate相当于取反 相当于 !p1.test(str)
        boolean b3 = p1.negate().test(str);
        if (b3) {
            System.out.println("不包含W");
        }
        System.out.println("test()方法执行结束...");
    }

}
