package com.moon.concurrent.pattern;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.LockSupport;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 同步模式之保护性暂停 示例
 *
 * @author MooNkirA
 * @version 1.0
 * @date 2023-02-16 21:30
 * @description
 */
public class SequentialControlDemo {

    public static final Logger log = LoggerFactory.getLogger(SequentialControlDemo.class);

    private static final Object obj = new Object(); // 用来同步的对象
    private static volatile boolean hasRuned = false; // 运行标记，是否执行过

    /**
     * 使用 wait/notify 实现固定顺序输出
     */
    @Test
    public void WaitNotifyFixedSequenceTest() throws InterruptedException {
        Thread t1 = new Thread(() -> {
            synchronized (obj) {
                // 判断线程2是否执行
                while (!hasRuned) {
                    try {
                        obj.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
            log.debug("线程{}执行完成", Thread.currentThread().getName());
        }, "t1");

        Thread t2 = new Thread(() -> {
            log.debug("线程{}执行", Thread.currentThread().getName());
            synchronized (obj) {
                // 修改运行标记
                hasRuned = true;
                // 通知 obj 锁上等待的线程（可能有多个，因此需要用 notifyAll）
                obj.notifyAll();
            }
        }, "t2");

        t1.start();
        t2.start();
        Thread.sleep(2000);
    }

    /**
     * 使用 park/unpark 实现固定顺序输出
     */
    @Test
    public void ParkUnparkFixedSequenceTest() throws InterruptedException {
        Thread t1 = new Thread(() -> {
            String name = Thread.currentThread().getName();
            log.debug("线程{}申请“许可”", name);
            // 当没有“许可”时，当前线程暂停运行，直到有“许可”时，就会用掉该“许可”，当前线程恢复运行
            LockSupport.park();
            log.debug("线程{}执行完成", name);
        }, "t1");

        Thread t2 = new Thread(() -> {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            log.debug("线程{}执行", Thread.currentThread().getName());
            // 指定给线程t1 发放“许可”，可以连续多次调用 unpark，但只会发放一个“许可”
            LockSupport.unpark(t1);
        }, "t2");

        t1.start();
        t2.start();
        Thread.sleep(2000);
    }

    /**
     * 使用 wait/notify 实现交替输出
     */
    @Test
    public void WaitNotifyAlternatingTest() throws InterruptedException {
        SyncWaitNotify syncWaitNotify = new SyncWaitNotify(1, 5);

        new Thread(() -> syncWaitNotify.print(1, 2, "a")).start();
        new Thread(() -> syncWaitNotify.print(2, 3, "b")).start();
        new Thread(() -> syncWaitNotify.print(3, 1, "c")).start();
        Thread.sleep(2000);
    }

    class SyncWaitNotify {
        private int flag; // 标识
        private int loopNumber; // 循环的次数

        public SyncWaitNotify(int flag, int loopNumber) {
            this.flag = flag;
            this.loopNumber = loopNumber;
        }

        public void print(int waitFlag, int nextFlag, String message) {
            for (int i = 0; i < loopNumber; i++) {
                synchronized (this) {
                    while (this.flag != waitFlag) {
                        try {
                            this.wait(); // 等待
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                    System.out.print(message);
                    flag = nextFlag; // 设置下一步的标识
                    this.notifyAll(); // 唤醒其他线程
                }
            }
        }
    }

    /**
     * 使用 Lock 条件变量实现交替输出
     */
    @Test
    public void LockAlternatingTest() throws InterruptedException {
        AwaitSignal awaitSignal = new AwaitSignal(5);
        Condition condition1 = awaitSignal.newCondition();
        Condition condition2 = awaitSignal.newCondition();
        Condition condition3 = awaitSignal.newCondition();

        new Thread(() -> awaitSignal.print(condition1, condition2, "a")).start();
        new Thread(() -> awaitSignal.print(condition2, condition3, "b")).start();
        new Thread(() -> awaitSignal.print(condition3, condition1, "c")).start();

        awaitSignal.lock();
        try {
            log.debug("开始");
            condition1.signal();
        } finally {
            awaitSignal.unlock();
        }
        Thread.sleep(2000);
    }

    class AwaitSignal extends ReentrantLock {
        private final int loopNumber; // 循环的次数

        public AwaitSignal(int loopNumber) {
            this.loopNumber = loopNumber;
        }

        public void print(Condition current, Condition next, String message) {
            for (int i = 0; i < loopNumber; i++) {
                this.lock();
                try {
                    current.await(); // 条件变量的等待
                    System.out.print(message);
                    next.signal();// 唤醒下一个线程
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    this.unlock();
                }
            }
        }
    }

    /**
     * 使用 park/unpark 实现交替输出
     */
    @Test
    public void ParkUnparkAlternatingTest() throws InterruptedException {
        SyncPark syncPark = new SyncPark(3);
        Thread t1 = new Thread(() -> syncPark.print("a"));
        Thread t2 = new Thread(() -> syncPark.print("b"));
        Thread t3 = new Thread(() -> syncPark.print("c"));
        syncPark.setThreads(t1, t2, t3);
        syncPark.start();

        Thread.sleep(2000);
    }

    class SyncPark {
        private int loopNumber;
        private Thread[] threads;

        public SyncPark(int loopNumber) {
            this.loopNumber = loopNumber;
        }

        public void setThreads(Thread... threads) {
            this.threads = threads;
        }

        public void print(String str) {
            for (int i = 0; i < loopNumber; i++) {
                LockSupport.park();
                System.out.print(str);
                LockSupport.unpark(nextThread());
            }
        }

        private Thread nextThread() {
            Thread current = Thread.currentThread();
            int index = 0;
            for (int i = 0; i < threads.length; i++) {
                if (threads[i] == current) {
                    index = i;
                    break;
                }
            }
            if (index < threads.length - 1) {
                return threads[index + 1];
            } else {
                return threads[0];
            }
        }

        public void start() {
            for (Thread thread : threads) {
                thread.start();
            }
            LockSupport.unpark(threads[0]);
        }
    }

}
