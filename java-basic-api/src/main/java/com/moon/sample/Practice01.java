package com.moon.sample;

import java.util.Random;

/**
 * 练习题
 *
 * @author MooNkirA
 * @version 1.0
 * @date 2022-10-21 23:15
 * @description
 */
public class Practice01 {

    // 定义打印遍历数组的方法
    public static void printArr(int[] arr) {
        System.out.print("[");
        for (int i = 0; i < arr.length; i++) {
            if (i < arr.length - 1) {
                System.out.print(arr[i] + ", ");
            } else {
                System.out.print(arr[i] + "]");
            }
        }
    }

    // 定义一个数组反转功能的方法
    public static void reverse(int[] arr) {
        for (int i = 0, j = arr.length - 1; i <= j; i++, j--) {
            int temp = arr[i];
            arr[i] = arr[j];
            arr[j] = temp;
        }
        // 调用打印数组的方法
        printArr(arr);
    }


    // 定义遍历数组获取最大值 
    public static int getMax(int[] arr) {
        int max = arr[0];
        for (int i = 1; i < arr.length; i++) {
            if (max < arr[i]) {
                max = arr[i];
            }
        }
        return max;
    }

    // 定义遍历数组获取最小值 
    public static int getMin(int[] arr) {
        int min = arr[0];
        for (int i = 1; i < arr.length; i++) {
            if (min > arr[i]) {
                min = arr[i];
            }
        }
        return min;
    }


    // 每2个数字换行，每3个数字再换行一次
    public static void test01() {
        int[] arr = new int[100];
        int count = 0;
        for (int i = 0; i < arr.length; i++) {
            arr[i] = i;
            System.out.print(arr[i] + " ");
            count++;
            if (count == 5 || count == 2) {
                System.out.println();
            }
            if (count == 5) {
                count = 0;
            }
        }
    }


    // 随机生成不重复的数值
    public static void test02() {
        Random ran = new Random();
        int[] arr = new int[10];

        // 给arr数组传入不重复的整数
        for (int i = 0; i < arr.length; i++) {
            arr[i] = ran.nextInt(100) + 1;
            for (int j = 0; j < i; j++) {
                if (arr[j] == arr[i]) {
                    i--;
                    break;
                }
            }
        }
    }

    // 如果数据在之前已经出现了，就不在输出。
    public static void extracted2(int[] arr) {
        for (int i = 0; i < arr.length; i++) {
            int count = 0;
            int a = arr[i];// a 在数组出现了几次

            for (int j = 0; j < arr.length; j++) {
                if (arr[j] == a) {
                    count++;
                }
            }
            boolean flag = true;
            // 如果 a 在 之前已经出现了 就不要在输出.
            for (int k = 0; k < i; k++) {
                if (arr[k] == a) {
                    flag = false;
                    break;
                }
            }
            if (flag) {
                System.out.println(a + "在数组中出现了" + count + "次");
            }
        }
    }

    // 第1种生成随机码的方法
    public static char[] randomNum(char[] arr) {
        Random ran = new Random();
        char[] arr1 = new char[62];    // 定义一个长度等于26个大小写字母和10个数字的个数和的数字26+26+10
        int index = 0;
        for (char i = 'a'; i <= 'z'; i++) {
            arr1[index] = i;
            index++;
        }
        for (char i = 'A'; i <= 'Z'; i++) {
            arr1[index] = i;
            index++;
        }
        for (char i = '0'; i <= '9'; i++) {
            arr1[index] = i;
            index++;
        }

        for (int i = 0; i < arr.length; i++) {
            index = ran.nextInt(arr1.length);
            arr[i] = arr1[index];
        }

        return arr;
    }


    // 将一个数组中的偶数放左边,奇数放右边
    public static void test03() {
        int[] arr = {1, 2, 3, 4, 5, 6, 7, 8, 9};

        // 第1种方法
        int[] arr1 = new int[arr.length];

        for (int i = 0, j = 0, k = arr1.length - 1; i < arr.length; i++) {
            if (arr[i] % 2 == 0) {
                arr1[j] = arr[i];
                j++;
            } else {
                arr1[k] = arr[i];
                k--;
            }
        }

        // 第2种方法
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] % 2 != 0) {
                for (int j = i + 1; j < arr.length; j++) {
                    if (arr[j] % 2 == 0) {
                        int temp = arr[i];
                        arr[i] = arr[j];
                        arr[j] = temp;
                        break;
                    }
                }
            } else {
                continue;
            }
        }
    }

}
