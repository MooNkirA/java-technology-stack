package com.moon.java.lang.reflect;

import com.moon.common.model.Bean1;
import com.moon.common.model.Bean2;
import com.moon.common.model.Student;
import org.junit.Test;
import org.springframework.core.LocalVariableTableParameterNameDiscoverer;
import org.springframework.core.StandardReflectionParameterNameDiscoverer;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.util.Arrays;

/**
 * Method 类测试示例
 *
 * @author MooNkirA
 * @version 1.0
 * @date 2021-07-15 10:35
 * @description
 */
public class MethodDemo {

    @Test
    public void testMethodBasic() throws Exception {
        // 获取Student的Class对象，直接类名.class;
        Class<Student> c = Student.class;
        // 反射获取实例
        Student student = c.newInstance();
        System.out.println("首次反射获取的实例：" + student.toString());
        // 获取所有的成员变量(包含private修饰)
        Field[] declaredFields = c.getDeclaredFields();

        for (Field field : declaredFields) {
            // 获取字段名称
            String fieldName = field.getName();
            // 字段的类型
            String typeName = field.getType().getTypeName();
            if (field.isAccessible()) {
                if (String.class.getTypeName().equals(typeName)) {
                    field.set(student, fieldName + " :: is public field");
                } else if (int.class.getTypeName().equals(typeName)) {
                    field.set(student, 123);
                }
            } else {
                field.setAccessible(true);
                if (String.class.getTypeName().equals(typeName)) {
                    field.set(student, fieldName + " :: is private field");
                } else if (int.class.getTypeName().equals(typeName)) {
                    field.set(student, 999);
                }
            }
        }

        System.out.println("反射设置属性后：" + student.toString());
    }

    /*
     * 测试编译时添加了 `-parameters` 参数，可以生成参数表，通过反射就可以拿到方法参数名
     *  如：javac -parameters .\Bean1.java
     *      javap -c -v .\Bean1.class
     */
    @Test
    public void testGetMethodArgumentName() throws Exception {
        // 反射获取普通中方法参数名
        Method foo = Bean1.class.getMethod("foo", String.class, int.class);
        for (Parameter parameter : foo.getParameters()) {
            System.out.println(parameter);
        }

        // 反射获取接口中方法参数名
        Method foo2 = Bean2.class.getMethod("foo", String.class, int.class);
        for (Parameter parameter : foo2.getParameters()) {
            System.out.println(parameter);
        }
    }

    /*
     * 测试编译时添加了 -g 参数，可以生成调试信息 (大部分 IDE 编译时都会自动加 -g 参数)
     * 如：javac -g .\Bean1.java
     *     javap -c -v .\Bean1.class
     *  对于普通类，会包含局部变量表，用 asm 技术可以拿到方法参数名
     *  对于接口，不会包含局部变量表，无法获取方法参数名。
     */
    @Test
    public void testGetMethodArgumentName2() throws Exception {
        // 使用 asm 技术获取普通中方法参数名，示例不使用 asm 原生的，而使用 Spring 框架封装好的工具类
        Method foo = Bean1.class.getMethod("foo", String.class, int.class);

        // 基于 LocalVariableTable 本地变量表
        LocalVariableTableParameterNameDiscoverer discoverer = new LocalVariableTableParameterNameDiscoverer();
        String[] parameterNames = discoverer.getParameterNames(foo);
        System.out.println(Arrays.toString(parameterNames));
    }

}
