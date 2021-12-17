package com.moon.concurrent.pattern;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.LinkedList;
import java.util.StringJoiner;

/**
 * 异步模式之生产者/消费者示例
 *
 * @author MooNkirA
 * @version 1.0
 * @date 2021-12-16 15:11
 * @description
 */
public class ProducerConsumerDemo {

    public static void main(String[] args) {
        // 创建消息队列
        MessageQueue queue = new MessageQueue(6);
        // 创建多个生产者
        for (int i = 1; i < 5; i++) {
            int id = i;
            new Thread(() -> {
                queue.put(new Message(id, "值" + id));
            }, "生产者" + i).start();
        }

        // 创建一个消息者
        new Thread(() -> {
            while (true) {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                Message message = queue.take();
            }
        }, "消费者").start();
    }

}

/* 定义消息队列类，此示例只用于 java 线程之间通信 */
class MessageQueue {
    private final static Logger LOGGER = LoggerFactory.getLogger(MessageQueue.class);

    // 消息的队列集合
    private final LinkedList<Message> list = new LinkedList<>();
    // 队列容量
    private final int capcity;

    public MessageQueue(int capcity) {
        this.capcity = capcity;
    }

    // 获取消息
    public Message take() {
        // 检查队列是否为空
        synchronized (list) {
            while (list.isEmpty()) {
                try {
                    LOGGER.info("队列为空, 消费者线程等待");
                    list.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            // 从队列头部获取消息并返回
            Message message = list.removeFirst();
            LOGGER.info("已消费消息 {}", message);
            list.notifyAll();
            return message;
        }
    }

    // 存入消息
    public void put(Message message) {
        synchronized (list) {
            // 检查消息是否已满
            while (list.size() == capcity) {
                try {
                    LOGGER.info("队列已满, 生产者线程等待");
                    list.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            // 将消息加入队列尾部
            list.addLast(message);
            LOGGER.info("已生产消息 {}", message);
            list.notifyAll();
        }
    }
}

/* 定义消息类 */
final class Message {
    private final int id;
    private final Object value;

    public Message(int id, Object value) {
        this.id = id;
        this.value = value;
    }

    public int getId() {
        return id;
    }

    public Object getValue() {
        return value;
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", Message.class.getSimpleName() + "[", "]")
                .add("id=" + id)
                .add("value=" + value)
                .toString();
    }
}
