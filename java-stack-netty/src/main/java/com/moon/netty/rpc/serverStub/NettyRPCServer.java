package com.moon.netty.rpc.serverStub;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.serialization.ClassResolvers;
import io.netty.handler.codec.serialization.ObjectDecoder;
import io.netty.handler.codec.serialization.ObjectEncoder;

/**
 * 自定义RPC案例 - 网络处理服务器
 *
 * @author MoonZero
 * @version 1.0
 * @date 2019-10-15 11:26
 * @description
 */
public class NettyRPCServer {

    /* 服务器端口号 */
    private int port;

    public NettyRPCServer(int port) {
        this.port = port;
    }

    /**
     * 业务处理
     */
    private void start() {
        EventLoopGroup bossGroup = new NioEventLoopGroup();
        EventLoopGroup workerGroup = new NioEventLoopGroup();
        try {
            ServerBootstrap serverBootstrap = new ServerBootstrap();
            serverBootstrap.group(bossGroup, workerGroup)
                    .channel(NioServerSocketChannel.class)
                    .option(ChannelOption.SO_BACKLOG, 128)
                    .childOption(ChannelOption.SO_KEEPALIVE, true)
                    .localAddress(port) // 设置端口号
                    .childHandler(new ChannelInitializer<SocketChannel>() {
                        @Override
                        protected void initChannel(SocketChannel socketChannel) throws Exception {
                            ChannelPipeline pipeline = socketChannel.pipeline();
                            // 往pipeline链中添加一个编码器(使用netty提供的编码器)
                            pipeline.addLast("encoder", new ObjectEncoder());
                            /*
                             * 往pipeline链中添加一个解码器(使用netty提供的解码器)
                             *      构造方法第一个参数设置二进制数据的最大字节数  第二个参数设置具体使用哪个类解析器
                             */
                            pipeline.addLast("decoder", new ObjectDecoder(Integer.MAX_VALUE, ClassResolvers.cacheDisabled(null)));
                            // 往pipeline链中添加自定义的handler(业务处理类)
                            pipeline.addLast(new InvokeHandler());
                            System.out.println("......Netty RPC Server handler is ready......");
                        }
                    });

            System.out.println("Netty RPC Server start......");
            ChannelFuture channelFuture = serverBootstrap.bind(port).sync();
            channelFuture.channel().closeFuture().sync();

        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            bossGroup.shutdownGracefully();
            workerGroup.shutdownGracefully();
            System.out.println("Netty RPC Server is close......");
        }
    }

    public static void main(String[] args) {
        // 开启服务
        new NettyRPCServer(9999).start();
    }

}
