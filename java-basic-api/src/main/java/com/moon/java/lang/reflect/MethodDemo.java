package com.moon.java.lang.reflect;

import com.moon.common.model.Student;
import org.junit.Test;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;

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

}
