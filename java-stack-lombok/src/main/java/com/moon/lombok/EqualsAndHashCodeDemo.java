package com.moon.lombok;

import lombok.EqualsAndHashCode;

import java.time.LocalDate;

/**
 * EqualsAndHashCode 注解 Demo
 *
 * @author MoonZero
 * @version 1.0
 * @date 2019-11-7 08:58
 * @description `@EqualsAndHashCode` 注解可以为指定类生成 equals 和 hashCode 方法
 */
@EqualsAndHashCode
public class EqualsAndHashCodeDemo {
    String firstName;
    String lastName;
    LocalDate dateOfBirth;
}
