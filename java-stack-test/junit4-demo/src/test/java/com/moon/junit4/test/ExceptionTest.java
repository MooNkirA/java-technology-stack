package com.moon.junit4.test;

import org.junit.Test;

/**
 * 异常测试示例
 *
 * @author MooNkirA
 * @version 1.0
 * @date 2024-02-07 17:34
 * @description
 */
public class ExceptionTest {

    @Test(expected = ArithmeticException.class)
    public void exceptionTest() {
        System.out.println("in exception success test");
        int a = 0;
        int b = 1 / a;
    }

    @Test(expected = NullPointerException.class)
    public void exceptionFailTest() {
        System.out.println("in exception fail test");
        int a = 0;
        int b = 1 / a;
    }

}
