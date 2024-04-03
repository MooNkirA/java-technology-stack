package com.moon.mockito.test;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.List;

import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.when;

/**
 * 参数匹配测试示例
 *
 * @author MooNkirA
 * @version 1.0
 * @date 2024-02-08 13:44
 * @description
 */
@RunWith(MockitoJUnitRunner.class)
public class ParameterTest {
    @Mock
    private List<String> testList;

    @Test
    public void testParameter() {
        // 精确匹配 0
        when(testList.get(0)).thenReturn("a");
        Assert.assertEquals("a", testList.get(0));

        // 精确匹配 0
        when(testList.get(0)).thenReturn("b");
        Assert.assertEquals("b", testList.get(0));

        // 模糊匹配
        when(testList.get(anyInt())).thenReturn("c");
        Assert.assertEquals("c", testList.get(0));
        Assert.assertEquals("c", testList.get(1));
    }

}
