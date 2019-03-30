package com.study.chapter1.lock.lock;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;

/**
 * @author YuYangjun
 * @desc
 * @date 2019/3/21 11:22 AM
 */
public class JohnLock implements Lock{

    JohnAqs aqsDemo = new JohnAqs(){
        @Override
        public boolean tryAcquire(){
            if(0 != state.get()){
                return false;
            }else{
                return owner.compareAndSet(null, Thread.currentThread());
            }
        }

        @Override
        public boolean tryRelease(){
            return owner.compareAndSet(Thread.currentThread(),null);
        }

        @Override
        public int tryAcquireShared(){
            if(null != owner.get() && !owner.get().equals(Thread.currentThread())){
                return -1;
            }
            return state.incrementAndGet();
        }

        @Override
        public boolean tryReleaseShared(){
            return state.decrementAndGet() >= 0;
        }
    };

    @Override
    public void lock() {
        aqsDemo.acquire();
    }

    @Override
    public void lockInterruptibly() throws InterruptedException {

    }

    @Override
    public boolean tryLock() {
        return aqsDemo.tryAcquire();
    }

    @Override
    public boolean tryLock(long time, TimeUnit unit) throws InterruptedException {
        return false;
    }

    @Override
    public void unlock() {
        aqsDemo.release();
    }

    @Override
    public Condition newCondition() {
        return null;
    }
}
