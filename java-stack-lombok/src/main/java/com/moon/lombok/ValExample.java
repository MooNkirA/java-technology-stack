package com.moon.lombok;

import lombok.val;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * val demo
 *
 * @author MoonZero
 * @version 1.0
 * @date 2019-11-7 14:25
 * @description val 用在局部变量前面，相当于将变量声明为 final
 */
public class ValExample {
    public String example() {
        val example = new ArrayList<String>();
        example.add("Hello, World!");
        val foo = example.get(0);
        return foo.toLowerCase();
    }

    public void example2() {
        val map = new HashMap<Integer, String>();
        map.put(0, "zero");
        map.put(5, "five");
        for (val entry : map.entrySet()) {
            System.out.printf("%d: %s\n", entry.getKey(), entry.getValue());
        }
    }
}
