package com.study.chapter1.thread;

/**
 * @author YuYangjun
 * @desc
 * @date 2019/3/6 5:26 PM
 */
public class Demo3 {

    public static void main(String[] args) throws InterruptedException{
        StopThread thread = new StopThread();
        thread.start();
        Thread.sleep(1000);

        thread.stop();
//        thread.interrupt();

        while(thread.isAlive()){
            // 确保线程已经终止
        }

        thread.print();
    }
}
