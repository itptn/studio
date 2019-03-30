package com.study.chapter1.lock.cas;

/**
 * @author YuYangjun
 * @desc
 * @date 2019/3/18 4:56 PM
 */
public class ObjectSyncDemo {

    public void test1(Object arg){
        // JIT优化，消除了锁
        StringBuffer sb = new StringBuffer();
        sb.append("a");
        sb.append(arg);
        sb.append("b");

//        System.out.println(sb.toString());
    }

    public static void main(String[] args) {
        for (int i = 0; i < 1000000; i++) {
            new ObjectSyncDemo().test1("123");
        }
    }
}
