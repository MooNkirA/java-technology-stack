package com.moon.netty.basic;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.util.CharsetUtil;

/**
 * netty 自定义服务器端的业务处理类
 *
 * @author MoonZero
 * @version 1.0
 * @date 2019-10-7 18:04
 * @description
 */
public class NettyServerHandler extends ChannelInboundHandlerAdapter {

    /**
     * 读取数据事件
     *
     * @param ctx
     * @param msg
     * @throws Exception
     */
    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        System.out.println("Server:" + ctx);
        // 获取客户端消息
        ByteBuf byteBuf = (ByteBuf) msg;
        System.out.println("客户端发来的消息：" + byteBuf.toString(CharsetUtil.UTF_8));
    }

    /**
     * 数据读取完毕事件
     *
     * @param ctx
     * @throws Exception
     */
    @Override
    public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
        // 使用操作缓冲区的工具类，获取 ByteBuf 对象，并返回数据
        ctx.writeAndFlush(Unpooled.copiedBuffer("服务器数据读取完毕后处理逻辑", CharsetUtil.UTF_8));
    }

    /**
     * 通道发生异常事件
     *
     * @param ctx
     * @param cause
     * @throws Exception
     */
    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        // 打印异常信息
        cause.printStackTrace();
        // 发生异常，将通道关闭
        ctx.close();
    }
}
