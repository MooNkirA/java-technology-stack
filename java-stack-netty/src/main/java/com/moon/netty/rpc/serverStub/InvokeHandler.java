package com.moon.netty.rpc.serverStub;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import org.reflections.Reflections;

import java.lang.reflect.Method;
import java.util.Set;

/**
 * 自定义RPC案例 - 服务器端业务处理类
 *
 * @author MoonZero
 * @version 1.0
 * @date 2019-10-14 23:16
 * @description
 */
public class InvokeHandler extends ChannelInboundHandlerAdapter {

    /* 服务方接口和实现类所在的包路径 */
    private final static String INTERFACE_PATH = "com.moon.netty.rpc.server";

    /**
     * 通道读取数据事件。读取客户端发来的数据并通过反射调用实现类的方法
     *
     * @param ctx
     * @param msg
     * @throws Exception
     */
    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        // 1. 向下强转成调用相关类信息对象ClassInfo
        ClassInfo classInfo = (ClassInfo) msg;
        // 2. 通过反射获取请求需要调用的接口的实现类的字节码对象
        Class<?> clazz = Class.forName(getImplClassName(classInfo));
        // 3. 通过反射获取调用的方法对象
        Method method = clazz.getMethod(classInfo.getMethodName(), classInfo.getTypes());
        // 4. 调用实现类的方法
        Object result = method.invoke(clazz.newInstance(), classInfo.getObjects());
        // 5. 将调用方法后返回的数据写到客户端
        ctx.channel().writeAndFlush(result);
    }

    /**
     * 得到指定接口下某个实现类的名字
     *
     * @param classInfo
     * @return 类全命名
     */
    private String getImplClassName(ClassInfo classInfo) throws Exception {
        // 截取调用的接口的名称
        int lastDot = classInfo.getClassName().lastIndexOf(".");
        String interfaceName = classInfo.getClassName().substring(lastDot);

        // 获取接口的字节码对象
        Class superClass = Class.forName(INTERFACE_PATH + interfaceName);

        // 扫描接口所在的包
        Reflections reflections = new Reflections(INTERFACE_PATH);
        // 获取所有接口的实现类
        Set<Class> implClassSet = reflections.getSubTypesOf(superClass);

        if (implClassSet.size() == 0) {
            System.out.println("未找到实现类");
            return null;
        } else if (implClassSet.size() > 1) {
            System.out.println("找到多个实现类，未明确使用哪一个");
            return null;
        } else {
            Class[] classes = implClassSet.toArray(new Class[0]);
            // 返回实现类名称
            return classes[0].getName();
        }
    }

}
