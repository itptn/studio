package com.study.chapter1.lock.lock;

import sun.misc.Unsafe;

import java.lang.reflect.Field;

/**
 * @author YuYangjun
 * @desc
 * @date 2019/3/14 7:49 PM
 */
public class LockDemo1 {

    volatile int i = 0;

    static Unsafe unsafe = null;
    private static long iOffset;

    static {
        try {
            Field theUnsafe = Unsafe.class.getDeclaredField("theUnsafe");
            theUnsafe.setAccessible(true);
            unsafe = (Unsafe) theUnsafe.get(null);
            iOffset = unsafe.objectFieldOffset(LockDemo1.class.getDeclaredField("i"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void add(){
        int current, ni;
        do {
            current = unsafe.getIntVolatile(this, iOffset);
            ni = current + 1;
        }while (!unsafe.compareAndSwapInt(this, iOffset, current, ni));
    }

    public static void main(String[] args) throws InterruptedException {
        LockDemo1 ld = new LockDemo1();
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
