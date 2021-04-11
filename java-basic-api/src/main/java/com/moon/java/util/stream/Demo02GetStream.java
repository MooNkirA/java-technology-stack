package com.moon.java.util.stream;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.regex.Pattern;
import java.util.stream.Stream;

/**
 * JDK8 新特性 Stream - 获取stream流常用方式的示例
 *
 * @author MooNkirA
 * @version 1.0
 * @date 2020-9-26 11:16
 * @description
 */
public class Demo02GetStream {

    @Test
    public void getStreamTest() {
        // 方式1：根据Collection接口的获取流
        // Collection接口中有一个默认的方法: default Stream<E> stream()
        List<String> list = new ArrayList<>();
        Stream<String> stream1 = list.stream();

        Set<String> set = new HashSet<>();
        Stream<String> stream2 = set.stream();

        // java.util.Map 接口不是 Collection 的子接口，所以获取对应的流需要分key、value或entry等情况：
        Map<String, String> map = new HashMap<>();
        Stream<String> stream3 = map.keySet().stream();
        Stream<String> stream4 = map.values().stream();
        Stream<Map.Entry<String, String>> stream5 = map.entrySet().stream();

        // 创建并行的流，parallelStream是流并行处理程序的代替方法
        Stream<String> stream6 = list.parallelStream();

        // 方式2 : Stream中的静态方法of、iterate、generate获取流
        // static<T> Stream<T> of(T... values)
        Stream<String> stream7 = Stream.of("aa", "bb", "cc");
        stream7.forEach(System.out::println);

        String[] strs = {"aa", "bb", "cc"};
        Stream<String> stream8 = Stream.of(strs);
        stream8.forEach(System.out::println);

        // 基本数据类型的数组行不行?不行的,会将整个数组看做一个元素进行操作.
        int[] arr = {11, 22, 33};
        Stream<int[]> stream9 = Stream.of(arr);
        stream9.forEach(System.out::println); // [I@2a33fae0

        // 使用iterate方法获取流
        Stream<Integer> stream10 = Stream.iterate(0, (x) -> x + 2).limit(6);
        stream10.forEach(System.out::println); // 0 2 4 6 8 10

        // 使用generate获取流
        Stream<Double> stream11 = Stream.generate(Math::random).limit(2);
        stream11.forEach(System.out::println);

        // 方式3：使用Arrays.stream()将数组转成流
        Integer[] nums = new Integer[10];
        Stream<Integer> stream12 = Arrays.stream(nums);
        stream12.forEach(System.out::println);

        // 方式4：BufferedReader.lines()，处理将每行内容转成流
        /*BufferedReader reader = new BufferedReader(new FileReader("F:\\test_stream.txt"));
        Stream<String> lineStream = reader.lines();
        lineStream.forEach(System.out::println);*/

        // 方式5：Pattern.splitAsStream()，将字符串分隔成流
        Pattern pattern = Pattern.compile(",");
        Stream<String> stringStream = pattern.splitAsStream("a,b,c,d");
        stringStream.forEach(System.out::println);
    }

}
