package com.moon.concurrent.juc;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.AbstractQueuedSynchronizer;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;

/**
 * 自定义 AbstractQueuedSynchronizer 实现不可重入锁 示例
 *
 * @author MooNkirA
 * @version 1.0
 * @date 2023-03-04 08:07
 * @description
 */
@Slf4j
public class AbstractQueuedSynchronizerDemo {

    public static void main(String[] args) {
        CustomLock lock = new CustomLock();
        new Thread(() -> {
            lock.lock();
            log.debug("locking...");
            lock.lock();
            try {
                log.debug("locking...");
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                log.debug("unlocking...");
                lock.unlock();
            }
        }, "t1").start();

        new Thread(() -> {
            lock.lock();
            try {
                log.debug("locking...");
            } finally {
                log.debug("unlocking...");
                lock.unlock();
            }
        }, "t2").start();
    }

}

// 自定义锁（不可重入锁）
class CustomLock implements Lock {

    private CustomQueuedSynchronizer sync = new CustomQueuedSynchronizer();

    // 加锁（不成功会进入等待队列）
    @Override
    public void lock() {
        sync.acquire(1);
    }

    // 加锁，不成功，进入等待队列，可打断
    @Override
    public void lockInterruptibly() throws InterruptedException {
        sync.acquireInterruptibly(1);
    }

    // 尝试加锁（一次），不成功返回，不进入队列
    @Override
    public boolean tryLock() {
        return sync.tryAcquire(1);
    }

    // 尝试加锁，不成功，进入等待队列，带超时
    @Override
    public boolean tryLock(long time, TimeUnit unit) throws InterruptedException {
        return sync.tryAcquireNanos(1, unit.toNanos(time));
    }

    // 解锁
    @Override
    public void unlock() {
        sync.release(1);
    }

    // 创建条件变量
    @Override
    public Condition newCondition() {
        return sync.newCondition();
    }
}

// 自定义同步器，实现 AbstractQueuedSynchronizer
class CustomQueuedSynchronizer extends AbstractQueuedSynchronizer {

    @Override
    protected boolean tryAcquire(int arg) {
        if (compareAndSetState(0, 1)) {
            // 加上了锁，并设置 owner 为当前线程
            setExclusiveOwnerThread(Thread.currentThread());
            return true;
        }
        return false;
    }

    @Override
    protected boolean tryRelease(int arg) {
        setExclusiveOwnerThread(null);
        setState(0);
        return true;
    }

    // 是否持有独占锁
    @Override
    protected boolean isHeldExclusively() {
        return getState() == 1;
    }

    public Condition newCondition() {
        return new ConditionObject();
    }

}


