package com.moon.netty.codec;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.protobuf.ProtobufDecoder;

/**
 * Protobuf 编解码器案例 - 服务端
 *
 * @author MoonZero
 * @version 1.0
 * @date 2019-10-14 14:36
 * @description
 */
public class NettyServer {

    public static void main(String[] args) throws InterruptedException {
        // 1. 创建一个线程组：用来处理网络事件，接收客户端连接
        EventLoopGroup bossGroup = new NioEventLoopGroup();

        // 2. 创建一个线程组：处理网络事情，处理通道 IO 操作
        EventLoopGroup workerGroup = new NioEventLoopGroup();

        // 3. 创建服务器端启动助手来配置参数
        ServerBootstrap serverBootstrap = new ServerBootstrap();
        serverBootstrap.group(bossGroup, workerGroup)   // 4. 设置两个线程组
                .channel(NioServerSocketChannel.class)  // 5. 使用NioServerSocketChannel作为服务器端通道的实现
                .option(ChannelOption.SO_BACKLOG, 128)  // 6. 设置线程队列中等待连接的个数
                .childOption(ChannelOption.SO_KEEPALIVE, true)  // 7. 保持活动连接状态
                .childHandler(new ChannelInitializer<SocketChannel>() { // 8. 创建一个通道初始化对象
                    // 9. 往Pipeline链中添加自定义的handler类
                    @Override
                    protected void initChannel(SocketChannel socketChannel) throws Exception {
                        // 获取通道
                        ChannelPipeline pipeline = socketChannel.pipeline();

                        /* 向 Pipeline 链中添加 ProtobufDecoder 解码器对象 */
                        pipeline.addLast("decoder", new ProtobufDecoder(BookMessage.Book.getDefaultInstance()));

                        pipeline.addLast(new NettyServerHandler());
                    }
                });

        // 10. 启动服务器端并绑定端口，等待接受客户端连接(非阻塞)
        ChannelFuture channelFuture = serverBootstrap.bind(9999).sync();
        System.out.println("......Server is starting......");

        // 11. 关闭通道，关闭线程组
        channelFuture.channel().closeFuture().sync();
        bossGroup.shutdownGracefully();
        workerGroup.shutdownGracefully();
    }

}
