package com.moon.lombok;

import lombok.RequiredArgsConstructor;

/**
 * RequiredArgsConstructor 注解demo
 *
 * @author MoonZero
 * @version 1.0
 * @date 2019-11-7 08:50
 * @description `@RequiredArgsConstructor` 注解可以为指定类必需初始化的成员变量，如 final 成员变量，生成对应的构造函数
 */
@RequiredArgsConstructor
public class RequiredArgsConstructorDemo {
    private final long id;
    private String name;
    private int age;
}
