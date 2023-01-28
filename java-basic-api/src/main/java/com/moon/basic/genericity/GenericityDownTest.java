package com.moon.basic.genericity;

import com.moon.basic.genericity.entity.Animal;
import com.moon.basic.genericity.entity.Cat;
import com.moon.basic.genericity.entity.MiniCat;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * 泛型通配符下限测试
 *
 * @author MooNkirA
 * @version 1.0
 * @date 2022-10-15 11:21
 * @description
 */
public class GenericityDownTest {

    // 泛型类基础使用测试
    @Test
    public void test01() {
        List<Animal> animals = new ArrayList<>();
        List<Cat> cats = new ArrayList<>();
        List<MiniCat> miniCats = new ArrayList<>();

        showAnimal(animals);
        showAnimal(cats);
        // showAnimal(miniCats); // 报错
    }

    /**
     * 泛型下限通配符，调用该方法时，要求集合只能是Cat或Cat的父类类型
     */
    public void showAnimal(List<? super Cat> list) {
        // 对于指定下限的泛型集合来说，编译器只知道集合元素是下限的父类型，但具体是那种父类型不确定，
        // 因此，这种泛型集合能向其中添加元素
        list.add(new Cat("小白", 3));
        list.add(new MiniCat("小黑", 2, 1));
        // 而指定泛型下限的集合元素，循环读取的内容只能通过 Object 类型来接收
        for (Object o : list) {
            System.out.println(o);
        }
    }

}
