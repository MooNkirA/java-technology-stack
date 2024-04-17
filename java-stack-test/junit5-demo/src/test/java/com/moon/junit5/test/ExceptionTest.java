package com.moon.junit5.test;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertThrows;

/**
 * 异常测试示例
 *
 * @author MooNkirA
 * @version 1.0
 * @date 2024-02-07 22:21
 * @description
 */
public class ExceptionTest {

    // 标准的测试例子
    @Test
    @DisplayName("Exception Test Demo")
    void assertThrowsException() {
        String str = null;
        assertThrows(IllegalArgumentException.class, () -> Integer.valueOf(str));
    }

    // 注:异常失败例子，当Lambda表达式中代码出现的异常会跟首个参数的异常类型进行比较，如果不属于同一类异常，则失败
    @Test
    @DisplayName("Exception Test Demo2")
    void assertThrowsException2() {
        String str = null;
        assertThrows(NullPointerException.class, () -> Integer.valueOf(str));
    }

}
