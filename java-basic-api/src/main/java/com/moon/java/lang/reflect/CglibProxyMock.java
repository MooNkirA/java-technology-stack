package com.moon.java.lang.reflect;

import com.moon.common.interfaces.impl.Target;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;
import java.lang.reflect.UndeclaredThrowableException;

/**
 * 模拟 CGlib 实现动态代理的代理类
 *
 * @author MooNkirA
 * @version 1.0
 * @date 2022-05-23 10:56
 * @description
 */
// CGlib 代理是继承被代理类
public class CglibProxyMock extends Target {

    // 定义用于增强的回调接口的属性
    private MethodInterceptor methodInterceptor;

    // 通过构造函数或者 setter 方法来接收 MethodInterceptor 回调接口
    public CglibProxyMock(MethodInterceptor methodInterceptor) {
        this.methodInterceptor = methodInterceptor;
    }

    // 定义方法反射调用的 Method 属性
    static Method save0;
    static Method save1;
    static Method save2;
    // 定义不使用反射调用的 MethodProxy 属性
    static MethodProxy save0Proxy;
    static MethodProxy save1Proxy;
    static MethodProxy save2Proxy;

    static {
        try {
            save0 = Target.class.getMethod("save");
            save1 = Target.class.getMethod("save", int.class);
            save2 = Target.class.getMethod("save", long.class);
            /*
             * 创建 MethodProxy 对象
             *  参数1：被代理类字节码对象
             *  参数2：代理类字节码对象
             *  参数3：代理方法的返回值与形参的表达式
             *  参数4：增强后的方法名称
             *  参数5：原始方法的名称
             */
            save0Proxy = MethodProxy.create(Target.class, CglibProxyMock.class, "()V", "save", "saveSuper");
            save1Proxy = MethodProxy.create(Target.class, CglibProxyMock.class, "(I)V", "save", "saveSuper");
            save2Proxy = MethodProxy.create(Target.class, CglibProxyMock.class, "(J)V", "save", "saveSuper");
        } catch (NoSuchMethodException e) {
            throw new NoSuchMethodError(e.getMessage());
        }
    }

    // ***** 带原始功能的方法，直接调用父类方法 *****
    public void saveSuper() {
        super.save();
    }

    public void saveSuper(int i) {
        super.save(i);
    }

    public void saveSuper(long j) {
        super.save(j);
    }

    // ***** 带增强功能的方法，通过 MethodInterceptor.intercept 回调方法执行功能增强 *****
    @Override
    public void save() {
        try {
            methodInterceptor.intercept(this, save0, new Object[0], save0Proxy);
        } catch (Throwable e) {
            throw new UndeclaredThrowableException(e);
        }
    }

    @Override
    public void save(int i) {
        try {
            methodInterceptor.intercept(this, save1, new Object[]{i}, save1Proxy);
        } catch (Throwable e) {
            throw new UndeclaredThrowableException(e);
        }
    }

    @Override
    public void save(long j) {
        try {
            methodInterceptor.intercept(this, save2, new Object[]{j}, save2Proxy);
        } catch (Throwable e) {
            throw new UndeclaredThrowableException(e);
        }
    }
}
