package com.moon.netty.codec;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

/**
 * Protobuf 编解码器案例 - 客户端业务处理类
 *
 * @author MoonZero
 * @version 1.0
 * @date 2019-10-14 14:11
 * @description
 */
public class NettyClientHandler extends ChannelInboundHandlerAdapter {

    /**
     * 通道就绪方法
     *
     * @param ctx
     * @throws Exception
     */
    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        System.out.println("Client========" + ctx);
        // 使用Protobuf程序生成的java类对发送的信息进行处理
        BookMessage.Book book = BookMessage.Book.newBuilder().setId(1).setName("Java从入门到放弃").build();
        // 向服务端输出数据
        ctx.writeAndFlush(book);
    }

}
