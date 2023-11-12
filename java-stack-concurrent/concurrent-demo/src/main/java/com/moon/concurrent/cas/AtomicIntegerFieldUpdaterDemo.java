package com.moon.concurrent.cas;

import org.junit.Test;

import java.util.concurrent.atomic.AtomicIntegerFieldUpdater;

/**
 * AtomicIntegerFieldUpdater 示例
 *
 * @author MooNkirA
 * @version 1.0
 * @date 2023-02-22 23:10
 * @description
 */
public class AtomicIntegerFieldUpdaterDemo {

    private volatile int field;

    @Test
    public void atomicIntegerFieldUpdaterTest() {
        AtomicIntegerFieldUpdater<AtomicIntegerFieldUpdaterDemo> fieldUpdater =
                AtomicIntegerFieldUpdater.newUpdater(AtomicIntegerFieldUpdaterDemo.class, "field");

        AtomicIntegerFieldUpdaterDemo testDemo = new AtomicIntegerFieldUpdaterDemo();
        fieldUpdater.compareAndSet(testDemo, 0, 10);
        System.out.println(testDemo.field); // 修改成功 field = 10
        fieldUpdater.compareAndSet(testDemo, 10, 20); // 修改成功 field = 20
        System.out.println(testDemo.field);
        fieldUpdater.compareAndSet(testDemo, 10, 30); // 修改失败 field = 20
        System.out.println(testDemo.field);
    }

}
