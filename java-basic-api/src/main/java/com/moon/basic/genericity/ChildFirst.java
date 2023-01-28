package com.moon.basic.genericity;

/**
 * 泛型类派生子类情况一：子类也是泛型类，那么子类的泛型标识要和父类一致。
 *
 * @author MooNkirA
 * @version 1.0
 * @date 2022-10-15 11:19
 * @description
 */
public class ChildFirst<T> extends Parent<T> {

    @Override
    public T getValue() {
        return super.getValue();
    }

}
