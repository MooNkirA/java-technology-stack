package com.moon.java.lang.reflect;

import com.moon.common.interfaces.DemoInterface;
import com.moon.common.interfaces.impl.DemoTarget;
import com.moon.common.interfaces.impl.Target;
import net.sf.cglib.core.Signature;
import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;
import net.sf.cglib.reflect.FastClass;
import org.junit.Test;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * CGlib 实现动态代理基础示例
 *
 * @author MooNkirA
 * @version 1.0
 * @date 2022-05-23 10:13
 * @description
 */
public class CglibProxyDemo {

    /*
     * 通过 MethodProxy 调用目标方法，在 jdk >= 9 的情况下，在调用 Object 的方法会有问题，
     * 启动程序时需要设置： --add-opens java.base/java.lang=ALL-UNNAMED
     */
    @Test
    public void testCglibBasic() {
        // 创建被代理对象
        DemoTarget demoTarget = new DemoTarget();

        // 创建代理
        DemoInterface demoInterface = (DemoInterface) Enhancer.create(DemoTarget.class, new MethodInterceptor() {

            @Override
            public Object intercept(Object proxy, Method method, Object[] args, MethodProxy methodProxy) throws Throwable {
                // 模拟增强逻辑
                System.out.println("CGlib 动态代理的 MethodProxy signature: " + methodProxy.getSignature());
                // 使用反射，调用原始目标方法
                // return method.invoke(demoTarget, args);
                // 内部没有用反射, 需要目标对象，spring 框架采用这种方式
                return methodProxy.invoke(demoTarget, args);
                // 内部没有用反射, 需要代理对象
                // return methodProxy.invokeSuper(proxy, args);
            }
        });

        demoInterface.foo();
        demoInterface.bar();
    }

    // 测试模拟实现 cglib 动态代理功能
    @Test
    public void testCglibProxyMock() {
        // 创建被代理对象
        Target target = new Target();

        // 创建代理
        CglibProxyMock proxyMock = new CglibProxyMock(new MethodInterceptor() {
            @Override
            public Object intercept(Object proxy, Method method, Object[] args, MethodProxy methodProxy) throws Throwable {
                // 模拟增强逻辑
                System.out.println("CGlib 动态代理的 MethodProxy signature: " + methodProxy.getSignature());
                // 使用反射，调用原始目标方法
                // return method.invoke(target, args);
                // 内部没有用反射, 需要目标对象，spring 框架采用这种方式
                // return methodProxy.invoke(target, args);
                // 内部没有用反射, 需要代理对象
                return methodProxy.invokeSuper(proxy, args);
            }
        });

        proxyMock.save();
        proxyMock.save(1);
        proxyMock.save(2L);
    }

    // 模拟测试 methodProxy.invoke(target, args) 方法，配合目标对象进行方法的调用实现流程
    @Test
    public void testTargetFastClass() throws InvocationTargetException {
        // MethodProxy.create 方法调用时，就相当于创建了 FastClass 实现类，创建过程就会记录了方法签名信息
        TargetFastClass fastClass = new TargetFastClass(Target.class);
        // 调用时，根据方法签名获取方法的编号
        int index = fastClass.getIndex(new Signature("save", "(I)V"));
        System.out.println(index);
        // 根据方法编号，调用相应目标的方法
        fastClass.invoke(index, new Target(), new Object[]{100});
    }

    /**
     * 模拟 MethodProxy 为避免反射调用目标方法时生成的 FastClass 实现类。
     * 即 methodProxy.invoke(target, args) 方法的实现原理，配置目标对象使用。
     * 注：只抽取两个比较重点的方法实现做测试
     */
    class TargetFastClass extends FastClass {

        // FastClass 要求必须要调用其有参构造方法
        public TargetFastClass(Class clazz) {
            super(clazz);
        }

        // 初始化方法相应的签名对象
        Signature s0 = new Signature("save", "()V");
        Signature s1 = new Signature("save", "(I)V");
        Signature s2 = new Signature("save", "(J)V");

        /**
         * 获取目标方法的编号
         *
         * @param signature 被调用的方法签名，包括方法名字、参数返回值
         * @return
         */
        @Override
        public int getIndex(Signature signature) {
            /*
                判断方法签名，返回相应的方法编号。示例假设方法编号如下：
                    save()      0
                    save(int)   1
                    save(long)  2
            */
            if (s0.equals(signature)) {
                return 0;
            } else if (s1.equals(signature)) {
                return 1;
            } else if (s2.equals(signature)) {
                return 2;
            }
            return -1;
        }

