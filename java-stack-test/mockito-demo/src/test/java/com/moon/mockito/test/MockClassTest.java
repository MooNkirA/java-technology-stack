package com.moon.mockito.test;

import org.junit.Assert;
import org.junit.Test;

import java.util.List;
import java.util.Random;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 * 使用 mock 方法示例
 * @author MooNkirA
 * @version 1.0
 * @date 2024-02-08 12:54
 * @description
 */
public class MockClassTest {

    @Test
    public void mockClassTest() {
        Random mockRandom = mock(Random.class);

        // 默认值: mock 对象的方法的返回值默认都是返回类型的默认值
        System.out.println(mockRandom.nextBoolean()); // false
        System.out.println(mockRandom.nextInt()); // 0
        System.out.println(mockRandom.nextDouble()); // 0.0

        // mock: 指定调用 nextInt 方法时，永远返回 100
        when(mockRandom.nextInt()).thenReturn(100);
        Assert.assertEquals(100, mockRandom.nextInt());
        Assert.assertEquals(100, mockRandom.nextInt());
    }

    @Test
    public void mockInterfaceTest() {
        List mockList = mock(List.class);

        // 接口的默认值：和类方法一致，都是默认返回值
        Assert.assertEquals(0, mockList.size());
        Assert.assertEquals(null, mockList.get(0));

        // 注意：调用 mock 对象的写方法，是没有效果的
        mockList.add("a");
        Assert.assertEquals(0, mockList.size());      // 没有指定 size() 方法返回值，这里结果是默认值
        Assert.assertEquals(null, mockList.get(0));   // 没有指定 get(0) 返回值，这里结果是默认值

        // mock值测试
        when(mockList.get(0)).thenReturn("a");          // 指定 get(0)时返回 a
        Assert.assertEquals(0, mockList.size());        // 没有指定 size() 方法返回值，这里结果是默认值
        Assert.assertEquals("a", mockList.get(0));      // 因为上面指定了 get(0) 返回 a，所以这里会返回 a
        Assert.assertEquals(null, mockList.get(1));     // 没有指定 get(1) 返回值，这里结果是默认值
    }

}
