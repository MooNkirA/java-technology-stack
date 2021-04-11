package com.moon.jdk8features.interfacefeature;

import org.junit.Test;

/**
 * JDK8 接口增强：默认方法 使用示例
 *
 * @author MooNkirA
 * @version 1.0
 * @date 2020-9-25 13:52
 * @description
 */
public class Demo01DefaultFunction {

    @Test
    public void defaultFunctionTest() {
        // 方式一：创建实现类，直接调用默认方法
        Cat cat = new Cat();
        cat.eat();
        System.out.println("--------------------------");
        // 方式二：创建实现类，实现类重写默认方法，再调用
        Person person = new Person();
        person.eat();
    }

}

/**
 * 定义动物接口
 */
interface Animal {
    /**
     * 定义默认方法（方法修饰符可省略，默认是public）
     */
    public default void eat() {
        System.out.println("我是Animal接口的默认方法eat()...");
    }
}

/**
 * 默认方法使用方式一: 实现类可以直接使用
 */
class Cat implements Animal {
}

/**
 * 默认方法使用方式二: 实现类重写接口默认方法，对象进行调用
 */
class Person implements Animal {
    @Override
    public void eat() {
        System.out.println("我是Person实现类重写后的默认方法eat()...");
    }
}
