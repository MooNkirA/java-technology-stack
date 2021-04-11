package com.moon.jdk8features.interfacefeature;

/**
 * JDK8 接口增强：默认方法和静态方法 综合使用示例
 *
 * @author MooNkirA
 * @version 1.0
 * @date 2020-9-25 14:37
 * @description
 */
public class Demo04Jdk8DefaultAndStaticMethod {
    public static void main(String[] args) {
        Vehicle vehicle = new Car();
        vehicle.print();
    }
}

interface Vehicle {
    default void print() {
        System.out.println("我是一辆车!");
    }

    static void blowHorn() {
        System.out.println("按喇叭!!!");
    }
}

interface FourWheeler {
    default void print() {
        System.out.println("我是一辆四轮车!");
    }
}

class Car implements Vehicle, FourWheeler {
    // 重写两个接口同名默认方法
    @Override
    public void print() {
        Vehicle.super.print();
        FourWheeler.super.print(); // 调用接口的默认方法
        Vehicle.blowHorn(); // 调用接口静态方法
        System.out.println("我是一辆汽车!");
    }
}