        /**
         * 根据方法的编号，正常调用目标对象中的方法
         *
         * @param index  方法的编号
         * @param target 目标对象
         * @param args   被调用的方法的形参
         * @return
         * @throws InvocationTargetException
         */
        @Override
        public Object invoke(int index, Object target, Object[] args) throws InvocationTargetException {
            // 根据方法编号，调用相应的对象中的方法
            if (index == 0) {
                ((Target) target).save();
                return null;
            } else if (index == 1) {
                ((Target) target).save((int) args[0]);
                return null;
            } else if (index == 2) {
                ((Target) target).save((long) args[0]);
                return null;
            } else {
                throw new RuntimeException("无此方法");
            }
        }

        @Override
        public int getIndex(String name, Class[] parameterTypes) {
            return 0;
        }

        @Override
        public int getIndex(Class[] parameterTypes) {
            return 0;
        }

        @Override
        public Object newInstance(int index, Object[] args) throws InvocationTargetException {
            return null;
        }

        @Override
        public int getMaxIndex() {
            return 0;
        }
    }

    // 模拟测试 methodProxy.invokeSuper(proxy, args) 方法，配合 cglib 代理对象进行方法的调用实现流程
    // 注：此测试使用 CglibProxyMock 自定义的模拟 cglib 代理实现，非 cglib 原生
    @Test
    public void testProxyFastClass() throws InvocationTargetException {
        // MethodProxy.create 方法调用时，就相当于创建了 FastClass 实现类，创建过程就会记录了方法签名信息
        ProxyFastClass fastClass = new ProxyFastClass(Target.class);
        // 调用时，根据方法签名获取方法的编号
        int index = fastClass.getIndex(new Signature("saveSuper", "(I)V"));
        System.out.println(index);
        // 根据方法编号，调用相应目标的方法
        fastClass.invoke(index, new CglibProxyMock((obj, method, args, proxy) -> proxy.invokeSuper(obj, args)), new Object[]{100});
    }

    /**
     * 模拟 MethodProxy 为避免反射调用目标方法时生成的 FastClass 实现类。
     * 即 methodProxy.invokeSuper(proxy, args) 方法的实现原理，配置 cglib 代理对象使用。
     * 注：只抽取两个比较重点的方法实现做测试
     */
    class ProxyFastClass extends FastClass {

        // FastClass 要求必须要调用其有参构造方法
        public ProxyFastClass(Class clazz) {
            super(clazz);
        }

        // 初始化代理中方法相应的签名对象
        Signature s0 = new Signature("saveSuper", "()V");
        Signature s1 = new Signature("saveSuper", "(I)V");
        Signature s2 = new Signature("saveSuper", "(J)V");

        /**
         * 获取代理中目标方法的编号
         *
         * @param signature 被调用的方法签名，包括方法名字、参数返回值
         * @return
         */
        @Override
        public int getIndex(Signature signature) {
            /*
                判断方法签名，返回相应的方法编号。示例假设代理中调用原目标方法编号如下：
                    saveSuper()      0
                    saveSuper(int)   1
                    saveSuper(long)  2
            */
            if (s0.equals(signature)) {
                return 0;
            } else if (s1.equals(signature)) {
                return 1;
            } else if (s2.equals(signature)) {
                return 2;
            }
            return -1;
        }

        /**
         * 根据方法的编号，调用代理类中定义的目标对象方法
         *
         * @param index 方法的编号
         * @param proxy 代理对象
         * @param args  被调用的方法的形参
         * @return
         * @throws InvocationTargetException
         */
        @Override
        public Object invoke(int index, Object proxy, Object[] args) throws InvocationTargetException {
            // 根据方法编号，调用代理对象中的相应方法
            if (index == 0) {
                ((CglibProxyMock) proxy).saveSuper();
                return null;
            } else if (index == 1) {
                ((CglibProxyMock) proxy).saveSuper((int) args[0]);
                return null;
            } else if (index == 2) {
                ((CglibProxyMock) proxy).saveSuper((long) args[0]);
                return null;
            } else {
                throw new RuntimeException("无此方法");
            }
        }

        @Override
        public int getIndex(String name, Class[] parameterTypes) {
            return 0;
        }

        @Override
        public int getIndex(Class[] parameterTypes) {
            return 0;
        }

        @Override
        public Object newInstance(int index, Object[] args) throws InvocationTargetException {
            return null;
        }

        @Override
        public int getMaxIndex() {
            return 0;
        }
    }
}
