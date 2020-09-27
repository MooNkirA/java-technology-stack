package com.moon.java.jdk8stream;

import com.moon.java.common.model.Person;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

/**
 * JDK8 新特性 Stream - 相关方法基础使用示例
 *
 * @author MooNkirA
 * @version 1.0
 * @date 2020-9-26 17:01
 * @description
 */
public class Demo03StreamFunction {

    private static final List<String> nameList = new ArrayList<>();

    static {
        Collections.addAll(nameList, "天锁斩月", "剑圣", "石原里美", "樱木花道", "敌法师", "新垣结衣");
    }

    /* Stream流 - forEach方法示例测试 */
    @Test
    public void forEachTest() {
        // 示例1：遍历名称字符串集合
        // 获取流，调用Stream流的forEach方法遍历集合
        nameList.stream().forEach((String str) -> {
            System.out.println(str);
        });

        // 简化Lambda表达式
        nameList.stream().forEach(str -> System.out.println(str));

        // 使用方法引用替换Lambda表达式
        nameList.stream().forEach(System.out::println);

        // 示例2：输出了10个随机数
        Random random = new Random();
        // Random类的ints()方法获取IntStream流对象，可以使用
        random.ints().limit(10).forEach(System.out::println);
    }

    /* Stream流 - count方法示例测试 */
    @Test
    public void countTest() {
        // 获取流，调用Stream流的count获取集合的个数
        long count = nameList.stream().count();
        System.out.println("count: " + count);
    }

    /* Stream流 - filter方法示例测试 */
    @Test
    public void filterTest() {
        // 示例1：获取流，调用Stream流的filter过滤名字长度为4个字的人
        /*nameList.stream().filter((String s) -> {
            return s.length() == 4;
        }).forEach((String n) -> {
            System.out.println(n);
        });*/

        // 简化lambda表达式与使用方法引用
        nameList.stream().filter(s -> s.length() == 4).forEach(System.out::println);

        // 示例2：获取空字符串的数量
        List<String> strings = Arrays.asList("abc", "", "bc", "efg", "abcd", "", "jkl");
        int count = (int) strings.stream().filter(String::isEmpty).count();
        System.out.println("空字符串的数量: " + count); // 空字符串的数量: 2
    }

    /* Stream流 - limit方法示例测试 */
    @Test
    public void limitTest() {
        // 示例1：获取流，调用Stream流的limit获取前3个名字
        nameList.stream()
                .limit(3)
                .forEach(System.out::println);

        // 示例2：获取10个随机数
        new Random().ints().limit(10).forEach(System.out::println);
    }

    /* Stream流 - skip方法示例测试 */
    @Test
    public void skipTest() {
        // 示例1：获取流，调用Stream流的skip跳过前面2个数据
        nameList.stream()
                .skip(2)
                .forEach(System.out::println);
    }

    /* Stream流 - map方法示例测试 */
    @Test
    public void mapTest() {
        Stream<String> original = Stream.of("11", "22", "33");
        // 获取流，调用Stream流的map将一种类型的流转换成另一种类型的流
        /*
         * 示例1：将Stream流中的字符串转成Integer
         *  map 方法的参数通过方法引用，将字符串类型转换成为了int类型（并自动装箱为 Integer 类对象）。
         */
        /*Stream<Integer> stream = original.map((String s) -> {
            return Integer.parseInt(s);
        });*/

        // 简化lambda表达式
        // original.map(s -> Integer.parseInt(s)).forEach(System.out::println);

        // 使用方法引用
        original.map(Integer::parseInt).forEach(System.out::println);

        // 示例2：获取数组元素的平方数
        List<Integer> numbers = Arrays.asList(3, 2, 2, 3, 7, 3, 5);
        List<Integer> squaresList = numbers.stream()
                .map(i -> i * i)
                .distinct()
                .collect(Collectors.toList());
        System.out.println(squaresList);    // 输出：[9, 4, 49, 25]
    }

    /* Stream流 - sorted方法示例测试 */
    @Test
    public void sortedTest() {
        /*
         * 示例1：
         *   sorted(): 根据元素的自然顺序排序
         *   sorted(Comparator<? super T> comparator): 根据比较器指定的规则排序
         */
        Stream<Integer> stream = Stream.of(33, 22, 11, 55);

        // 对元素自然顺序排序
        // stream.sorted().forEach(System.out::println);

        // 使用比较器排序
        /*stream.sorted((Integer i1, Integer i2) -> {
            return i2 - i1;
        }).forEach(System.out::println);*/

        // 使用lambda表达与方法引用
        stream.sorted((i1, i2) -> i2 - i1).forEach(System.out::println);

        // 示例2：使用 sorted 方法对输出的 10 个随机数进行排序
        new Random().ints().limit(10).sorted().forEach(System.out::println);

        // 示例3：字符串排序。String 类自身已实现Compareable接口
        List<String> strList = Arrays.asList("dd", "ff", "aa")
                .stream()
                .sorted()
                .collect(Collectors.toList());
        System.out.println(strList);

        // 示例4：对象自定义排序：先按姓名升序，姓名相同则按年龄升序
        Person p1 = new Person("石原里美", 31);
        Person p2 = new Person("新垣结衣", 28);
        Person p3 = new Person("敌法师", 180);
        Person p4 = new Person("新月", 18);
        List<Person> persons = Arrays.asList(p1, p2, p3, p4).stream().sorted((o1, o2) -> {
            if (o1.getName().startsWith(o2.getName().substring(0, 1))) {
                return o1.getAge() - o2.getAge();
            } else {
                return o1.getName().compareTo(o2.getName());
            }
        }).collect(Collectors.toList());
        System.out.println(persons);
    }

