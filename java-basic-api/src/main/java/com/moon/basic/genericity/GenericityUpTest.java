package com.moon.basic.genericity;

import com.moon.basic.genericity.entity.Animal;
import com.moon.basic.genericity.entity.Cat;
import com.moon.basic.genericity.entity.MiniCat;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * 泛型通配符上限测试
 *
 * @author MooNkirA
 * @version 1.0
 * @date 2022-10-15 11:21
 * @description
 */
public class GenericityUpTest {

    // 泛型类基础使用测试
    @Test
    public void test01() {
        List<Animal> animals = new ArrayList<>();
        List<Cat> cats = new ArrayList<>();
        List<MiniCat> miniCats = new ArrayList<>();

        cats.addAll(cats);
        cats.addAll(miniCats);
        // showAnimal(animals); // 报错
        showAnimal(cats);
        showAnimal(miniCats);
    }

    /**
     * 泛型上限通配符，调用该方法时，传递的集合类型只能是Cat或Cat的子类类型。
     */
    public void showAnimal(List<? extends Cat> list) {
        // 这里泛型形参集合不能添加元素。
        // 因为 <? extends Cat> 表示未知的子类，程序无法确定这个类型是什么，所以无法将任何对象添加到集合中
        // list.add(new Animal()); // 报错
        // list.add(new Cat()); // 报错
        // list.add(new MiniCat()); // 报错
        // 因此，这种指定通配符上限的集合，只能从集合中读取元素（取出的元素总是上限类型），不能向集合中添加元素。
        for (int i = 0; i < list.size(); i++) {
            Cat cat = list.get(i);
            System.out.println(cat);
        }
    }

}
