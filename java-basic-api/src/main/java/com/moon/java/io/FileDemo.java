package com.moon.java.io;

import org.junit.Test;

import java.io.File;
import java.util.Arrays;

/**
 * File 类基础 API 示例
 *
 * @author MooNkirA
 * @version 1.0
 * @date 2021-06-14 12:57
 * @description
 */
public class FileDemo {

    /**
     * 获取 E 盘 aaa 文件夹中 b.txt 文件的文件名，文件大小，文件的绝对路径和父路径等信息，并将信息输出在控制台。
     */
    @Test
    public void testGetFileInfo() {
        // 获取 E 盘 aaa 文件夹中 b.txt对象
        File file = new File("e:\\aaa\\b.txt");
        // 判断对象是否文件
        System.out.println("对象是否为文件：" + file.isFile());
        // 获取文件的大小,单位：字节
        System.out.println("对象文件的大小 ：" + file.length());
        // 获取文件名
        System.out.println("对象文件的名称：" + file.getName());
        // 获取文件的绝对路径
        System.out.println("对象的绝对路径是：" + file.getAbsolutePath());
        // 获取父路径信息
        System.out.println("对象的父路径是：" + file.getParent());
        // 获取路径
        System.out.println("对象的路径是(用什么方式创建的对象,就返回什么方式的路径)：" + file.getPath());

        File file2 = new File("qq.txt");
        // 获取这个相对路径的对象的绝对路径
        System.out.println("相对路径对象的绝对路径是：" + file2.getAbsolutePath());
    }

    /**
     * 测试获取文件列表
     */
    @Test
    public void testGetFileList() {
        // 创建文件夹对象
        File file = new File("E:\\00-Downloads\\test\\");
        String[] list = file.list();
        // 输出该文件夹里的文件列表
        System.out.println(Arrays.toString(list));

        // 创建文件对象（非文件夹）
        File file2 = new File("E:\\00-Downloads\\a.txt");
        String[] list2 = file2.list();
        // 输出null
        System.out.println(Arrays.toString(list2));
    }

}
