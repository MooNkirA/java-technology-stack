package com.moon.mockito.test;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Random;

import static org.mockito.Mockito.when;

/**
 * 使用 @Mock 注解示例
 *
 * @author MooNkirA
 * @version 1.0
 * @date 2024-02-08 13:38
 * @description
 */
@RunWith(MockitoJUnitRunner.class)
public class MockAnnotationTest {

    @Mock
    private Random random;

    @Test
    public void test() {
        when(random.nextInt()).thenReturn(100);
        Assert.assertEquals(100, random.nextInt());
    }

}
