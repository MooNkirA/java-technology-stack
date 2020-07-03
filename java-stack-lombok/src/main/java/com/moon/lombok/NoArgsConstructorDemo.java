package com.moon.lombok;

import lombok.NoArgsConstructor;

/**
 * NoArgsConstructor 注解demo
 *
 * @author MoonZero
 * @version 1.0
 * @date 2019-11-6 23:53
 * @description 注解可以为指定类，生成默认的构造函数
 */
@NoArgsConstructor(staticName = "getInstance")
public class NoArgsConstructorDemo {
    private long id;
    private String name;
    private int age;
}
