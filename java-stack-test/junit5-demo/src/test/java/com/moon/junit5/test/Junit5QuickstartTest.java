package com.moon.junit5.test;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Junit 5 快速开始
 *
 * @author MooNkirA
 * @version 1.0
 * @date 2024-02-07 18:52
 * @description
 */
public class Junit5QuickstartTest {

    @Test // JUnit 5 不再需要手动将测试类与测试方法设置为 public，包可见的访问级别就足够了。
    void firstTest() {
        assertEquals(2, 1 + 1);
    }

}
