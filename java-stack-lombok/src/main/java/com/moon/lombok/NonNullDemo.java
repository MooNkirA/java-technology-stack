package com.moon.lombok;

import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;

/**
 * NonNull 注解 Demo
 *
 * @author MoonZero
 * @version 1.0
 * @date 2019-11-7 14:12
 * @description
 */
public class NonNullDemo {
    @Getter
    @Setter
    @NonNull
    private String name;
}
