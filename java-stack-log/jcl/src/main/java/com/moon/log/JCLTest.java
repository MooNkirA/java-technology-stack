package com.moon.log;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.junit.Test;

/**
 * JCL 日志门面基础使用测试
 *
 * @author MooNkirA
 * @version 1.0
 * @date 2022-06-18 12:43
 * @description
 */
public class JCLTest {

    // 测试 JCL 门面基础使用，分别测试不引入日志实现与引入 log4j 日志实现的输出效果
    @Test
    public void testBasic() {
        // 获取 log日志记录器对象
        Log log = LogFactory.getLog(JCLTest.class);
        // 日志记录输出
        log.info("hello jcl");
    }

}
