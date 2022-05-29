package com.moon.design.adapter;

/**
 * 待适配者的类
 *
 * @author MooNkirA
 * @version 1.0
 * @date 2022-05-25 11:01
 * @description
 */
public class Adaptee {

    public void specificRequest() {
        System.out.println("适配者中的业务代码被调用！");
    }

}
