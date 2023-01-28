package com.moon.sample;

import org.junit.Test;

import java.util.Scanner;

/**
 * Java 经典逻辑编程 50 题
 *
 * @author MooNkirA
 * @version 1.0
 * @date 2022-10-21 22:52
 * @description
 */
public class JavaClassicLogic {

    /*
     【程序1】
        題目：古典问题：有一对兔子，从出生后第3个月起每个月都生一对兔子，小兔子长到第三个月后每个月又生一对兔子，假如兔子都不死，问每个月的兔子总数为多少？
        分析：咋一看不知道如何下手，但是你在草稿纸上写写分析一下，就很快发现其中的规律了
    */
    @Test
    public void test01() {
        Scanner in = new Scanner(System.in);

        System.out.println("你想知道前几个月的兔子的数量");
        int month = in.nextInt();

        int[] mon = new int[month];
        if (month < 3) {
            System.out.println("第" + month + "个月有 1 对兔子，共 2 只");
        } else {
            for (int i = 2; i < month; i++) {
                mon[0] = mon[1] = 1;
                mon[i] = mon[i - 1] + mon[i - 2];
                System.out.printf("第 %d 个月有 %d 对兔子，共 %d 只兔子\n", i + 1, mon[i], 2 * mon[i]);
            }
        }
    }

    /*
        【程序2】
        题目：判断101-200之间有多少个素数，并输出所有素数。
        分析：如果知道素数是什么，该题就应该不难了
     */
    @Test
    public void test02() {
        System.out.print("101--200中的素数有：");
        for (int i = 101; i <= 200; i++) {
            if (isPrime(i))
                System.out.print(" " + i);
        }
    }

    // isPrime方法用来判断一个数是否是素数
    private boolean isPrime(int i) {
        for (int j = 2; j <= Math.sqrt(i); j++) {
            if (i % j == 0)
                return false;
        }
        return true;
    }

    /*
        【程序3】
        题目：打印出所有的"水仙花数"，所谓"水仙花数"是指一个三位数，其各位数字立方和等于该数本身。
        例如：153是一个"水仙花数"，因为153=1的三次方＋5的三次方＋3的三次方。
        分析：解决这个题目主要要知道怎么把一个数的各个位上的数拆分出来
     */
    @Test
    public void test03() {
        System.out.print("水仙花数有：");
        for (int num = 100; num < 1000; num++) {
            if (isNarcissisticNum(num))
                System.out.print(" " + num);
        }
        System.out.println("  ");
    }

    // 一个判断正整数是否为水仙花数的方法
    private static boolean isNarcissisticNum(int num) {
        int a = num / 100;          // 分离出百位 a
        int b = (num / 10) % 10;    // 分离出十位 b
        int c = num % 10;           // 分离出个位 c
        int sum = a * a * a + b * b * b + c * c * c;
        return sum == num;
    }


    /*

     */
    @Test
    public void test00() {

    }

}
