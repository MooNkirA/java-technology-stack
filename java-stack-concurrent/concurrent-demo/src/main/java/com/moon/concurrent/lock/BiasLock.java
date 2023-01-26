package com.moon.concurrent.lock;

import org.openjdk.jol.info.ClassLayout;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Vector;
import java.util.concurrent.locks.LockSupport;

/**
 * 偏向锁测试
 * <p>测试说明详见《并发编程-原理篇》笔记</p>
 *
 * @author MooNkirA
 * @version 1.0
 * @date 2023-01-22 09:17
 * @description
 */

public class BiasLock {

    /* 日志对象 */
    private static final Logger log = LoggerFactory.getLogger(BiasLock.class);

    public static void main(String[] args) throws Exception {
        // delayTest();
        // biasLockTest();
        // hashCodeUndoBiasLockTest();
        // otherThreadUndoBiasLockTest();
        // waitNotifyUndoBiasLockTest();
        // batchBiasLockTest();
        batchUndoBiasLockTest();
    }

    /**
     * 偏向锁延迟特性测试
     */
    public static void delayTest() {
        log.debug(ClassLayout.parseInstance(new Dog()).toPrintable());

        try {
            Thread.sleep(4000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        log.debug(ClassLayout.parseInstance(new Dog()).toPrintable());
    }

    /**
     * 测试偏向锁，使用与禁用不同的效果
     * 1. 添加虚拟机参数 -XX:BiasedLockingStartupDelay=0 禁用偏向锁延迟生效
     * 2. 添加虚拟机参数 `-XX:-UseBiasedLocking` 禁用偏向锁
     */
    public static void biasLockTest() {
        Dog d = new Dog();
        ClassLayout classLayout = ClassLayout.parseInstance(d);

        new Thread(() -> {
            log.debug("synchronized 前");
            System.out.println(classLayout.toPrintable());
            synchronized (d) {
                log.debug("synchronized 中");
                System.out.println(classLayout.toPrintable());
            }
            log.debug("synchronized 后");
            System.out.println(classLayout.toPrintable());
        }, "T1").start();
    }

    /**
     * 偏向锁撤销 - 调用对象 hashCode
     */
    public static void hashCodeUndoBiasLockTest() {
        Dog d = new Dog();
        ClassLayout classLayout = ClassLayout.parseInstance(d);

        log.debug("调用对象的hashCode: {}", d.hashCode());
        new Thread(() -> {
            log.debug("synchronized 前");
            System.out.println(classLayout.toPrintable());
            synchronized (d) {
                log.debug("synchronized 中");
                System.out.println(classLayout.toPrintable());
            }
            log.debug("synchronized 后");
            System.out.println(classLayout.toPrintable());
        }, "T1").start();
    }

    /**
     * 偏向锁撤销 - 其它线程使用对象
     */
    public static void otherThreadUndoBiasLockTest() {
        Dog d = new Dog();
        Thread t1 = new Thread(() -> {
            synchronized (d) {
                log.debug(ClassLayout.parseInstance(d).toPrintable());
            }
            synchronized (BiasLock.class) {
                BiasLock.class.notify();
            }

            // 如果不用 wait/notify 使用 join 必须打开下面的注释
            // 因为：t1 线程不能结束，否则底层线程可能被 jvm 重用作为 t2 线程，底层线程 id 是一样的
            /*try {
                System.in.read();
            } catch (IOException e) {
                e.printStackTrace();
            }*/
        }, "t1");
        t1.start();

        Thread t2 = new Thread(() -> {
            synchronized (BiasLock.class) {
                try {
                    BiasLock.class.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            log.debug(ClassLayout.parseInstance(d).toPrintable());
            synchronized (d) {
                log.debug(ClassLayout.parseInstance(d).toPrintable());
            }
            log.debug(ClassLayout.parseInstance(d).toPrintable());
        }, "t2");
        t2.start();
    }

    /**
     * 偏向锁撤销 - 调用 wait/notify
     */
    public static void waitNotifyUndoBiasLockTest() {
        Dog d = new Dog();

        new Thread(() -> {
            log.debug(ClassLayout.parseInstance(d).toPrintable());
            synchronized (d) {
                log.debug(ClassLayout.parseInstance(d).toPrintable());
                try {
                    d.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                log.debug(ClassLayout.parseInstance(d).toPrintable());
            }
        }, "t1").start();

        new Thread(() -> {
            try {
                Thread.sleep(6000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            synchronized (d) {
                log.debug("t2 notify");
                d.notify();
            }
        }, "t2").start();
    }

    /**
     * 批量重偏向
     */
    public static void batchBiasLockTest() {
        Vector<Dog> list = new Vector<>();

        new Thread(() -> {
            for (int i = 0; i < 30; i++) {
                Dog d = new Dog();
                list.add(d);
                synchronized (d) {
                    log.debug(i + "\t" + ClassLayout.parseInstance(d).toPrintable());
                }
            }
            synchronized (list) {
                list.notify();
            }
        }, "t1").start();

        new Thread(() -> {
            synchronized (list) {
                try {
                    list.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            log.debug("===============> ");
            for (int i = 0; i < 30; i++) {
                Dog d = list.get(i);
                log.debug(i + "\t" + ClassLayout.parseInstance(d).toPrintable());
                synchronized (d) {
                    log.debug(i + "\t" + ClassLayout.parseInstance(d).toPrintable());
                }
                log.debug(i + "\t" + ClassLayout.parseInstance(d).toPrintable());
            }
        }, "t2").start();
    }

    static Thread t1, t2, t3;

    /**
     * 批量撤销
     */
    private static void batchUndoBiasLockTest() throws InterruptedException {
        Vector<Dog> list = new Vector<>();

        int loopNumber = 39;
        t1 = new Thread(() -> {
            for (int i = 0; i < loopNumber; i++) {
                Dog d = new Dog();
                list.add(d);
                synchronized (d) {
                    log.debug(i + "\t" + ClassLayout.parseInstance(d).toPrintable());
                }
            }
            LockSupport.unpark(t2);
        }, "t1");
        t1.start();

        t2 = new Thread(() -> {
            LockSupport.park();
            log.debug("===============> ");
            for (int i = 0; i < loopNumber; i++) {
                Dog d = list.get(i);
                log.debug(i + "\t" + ClassLayout.parseInstance(d).toPrintable());
                synchronized (d) {
                    log.debug(i + "\t" + ClassLayout.parseInstance(d).toPrintable());
                }
                log.debug(i + "\t" + ClassLayout.parseInstance(d).toPrintable());
            }
            LockSupport.unpark(t3);
        }, "t2");
        t2.start();

        t3 = new Thread(() -> {
            LockSupport.park();
            log.debug("===============> ");
            for (int i = 0; i < loopNumber; i++) {
                Dog d = list.get(i);
                log.debug(i + "\t" + ClassLayout.parseInstance(d).toPrintable());
                synchronized (d) {
                    log.debug(i + "\t" + ClassLayout.parseInstance(d).toPrintable());
                }
                log.debug(i + "\t" + ClassLayout.parseInstance(d).toPrintable());
            }
        }, "t3");
        t3.start();

        t3.join();
        log.debug(ClassLayout.parseInstance(new Dog()).toPrintable());
    }
}

class Dog {
}
