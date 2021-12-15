package com.moon.concurrent.demo;

import java.io.IOException;

/**
 * Thread.State 枚举中的 6 种线程状态出现的测试
 *
 * @author MooNkirA
 * @version 1.0
 * @date 2021-12-14 14:42
 * @description
 */
public class ThreadStateDemo {

    public static void main(String[] args) throws IOException {
        // 线程1：只创建没有调用 start() 方法，所在处于新建状态【NEW】
        Thread t1 = new Thread("t1") {
            @Override
            public void run() {
                System.out.println("running...");
            }
        };

        // 线程2：设置无限循环，所以线程会处于可运行状态【RUNNABLE】
        Thread t2 = new Thread("t2") {
            @Override
            public void run() {
                while (true) { // runnable

                }
            }
        };
        t2.start();

        // 线程3：只有一行代码，开启线程后可以马上执行结束，所以线程为结束状态【TERMINATED】
        Thread t3 = new Thread("t3") {
            @Override
            public void run() {
                System.out.println("running...");
            }
        };
        t3.start();

        // 线程4：设置同步锁，拿到锁后，进行长时间的休眠，所以线程状态为阻塞状态【BLOCKED/TIMED_WAITING】
        Thread t4 = new Thread("t4") {
            @Override
            public void run() {
                synchronized (ThreadStateDemo.class) {
                    try {
                        Thread.sleep(1000000); // timed_waiting
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        };
        t4.start();

        // 线程5：调用线程2的 join 方法，进行阻塞状态，因为线程2是无限循环，所以此线程会一直等待线程2的结束【WAITING】
        Thread t5 = new Thread("t5") {
            @Override
            public void run() {
                try {
                    t2.join(); // waiting
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        };
        t5.start();

        // 线程6：因为线程4获取锁对象并且一直不释放锁，所以此线程也会进入阻塞状态【BLOCKED/TIMED_WAITING】。（或者此线程先拿到锁，此时线程4就进入 BLOCKED 状态）
        Thread t6 = new Thread("t6") {
            @Override
            public void run() {
                synchronized (ThreadStateDemo.class) { // blocked
                    try {
                        Thread.sleep(1000000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        };
        t6.start();

        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("t1 state : " + t1.getState());
        System.out.println("t2 state : " + t2.getState());
        System.out.println("t3 state : " + t3.getState());
        System.out.println("t4 state : " + t4.getState());
        System.out.println("t5 state : " + t5.getState());
        System.out.println("t6 state : " + t6.getState());
        System.in.read();
    }

}
