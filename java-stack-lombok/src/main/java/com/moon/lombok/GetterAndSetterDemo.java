package com.moon.lombok;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

/**
 * Getter/Setter 注解 demo
 *
 * @author MoonZero
 * @version 1.0
 * @date 2019-11-6 23:07
 * @description 使用 `@Getter` 或 `@Setter` 注释任何类或字段，Lombok 会自动生成默认的 getter/setter 方法
 */
@Getter
@Setter
public class GetterAndSetterDemo {
    String firstName;
    String lastName;
    LocalDate dateOfBirth;
}
