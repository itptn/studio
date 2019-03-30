package com.study.chapter1.thread;

/**
 * @author YuYangjun
 * @desc
 * @date 2019/3/6 6:00 PM
 */
public class Demo4 {
    private volatile static boolean flag = true;

    public static void main(String[] args) throws InterruptedException{
        new Thread(() -> {
            try {
                while(flag){
                    System.out.println("运行中");
                    Thread.sleep(1000);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }).start();

        Thread.sleep(3000);
        flag = false;
        System.out.println("程序运行结束");
    }
}
