package com.moon.design.adapter.objectadapter;

import com.moon.design.adapter.Adaptee;
import com.moon.design.adapter.Target;

/**
 * 对象适配器，只需要实现目标接口。在类中定义适配者类型的属性，
 * 通过构造函数或者 setter 方法获取到适配者对象，
 * 在调用目标接口方法时，通过适配者对象调用相应的方法
 *
 * @author MooNkirA
 * @version 1.0
 * @date 2022-05-25 11:13
 * @description
 */
public class ObjectAdapter implements Target {

    private final Adaptee adaptee;

    public ObjectAdapter(Adaptee adaptee) {
        this.adaptee = adaptee;
    }

    public void request() {
        // 通过适配者对象调用
        adaptee.specificRequest();
    }
}
