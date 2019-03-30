package com.study.chapter1.lock.lock;

import java.util.concurrent.locks.Lock;

/**
 * @author YuYangjun
 * @desc
 * @date 2019/3/19 7:52 PM
 */
public class LockDemo3 {

    volatile int i = 0;

    Lock lock = new JohnLock();

    public void add() {
        lock.lock();
//        lock.tryLock();
        try {
            i++;
        } finally {
            lock.unlock();
        }
    }

    public static void main(String[] args) throws InterruptedException {
        LockDemo3 ld = new LockDemo3();
        for (int i = 0; i < 2; i++) {
            new Thread(() -> {
                for (int j = 0; j < 10000; j++) {
                    ld.add();
                }
            }).start();
        }
        Thread.sleep(2000);
        System.out.println(ld.i);
    }
}