    /* Stream流 - distinct方法示例测试 */
    @Test
    public void distinctTest() {
        // 示例1：基本类型集合去重
        List<Integer> integerList = Stream.of(22, 33, 22, 11, 33)
                .distinct()
                .collect(Collectors.toList());
        System.out.println(integerList);

        List<String> stringList = Stream.of("aa", "bb", "aa", "bb", "cc")
                .distinct()
                .collect(Collectors.toList());
        System.out.println(stringList);

        // 示例2：对象集合去重。引用对象必须要重写hashCode和equal方法，否则去重无效。
        List<Person> persons = Stream.of(
                new Person("新垣结衣", 18),
                new Person("石原里美", 30),
                new Person("夜神月", 16),
                new Person("新垣结衣", 18),
                new Person("石原里美", 30),
                new Person("L", 17)
        ).distinct().collect(Collectors.toList());
        System.out.println(persons);
    }

    /* Stream流 - anyMatch、allMatch、noneMatch方法示例测试 */
    @Test
    public void matchTest() {
        // 定义集合
        List<Integer> list = Arrays.asList(5, 3, 6, 1);

        // allMatch: 匹配所有元素，所有元素都需要满足条件
        boolean allMatch = list.stream().allMatch(i -> i > 2);
        System.out.println(allMatch);
        // anyMatch: 匹配某个元素，只要有其中一个元素满足条件即可
        boolean anyMatch = list.stream().anyMatch(i -> i > 5);
        System.out.println(anyMatch);
        // noneMatch: 匹配所有元素，所有元素都不满足条件
        boolean noneMatch = list.stream().noneMatch(i -> i < 0);
        System.out.println(noneMatch);
    }

    /* Stream流 - findAny、findFirst方法示例测试 */
    @Test
    public void findTest() {
        // 定义集合
        List<Integer> list = Arrays.asList(33, 11, 5, 22);

        // findFirst:
        Optional<Integer> firstElement = list.stream().findFirst();
        System.out.println("findFirst()方法获取集合的第一个元素: " + firstElement.orElseThrow(() -> new NullPointerException("no element")));
        // findAny:
        Optional<Integer> anyElement = list.stream().findAny();
        System.out.println("findAny()方法获取集合的第一个元素: " + anyElement.orElseThrow(() -> new NullPointerException("no element")));
    }

    /* Stream流 - max、min方法示例测试 */
    @Test
    public void maxAndMinTest() {
        // 定义集合
        List<Integer> list = Arrays.asList(5, 3, 6, 1);

        // 获取最大值
        Optional<Integer> max = list.stream().max((o1, o2) -> o1 - o2);
        System.out.println("最大值: " + max.get());

        // 获取最小值
        Optional<Integer> min = list.stream().min(Integer::compareTo);
        System.out.println("最小值: " + min.get());
    }

    /* Stream流 - reduce方法示例测试 */
    @Test
    public void reduceTest() {
        // 定义集合
        List<Integer> list = Arrays.asList(4, 5, 3, 9);

        /*
         * T reduce(T identity, BinaryOperator<T> accumulator);
         *  第1个参数identity：方法执行时初始值，首次执行的时候，会赋值给accumulator参数函数的第一个入参
         *  第2个参数accumulator：流每次处理数据的逻辑
         *
         * 示例中reduce方法的执行流程：
         *  第一次, 将默认值赋值给x, 取出集合第一元素赋值给y
         *  第二次, 将上一次返回的结果赋值x, 取出集合第二元素赋值给y
         *  第三次, 将上一次返回的结果赋值x, 取出集合第三元素赋值给y
         *  第四次, 将上一次返回的结果赋值x, 取出集合第四元素赋值给y
         */
        // 使用reduce方法求和
        int result = list.stream().reduce(0, (x, y) -> {
            System.out.println("x = " + x + ", y = " + y);
            return x + y;
        });
        System.out.println("result = " + result); // 21

        // 使用reduce方法获取最大值
        Integer max = list.stream().reduce(0, (x, y) -> x > y ? x : y);
        System.out.println("max = " + max);

        // 字符串连接，concat = "ABCD"
        String concatString = Stream.of("A", "B", "C", "D").reduce("", String::concat);
        System.out.println("concatString = " + concatString);

        // 求最小值，minValue = -3.0
        double minValue = Stream.of(-1.5, 1.0, -3.0, -2.0).reduce(Double.MAX_VALUE, Double::min);
        System.out.println("minValue = " + minValue);

        // 求和，sumValue = 11, 有起始值
        int sumValue = Stream.of(1, 2, 3, 4).reduce(1, Integer::sum);
        System.out.println("sumValue = " + sumValue);

        // 求和，sumValue = 10, 无起始值
        sumValue = Stream.of(1, 2, 3, 4).reduce(Integer::sum).get();
        System.out.println("sumValue = " + sumValue);

        // 过滤，字符串连接，concatString = "ace"
        concatString = Stream.of("a", "B", "c", "D", "e", "F")
                .filter(x -> x.compareTo("Z") > 0)
                .reduce("", String::concat);
        System.out.println("concatString = " + concatString);

        //经过测试，当元素个数小于24时，并行时线程数等于元素个数，当大于等于24时，并行时线程数为16
        List<Integer> testList = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24);

