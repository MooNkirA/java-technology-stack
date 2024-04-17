package com.moon.junit5.test;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

/**
 * 禁用测试方法示例
 *
 * @author MooNkirA
 * @version 1.0
 * @date 2024-02-07 19:27
 * @description
 */
public class DisabledMethodTest {

    @Disabled
    @Test
    void testWillBeSkipped() {
    }

    @Test
    void testWillBeExecuted() {
    }

}
