package com.moon.basic.genericity;

/**
 * 在泛型类中定义泛型方法
 *
 * @author MooNkirA
 * @version 1.0
 * @date 2022-10-15 13:23
 * @description
 */
public class GenericMethod<T> {

    private T key;

    // 此方法非泛型方法，这是一个普通方法，它的数据类型遵从泛型类的类型
    public T getKey() {
        return key;
    }

    // 此方法是泛型方法，不会依赖父类的泛型类型，即使以 <T> 作为泛型变量，也与泛型类无关
    public <E> void show(E[] e) {
        for (int i = 0; i < e.length; i++) {
            System.out.println(e[i]);
        }
    }

}
