package com.moon.jvm.classloader;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * 自定义文件类加载器，需要继承 ClassLoader 抽象类
 *
 * @author MooNkirA
 * @version 1.0
 * @date 2022-12-22 21:53
 * @description
 */
public class MyFileClassLoader extends ClassLoader {

    private String directory; // 被加载的类所在的目录

    // 父类加载器：AppClassLoader系统类加载器
    public MyFileClassLoader(String directory) {
        super();
        this.directory = directory;
    }

    // 指定要加载的类所在的文件目录
    public MyFileClassLoader(String directory, ClassLoader parent) {
        super(parent);
        this.directory = directory;
    }

    /**
     * 覆盖 findClass 方法，并使用 defineClass 返回 Class 对象
     *
     * @param name
     * @return
     * @throws ClassNotFoundException
     */
    @Override
    protected Class<?> findClass(String name) throws ClassNotFoundException {
        try {
            // 包名转换为目录
            StringBuilder sb = new StringBuilder();
            sb.append(directory).append(File.separator).append(name.replace(".", File.separator)).append(".class");
            String file = sb.toString();

            // 构建输入流
            InputStream in = new FileInputStream(file);
            // 构建输出流:ByteArrayOutputStream
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            // 读取文件
            int len = -1;//读取到的数据的长度
            byte[] buf = new byte[2048];//缓存
            while ((len = in.read(buf)) != -1) {
                baos.write(buf, 0, len);
            }
            byte[] data = baos.toByteArray();
            in.close();
            baos.close();
            return defineClass(name, data, 0, data.length);
        } catch (IOException e) {
            System.out.println(e);
            return null;
        }
    }

    // 测试
    public static void main(String[] args) throws Exception {
        MyFileClassLoader cl = new MyFileClassLoader("d:/");
        Class<?> aClass = cl.loadClass("com.moon.Demo");
        aClass.newInstance();

        /* 热部署实现示例 */
        MyFileClassLoader myFileClassLoader1 = new MyFileClassLoader("d:/", null);
        MyFileClassLoader myFileClassLoader2 = new MyFileClassLoader("d:/", myFileClassLoader1);
        Class clazz1 = myFileClassLoader1.loadClass("com.moon.Demo");
        Class clazz2 = myFileClassLoader2.loadClass("com.moon.Demo");
        System.out.println("class1:" + clazz1.hashCode());
        System.out.println("class2:" + clazz2.hashCode());
        // 结果:class1和class2的hashCode一致

        MyFileClassLoader myFileClassLoader3 = new MyFileClassLoader("d:/", null);
        MyFileClassLoader myFileClassLoader4 = new MyFileClassLoader("d:/", myFileClassLoader3);
        Class clazz3 = myFileClassLoader3.findClass("com.moon.Demo");
        Class clazz4 = myFileClassLoader4.findClass("com.moon.Demo");
        System.out.println("class3:" + clazz3.hashCode());
        System.out.println("class4:" + clazz4.hashCode());
        // 结果：class1和class2的hashCode不一致
    }
}
