package com.moon.netty.rpc.server.ipml;

import com.moon.netty.rpc.server.HelloNetty;

/**
 * 服务的提供方接口实现类
 *
 * @author MoonZero
 * @version 1.0
 * @date 2019-10-14 17:08
 * @description
 */
public class HelloNettyImpl implements HelloNetty {

    @Override
    public String hello() {
        return "hello, netty";
    }

}
