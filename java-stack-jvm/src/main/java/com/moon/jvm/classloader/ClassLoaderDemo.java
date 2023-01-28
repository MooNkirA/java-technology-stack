package com.moon.jvm.classloader;

import sun.net.spi.nameservice.dns.DNSNameService;

/**
 * ClassLoader 相关示例
 *
 * @author MooNkirA
 * @version 1.0
 * @date 2022-12-20 21:36
 * @description
 */
public class ClassLoaderDemo {

    public static void main(String[] args) throws Exception {
        ClassLoader cl1 = Object.class.getClassLoader();
        // Object 类是由引导类加载器加载，因此输出为 null
        System.out.println("cl1:" + cl1);

        // DNSNameService类位于 dnsns.jar 包中，它存在于 jre/lib/ext 目录下
        ClassLoader cl = DNSNameService.class.getClassLoader();
        System.out.println(cl); // 打印结果sun.misc.Launcher$ExtClassLoader

        // 平常编写的 Java 类是使用的应用类加载器
        ClassLoader classLoader = ClassLoaderDemo.class.getClassLoader();
        System.out.println(classLoader); // sun.misc.Launcher$AppClassLoader
        System.out.println("-----------------");

        // 演示类加载器的父子关系
        ClassLoader loader = ClassLoaderDemo.class.getClassLoader();
        while (loader != null) {
            System.out.println(loader);
            loader = loader.getParent();
        }
    }

}
