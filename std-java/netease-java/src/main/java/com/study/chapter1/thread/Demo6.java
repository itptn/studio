package com.study.chapter1.thread;

import org.junit.Test;

import java.util.concurrent.locks.LockSupport;

/**
 * @author YuYangjun
 * @desc 三种线程协作通信方式：suspend/resume、wait/notify、park/unpark
 * @date 2019/3/8 2:38 PM
 */
public class Demo6 {

    public static Object baoZiDian = null;

    @Test
    /** 正常的suspend/resume */
    public void suspendResumeTest() throws InterruptedException{
        Thread consumerThread = new Thread(() -> {
            if(null == baoZiDian){
                System.out.println("1 进入等待");
                Thread.currentThread().suspend();
            }
            System.out.println("2 买到包子");
        });
        consumerThread.start();

        Thread.sleep(3000);
        baoZiDian = new Object();
        consumerThread.resume();
        System.out.println("3 包子好了");
    }

    @Test
    /** 死锁的suspend/resume。 suspend并不会像wait一样释放锁，故此容易写出死锁代码 */
    public void suspendResumeDeadLockTest() throws InterruptedException{
        Thread consumerThread = new Thread(() -> {
            if(null == baoZiDian){
                System.out.println("1 进入等待");
                synchronized (this){
                    Thread.currentThread().suspend();
                }
            }
            System.out.println("2 买到包子");
        });
        consumerThread.start();

        Thread.sleep(3000);
        baoZiDian = new Object();
        synchronized (this) {
            consumerThread.resume();
        }
        System.out.println("3 包子好了");
    }

    @Test
    /** 导致程序永久挂起的suspend/resume */
    public void suspendResumeDeadLockTest2() throws InterruptedException{
        Thread consumerThread = new Thread(() -> {
            if(null == baoZiDian){
                System.out.println("1 进入等待");
                try {
                    Thread.sleep(5000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                Thread.currentThread().suspend();
            }
            System.out.println("2 买到包子");
        });
        consumerThread.start();

        Thread.sleep(3000);
        baoZiDian = new Object();
        consumerThread.resume();
        System.out.println("3 包子好了");
        consumerThread.join();
    }

    @Test
    /** 正常的wait/notify */
    public void waitNOfityTest() throws InterruptedException{
        Thread consumerThread = new Thread(() -> {
            while(null == baoZiDian){
                synchronized (this){
                    try {
                        System.out.println("1 进入等待");
                        this.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
            System.out.println("2 买到包子");
        });
        consumerThread.start();

        Thread.sleep(3000);
        baoZiDian = new Object();
        synchronized (this) {
            this.notifyAll();
            System.out.println("3 包子好了");
        }
    }

    @Test
    /** 会导致程序永久等待的wait/notify */
    public void waitNotifyDeadLockTest() throws InterruptedException{
        Thread consumerThread = new Thread(() -> {
            while(null == baoZiDian){
                try {
                    Thread.sleep(5000L);
                } catch (InterruptedException e1) {
                    e1.printStackTrace();
                }
                synchronized (this){
                    try {
                        System.out.println("1 进入等待");
                        this.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
            System.out.println("2 买到包子");
        });
        consumerThread.start();

        Thread.sleep(3000);
        baoZiDian = new Object();
        synchronized (this) {
            this.notifyAll();
            System.out.println("3 包子好了");
        }
    }

    @Test
    /** 正常的park/unpark */
    public void parkUnparkTest() throws InterruptedException{
        Thread consumerThread = new Thread(() -> {
            while(null == baoZiDian){
                System.out.println("1 进入等待");
                LockSupport.park();
            }
            System.out.println("2 买到包子");
        });
        consumerThread.start();

        Thread.sleep(3000);
        baoZiDian = new Object();
        LockSupport.unpark(consumerThread);
        System.out.println("3 包子好了");
    }

    @Test
    /** 死锁的park/unpark */
    public void parkUnparkDeadLockTest() throws InterruptedException{
        Thread consumerThread = new Thread(() -> {
            while(null == baoZiDian){
                System.out.println("1 进入等待");
                synchronized (this){
                    LockSupport.park();
                }
            }
            System.out.println("2 买到包子");
        });
        consumerThread.start();

        Thread.sleep(3000);
        baoZiDian = new Object();
        synchronized (this){
            LockSupport.unpark(consumerThread);
        }
        System.out.println("3 包子好了");
    }
}
