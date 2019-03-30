package com.study.chapter1.thread;

import org.junit.Test;

import java.util.HashMap;

/**
 * @author YuYangjun
 * @desc
 * @date 2019/3/9 10:41 AM
 */
public class Demo7 {

    private static ThreadLocal<String> value = new ThreadLocal<>();

    @Test
    public void threadLocalTest() throws InterruptedException{
        value.set("这是主线程设置的123");
        String v = value.get();
        System.out.println("线程1执行之前，主线程取到的值：" + v);

        new Thread(new Runnable() {
            @Override
            public void run() {
                String v = value.get();
                System.out.println("线程1取到的值：" + v);
                // 设置 threadLocal
                value.set("这是线程1设置的456");

                v = value.get();
                System.out.println("重新设置之后，线程1取到的值：" + v);
                System.out.println("线程1执行结束");
            }
        }).start();

        Thread.sleep(5000L); // 等待所有线程执行结束
        v = value.get();
        System.out.println("线程1执行之后，主线程取到的值：" + v);
        HashMap a;
    }
}
