package com.moon.netty.codec;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

/**
 * Protobuf 编解码器案例 - 服务端处理类
 *
 * @author MoonZero
 * @version 1.0
 * @date 2019-10-14 14:34
 * @description
 */
public class NettyServerHandler extends ChannelInboundHandlerAdapter {

    /**
     * 通道读取事件
     *
     * @param ctx
     * @param msg
     * @throws Exception
     */
    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        System.out.println("Server========" + ctx);
        // 获取客户端发送的数据
        BookMessage.Book book = (BookMessage.Book) msg;
        System.out.println("客户端发来数据：" + book.getName());
    }

}
