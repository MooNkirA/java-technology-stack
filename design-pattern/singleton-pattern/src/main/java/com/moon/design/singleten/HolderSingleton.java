package com.moon.design.singleten;

/**
 * 单例类实现，静态内部类实现式
 *
 * @author MooNkirA
 * @version 1.0
 * @date 2021-3-18 11:02
 * @description
 */
public class HolderSingleton {

    private HolderSingleton() {
    }

    /*
     * 当getInstance方法第一次被调用的时候，它第一次读取InstanceHolder.INSTANCE时，会触发InstanceHolder类的初始化。
     * 而InstanceHolder类在装载并被初始化的时候，会初始化它的静态成员变量、静态域，从而创建HolderSingleton的实例。
     * 由于是静态的域，因此只会在虚拟机装载类的时候初始化一次，并由虚拟机来保证它的线程安全性。
     * 这个模式的优势在于，getInstance方法并没有做线程同步控制，并且只是执行一个域的访问，因此延迟初始化并没有增加任何访问成本。
     */
    public static HolderSingleton getInstance() {
        return InstanceHolder.INSTANCE;
    }

    /* 内部类前面加static关键字，表示的是类级内部类，类级内部类只有在使用时才会被加载 */
    private static class InstanceHolder {
        // 静态变量的初始化是由JVM保证线程安全的，在类的加载时就完成了静态变量的赋值
        static final HolderSingleton INSTANCE = new HolderSingleton();
    }

}
