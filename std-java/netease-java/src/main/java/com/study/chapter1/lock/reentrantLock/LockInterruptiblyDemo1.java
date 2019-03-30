package com.study.chapter1.lock.reentrantLock;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author YuYangjun
 * @desc 可响应中断
 * @date 2019/3/20 5:01 PM
 */
public class LockInterruptiblyDemo1 {
    private Lock lock = new ReentrantLock();

    public static void main(String[] args) throws InterruptedException{
        LockInterruptiblyDemo1 demo1 = new LockInterruptiblyDemo1();
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                try {
                    demo1.test(Thread.currentThread());
                } catch (Exception e) {

                }
            }
        };

        Thread thread0 = new Thread(runnable);
        Thread thread1 = new Thread(runnable);
        thread0.start();
        Thread.sleep(500); // 等待0.5秒，让thread0先执行

        thread1.start();
        Thread.sleep(2000); // 两秒后，中断thread1
        thread1.interrupt();
    }

    public void test(Thread thread) throws InterruptedException{
        System.out.println(Thread.currentThread().getName()+", 想获取锁");

        lock.lockInterruptibly(); // 注意，如果需要正确中断等待锁的线程，必须将获取锁放在外面，然后将InterruptedException抛出
        try {
            System.out.println(thread.getName() + " 得到了锁");
            Thread.sleep(10000); // 抢到锁, 10秒不释放
        } finally{
            System.out.println(Thread.currentThread().getName()+", 执行了finally");
            lock.unlock();
            System.out.println(Thread.currentThread().getName()+", 释放了锁");
        }
    }
}
