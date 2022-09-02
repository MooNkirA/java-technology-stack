package com.moon.jdk8features.interfacefeature;

import com.moon.common.model.User;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Supplier;

/**
 * 利用 `Supplier` 与 `Consumer` 函数式接口特性，实现通用对象 Builder
 *
 * @author MooNkirA
 * @version 1.0
 * @date 2022-08-10 9:31
 * @description
 */
public class CommonBuilder<T> {

    private final Supplier<T> instantiator;
    private List<Consumer<T>> modifiers = new ArrayList<>();

    public CommonBuilder(Supplier<T> instantiator) {
        this.instantiator = instantiator;
    }

    public static <T> CommonBuilder<T> of(Supplier<T> instantiator) {
        return new CommonBuilder<>(instantiator);
    }

    public <V> CommonBuilder<T> with(ValueConsumer<T, V> consumer, V v) {
        Consumer<T> c = instance -> consumer.accept(instance, v);
        modifiers.add(c);
        return this;
    }

    public T build() {
        // 获取创建的对象
        T value = instantiator.get();
        // 循环所有消费方法，设置对象属性值
        modifiers.forEach(modifier -> modifier.accept(value));
        modifiers.clear();
        return value;
    }

    // 自定义消费函数式接口
    @FunctionalInterface
    public interface ValueConsumer<T, V> {
        void accept(T t, V v);
    }

    /* 测试 */
    public static void main(String[] args) {
        CommonBuilder<User> builder = CommonBuilder.of(User::new);
        User user = builder
                .with(User::setUserName, "MooN")
                .with(User::setAge, 28)
                .build();

        System.out.println(user);
    }
}
