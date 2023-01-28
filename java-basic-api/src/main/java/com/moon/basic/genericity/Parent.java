package com.moon.basic.genericity;

/**
 * 泛型父类
 *
 * @author MooNkirA
 * @version 1.0
 * @date 2022-10-15 11:18
 * @description
 */
public class Parent<E> {

    private E value;

    public E getValue() {
        return value;
    }

    public void setValue(E value) {
        this.value = value;
    }

}