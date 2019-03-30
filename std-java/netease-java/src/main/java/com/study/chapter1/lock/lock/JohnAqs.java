package com.study.chapter1.lock.lock;

import java.util.Iterator;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;
import java.util.concurrent.locks.LockSupport;

/**
 * @author YuYangjun
 * @desc 抽象队列同步器
 * @date 2019/3/21 2:29 PM
 */
public class JohnAqs {

    // 同步资源状态
    protected volatile AtomicInteger state = new AtomicInteger(0);
    // 当前锁的拥有者
    protected volatile AtomicReference<Thread> owner = new AtomicReference<>();
    // java q 线程安全
    public volatile LinkedBlockingQueue<Thread> waiters = new LinkedBlockingQueue<>();

    /**
     * 尝试获取资源
     * @author: YuYangjun
     * @date: 2019/3/21 2:54 PM
     */
    public boolean tryAcquire(){
        throw new UnsupportedOperationException();
    }

    /**
     * 尝试获取资源
     * @author: YuYangjun
     * @date: 2019/3/21 2:54 PM
     */
    public int tryAcquireShared(){
        throw new UnsupportedOperationException();
    }

    /**
     * 尝试释放资源
     * @author: YuYangjun
     * @date: 2019/3/21 2:55 PM
     */
    public boolean tryRelease(){
        throw new UnsupportedOperationException();
    }

    /**
     * 尝试释放资源
     * @author: YuYangjun
     * @date: 2019/3/21 2:55 PM
     */
    public boolean tryReleaseShared(){
        throw new UnsupportedOperationException();
    }

    public void acquire(){
        boolean addQ = true;
        while (!tryAcquire()) {
            if (addQ) {
                // 塞到等待锁的集合中
                waiters.offer(Thread.currentThread());
                addQ = false;
            } else {
                // 挂起这个线程
                LockSupport.park();
                // 后续，等待其他线程释放锁，收到通知之后继续循环
            }
        }
        waiters.remove(Thread.currentThread());
    }

    public void release(){
        if(tryRelease()){
            // 唤醒其他线程
            Iterator<Thread> iterator = waiters.iterator();
            while(iterator.hasNext()){
                LockSupport.unpark(iterator.next());
            }
        }
    }

    // 判断量够不够
    public void acquireShared() {
        boolean addQ = true;
        while (tryAcquireShared() < 0) {
            if (addQ) {
                // 塞到等待锁的集合中
                waiters.offer(Thread.currentThread());
                addQ = false;
            } else {
                // 挂起这个线程
                LockSupport.park();
                // 后续，等待其他线程释放锁，收到通知之后继续循环
            }
        }
        waiters.remove(Thread.currentThread());
    }

    public void releaseShared() {
        // cas 修改 owner 拥有者
        if (tryReleaseShared()) {
            Iterator<Thread> iterator = waiters.iterator();
            while (iterator.hasNext()) {
                Thread waiter = iterator.next();
                LockSupport.unpark(waiter); // 唤醒线程继续 抢锁
            }
        }
    }
}