        Integer v = testList.stream().reduce((x1, x2) -> x1 + x2).get();
        System.out.println(v);   // 300

        // 可以使用方法引用简化lambda表达式。Integer类的static int sum(int a, int b)静态方法等价于(x1, x2) -> x1 + x2
        Integer v1 = testList.stream().reduce(10, Integer::sum);
        System.out.println(v1);  // 310

        Integer v2 = testList.stream().reduce(0,
                (x1, x2) -> {
                    System.out.println("stream accumulator: x1:" + x1 + "  x2:" + x2);
                    return x1 - x2;
                },
                (x1, x2) -> {
                    System.out.println("stream combiner: x1:" + x1 + "  x2:" + x2);
                    return x1 * x2;
                });
        System.out.println(v2); // -300

        Integer v3 = testList.parallelStream().reduce(0,
                (x1, x2) -> {
                    System.out.println("parallelStream accumulator: x1:" + x1 + "  x2:" + x2);
                    return x1 - x2;
                },
                (x1, x2) -> {
                    System.out.println("parallelStream combiner: x1:" + x1 + "  x2:" + x2);
                    return x1 * x2;
                });
        System.out.println(v3); // -775946240
    }

    /* Stream流 - map与reduce方法配合使用示例测试 */
    @Test
    public void mapAndReduceTest() {
        // 准备测试的集合数据
        List<Person> persons = Arrays.asList(
                new Person("新垣结衣", 18),
                new Person("夜神月", 16),
                new Person("石原里美", 30),
                new Person("L", 17)
        );

        /*
         * 示例1：求出所有年龄的总和
         *   1.得到所有的年龄
         *   2.让年龄相加
         */
        Integer totalAge = persons.stream()
                .map(p -> p.getAge())
                .reduce(0, Integer::sum); // Integer类有sum方法，相当于(x, y) -> x + y
        System.out.println("totalAge = " + totalAge);

        /*
         * 示例2：求出最大年龄
         *  1.得到所有的年龄
         *  2.获取最大的年龄
         */
        Integer maxAge = persons.stream()
                .map(Person::getAge) // 使用方法引用简化lambda表达式，类名::引用成员方法 相当于 p -> p.getAge()
                .reduce(0, Math::max); // Math类有max方法，相当于(a, b) -> (a >= b) ? a : b
        System.out.println("maxAge = " + maxAge);

        // 示例3：统计a出现的次数。实现思路：将要统计的元素转成数值1，其他元素转成0，然后将所有1相加即为元素出现的次数
        //                         1    0    0    1    0    1
        Integer count = Stream.of("a", "c", "b", "a", "b", "a")
                .map(s -> {
                    if ("a".equalsIgnoreCase(s)) {
                        return 1;
                    } else {
                        return 0;
                    }
                })
                .reduce(0, Integer::sum);
        System.out.println("count = " + count);
    }

    /* Stream流 - mapToInt方法示例测试 */
    @Test
    public void mapToIntTest() {
        List<Integer> list = Arrays.asList(1, 2, 3, 4, 5);

        // 示例：把大于3的打印出来
        // Integer占用的内存比int多,在Stream流操作中会自动装箱和拆箱
        list.stream().filter(n -> n > 3)
                .forEach(System.out::println);

        /*
         * IntStream mapToInt(ToIntFunction<? super T> mapper);
         *  IntStream：内部操作的是int类型的数据，可以节省内存，减少自动装箱和拆箱的操作
         */
        // IntStream intStream = list.stream().mapToInt((Integer n) -> n.intValue());
        IntStream intStream = list.stream().mapToInt(Integer::intValue); // 使用方法引用简化代码
        intStream.filter(n -> n > 3).forEach(System.out::println);
    }

    /* Stream流 - concat方法示例测试 */
    @Test
    public void concatTest() {
        Stream<String> streamA = Stream.of("夜神月", "L", "斩月");
        Stream<String> streamB = Stream.of("石原里美", "新垣结衣");

        // 合并成一个流
        Stream<String> newStream = Stream.concat(streamA, streamB);
        // 注意：合并流之后，之前的流都已关闭，如果再次操作会出现异常
        // streamA.forEach(System.out::println);

        newStream.forEach(System.out::println);
    }

}
