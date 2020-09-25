package com.moon.java.jdk8interface;

import org.junit.Test;

/**
 * JDK8 接口增强：静态方法 使用示例
 *
 * @author MooNkirA
 * @version 1.0
 * @date 2020-9-25 14:29
 * @description
 */
public class Demo03StaticFunction {

    @Test
    public void defaultFunctionTest() {
        // 创建接口实现类
        StaticMethodInterfaceImpl impl = new StaticMethodInterfaceImpl();
        // 报错，说明实现类无法继承接口的静态方法，对象也不能调用
        // impl.staticMethod();
        // 接口静态方法的调用：接口名.静态方法名();
        StaticMethodInterface.staticMethod();
    }

}

/**
 * 定义有静态方法的接口
 */
interface StaticMethodInterface {
    /**
     * 定义静态方法（方法修饰符可省略，默认是public）
     */
    public static void staticMethod() {
        System.out.println("我是StaticMethodInterface接口的静态方法staticMethod()...");
    }
}

/**
 * 接口实现类，静态方法不能被继承与重写
 */
class StaticMethodInterfaceImpl implements StaticMethodInterface {
    // 静态方法不被重写，也不被继承
    /*@Override
    public void staticMethod() {
    }*/
}

