package com.study.chapter1.lock.readWriteLock;

import com.study.chapter1.lock.lock.JohnAqs;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;

/**
 * @author YuYangjun
 * @desc
 * @date 2019/3/21 11:22 AM
 */
public class JohnReadWriteLock implements ReadWriteLock {

    JohnAqs johnAqs = new JohnAqs(){
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
    public Lock readLock() {
        return new Lock() {
            @Override
            public void lock() {
                johnAqs.acquireShared();
            }

            @Override
            public void lockInterruptibly() throws InterruptedException {

            }

            @Override
            public boolean tryLock() {
                return false;
            }

            @Override
            public boolean tryLock(long time, TimeUnit unit) throws InterruptedException {
                return false;
            }

            @Override
            public void unlock() {
                johnAqs.releaseShared();
            }

            @Override
            public Condition newCondition() {
                return null;
            }
        };
    }

    @Override
    public Lock writeLock() {
        return new Lock() {
            @Override
            public void lock() {
                johnAqs.acquire();
            }

            @Override
            public void lockInterruptibly() throws InterruptedException {

            }

            @Override
            public boolean tryLock() {
                return false;
            }

            @Override
            public boolean tryLock(long time, TimeUnit unit) throws InterruptedException {
                return false;
            }

            @Override
            public void unlock() {
                johnAqs.release();
            }

            @Override
            public Condition newCondition() {
                return null;
            }
        };
    }
}
