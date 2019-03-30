package com.study.chapter1.lock.readWriteLock;

import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * @author YuYangjun
 * @desc
 * @date 2019/3/20 7:14 PM
 */
public class ReentrantReadWriteLockDemo2 {

    ReadWriteLock lock = new ReentrantReadWriteLock();

    public static void main(String[] args) {
        final ReentrantReadWriteLockDemo2 readWriteLockDemo1 = new ReentrantReadWriteLockDemo2();
        //多线程同时读写
        new Thread(()->{
            readWriteLockDemo1.read(Thread.currentThread());
        }).start();

        new Thread(()->{
            readWriteLockDemo1.write(Thread.currentThread());
        }).start();

        new Thread(()->{
            readWriteLockDemo1.read(Thread.currentThread());
        }).start();
    }

    /**
     * 不管读写，只有一个线程能用，独享锁
     * @param: thread
     * @author: YuYangjun
     * @date: 2019/3/20 7:18 PM
     */
    private synchronized void read(Thread thread) {// 2秒
        lock.readLock().lock();
        try {
            long start = System.currentTimeMillis();
            while (System.currentTimeMillis() - start <= 1) {
                System.out.println(thread.getName() + " 正在进行 读 操作");
            }
            System.out.println(thread.getName() + " 读 操作完毕");
        }finally {
            lock.readLock().unlock();
        }
    }

    /**
     * 写
     * @param: thread
     * @author: YuYangjun
     * @date: 2019/3/20 7:18 PM
     */
    private void write(Thread thread) {
        lock.writeLock().lock();
        try {
            long start = System.currentTimeMillis();
            while (System.currentTimeMillis() - start <= 1) {
                System.out.println(thread.getName() + " 正在进行 写 操作");
            }
            System.out.println(thread.getName() + " 写 操作完毕");
        }finally {
            lock.writeLock().unlock();
        }
    }
}
