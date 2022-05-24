package com.moon.java.lang.reflect;

import com.moon.common.interfaces.DemoInterface;
import com.moon.common.interfaces.impl.DemoTarget;
import org.junit.Test;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * 模拟 JDK 动态生成的代理的 $Proxy0.class 源码
 *
 * @author MooNkirA
 * @version 1.0
 * @date 2022-05-22 21:52
 * @description
 */
public class JdkProxyDemo {

    @Test
    public void testCustomProxy() {
        // 创建自己实现的代理对象
        DemoInterface demo = new $Proxy0(new InvocationHandler() {
            @Override
            public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                // 模拟功能增强
                System.out.println("jdK 动态代理功能增强...");
                // 调用目标的方法，并返回
                return method.invoke(new DemoTarget(), args);
            }
        });

        // 调用接口的方法，由代理来实现
        demo.foo();
        demo.bar();
    }

}
