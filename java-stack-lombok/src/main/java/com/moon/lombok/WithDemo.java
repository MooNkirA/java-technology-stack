package com.moon.lombok;

import lombok.AccessLevel;
import lombok.NonNull;
import lombok.With;

/**
 * With 注解 Demo
 *
 * @author MoonZero
 * @version 1.0
 * @date 2019-11-7 14:19
 * @description 在类的字段上应用 `@With` 注解之后，将会自动生成一个 `withFieldName(newValue)` 的方法，
 * <P>该方法会基于 newValue 调用相应构造函数，创建一个当前类对应的实例</P>
 */
public class WithDemo {
    @With(AccessLevel.PROTECTED)
    @NonNull
    private final String name;
    @With
    private final int age;

    public WithDemo(String name, int age) {
        if (name == null) throw new NullPointerException();
        this.name = name;
        this.age = age;
    }
}
