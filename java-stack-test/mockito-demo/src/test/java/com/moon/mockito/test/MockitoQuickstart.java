package com.moon.mockito.test;

import com.moon.mockito.dao.DemoDao;
import com.moon.mockito.service.DemoService;
import org.junit.Assert;
import org.junit.Test;
import org.mockito.Mockito;

/**
 * mockito 基础使用示例
 *
 * @author MooNkirA
 * @version 1.0
 * @date 2024-02-08 12:33
 * @description
 */
public class MockitoQuickstart {

    @Test
    public void testBasic() {
        // mock DemoDao instance
        DemoDao mockDemoDao = Mockito.mock(DemoDao.class);

        // 使用 mockito 对 getDemoStatus 方法打桩
        Mockito.when(mockDemoDao.getDemoStatus()).thenReturn(1);

        // 调用 mock 对象的 getDemoStatus 方法，结果永远是 1
        Assert.assertEquals(1, mockDemoDao.getDemoStatus());

        // mock DemoService
        DemoService mockDemoService = new DemoService(mockDemoDao);
        Assert.assertEquals(1, mockDemoService.getDemoStatus());
    }

}
