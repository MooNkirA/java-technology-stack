package com.moon.java.functionalinterface;

import org.junit.Test;

import java.util.Arrays;
import java.util.function.Supplier;

/**
 * JDK 内置函数式接口 - Supplier 基础使用示例
 *
 * @author MooNkirA
 * @version 1.0
 * @date 2020-9-25 15:43
 * @description
 */
public class Demo02Supplier {

    @Test
    public void supplierTest() {
        // 使用Lambda表达式返回数组元素最大值
        printMax(() -> {
            System.out.println("Supplier接口实现get()方法执行开始...");
            int[] arr = {11, 99, 88, 77, 22};
            // Arrays工具类的sort方法默认是升序排序
            Arrays.sort(arr);
            return arr[arr.length - 1];
        });
    }

    private void printMax(Supplier<Integer> supplier) {
        System.out.println("printMax()方法执行开始...");
        // 调用“供给”接口Supplier，获取数组最大值
        Integer max = supplier.get();
        System.out.println("max = " + max);
        System.out.println("printMax()方法执行结束...");
    }
}
