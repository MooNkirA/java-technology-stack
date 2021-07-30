package com.moon.java.beans;

import com.moon.common.model.Student;
import org.junit.Test;

import java.beans.BeanInfo;
import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.beans.MethodDescriptor;
import java.beans.PropertyDescriptor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

/**
 * Introspector 是提供了了解目标 Java bean 支持的属性，事件和方法的标准方法的工具类
 *
 * @author MooNkirA
 * @version 1.0
 * @date 2021-07-15 14:22
 * @description
 */
public class IntrospectorDemo {

    /*
     * 获取类信息。
     * 在 Java Bean 上进行内省，了解其所有属性、公开的方法和事件。
     */
    @Test
    public void testGetBeanInfo() throws IntrospectionException {
        BeanInfo beanInfo = Introspector.getBeanInfo(Student.class);
        // 获取所有类方法信息
        MethodDescriptor[] methodDescriptors = beanInfo.getMethodDescriptors();
        for (MethodDescriptor methodDescriptor : methodDescriptors) {
            System.out.println(Student.class.getSimpleName() + "类的方法：" + methodDescriptor.getName());
        }

        // 获取所有类属性信息
        PropertyDescriptor[] propertyDescriptors = beanInfo.getPropertyDescriptors();
        for (PropertyDescriptor propertyDescriptor : propertyDescriptors) {
            String propertyName = propertyDescriptor.getName();
            System.out.println("属性名称：" + propertyName);
            Optional.ofNullable(propertyDescriptor.getReadMethod()).ifPresent(getterMethod -> {
                System.out.println(propertyName + "属性的getter方法是：" + getterMethod.getName());
            });
            Optional.ofNullable(propertyDescriptor.getWriteMethod()).ifPresent(setterMethod -> {
                System.out.println(propertyName + "属性的setter方法是：" + setterMethod.getName());
            });
        }
    }

    /*
     * 使用 Introspector 的获取 BeanInfo 的方式实现 Map 转java bean
     */
    @Test
    public void testMapToBeanByIntrospector() throws IntrospectionException, InvocationTargetException, IllegalAccessException, InstantiationException {
        Map<String, Object> map = new HashMap<>();
        map.put("name", "MooN");
        map.put("age", 22);
        map.put("publicField", "zero");
        map.put("noThisField", "zero"); // 设置没有的属性

        // 获取需要转换的类信息
        BeanInfo beanInfo = Introspector.getBeanInfo(Student.class);
        Student student = Student.class.newInstance();
        // 循环获取类所有属性
        for (PropertyDescriptor propertyDescriptor : beanInfo.getPropertyDescriptors()) {
            // 属性的名称
            String fieldName = propertyDescriptor.getName();
            // 获取属性相应的setter方法
            Method setterMethod = propertyDescriptor.getWriteMethod();
            // 判断 map 是否包含 bean 字段
            if (setterMethod != null && map.containsKey(fieldName)) {
                // 判断是否私有
                if (!setterMethod.isAccessible()) {
                    setterMethod.setAccessible(true);
                }
                // 调用set方法
                setterMethod.invoke(student, map.get(fieldName));
            }
        }

        // 转换后的bean实例（注意：Student 类中的 publicField 属性没有set方法）
        System.out.println(student);
    }

}
