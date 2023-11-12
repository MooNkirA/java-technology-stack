package com.moon.lombok;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.util.StringJoiner;

/**
 * Accessors 注解
 *
 * @author MooNkirA
 * @version 1.0
 * @date 2023-09-07 19:28
 * @description
 */
// 使用 @Accessors 注解，设置 chain = true，生成返回 this 的 setter 方法
@Accessors(chain = true)
@Getter
@Setter
public class AccessorsDemo {

    // 定义必传属性，使用 final 修饰，不提供 setter 方法
    private final int studentId; // 学生ID
    private final int grade; // 年级
    private final int classNum; // 班级

    // 定义选填属性，提供 setter 方法
    private String name; // 姓名
    private String gender; // 性别
    private String address; // 住址

    // 定义构造方法，接收必传参数
    public AccessorsDemo(int studentId, int grade, int classNum) {
        this.studentId = studentId;
        this.grade = grade;
        this.classNum = classNum;
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", AccessorsDemo.class.getSimpleName() + "[", "]")
                .add("studentId=" + studentId)
                .add("grade=" + grade)
                .add("classNum=" + classNum)
                .add("name='" + name + "'")
                .add("gender='" + gender + "'")
                .add("address='" + address + "'")
                .toString();
    }

    // 使用示例
    public static void main(String[] args) {
        AccessorsDemo demo = new AccessorsDemo(1001, 3, 8) // 创建一个学生对象，传入必传参数
                .setName("MooN") // 设置姓名
                .setGender("男") // 设置性别
                .setAddress("广州市天河区"); // 设置住址
        System.out.println(demo);
    }
}
