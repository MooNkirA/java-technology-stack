package com.moon.java.net;

import org.junit.Test;

import java.net.InetAddress;
import java.net.UnknownHostException;

/**
 * InetAddress 基础示例
 *
 * @author MooNkirA
 * @version 1.0
 * @date 2021-07-11 21:41
 * @description
 */
public class InetAddressDemo {

    @Test
    public void testBasic() throws UnknownHostException {
        InetAddress inetAddress = InetAddress.getLocalHost();
        System.out.println("InetAddress#getHostAddress() :: " + inetAddress.getHostAddress());
        System.out.println("InetAddress#getHostName() :: " + inetAddress.getHostName());
        System.out.println("InetAddress#getCanonicalHostName() :: " + inetAddress.getCanonicalHostName());
    }

}
