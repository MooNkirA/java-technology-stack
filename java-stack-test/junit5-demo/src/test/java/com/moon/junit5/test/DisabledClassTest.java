package com.moon.junit5.test;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

/**
 * 禁用整个类测试示例
 *
 * @author MooNkirA
 * @version 1.0
 * @date 2024-02-07 19:26
 * @description
 */
@Disabled
public class DisabledClassTest {

    @Test
    void testWillBeSkipped() {
    }

}
