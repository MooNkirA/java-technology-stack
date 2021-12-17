package com.moon.concurrent.lock;

/**
 * synchronized 修饰方法
 *
 * @author MooNkirA
 * @version 1.0
 * @date 2021-12-15 8:36
 * @description
 */
public class SynchronizedMethod {

    public static void main(String[] args) throws InterruptedException {
        /* synchronized 修饰成员方法的示例 */
        CounterMethod counter = new CounterMethod();

        Thread t1 = new Thread(() -> {
            for (int i = 0; i < 5000; i++) {
                counter.increment();
            }
        }, "t1");

        Thread t2 = new Thread(() -> {
            for (int i = 0; i < 5000; i++) {
                counter.decrement();
            }
        }, "t2");

        t1.start();
        t2.start();
        t1.join();
        t2.join();
        // 程序如果是线程安全的，最终结果是0
        System.out.println("count: " + counter.get());

        /* synchronized 修饰静态成员方法的示例 */
        Thread t3 = new Thread(() -> {
            for (int i = 0; i < 5000; i++) {
                CounterStatic.increment();
            }
        }, "t3");

        Thread t4 = new Thread(() -> {
            for (int i = 0; i < 5000; i++) {
                CounterStatic.decrement();
            }
        }, "t4");

        t3.start();
        t4.start();
        t3.join();
        t4.join();
        // 程序如果是线程安全的，最终结果是0
        System.out.println("count: " + CounterStatic.get());
    }

}

class CounterMethod {
    private int count = 0;

    /*
     * 在方法前使用 synchronized 相当于
     *  public void test() {
     *      synchronized (this) {
     *          ....
     *      }
     *  }
     */
    public synchronized void increment() {
        count++;
    }

    public synchronized void decrement() {
        count--;
    }

    public synchronized int get() {
        return count;
    }
}

class CounterStatic {
    private static int count = 0;

    /*
     * 在静态方法前使用 synchronized 相当于
     *  public static void test() {
     *      synchronized (本类.class) {
     *          ....
     *      }
     *  }
     */
    public synchronized static void increment() {
        count++;
    }

    public synchronized static void decrement() {
        count--;
    }

    public synchronized static int get() {
        return count;
    }
}

