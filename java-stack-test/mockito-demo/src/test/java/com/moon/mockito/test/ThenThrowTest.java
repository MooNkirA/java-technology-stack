package com.moon.mockito.test;

import org.junit.Assert;
import org.junit.Test;

import java.util.Random;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 * Mock 异常测试示例
 *
 * @author MooNkirA
 * @version 1.0
 * @date 2024-02-08 13:57
 * @description
 */
public class ThenThrowTest {
    /**
     * 例子1： thenThrow 用来让函数调用抛出异常.
     */
    @Test
    public void throwTest1() {

        Random mockRandom = mock(Random.class);
        when(mockRandom.nextInt()).thenThrow(new RuntimeException("异常"));

        try {
            mockRandom.nextInt();
            Assert.fail();  // 上面会抛出异常，所以不会走到这里
        } catch (Exception ex) {
            Assert.assertTrue(ex instanceof RuntimeException);
            Assert.assertEquals("异常", ex.getMessage());
        }
    }

    /**
     * thenThrow 中可以指定多个异常。在调用时异常依次出现。若调用次数超过异常的数量，再次调用时抛出最后一个异常。
     */
    @Test
    public void throwTest2() {

        Random mockRandom = mock(Random.class);
        when(mockRandom.nextInt()).thenThrow(new RuntimeException("异常1"), new RuntimeException("异常2"));

        try {
            mockRandom.nextInt();
            Assert.fail();
        } catch (Exception ex) {
            Assert.assertTrue(ex instanceof RuntimeException);
            Assert.assertEquals("异常1", ex.getMessage());
        }

        try {
            mockRandom.nextInt();
            Assert.fail();
        } catch (Exception ex) {
            Assert.assertTrue(ex instanceof RuntimeException);
            Assert.assertEquals("异常2", ex.getMessage());
        }
    }

}
