package com.moon.netty.chat;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.codec.string.StringEncoder;

import java.util.Scanner;

/**
 * 网络聊天案例 - netty 聊天程序客户端
 *
 * @author MoonZero
 * @version 1.0
 * @date 2019-10-14 09:53
 * @description
 */
public class ChatClient {

    /* 服务器端IP地址 */
    private final String host;
    /* 服务器端端口号 */
    private final int port;

    public ChatClient(String host, int port) {
        this.host = host;
        this.port = port;
    }

    /**
     * 业务处理
     */
    private void run() {
        // 1. 创建一个 EventLoopGroup 线程组
        NioEventLoopGroup group = new NioEventLoopGroup();

        try {
            // 2. 创建客户端的启动助手，完成相关配置
            Bootstrap bootstrap = new Bootstrap()
                    .group(group)   // 3. 设置线程组
                    .channel(NioSocketChannel.class)    // 4. 设置客户端通道的实现类
                    .handler(new ChannelInitializer<SocketChannel>() {  // 5. 创建一个通道初始化对象
                        // 6. 往Pipeline链中添加自定义的handler类
                        @Override
                        protected void initChannel(SocketChannel socketChannel) throws Exception {
                            // 获取ChannelPipeline通道处理链
                            ChannelPipeline pipeline = socketChannel.pipeline();
                            // 往pipeline链中添加一个解码器(使用netty提供的解码器)
                            pipeline.addLast("decoder", new StringDecoder());
                            // 往pipeline链中添加一个编码器(使用netty提供的编码器)
                            pipeline.addLast("encoder", new StringEncoder());
                            // 往pipeline链中添加自定义的handler(业务处理类)
                            pipeline.addLast(new ChatClientHandler());
                            System.out.println("......Netty Chat Client is ready......");
                        }
                    });

            // 7. 启动客户端去连接服务器端(非阻塞)。connect方法是异步的；sync方法是同步阻塞的
            ChannelFuture channelFuture = bootstrap.connect(host, port).sync();

            // 8. 获取通道
            Channel channel = channelFuture.channel();
            System.out.println("------" + channel.localAddress().toString().substring(1) + "------");
            Scanner scanner = new Scanner(System.in);
            while (scanner.hasNextLine()) {
                String msg = scanner.nextLine();
                // 给服务端输出信息
                channel.writeAndFlush(msg + "\r\n");
            }

        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            // 线程组
            group.shutdownGracefully();
        }
    }

    public static void main(String[] args) {
        // 开启客户端
        new ChatClient("127.0.0.1", 9999).run();
    }

}
