package com.moon.mockito.test;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import static org.mockito.Mockito.when;

/**
 * Powermock 支持 mock 静态方法示例
 *
 * @author MooNkirA
 * @version 1.0
 * @date 2024-02-08 14:49
 * @description
 */
@RunWith(PowerMockRunner.class) // 这必须设置使用 PowerMockRunner 运行器，替代 MockitoJUnitRunner，会兼容 @Mock 等注解。
@PrepareForTest(PowermockStaticDemoService.class)  // 声明要处理的类
public class PowermockStaticTest {

    @Test
    public void test() {
        PowerMockito.mockStatic(PowermockStaticDemoService.class);  // 声明模拟的类，这也是必须的
        when(PowermockStaticDemoService.add(1, 2)).thenReturn(100);
        Assert.assertEquals(100, PowermockStaticDemoService.add(1, 2));
    }

}

class PowermockStaticDemoService {

    public static int add(int a, int b) {
        return a + b;
    }

}
