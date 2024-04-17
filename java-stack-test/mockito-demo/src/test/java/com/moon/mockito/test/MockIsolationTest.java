package com.moon.mockito.test;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import static org.mockito.Mockito.when;

/**
 * Mcok 测试隔离
 *
 * @author MooNkirA
 * @version 1.0
 * @date 2024-02-08 14:24
 * @description
 */
@RunWith(MockitoJUnitRunner.class)
public class MockIsolationTest {

    static class ExampleService {

        public int add(int a, int b) {
            return a + b;
        }

    }

    @Mock
    private ExampleService exampleService;

    @Test
    public void test01() {
        System.out.println("---call test01---");

        System.out.println("打桩前: " + exampleService.add(1, 2));

        when(exampleService.add(1, 2)).thenReturn(100);

        System.out.println("打桩后: " + exampleService.add(1, 2));
    }

    @Test
    public void test02() {
        System.out.println("---call test02---");

        System.out.println("打桩前: " + exampleService.add(1, 2));

        when(exampleService.add(1, 2)).thenReturn(100);

        System.out.println("打桩后: " + exampleService.add(1, 2));
    }

}
