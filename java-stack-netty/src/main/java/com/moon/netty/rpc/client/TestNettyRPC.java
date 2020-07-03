package com.moon.netty.rpc.client;

import com.moon.netty.rpc.clientStub.NettyRPCProxy;

/**
 * 自定义RPC案例 - 服务调用方
 *
 * @author MoonZero
 * @version 1.0
 * @date 2019-10-15 13:44
 * @description
 */
public class TestNettyRPC {

    public static void main(String[] args) {
        /*
         * 第1次PRC运程调用，创建HelloNetty接口的代理对象
         *      注意：导入是本地（本包下）的接口
         */
        HelloNetty helloNetty = (HelloNetty) NettyRPCProxy.create(HelloNetty.class);
        System.out.println(helloNetty.hello());

        /*
         * 第1次PRC运程调用，创建HelloRPC接口的代理对象
         *      注意：导入是本地（本包下）的接口
         */
        HelloRPC helloRPC = (HelloRPC) NettyRPCProxy.create(HelloRPC.class);
        System.out.println(helloRPC.hello("MooNkirA"));
    }

}
