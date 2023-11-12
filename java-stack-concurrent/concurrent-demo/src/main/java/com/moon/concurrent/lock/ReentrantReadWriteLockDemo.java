package com.moon.concurrent.lock;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * ReentrantReadWriteLock 基础使用示例
 * <p>
 * 示例需求：提供一个 数据容器类，
 * 内部分别使用读锁保护数据的 read() 方法，
 * 写锁保护数据的 write() 方法
 * </p>
 *
 * @author MooNkirA
 * @version 1.0
 * @date 2023-03-06 23:17
 * @description
 */
public class ReentrantReadWriteLockDemo {

    public static void main(String[] args) {
        DataContainer dataContainer = new DataContainer();
        new Thread(() -> {
            // dataContainer.read();
            // 测试『写锁-写锁』相互阻塞
            dataContainer.write();
        }, "t1").start();
        new Thread(() -> {
            // 测试『读锁-读锁』可以并发执行
            // dataContainer.read();
            // 测试『读锁-写锁』相互阻塞
            dataContainer.write();
        }, "t2").start();
    }

}

@Slf4j
class DataContainer {

    private Object data;
    private final ReentrantReadWriteLock reentrantReadWriteLock = new ReentrantReadWriteLock();
    private final ReentrantReadWriteLock.ReadLock readLock = reentrantReadWriteLock.readLock();
    private final ReentrantReadWriteLock.WriteLock writeLock = reentrantReadWriteLock.writeLock();

    public Object read() {
        log.debug("获取读锁...");
        readLock.lock();
        try {
            log.debug("读取");
            Thread.sleep(1000);
            return data;
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            log.debug("释放读锁...");
            readLock.unlock();
        }
        return null;
    }

    public void write() {
        log.debug("获取写锁...");
        writeLock.lock();
        try {
            log.debug("写入");
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            log.debug("释放写锁...");
            writeLock.unlock();
        }
    }
}
