package com.moon.netty.rpc.server;

/**
 * 服务的提供方接口
 *
 * @author MoonZero
 * @version 1.0
 * @date 2019-10-14 17:05
 * @description
 */
public interface HelloRPC {

    String hello(String name);

}
