package com.moon.lombok;

import lombok.Getter;

/**
 * LazyGetter 属性 demo
 *
 * @author MoonZero
 * @version 1.0
 * @date 2019-11-6 23:09
 * @description `@Getter` 注解支持一个 lazy 属性，该属性默认为 false。当设置为 true 时，会启用延迟初始化，即当首次调用 getter 方法时才进行初始化
 */
public class LazyGetterDemo {
    public static void main(String[] args) {
        com.moon.lombok.LazyGetterDemo m = new com.moon.lombok.LazyGetterDemo();
        System.out.println("Main instance is created");
        m.getLazy();
    }

    @Getter
    private final String notLazy = createValue("not lazy");

    @Getter(lazy = true)
    private final String lazy = createValue("lazy");

    private String createValue(String name) {
        System.out.println("createValue(" + name + ")");
        return null;
    }
}
