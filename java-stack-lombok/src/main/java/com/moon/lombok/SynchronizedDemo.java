package com.moon.lombok;

import lombok.Synchronized;

/**
 * Synchronized 注解 Demo
 *
 * @author MoonZero
 * @version 1.0
 * @date 2019-11-7 13:57
 * @description `@Synchronized` 是同步方法修饰符的更安全的变体。与 synchronized 一样，该注解只能应用在静态和实例方法上
 */
public class SynchronizedDemo {

    private final Object readLock = new Object();

    @Synchronized
    public static void hello() {
        System.out.println("world");
    }

    @Synchronized
    public int answerToLife() {
        return 42;
    }

    @Synchronized("readLock")
    public void foo() {
        System.out.println("bar");
    }

}
