package com.moon.netty.rpc.clientStub;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

/**
 * 自定义RPC案例 - 客户端业务处理类
 *
 * @author MoonZero
 * @version 1.0
 * @date 2019-10-15 11:50
 * @description
 */
public class ResultHandler extends ChannelInboundHandlerAdapter {

    private Object response;

    public Object getResponse() {
        return response;
    }

    /**
     * 读取服务器端返回的数据(远程调用的结果)
     *
     * @param ctx
     * @param msg
     * @throws Exception
     */
    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        // 获取返回结果
        this.response = msg;
        // 关闭通道
        ctx.close();
    }

}
