package com.moon.jvm;

import org.openjdk.jol.info.ClassLayout;

/**
 * 对象的内存布局，此布局就跟操作系统有关系。
 *
 * @author MooNkirA
 * @version 1.0
 * @date 2024-02-11 22:28
 * @description
 */
public class JOLDemo {

    private String id;
    private String name;

    public static void main(String[] args) {
        JOLDemo o = new JOLDemo();
        System.out.println(ClassLayout.parseInstance(o).toPrintable());

        synchronized (o) {
            System.out.println(ClassLayout.parseInstance(o).toPrintable());
        }
    }

}
