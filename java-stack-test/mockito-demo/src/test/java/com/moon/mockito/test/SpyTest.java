package com.moon.mockito.test;

import org.junit.Assert;
import org.junit.Test;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.when;

/**
 * spy 基础使用示例与 Mock 的对比
 *
 * @author MooNkirA
 * @version 1.0
 * @date 2024-02-08 14:13
 * @description
 */
public class SpyTest {
    // 测试 spy
    @Test
    public void test_spy() {
        ExampleService spyExampleService = spy(new ExampleService());
        // 默认会走真实方法
        Assert.assertEquals(3, spyExampleService.add(1, 2));
        // 打桩后，不会走了
        when(spyExampleService.add(1, 2)).thenReturn(10);
        Assert.assertEquals(10, spyExampleService.add(1, 2));
        // 但是参数比匹配的调用，依然走真实方法
        Assert.assertEquals(3, spyExampleService.add(2, 1));
    }

    // 测试 mock
    @Test
    public void test_mock() {
        ExampleService mockExampleService = mock(ExampleService.class);
        // 默认返回结果是返回类型int的默认值
        Assert.assertEquals(0, mockExampleService.add(1, 2));
    }

}

class ExampleService {

    int add(int a, int b) {
        return a + b;
    }

}
