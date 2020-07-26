package com.moon.jvm;

/**
 * JAVA方法的运行与虚拟机栈
 *
 * @author MooNkirA
 * @version 1.0
 * @date 2020-7-25 16:36
 * @description
 */
public class MethodAndStack {

    public static void main(String[] args) {
        System.out.println("main方法执行开始");
        A();
        System.out.println("main方法执行结束");
    }

    public static void A() {
        System.out.println("A方法执行");
        B();
    }

    public static void B() {
        System.out.println("B方法执行");
        C();
    }

    public static void C() {
        System.out.println("C方法执行");
    }

}
