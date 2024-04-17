package com.moon.mockito.test;

import org.junit.Assert;
import org.junit.Test;
import org.mockito.Mock;

import static org.mockito.Mockito.doThrow;

/**
 * DoThrow 处理 void 返回值方法异常的示例
 *
 * @author MooNkirA
 * @version 1.0
 * @date 2024-02-08 14:00
 * @description
 */
public class DoThrowTest {

    static class ExampleService {
        public void hello() {
            System.out.println("Hello");
        }
    }

    @Mock
    private ExampleService exampleService;

    @Test
    public void test() {
        // 这种写法可以达到效果
        doThrow(new RuntimeException("异常")).when(exampleService).hello();

        try {
            exampleService.hello();
            Assert.fail();
        } catch (RuntimeException ex) {
            Assert.assertEquals("异常", ex.getMessage());
        }
    }

}
