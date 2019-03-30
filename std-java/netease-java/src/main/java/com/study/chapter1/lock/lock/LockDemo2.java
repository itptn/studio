package com.study.chapter1.lock.lock;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author YuYangjun
 * @desc
 * @date 2019/3/14 7:49 PM
 */
public class LockDemo2 {

    AtomicInteger i = new AtomicInteger(0);

    public void add(){
        i.incrementAndGet();
    }

    public static void main(String[] args) throws InterruptedException {
        LockDemo2 ld = new LockDemo2();
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
