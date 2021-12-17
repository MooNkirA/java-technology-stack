package com.moon.concurrent.pattern;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.Map;
import java.util.Random;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 保护性暂停 模式
 *
 * @author MooNkirA
 * @version 1.0
 * @date 2021-12-16 10:00
 * @description
 */
public class GuardedObjectDemo {

    public final static Logger LOGGER = LoggerFactory.getLogger(GuardedObjectDemo.class);

    /**
     * 保护性暂停基础使用示例
     */
    @Test
    public void testGuardedObjectBasic() {
        // 创建 GuardedObject 实例
        GuardedObject guardedObject = new GuardedObject();

        // 创建获取数据的线程
        new Thread(() -> {
            try {
                LOGGER.info("load start...");
                Thread.sleep(4000); // 模拟业务处理
                LOGGER.info("load complete...");
                // 返回结果并唤醒线程
                guardedObject.complete("i am result");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();

        // 另一个线程获取结果，结果返回前会进行等待
        Object o = guardedObject.get();
        LOGGER.info("get response: [{}]", o);
    }

    /**
     * 保护性暂停带超时时间改进版
     */
    @Test
    public void testGuardedObjectTimeOut() {
        // 创建 GuardedObject 实例
        GuardedObjectV2 guardedObject = new GuardedObjectV2();

        // 创建获取数据的线程
        new Thread(() -> {
            try {
                LOGGER.info("load start...");
                Thread.sleep(1000); // 模拟业务处理
                LOGGER.info("load complete...");
                // 返回结果并唤醒线程
                guardedObject.complete("i am result");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();

        // 另一个线程获取结果，结果返回前会进行等待，如超出最大等待时间，则直接唤醒
        Object o = guardedObject.get(3000);
        LOGGER.info("get response: [{}]", o);
    }

    /**
     * 保护性暂停多任务改进版
     */
    @Test
    public void testGuardedObjectMultiTask() throws InterruptedException, IOException {
        // 多个待接收结果的任务
        for (int i = 1; i < 4; i++) {
            new Thread(() -> {
                // 创建 GuardedObject
                GuardedObjectV3 guardedObject = TaskManagement.createGuardedObject();
                int id = guardedObject.getId();
                LOGGER.info("Receiver{} loading data...", id);
                // 等待获取返回结果
                Object o = guardedObject.get(5000);
                LOGGER.info("Receiver{} get response: [{}]", id, o);
            }).start();
        }

        Thread.sleep(1000);

        // 获取所有任务
        for (Integer id : TaskManagement.getIds()) {
            new Thread(() -> {
                GuardedObjectV3 guardedObject = TaskManagement.getGuardedObject(id);
                LOGGER.info("{} sending data...", id);
                try {
                    Thread.sleep(new Random().nextInt(3000));
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                guardedObject.complete(id + " :: send data");
            }).start();
        }
        System.in.read();
    }

}

/* 定义做为第三方的 GuardedObject */
class GuardedObject {

    private Object response;
    private final Object lock = new Object();

    public Object get() {
        synchronized (lock) {
            // 判断无响应结果，则循环等待
            while (response == null) {
                try {
                    GuardedObjectDemo.LOGGER.info("waiting....");
                    lock.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            return response;
        }
    }

    public void complete(Object response) {
        synchronized (lock) {
            // 条件满足，唤醒等待线程
            this.response = response;
            lock.notifyAll();
        }
    }

}

/* 可设置最大等待时间的 GuardedObject */
class GuardedObjectV2 {

    private Object response;
    private final Object lock = new Object();

    /**
     * 可设置超时时间
     *
     * @param timeout
     * @return
     */
    public Object get(long timeout) {
        synchronized (lock) {
            // 记录线程开始执行的时间
            long base = System.currentTimeMillis();
            // 记录已等待的时间
            long timePassed = 0;
            // 判断无响应结果，则循环等待
            while (response == null) {
                // 通过超时时间与唤醒前已等待的时间，计算剩余可等待时间
                long waitTime = timeout - timePassed;
                GuardedObjectDemo.LOGGER.info("waiting time: {}", waitTime);
                // 判断是否等待超时
                if (waitTime <= 0) {
                    GuardedObjectDemo.LOGGER.error("waiting time up, break..");
                    break;
                }
                try {
                    lock.wait(waitTime);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                // 这里需要注意被提前唤醒的情况。记录一下当前被唤醒经历的时长
                timePassed = System.currentTimeMillis() - base;
            }
            return response;
        }
    }

    public void complete(Object response) {
        synchronized (lock) {
            // 条件满足，唤醒等待线程
            this.response = response;
            GuardedObjectDemo.LOGGER.info("notifyAll...");
            lock.notifyAll();
        }
    }

}

/* 多任务版 GuardedObject */
class GuardedObjectV3 {

    // 标识不同的 GuardedObject
    private final int id;
    private Object response;
    private final Object lock = new Object();

    public GuardedObjectV3(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    /**
     * 可设置超时时间
     *
     * @param timeout
     * @return
     */
    public Object get(long timeout) {
        synchronized (lock) {
            // 记录线程开始执行的时间
            long base = System.currentTimeMillis();
            // 记录已等待的时间
            long timePassed = 0;
            // 判断无响应结果，则循环等待
            while (response == null) {
                // 通过超时时间与唤醒前已等待的时间，计算剩余可等待时间
                long waitTime = timeout - timePassed;
                GuardedObjectDemo.LOGGER.info("waiting time: {}", waitTime);
                // 判断是否等待超时
                if (waitTime <= 0) {
                    GuardedObjectDemo.LOGGER.error("waiting time up, break..");
                    break;
                }
                try {
                    lock.wait(waitTime);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                // 这里需要注意被提前唤醒的情况。记录一下当前被唤醒经历的时长
                timePassed = System.currentTimeMillis() - base;
            }
            return response;
        }
    }

    public void complete(Object response) {
        synchronized (lock) {
            // 条件满足，唤醒等待线程
            this.response = response;
            GuardedObjectDemo.LOGGER.info("notifyAll...");
            lock.notifyAll();
        }
    }

}

/* 多任务 GuardedObject 管理器 */
class TaskManagement {
    // 保存多个 GuardedObject 任务
    private final static Map<Integer, GuardedObjectV3> GUARDED_MAP = new ConcurrentHashMap<>();

    private static int id = 1;

    // 生成 GuardedObject 相应的id
    private static synchronized int generateId() {
        return id++;
    }

    public static GuardedObjectV3 getGuardedObject(int id) {
        // 从容器获取相应的 GuardedObject 并移除
        return GUARDED_MAP.remove(id);
    }

    public static GuardedObjectV3 createGuardedObject() {
        GuardedObjectV3 go = new GuardedObjectV3(generateId());
        // 放入容器并返回
        GUARDED_MAP.put(go.getId(), go);
        return go;
    }

    // 获取当前所有任务的id
    public static Set<Integer> getIds() {
        return GUARDED_MAP.keySet();
    }

}