package com.moon.netty.rpc.serverStub;

import lombok.Data;
import lombok.ToString;

import java.io.Serializable;

/**
 * 封装RPC调用时相关类信息
 *
 * @author MoonZero
 * @version 1.0
 * @date 2019-10-14 17:21
 * @description
 */
@Data
@ToString
public class ClassInfo implements Serializable {

    private static final long serialVersionUID = 1694348021302617385L;

    /* 类名 */
    private String className;
    /* 方法名 */
    private String methodName;
    /* 参数类型 */
    private Class<?>[] types;
    /* 参数列表 */
    private Object[] objects;

}
