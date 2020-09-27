package com.moon.java.jdk8stream;

import com.moon.java.common.model.Student;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.IntSummaryStatistics;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * JDK8 新特性 Stream - 收集操作collect方法使用示例
 *
 * @author MooNkirA
 * @version 1.0
 * @date 2020-9-27 14:01
 * @description
 */
public class Demo05Collect {

    // 定义用于测试的集合
    private static final List<String> LIST = new ArrayList<>();
    private static final List<Student> STUDENTS = new ArrayList<>();

    static {
        Collections.addAll(LIST, "天锁斩月", "L", "夜神月", "樱木花道", "宇智波鼬", "金田一一", "乌尔奇奥拉·西法", "L");
        Collections.addAll(STUDENTS,
                new Student("石原里美", 23, 95),
                new Student("樱庭奈奈美", 18, 34),
                new Student("长泽雅美", 23, 45),
                new Student("新垣结衣", 18, 88)
        );
    }

    /* collect收集操作 - 将流中数据收集到集合 */
    @Test
    public void streamToCollectionTest() {
        // 将流中的数据收集到List集合
        List<String> list = LIST.stream().collect(Collectors.toList());
        System.out.println("list: " + list);

        // 将流中的数据收集到Set集合
        Set<String> set = LIST.stream().collect(Collectors.toSet());
        System.out.println("set: " + set);

        // 将流中的数据收集到指定的ArrayList类型中
        ArrayList<String> arrayList = LIST.stream()
                .collect(Collectors.toCollection(ArrayList::new));
        System.out.println("arrayList: " + arrayList);

        // 将流中的数据收集到指定的ArrayList类型中
        HashSet<String> hashSet = LIST.stream().collect(Collectors.toCollection(HashSet::new));
        System.out.println("hashSet: " + hashSet);
    }

    /* collect收集操作 - 将流中数据收集到数组(相当于数据库中的聚合函数) */
    @Test
    public void streamToArrayTest() {
        // 将流中的数据收集到数组中，默认收到到Object类型的数组中
        Object[] objects = LIST.stream().toArray();
        for (Object object : objects) {
            System.out.println("objectArray element: " + object);
        }

        // 将流中的数据收集到指定类型的数组中
        String[] strArray = LIST.stream().toArray(String[]::new);
        for (String str : strArray) {
            System.out.println("strArray element: " + str + ", string length: " + str.length());
        }
    }

    /* collect收集操作 - 对流中数据进行聚合计算(相当于数据库中的聚合函数) */
    @Test
    public void streamToPolymerizationTest() {
        // 使用Collectors.maxBy()方法在收集流数据时获取最大值，获取学生分数的最大值
        Optional<Student> max = STUDENTS.stream()
                .collect(Collectors.maxBy((o1, o2) -> o1.getSocre() - o2.getSocre()));
        System.out.println("分数Socre最大值: " + max.orElseThrow(NullPointerException::new));

        // 使用Collectors.minBy()方法在收集流数据时获取最小值，获取学生分数最小值
        Optional<Student> min = STUDENTS.stream()
                .collect(Collectors.minBy((o1, o2) -> o1.getSocre() - o2.getSocre()));
        System.out.println("分数Socre最小值: " + min.orElseThrow(NullPointerException::new));

        // 使用Collectors.summingInt()方法在收集流数据时获取总和，获取学生年龄的总和
        Integer sum = STUDENTS.stream()
                // .collect(Collectors.summingInt(o -> o.getAge()));
                .collect(Collectors.summingInt(Student::getAge)); // 使用方法引用优化
        System.out.println("年龄总和: " + sum);

        // 使用Collectors.averagingInt()方法在收集流数据时获取平均值，获取学生分数的平均值
        Double avg = STUDENTS.stream()
                .collect(Collectors.averagingInt(Student::getSocre));
        System.out.println("分数平均值: " + avg);

        // 使用Collectors.counting()方法在收集流数据时获取数量，获取学生人数统计数量
        Long count = STUDENTS.stream().collect(Collectors.counting());
        System.out.println("统计学生数量: " + count);

        // Collectors.summarizingInt()聚合操作汇总的方法，可以实现以上所有操作
        IntSummaryStatistics statistics = STUDENTS.stream()
                .collect(Collectors.summarizingInt(Student::getSocre));
        System.out.println(
                String.format("max: %d, min: %d, sum: %d, avg: %s, count: %d",
                        statistics.getMax(),
                        statistics.getMin(),
                        statistics.getSum(),
                        statistics.getAverage(),
                        statistics.getCount()
                )
        );
    }

