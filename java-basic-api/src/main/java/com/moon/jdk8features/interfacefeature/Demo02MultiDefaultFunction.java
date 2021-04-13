package com.moon.jdk8features.interfacefeature;

import org.junit.Test;

/**
 * JDK8 接口增强：默认方法 不同接口同名方法的使用示例
 *
 * @author MooNkirA
 * @version 1.0
 * @date 2020-9-25 14:09
 * @description
 */
public class Demo02MultiDefaultFunction {

    @Test
    public void multiDefaultFunctionTest() {
        // 方式一：实现类，重写两个接口的同名方法
        MultiDefaultMethodImpl1 impl1 = new MultiDefaultMethodImpl1();
        impl1.defaultMethod();
        System.out.println("--------------------------");
        // 方式二：实现类，重写两个接口的同名方法，方法内部使用super关键字调用指定的接口的默认方法
        MultiDefaultMethodImpl2 impl2 = new MultiDefaultMethodImpl2();
        impl2.defaultMethod();
    }
}

/**
 * 定义接口1与同名参数列表相同的方法
 */
interface Java8Interface1 {
    default void defaultMethod() {
        System.out.println("Java8Interface1.defaultMethod()方法执行了....");
    }
}

/**
 * 定义接口2与同名参数列表相同的方法
 */
interface Java8Interface2 {
    default void defaultMethod() {
        System.out.println("Java8Interface2.defaultMethod()方法执行了....");
    }
}

/**
 * 第一个解决方案是创建自己的默认方法，来覆盖重写接口的默认方法
 */
class MultiDefaultMethodImpl1 implements Java8Interface1, Java8Interface2 {
    @Override
    public void defaultMethod() {
        System.out.println("实现两个接口的MultiDefaultMethodImpl1.defaultMethod()方法执行了....");
    }
}

/**
 * 第二种解决方案可以使用 super 关键字来调用指定接口的默认方法
 */
class MultiDefaultMethodImpl2 implements Java8Interface1, Java8Interface2 {
    @Override
    public void defaultMethod() {
        // 调用接口1的方法
        Java8Interface1.super.defaultMethod();
        System.out.println("实现两个接口的MultiDefaultMethodImpl2.defaultMethod()方法执行了....");
        // 调用接口2的方法
        Java8Interface2.super.defaultMethod();
    }
}


