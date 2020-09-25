package com.moon.java.functionalinterface;

import org.junit.Test;

import java.util.function.Predicate;

/**
 * JDK 内置函数式接口 - Predicate 基础使用示例
 *
 * @author MooNkirA
 * @version 1.0
 * @date 2020-9-25 17:14
 * @description
 */
public class Demo07Predicate {

    @Test
    public void predicateTest() {
        System.out.println("程序开始!");
        // 使用Lambda判断一个人名如果超过3个字就认为是很长的名字
        isLongName("石原里美", str -> str.length() > 3);
        System.out.println("程序结束!!");
    }

    private void isLongName(String name, Predicate<String> predicate) {
        System.out.println("isLongName()方法执行开始...");
        // 调用“判断型”接口Predicate，进行相应的逻辑处理
        boolean isLong = predicate.test(name);
        System.out.println("名字是否过长：" + isLong);
        System.out.println("isLongName()方法执行结束...");
    }

}
