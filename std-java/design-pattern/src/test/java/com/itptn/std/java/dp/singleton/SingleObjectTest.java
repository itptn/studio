package com.itptn.std.java.dp.singleton;

import org.junit.Test;

/**
 * @author YuYangjun
 * @desc
 * @date 2019/1/5 10:32 AM
 */
public class SingleObjectTest {

    @Test
    public void getSingleObject(){
        //不合法的构造函数
        //编译时错误：构造函数 SingleObject() 是不可见的
        //SingleObject object = new SingleObject();

        //获取唯一可用的对象
        SingleObject object = SingleObject.getInstance();

        //显示消息
        object.showMessage();

        //获取唯一可用的对象
        Singleton singleton = Singleton.INSTANCE;
        //显示消息
        singleton.showMessage();
    }
}
