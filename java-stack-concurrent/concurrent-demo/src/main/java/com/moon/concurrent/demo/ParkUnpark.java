package com.moon.concurrent.demo;

import java.util.concurrent.locks.LockSupport;

/**
 * LockSupport 类 park/unpark 基础使用示例
 *
 * @author MooNkirA
 * @version 1.0
 * @date 2021-12-16 16:02
 * @description
 */
public class ParkUnpark {

    public static void main(String[] args) throws InterruptedException {
        Thread t1 = new Thread(() -> {
            System.out.println("start...");
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("park...");
            /*
             * park 方法，让当前线程暂停
             *   如在 unpark 方法之前调用，则会暂停当前线程
             *   如在一次 unpark 方法之后调用，则不会暂停，继续运行
             */
            LockSupport.park();
            System.out.println("resume...");
        }, "t1");
        t1.start();

        Thread.sleep(1000);
        System.out.println("unpark...");
        // unpark 恢复运行指定的线程。在 park 方法前后均可调用
        LockSupport.unpark(t1);
    }

}