    /* collect收集操作 - 对流中数据进行分组 */
    @Test
    public void streamToGroupingByTest() {
        // 示例1：根据学生的年龄分组
        Map<Integer, List<Student>> ageGroupMap = STUDENTS.stream()
                .collect(Collectors.groupingBy(Student::getAge));
        /*
         * 在JDK8，Map提供一个新的API，用于Map数据的遍历
         *   default void forEach(BiConsumer<? super K, ? super V> action)
         */
        ageGroupMap.forEach((k, v) -> System.out.println(k + "::" + v));

        // 示例2：根据分数分组。将分数大于60的分为一组。小于60分成另一组
        Map<String, List<Student>> socreGroupMap = STUDENTS.stream().collect(Collectors.groupingBy(s -> {
            if (s.getSocre() > 60) {
                return "及格";
            } else {
                return "不及格";
            }
        }));
        socreGroupMap.forEach((k, v) -> System.out.println(k + "::" + v));
    }

    /* collect收集操作 - 对流中数据进行多级分组 */
    @Test
    public void streamToMultiGroupingByTest() {
        /*
         * 示例1：根据学生的年龄分组后，再根据分数分组
         *  使用到Collectors工具类的groupingBy重载方法
         *  public static <T, K, A, D> Collector<T, ?, Map<K, D>> groupingBy(Function<? super T, ? extends K> classifier,
         *                                 Collector<? super T, A, D> downstream)
         */
        Map<Integer, Map<String, List<Student>>> map = STUDENTS.stream()
                .collect(Collectors.groupingBy(Student::getAge, Collectors.groupingBy(s -> {
                    if (s.getSocre() > 60) {
                        return "及格";
                    } else {
                        return "不及格";
                    }
                })));

        // 遍历多级map
        map.forEach((k, v) -> {
            // 输出第一次分组的key
            System.out.println(k);
            // 遍历第二层map
            v.forEach((k2, v2) -> System.out.println("\t" + k2 + " == " + v2));
        });
    }

    /* collect收集操作 - 对流中数据进行分区 */
    @Test
    public void partitioningByTest() {
        // 示例1：根据学生的分数进行分区
        // partitioningBy会根据值是否为true，把集合分割为两个列表，一个true列表，一个false列表。
        Map<Boolean, List<Student>> map = STUDENTS.stream()
                .collect(Collectors.partitioningBy(s -> s.getSocre() > 60));
        // 遍历map
        map.forEach((k, v) -> System.out.println(k + " :: " + v));
    }

    /* collect收集操作 - 对流中字符串数据进行拼接 */
    @Test
    public void joiningTest() {
        // 示例1：将学生的姓名进去拼接
        // 1. 直接将所有元素进行拼接
        String names1 = STUDENTS.stream().map(Student::getName)
                .collect(Collectors.joining());
        System.out.println("字符直接拼接：" + names1);

        // 2. 指定连接符，将每个元素之间连接符拼接
        String names2 = STUDENTS.stream().map(Student::getName)
                .collect(Collectors.joining("_"));
        System.out.println("字符与连接符拼接：" + names2);

        // 3. 指定连接符、前缀、后缀，将每个元素之间连接符拼接再加上前后缀
        String names3 = STUDENTS.stream().map(Student::getName)
                .collect(Collectors.joining("_", "[", "]"));
        System.out.println("字符与连接符、前后缀拼接：" + names3);
    }

}
