package com.moon.java.lang.reflect;

import com.moon.common.interfaces.DemoInterface;
import com.moon.common.interfaces.impl.DemoTarget;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.lang.reflect.UndeclaredThrowableException;

/**
 * 此类是模拟 JDK 动态代理最终生成的代理类
 *
 * @author MooNkirA
 * @version 1.0
 * @date 2022-05-23 10:05
 * @description
 */
public class $Proxy0 extends Proxy implements DemoInterface {

    static Method foo;
    static Method bar;

    static {
        try {
            // 反射获取被代理类的中方法对象
            foo = DemoTarget.class.getMethod("foo");
            bar = DemoTarget.class.getMethod("bar");
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
            throw new NoSuchMethodError(e.getMessage());
        }
    }

    // 继承的 Proxy 类中，有一个 InvocationHandler 类型的属性，调用父类构造，给属性设值
    public $Proxy0(InvocationHandler h) {
        super(h);
    }

    @Override
    public void foo() {
        try {
            // 方法无参数
            h.invoke(this, foo, new Object[0]);
        } catch (RuntimeException | Error e) {
            throw e;
        } catch (Throwable e) {
            throw new UndeclaredThrowableException(e);
        }
    }

    @Override
    public int bar() {
        try {
            // 获取方法调用的返回值，并返回
            Object result = h.invoke(this, bar, new Object[0]);
            return (int) result;
        } catch (RuntimeException | Error e) {
            throw e;
        } catch (Throwable e) {
            throw new UndeclaredThrowableException(e);
        }
    }
}
