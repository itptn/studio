package com.study.chapter1.thread.volatiletest;

import java.util.concurrent.TimeUnit;

/**
 * @author YuYangjun
 * @desc
 * @date 2019/3/12 4:37 PM
 */
public class VisibilityDemo {
    private boolean flag = true;

    public static void main(String[] args) throws InterruptedException{
        VisibilityDemo demo1 = new VisibilityDemo();
        Thread thread1 = new Thread(new Runnable() {
            @Override
            public void run() {
                int i = 0;
                while (demo1.flag){
                    i++;
                }
                System.out.println("我已经结束：" + i);
            }
        });
        thread1.start();

        TimeUnit.SECONDS.sleep(2);
        demo1.flag = false;
        System.out.println("Flag设置为false");
    }
}