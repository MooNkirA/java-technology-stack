package com.moon.design.singleten;

/**
 * 单例模式实现，枚举式。
 * 首先创建Enum时，编译器会自动生成一个继承自java.lang.Enum的类，枚举成员声明中被static和final所修饰，
 * 虚拟机会保证这个成员在多线程环境中被正确的加锁和同步，所以是线程安全的。
 *
 * @author MooNkirA
 * @version 1.0
 * @date 2021-3-18 14:20
 * @description
 */
public enum EnumSingleton {

    instance

}
