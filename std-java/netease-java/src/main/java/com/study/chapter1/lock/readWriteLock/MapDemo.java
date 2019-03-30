package com.study.chapter1.lock.readWriteLock;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * @author YuYangjun
 * @desc
 * 将HashMap改造一个并发安全的
 * 比HastTable的实现，效率高，读取的适合并不会同步执行
 * @date 2019/3/20 7:43 PM
 */
public class MapDemo {
    private final Map<String, Object> m = new HashMap();
    private final ReentrantReadWriteLock rwl = new ReentrantReadWriteLock();
    private final Lock r = rwl.readLock();
    private final Lock w = rwl.writeLock();

    public Object get(String key){
        r.lock();
        try {
            return m.get(key);
        } finally {
            r.unlock();
        }
    }

    public Object[] allKeys(){
        r.lock();
        try {
            return m.keySet().toArray();
        } finally {
            r.unlock();
        }
    }

    public void put(String key, Object value){
        w.lock();
        try {
            m.put(key, value);
        } finally {
            w.unlock();
        }
    }
}