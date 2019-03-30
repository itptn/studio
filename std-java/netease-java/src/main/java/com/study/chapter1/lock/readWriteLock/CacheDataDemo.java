package com.study.chapter1.lock.readWriteLock;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * @author YuYangjun
 * @desc
 * @date 2019/3/20 7:54 PM
 */
public class CacheDataDemo {

    private Map<String, Object> map = new HashMap();
    private static ReadWriteLock rwl = new ReentrantReadWriteLock();
//    private static ReadWriteLock rwl = new JohnReadWriteLock();

    public static void main(String[] args) {
        CacheDataDemo cacheDataDemo = new CacheDataDemo();
        System.out.println(cacheDataDemo.get("ddd"));
        System.out.println(cacheDataDemo.get("ddd"));
        System.out.println(cacheDataDemo.get("ddd"));
        System.out.println(cacheDataDemo.get("ddd"));
        System.out.println(cacheDataDemo.get("ddd"));
        System.out.println(cacheDataDemo.get("ddd"));
        System.out.println(cacheDataDemo.get("ddd"));
        System.out.println(cacheDataDemo.get("ddd"));
        System.out.println(cacheDataDemo.get("ddd"));
        System.out.println(cacheDataDemo.get("ddd"));
        System.out.println(cacheDataDemo.get("ddd"));
        System.out.println(cacheDataDemo.get("ddd"));
        System.out.println(cacheDataDemo.get("ddd"));
        System.out.println(cacheDataDemo.get("ddd"));
        System.out.println(cacheDataDemo.get("ddd"));
        System.out.println(cacheDataDemo.get("ddd"));
        System.out.println(cacheDataDemo.get("ddd"));
    }

    public Object get(String key) {
        Object value = null;
        // 首先开启读锁，从缓存中去取
        rwl.readLock().lock();
        try {
            if (null == (value = map.get(key))) {
                // 必须释放读锁
                rwl.readLock().unlock();
                // 如果缓存中没有释放读锁，上写锁。如果不加锁，所有请求全部去查询数据库，就崩溃了
                rwl.writeLock().lock(); // 所有线程在此处等待  1000  1  999 (在同步代码里面再次检查是否缓存)
                try {
                    // 双重检查，防止已经有线程改变了当前的值，从而出现重复处理的情况
                    if (null == (value = map.get(key))) {
                        System.out.println(">>>查询数据...");
                        value = "Hello";
                        map.put(key, value);
                    }
                    rwl.readLock().lock(); // 加读锁 降级,这样就不会有其他线程能够改这个值，保证了数据一致性
                } finally {
                    rwl.writeLock().unlock(); // 释放写锁
                }
            }
        } finally {
            rwl.readLock().unlock();
        }
        return value;
    }
}