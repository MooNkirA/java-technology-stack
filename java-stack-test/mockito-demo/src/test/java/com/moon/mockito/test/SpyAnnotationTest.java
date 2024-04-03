package com.moon.mockito.test;

import org.junit.Assert;
import org.junit.Test;
import org.mockito.MockitoAnnotations;
import org.mockito.Spy;

import static org.mockito.Mockito.when;

/**
 * 注解 @Spy 示例
 *
 * @author MooNkirA
 * @version 1.0
 * @date 2024-02-08 14:17
 * @description
 */
public class SpyAnnotationTest {

    @Spy
    private SpyAnnotationDemoService spyAnnotationDemoService;

    @Test
    public void testSpy() {
        MockitoAnnotations.initMocks(this);

        Assert.assertEquals(3, spyAnnotationDemoService.add(1, 2));

        when(spyAnnotationDemoService.add(1, 2)).thenReturn(10);
        Assert.assertEquals(10, spyAnnotationDemoService.add(1, 2));
    }

}

class SpyAnnotationDemoService {
    int add(int a, int b) {
        return a + b;
    }
}
