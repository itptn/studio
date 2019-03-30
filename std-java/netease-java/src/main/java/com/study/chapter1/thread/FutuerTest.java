package com.study.chapter1.thread;


import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

/**
 * @author YuYangjun
 * @desc
 * @date 2019/3/22 4:32 PM
 */
public class FutuerTest {

    public static void main(String[] args) throws ExecutionException, InterruptedException{
        FutureTask<String> futureTask = new FutureTask(new Callable<String>() {
            @Override
            public String call() throws Exception {
                Thread.sleep(1000L);
                System.out.println("接口调用成功，返回结果");
                return "OK";
            }
        });

        new Thread(futureTask).start();

        System.out.println(futureTask.get().toString());
    }
}
