package com.moon.lombok;

import lombok.SneakyThrows;

/**
 * SneakyThrows 注解 Demo
 *
 * @author MoonZero
 * @version 1.0
 * @date 2019-11-7 14:08
 * @description `@SneakyThrows` 注解用于自动抛出已检查的异常，而无需在方法中使用 throw 语句显式抛出
 */
public class SneakyThrowsDemo {
    @SneakyThrows
    @Override
    protected Object clone() {
        return super.clone();
    }
}
