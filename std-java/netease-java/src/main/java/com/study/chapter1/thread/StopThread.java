package com.study.chapter1.thread;

/**
 * @author YuYangjun
 * @desc
 * @date 2019/3/6 5:29 PM
 */
public class StopThread extends Thread{
    private int i=0, j=0;

    @Override
    public void run(){
        synchronized (this) {
            ++i;
            try {
                Thread.sleep(10000);
            } catch (Exception e) {
                e.printStackTrace();
            }
            ++j;
        }
    }

    public void print(){
        System.out.println("i=" + i + " j=" + j);
    }
}
