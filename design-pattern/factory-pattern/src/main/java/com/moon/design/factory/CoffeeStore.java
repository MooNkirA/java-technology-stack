package com.moon.design.factory;

import com.moon.design.factory.method.CoffeeFactory;
import com.moon.design.factory.method.LatteCoffeeFactory;
import com.moon.design.factory.service.Coffee;
import com.moon.design.factory.service.LatteCoffee;
import com.moon.design.factory.service.AmericanCoffee;
import com.moon.design.factory.simple.SimpleCoffeeFactory;

import java.util.Objects;

/**
 * 咖啡店，生成咖啡（基础实现）
 *
 * @author MooNkirA
 * @version 1.0
 * @date 2023-09-29 18:46
 * @description
 */
public class CoffeeStore {

    public static void main(String[] args) {
        // 无设计模式的方式实现
        Coffee coffee = orderCoffee("latte");
        System.out.println(coffee.getName());

        System.out.println("===========");

        // 使用简单静态工厂模式
        Coffee coffee1 = simpleOrderCoffee("american");
        System.out.println(coffee1.getName());

        System.out.println("===========");

        // 使用工厂方法模式
        CoffeeStore coffeeStore = new CoffeeStore(new LatteCoffeeFactory());

        Coffee coffee2 = coffeeStore.methodOrderCoffee();
        System.out.println(coffee2.getName());
    }

    /**
     * 定义静态方法，根据类型选择不同的咖啡
     *
     * @param type
     * @return
     */
    public static Coffee orderCoffee(String type) {
        Coffee coffee = null;
        if ("american".equals(type)) {
            coffee = new AmericanCoffee();
        } else if ("latte".equals(type)) {
            coffee = new LatteCoffee();
        }

        if (Objects.nonNull(coffee)) {
            coffee.addMilk();
            coffee.addSuqar();
        }
        return coffee;
    }

    public static Coffee simpleOrderCoffee(String type) {
        // 通过工厂获得对象，不需要知道对象实现的细节
        Coffee coffee = SimpleCoffeeFactory.createCoffee(type);

        if (Objects.nonNull(coffee)) {
            coffee.addMilk();
            coffee.addSuqar();
        }
        return coffee;
    }

    private CoffeeFactory coffeeFactory;

    public CoffeeStore(CoffeeFactory coffeeFactory){
        this.coffeeFactory = coffeeFactory;
    }

    public Coffee methodOrderCoffee() {
        // 可以根据不同的工厂，创建不同的产品
        Coffee coffee = coffeeFactory.createCoffee();

        if (Objects.nonNull(coffee)) {
            coffee.addMilk();
            coffee.addSuqar();
        }
        return coffee;
    }
}
