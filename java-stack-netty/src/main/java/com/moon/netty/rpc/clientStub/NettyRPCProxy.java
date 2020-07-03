package com.moon.netty.rpc.clientStub;

import com.moon.netty.rpc.serverStub.ClassInfo;
import io.netty.bootstrap.Bootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.serialization.ClassResolvers;
import io.netty.handler.codec.serialization.ObjectDecoder;
import io.netty.handler.codec.serialization.ObjectEncoder;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * 自定义RPC案例 - 客户端代理类
 *
 * @author MoonZero
 * @version 1.0
 * @date 2019-10-15 12:26
 * @description
 */
public class NettyRPCProxy {

    /**
     * 根据接口创建代理对象
     *
     * @param target
     * @return
     */
    public static Object create(Class target) {
        return Proxy.newProxyInstance(target.getClassLoader(), new Class[]{target}, new InvocationHandler() {
            @Override
            public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                // 封装RPC调用时相关类信息对象
                ClassInfo classInfo = new ClassInfo();
                classInfo.setClassName(target.getName());
                classInfo.setMethodName(method.getName());
                classInfo.setTypes(method.getParameterTypes());
                classInfo.setObjects(args);

                // 使用netty发送数据
                NioEventLoopGroup group = new NioEventLoopGroup();
                // 创建返回数据对象
                ResultHandler resultHandler = new ResultHandler();

                try {
                    Bootstrap bootstrap = new Bootstrap()
                            .group(group)
                            .channel(NioSocketChannel.class)
                            .handler(new ChannelInitializer<SocketChannel>() {
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
                                    pipeline.addLast("handler", resultHandler);
                                    System.out.println("......Netty RPC Client is ready......");
                                }
                            });

                    // 连接通道
                    ChannelFuture channelFuture = bootstrap.connect("127.0.0.1", 9999).sync();
                    // 获取通道
                    Channel channel = channelFuture.channel();

                    /* 向服务端发送调用的信息 */
                    channel.writeAndFlush(classInfo).sync();

                    // 关闭连接
                    channel.closeFuture().sync();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    // 线程组
                    group.shutdownGracefully();
                }

                return resultHandler.getResponse();
            }
        });
    }

}
