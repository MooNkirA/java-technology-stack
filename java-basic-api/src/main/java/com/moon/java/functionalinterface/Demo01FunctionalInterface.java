package com.moon.java.functionalinterface;

import org.junit.Test;

/**
 * 函数式接口 @FunctionalInterface 基础使用示例
 *
 * @author MooNkirA
 * @version 1.0
 * @date 2020-9-25 15:19
 * @description
 */
public class Demo01FunctionalInterface {

    @Test
    public void functionalInterfaceTest() {
        // 创建待求和数组
        int[] arr = {1, 2, 3, 4};
        // 使用lambda表达式方式，调用方法
        sum(arr, a -> {
            int total = 0;
            for (int n : a) {
                total += n;
            }
            return total;
        });
    }

    // 定义方法，方法形参为自定义的函数式接口作为方法参数
    private void sum(int[] arr, Operator operator) {
        // 1. 调用函数式接口的求和抽象方法
        int sum = operator.getSum(arr);
        // 2. 输入结果
        System.out.println("数组的计算结果是：" + sum);
    }
}

/**
 * 定义函数式接口（只有一个抽象方法，可以有多个默认方法与静态方法）
 */
@FunctionalInterface
interface Operator {
    int getSum(int[] arr);
}
