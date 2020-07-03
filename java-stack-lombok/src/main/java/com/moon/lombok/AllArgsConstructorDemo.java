package com.moon.lombok;

import lombok.AllArgsConstructor;

/**
 * AllArgsConstructor 注解demo
 *
 * @author MoonZero
 * @version 1.0
 * @date 2019-11-7 08:42
 * @description 该注解可以为指定类，生成包含所有成员的构造函数
 */
@AllArgsConstructor
public class AllArgsConstructorDemo {
    private long id;
    private String name;
    private int age;
}
