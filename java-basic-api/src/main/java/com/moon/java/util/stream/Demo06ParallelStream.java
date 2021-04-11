package com.moon.java.util.stream;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Vector;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.LongStream;
import java.util.stream.Stream;

/**
 * JDK8 新特性 Stream - 并行流应用示例
 *
 * @author MooNkirA
 * @version 1.0
 * @date 2020-9-27 17:25
 * @description
 */
public class Demo06ParallelStream {

    // 定义测试使用的集合
    private final List<Integer> LIST = Arrays.asList(4, 5, 3, 9, 1, 2, 6);

    /* 并行流的创建测试 */
    @Test
    public void parallelStreamCreateTest() {
        // 方式一：直接使用Collection接口的 parallelStream() 方法创建并行流
        Stream<Integer> parallelStream1 = LIST.parallelStream();
        // 调用Stream接口的isParallel()来判断当前是否为并行流
        System.out.println("parallelStream1是否为并行流: " + parallelStream1.isParallel());

        // 方式二：将串行流转成并行流，调用Stream接口的 parallel() 方法，转成并行流
        Stream<Integer> parallelStream2 = LIST.stream().parallel();
        System.out.println("parallelStream2是否为并行流: " + parallelStream2.isParallel());
    }

    /* 串行与并行流的执行线程测试 */
    @Test
    public void serialAndParallelStreamTest() {
        // 创建串行流，输出执行时线程的名称
       /* LIST.stream().filter(s -> {
            System.out.println(Thread.currentThread() + "::" + s);
            return s > 3;
        }).count();
        System.out.println("------------------------");*/

        // 创建串行流，输出执行时线程的名称
        LIST.parallelStream().filter(s -> {
            System.out.println(Thread.currentThread() + "::" + s);
            return s > 3;
        }).count();
    }

    // ****************************************************
    //          for循环、串行流与并行流的执行效率对比
    // ****************************************************
    // 定义循环的次数
    private static final int times = 500000000;
    // 定义开始时间
    private long startTime;

    @Before
    public void init() {
        startTime = System.currentTimeMillis();
    }

    @After
    public void destory() {
        long endTime = System.currentTimeMillis();
        System.out.println("消耗时间: " + (endTime - startTime));
    }

    /* for循环对5亿个数字求和的执行效率测试  消耗时间：166 */
    @Test
    public void forEachEfficiencyTest() {
        int sum = 0;
        for (int i = 0; i < times; i++) {
            sum += i;
        }
    }

    /* 串行Stream流对5亿个数字求和的执行效率测试  消耗时间：390 */
    @Test
    public void serialStreamTest() {
        // LongStream类的rangeClosed静态方法用于创建一个指定执行次数的流
        LongStream.rangeClosed(0, times).reduce(0, Long::sum);
    }

    /* 并行Stream流对5亿个数字求和的执行效率测试  消耗时间：186 */
    @Test
    public void parallelStreamTest() {
        // LongStream类的rangeClosed静态方法用于创建一个指定执行次数的流
        LongStream.rangeClosed(0, times)
                .parallel() // 转成并行流
                .reduce(0, Long::sum);
    }

    // ****************************************************
    //        parallelStream并行流线程安全问题解决方案
    // ****************************************************
    @Test
    public void threadSafetyTest() {
        // 定义待测试的集合
        List<Integer> list = new ArrayList<>();

        /*
         * 创建并行流，往集合插入数据，会出现线程安全问题
         *  实现插入数据数量比指定的数次少
         */
        IntStream.rangeClosed(1, 1000)
                .parallel()
                // .forEach(i -> list.add(i));
                .forEach(list::add); // 使用方法引用简化lambda表达式
        System.out.println("list = " + list.size());

        /* 解决parallelStream线程安全问题方案一: 使用同步代码块 */
        list.clear(); // 演示线程安全，重复使用集合，需要先清空
        Object o = new Object(); // 定义锁对象
        IntStream.rangeClosed(1, 1000)
                .parallel()
                .forEach(i -> {
                    synchronized (o) {
                        list.add(i);
                    }
                });
        System.out.println("list = " + list.size());

        /* 解决parallelStream线程安全问题方案二: 使用线程安全的集合 */
        // 线程安全的集合1：Vector
        Vector<Integer> vector = new Vector();
        IntStream.rangeClosed(1, 1000)
                .parallel()
                .forEach(vector::add);
        System.out.println("vector = " + vector.size());

        // 线程安全的集合2：Collections工具类提供的synchronizedList方法，将非线程安全的list转成线程安全
        list.clear(); // 演示线程安全，重复使用集合，需要先清空
        List<Integer> synchronizedList = Collections.synchronizedList(list);
        IntStream.rangeClosed(1, 1000)
                .parallel()
                .forEach(synchronizedList::add);
        System.out.println("synchronizedList = " + synchronizedList.size());


        /* 解决parallelStream线程安全问题方案三: 调用Stream流的collect/toArray */
        List<Integer> collectList = IntStream.rangeClosed(1, 1000)
                .parallel()
                .boxed() // 转成stream流
                .collect(Collectors.toList());
        System.out.println("collectList = " + collectList.size());
    }


}
