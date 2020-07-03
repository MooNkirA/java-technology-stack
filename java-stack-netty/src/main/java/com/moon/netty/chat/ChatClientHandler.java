package com.moon.netty.chat;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

/**
 * 网络聊天案例 - netty 自定义客户端业务处理类
 *
 * @author MoonZero
 * @version 1.0
 * @date 2019-10-14 09:44
 * @description
 */
public class ChatClientHandler extends SimpleChannelInboundHandler<String> {

    /**
     * 读取数据事件
     *
     * @param ctx
     * @param msg
     * @throws Exception
     */
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, String msg) throws Exception {
        // 输出信息
        System.out.println(msg.trim());
    }

}
