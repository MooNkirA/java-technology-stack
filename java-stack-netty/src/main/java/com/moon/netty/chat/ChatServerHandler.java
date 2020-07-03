package com.moon.netty.chat;

import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

import java.util.ArrayList;
import java.util.List;

/**
 * 网络聊天案例 - netty 自定义服务器端业务处理类
 *
 * @author MoonZero
 * @version 1.0
 * @date 2019-10-13 08:11
 * @description
 */
public class ChatServerHandler extends SimpleChannelInboundHandler<String> {

    private static List<Channel> channels = new ArrayList<>();

    /**
     * 通道就绪事件
     *
     * @param ctx
     * @throws Exception
     */
    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        // 获取通道
        Channel channel = ctx.channel();
        // 将通道增加到集合中
        channels.add(channel);
        // 输出服务端信息
        System.out.println("[Server]:" + channel.remoteAddress().toString().substring(1) + "上线");
    }

    /**
     * 通道未就绪事件
     *
     * @param ctx
     * @throws Exception
     */
    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
        // 获取通道
        Channel channel = ctx.channel();
        // 删除掉线的通道
        channels.remove(channel);
        // 输出服务端信息
        System.out.println("[Server]:" + channel.remoteAddress().toString().substring(1) + "掉线");
    }

    /**
     * 读取数据事件
     *
     * @param ctx
     * @param msg
     * @throws Exception
     */
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, String msg) throws Exception {
        // 获取当前连接通道
        Channel currentChannel = ctx.channel();
        // 循环所有通道，排除当前通道
        for (Channel channel : channels) {
            if (channel != currentChannel) {
                // 向其他的客户端输出读取的数据
                channel.writeAndFlush("[" + channel.remoteAddress().toString().substring(1) + "]" + "说：" + msg + "\n");
            }
        }

    }

    /**
     * 通道发生异常处理事件
     *
     * @param ctx
     * @param cause
     * @throws Exception
     */
    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        // 获取当前连接通道
        Channel channel = ctx.channel();
        // 输出信息
        System.out.println("[Server]:" + channel.remoteAddress().toString().substring(1) + "异常");
        // 关闭通道
        ctx.close();
    }
}
