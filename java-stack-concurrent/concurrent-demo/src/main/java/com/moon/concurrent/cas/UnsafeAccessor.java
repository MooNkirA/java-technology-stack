package com.moon.concurrent.cas;

import com.moon.concurrent.entity.Student;
import sun.misc.Unsafe;

import java.lang.reflect.Field;

/**
 * 获取 Unsafe 工具类
 *
 * @author MooNkirA
 * @version 1.0
 * @date 2023-02-22 22:37
 * @description
 */
public class UnsafeAccessor {

    static Unsafe unsafe;

    static {
        try {
            Field theUnsafe = Unsafe.class.getDeclaredField("theUnsafe");
            theUnsafe.setAccessible(true);
            unsafe = (Unsafe) theUnsafe.get(null);
        } catch (NoSuchFieldException | IllegalAccessException e) {
            throw new Error(e);
        }
    }

    static Unsafe getUnsafe() {
        return unsafe;
    }

    // 测试
    public static void main(String[] args) throws NoSuchFieldException {
        Unsafe unsafe = UnsafeAccessor.getUnsafe();
        Field id = Student.class.getDeclaredField("id");
        Field name = Student.class.getDeclaredField("name");

        // 获得成员变量的偏移量
        long idOffset = UnsafeAccessor.unsafe.objectFieldOffset(id);
        long nameOffset = UnsafeAccessor.unsafe.objectFieldOffset(name);
        Student student = new Student();

        // 使用 cas 方法替换成员变量的值
        UnsafeAccessor.unsafe.compareAndSwapInt(student, idOffset, 0, 20); // 返回 true
        UnsafeAccessor.unsafe.compareAndSwapObject(student, nameOffset, null, "张三"); // 返回 true
        System.out.println(student); // 输出：Student{id=20, name='张三'}
    }
}
