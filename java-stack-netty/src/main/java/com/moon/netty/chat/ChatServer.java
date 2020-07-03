package com.moon.netty.chat;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.codec.string.StringEncoder;

/**
 * 网络聊天案例 - netty 聊天程序服务器端
 *
 * @author MoonZero
 * @version 1.0
 * @date 2019-10-13 11:18
 * @description
 */
public class ChatServer {

    /* 服务器端口号 */
    private int port;

    public ChatServer(int port) {
        this.port = port;
    }

    /**
     * 业务处理
     */
    private void run() {
        // 1. 创建一个线程组：用来处理网络事件，接收客户端连接
        EventLoopGroup bossGroup = new NioEventLoopGroup();
        // 2. 创建一个线程组：处理网络事情，处理通道 IO 操作
        EventLoopGroup workerGroup = new NioEventLoopGroup();
        try {
            // 3. 创建服务器端启动助手来配置参数
            ServerBootstrap serverBootstrap = new ServerBootstrap();
            // 启动助手完成服务器端的各种配置
            serverBootstrap.group(bossGroup, workerGroup)   // 4. 设置两个线程组
                    .channel(NioServerSocketChannel.class)  // 5. 使用NioServerSocketChannel作为服务器端通道的实现
                    .option(ChannelOption.SO_BACKLOG, 128)  // 6. 设置线程队列中等待连接的个数
                    .childOption(ChannelOption.SO_KEEPALIVE, true)  // 7. 保持活动连接状态
                    .childHandler(new ChannelInitializer<SocketChannel>() { // 8. 创建一个通道初始化对象
                        // 9. 往Pipeline链中添加自定义的handler类
                        @Override
                        protected void initChannel(SocketChannel socketChannel) throws Exception {
                            // 获取ChannelPipeline通道处理链
                            ChannelPipeline pipeline = socketChannel.pipeline();
                            // 往pipeline链中添加一个解码器(使用netty提供的解码器)
                            pipeline.addLast("decoder", new StringDecoder());
                            // 往pipeline链中添加一个编码器(使用netty提供的编码器)
                            pipeline.addLast("encoder", new StringEncoder());
                            // 往pipeline链中添加自定义的handler(业务处理类)
                            pipeline.addLast(new ChatServerHandler());
                            System.out.println("......Netty Chat Server is ready......");
                        }
                    });

            System.out.println("Netty Chat Server start......");
            // 10. 启动服务器端并绑定端口，等待接受客户端连接(非阻塞)。bind方法是异步的；sync方法是同步阻塞的
            ChannelFuture channelFuture = serverBootstrap.bind(port).sync();
            // 11. 关闭通道(异步)
            channelFuture.channel().closeFuture().sync();

        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            // 关闭线程组
            bossGroup.shutdownGracefully();
            workerGroup.shutdownGracefully();
            System.out.println("Netty Chat Server is close......");
        }
    }

    public static void main(String[] args) {
        // 开启服务
        new ChatServer(9999).run();
    }

}
