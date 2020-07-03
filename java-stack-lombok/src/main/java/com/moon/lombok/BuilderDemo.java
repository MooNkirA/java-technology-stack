package com.moon.lombok;

import lombok.Builder;

/**
 * Builder 注解 Demo
 *
 * @author MoonZero
 * @version 1.0
 * @date 2019-11-7 14:01
 * @description `@Builder` 注解可以为指定类实现建造者模式，该注解可以放在类、构造函数或方法上
 */
@Builder
public class BuilderDemo {
    private final String firstname;
    private final String lastname;
    private final String email;
}
