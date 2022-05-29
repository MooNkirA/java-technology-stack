package com.moon.design.adapter.objectadapter;

import com.moon.design.adapter.Adaptee;
import com.moon.design.adapter.Target;

/**
 * 对象适配器模式测试
 *
 * @author MooNkirA
 * @version 1.0
 * @date 2022-05-25 11:21
 * @description
 */
public class ObjectAdapterTest {

    public static void main(String[] args) {
        System.out.println("对象适配器模式测试：");
        Adaptee adaptee = new Adaptee();
        Target target = new ObjectAdapter(adaptee);
        // 调用目标接口方法，实际是调用了被适配者对象的方法
        target.request();
    }
}
