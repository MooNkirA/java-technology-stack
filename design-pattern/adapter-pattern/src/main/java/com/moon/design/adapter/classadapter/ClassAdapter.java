package com.moon.design.adapter.classadapter;

import com.moon.design.adapter.Adaptee;
import com.moon.design.adapter.Target;

/**
 * 类适配器。继承适配者（类），并实现目标接口。
 * 在目标接口中，调用适配者中相应的逻辑。
 *
 * @author MooNkirA
 * @version 1.0
 * @date 2022-05-25 11:03
 * @description
 */
public class ClassAdapter extends Adaptee implements Target {

    public void request() {
        // 调用适配者的方法
        super.specificRequest();
    }

}
