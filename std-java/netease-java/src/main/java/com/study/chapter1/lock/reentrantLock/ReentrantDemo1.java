package com.study.chapter1.lock.reentrantLock;

import java.util.concurrent.locks.ReentrantLock;

/**
 * @author YuYangjun
 * @desc 演示可重入
 * @date 2019/3/20 4:32 PM
 */
public class ReentrantDemo1 {
    private static final ReentrantLock lock = new ReentrantLock();

    public static void main(String[] args) {
        lock.lock();

        try {
            System.out.println("第一次获取锁");
            System.out.println("当前线程获取锁的次数" + lock.getHoldCount());
            lock.lock();
            System.out.println("第一次获取锁");
            System.out.println("当前线程获取锁的次数" + lock.getHoldCount());
        }finally {
            lock.unlock();
//            lock.unlock();
        }
        System.out.println("当前线程获取锁的次数" + lock.getHoldCount());

        //如果不释放，其他线程无法获取锁
        new Thread(()->{
            System.out.println(Thread.currentThread() + " 期望抢到锁");
            lock.lock();
            System.out.println(Thread.currentThread() + " 已经获取到锁");
        }).start();
    }

}
