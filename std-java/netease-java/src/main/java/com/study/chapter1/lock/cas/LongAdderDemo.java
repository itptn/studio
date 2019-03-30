package com.study.chapter1.lock.cas;

import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.atomic.LongAdder;

/**
 * @author YuYangjun
 * @desc
 * @date 2019/3/15 3:18 PM
 */
public class LongAdderDemo {

    private long count = 0;
    private void testSync() throws InterruptedException{
        for (int i = 0; i < 3; i++) {
            new Thread(()->{
                long startTime = System.currentTimeMillis();
                while(System.currentTimeMillis() - startTime < 2000){
                    synchronized (this){
                        ++count;
                    }
                }
                long endTime = System.currentTimeMillis();
                System.out.println("SyncThread spend: " + (endTime - startTime) + "ms, v-" + count);
            }).start();
        }
    }

    private AtomicLong aCount = new AtomicLong(0);
    private void testAtomic() throws InterruptedException{
        for (int i = 0; i < 3; i++) {
            new Thread(()->{
                long startTime = System.currentTimeMillis();
                while(System.currentTimeMillis() - startTime < 2000){
                    aCount.incrementAndGet();
                }
                long endTime = System.currentTimeMillis();
                System.out.println("AtomicThread spend: " + (endTime - startTime) + "ms, v-" + aCount.longValue());
            }).start();
        }
    }


    private LongAdder lCount = new LongAdder();
    private void testLongAdder() throws InterruptedException{
        for (int i = 0; i < 3; i++) {
            new Thread(()->{
                long startTime = System.currentTimeMillis();
                while(System.currentTimeMillis() - startTime < 2000){
                    lCount.increment();
                }
                long endTime = System.currentTimeMillis();
                System.out.println("LongAdderThread spend: " + (endTime - startTime) + "ms, v-" + lCount.sum());
            }).start();
        }
    }

    public static void main(String[] args) throws InterruptedException{
        LongAdderDemo l = new LongAdderDemo();
        l.testSync();
        l.testAtomic();
        l.testLongAdder();
    }
}
