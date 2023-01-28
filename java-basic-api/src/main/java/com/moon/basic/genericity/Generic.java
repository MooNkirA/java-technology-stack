package com.moon.basic.genericity;

/**
 * 泛型类的定义
 *
 * @param <T> 此处 T 可以随便写为任意标识，常见的如 T、E、K、V 等形式的参数常用于表示泛型
 *            在实例化泛型类时，必须指定T的具体类型
 */
public class Generic<T> {

    // 此成员变量的类型为T，T 的类型在创建实例时指定
    private T key;

    // 泛型构造方法形参的类型也为T，T 的类型在创建实例时指定
    public Generic(T key) {
        this.key = key;
    }

    // 泛型方法的返回值类型为 T，T 的类型在创建实例时指定
    public T getKey() {
        return key;
    }

    // 泛型方法的形参类型也为T，T 的类型在创建实例时指定
    public void setKey(T key) {
        this.key = key;
    }

}
