package com.study.chapter1.lock.lock;

/**
 * @author YuYangjun
 * @desc
 * @date 2019/3/14 7:49 PM
 */
public class LockDemo {
    volatile int i = 0;

    public void add(){
        i++;
    }

    public static void main(String[] args) throws InterruptedException {
        LockDemo ld = new LockDemo();
        for (int i = 0; i < 2; i++) {
            new Thread(()->{
                for (int j = 0; j < 10000; j++) {
                    ld.add();
                }
            }).start();
        }
        Thread.sleep(2000);
        System.out.println(ld.i);
    }
}
