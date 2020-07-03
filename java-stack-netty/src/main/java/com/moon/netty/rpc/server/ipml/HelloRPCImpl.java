package com.moon.netty.rpc.server.ipml;

import com.moon.netty.rpc.server.HelloRPC;

/**
 * 服务的提供方接口实现类
 *
 * @author MoonZero
 * @version 1.0
 * @date 2019-10-14 17:09
 * @description
 */
public class HelloRPCImpl implements HelloRPC {

    @Override
    public String hello(String name) {
        return "hello, " + name;
    }

}
