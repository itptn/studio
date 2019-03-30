package com.study.chapter1.thread;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Calendar;

/**
 * @author YuYangjun
 * @desc
 * @date 2019/3/8 2:39 PM
 */
public class FileShare {

    public static void main(String[] args) {
        new Thread(() -> {
            try {
                while(true){
                    Files.write(Paths.get("Demo7.log"), ("当前时间" + Calendar.getInstance().getTime().toString()).getBytes());
                    Thread.sleep(1000);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }).start();

        new Thread(() -> {
            try {
                while(true){
                    Thread.sleep(1000);
                    byte[] allBytes = Files.readAllBytes(Paths.get("Demo7.log"));
                    System.out.println(new String(allBytes));
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }).start();
    }
}
