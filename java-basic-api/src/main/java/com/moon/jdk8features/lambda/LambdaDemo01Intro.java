package com.moon.jdk8features.lambda;

import org.junit.Test;

/**
 * Lambda表达式示例
 *
 * @author MooNkirA
 * @version 1.0
 * @date 2020-9-24 22:52
 * @description
 */
public class LambdaDemo01Intro {

    /* 示例：当需要启动一个线程去完成任务时，通常会通过 `Runnable` 接口来定义任务内容，并使用 `Thread` 类来启动该线程。 */
    @Test
    public void quickstartTest() {
        /*
         * 传统写法，使用匿名内部类实现
         * 对于 Runnable 的匿名内部类用法，可以分析出几点内容：
         *      1.Thread 类需要 Runnable 接口作为参数，其中的抽象 run 方法是用来指定线程任务内容的核心
         *      2.为了指定 run 的方法体，不得不需要 Runnable 接口的实现类
         *      3.为了省去定义一个 Runnable 实现类的麻烦，不得不使用匿名内部类
         *      4.必须覆盖重写抽象 run 方法，所以方法名称、方法参数、方法返回值不得不再写一遍，且不能写错
         *      5.实际上，似乎只有方法体才是关键所在。
         */
        new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("新线程任务执行！");
            }
        }).start();

        /*
         * 使用Lambda表达式实现，Lambda是一个匿名函数
         *       简化匿名内部类的使用，语法更加简单
         */
        new Thread(() -> {
            System.out.println("使用Lambda表达式创建的线程任务执行了！");
        }).start();
    }

}
