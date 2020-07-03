package com.moon.netty.test.nio;

import com.moon.netty.test.BaseTest;
import org.junit.Test;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

/**
 * nio 测试
 *
 * @author MoonZero
 * @version 1.0
 * @date 2019-10-3 08:35
 * @description
 */
public class TestNIO extends BaseTest {

    /* 往本地文件中写数据 */
    @Test
    public void test1() throws Exception {
        // 1. 创建输出流
        FileOutputStream fileOutputStream = new FileOutputStream("E:\\00-Downloads\\moon.txt");
        // 2. 从流中得到一个通道
        FileChannel fileChannel = fileOutputStream.getChannel();
        // 3. 提供一个缓冲区
        ByteBuffer buffer = ByteBuffer.allocate(1024);
        // 4. 往缓冲区中存入数据
        String str = "hello,nio";
        buffer.put(str.getBytes());
        // 5. 翻转缓冲区
        buffer.flip();
        // 6. 把缓冲区写到通道中
        fileChannel.write(buffer);
        // 7. 关闭
        fileOutputStream.close();
    }

    /* 从本地文件中读取数据 */
    @Test
    public void test2() throws Exception {
        File file = new File("E:\\00-Downloads\\moon.txt");
        // 1. 创建输入流
        FileInputStream fileInputStream = new FileInputStream(file);
        // 2. 得到一个通道
        FileChannel fileChannel = fileInputStream.getChannel();
        // 3. 准备一个缓冲区
        ByteBuffer buffer = ByteBuffer.allocate((int) file.length());
        // 4. 从通道里读取数据并存到缓冲区中
        fileChannel.read(buffer);
        System.out.println(new String(buffer.array()));
        // 5. 关闭
        fileInputStream.close();
    }

    /* 使用NIO实现文件复制 */
    @Test
    public void test3() throws Exception {
        // 1. 创建两个流
        FileInputStream fileInputStream = new FileInputStream("E:\\00-Downloads\\moon.txt");
        FileOutputStream fileOutputStream = new FileOutputStream("E:\\00-Downloads\\moon_copy.txt");

        // 2. 得到两个通道
        FileChannel fileInChannel = fileInputStream.getChannel();
        FileChannel fileOutChannel = fileOutputStream.getChannel();

        // 3. 复制
        fileOutChannel.transferFrom(fileInChannel, 0, fileInChannel.size());

        // 4. 关闭
        fileInputStream.close();
        fileOutputStream.close();
    }

    /* 使用BIO实现文件复制 */
    @Test
    public void testBioCopy() throws Exception {
        // 1. 创建两个流
        FileInputStream fileInputStream = new FileInputStream("E:\\00-Downloads\\moon.txt");
        FileOutputStream fileOutputStream = new FileOutputStream("E:\\00-Downloads\\moon_copy.txt");

        // 2. 定义字节数组，使用一次读取数组方式复制文件
        byte[] bytes = new byte[1024];
        int len;
        while ((len = fileInputStream.read(bytes)) != -1) {
            fileOutputStream.write(bytes, 0, len);
        }

        // 3. 关闭资源
        fileInputStream.close();
        fileOutputStream.close();
    }

}
