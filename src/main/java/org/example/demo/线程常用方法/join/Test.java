package org.example.demo.线程常用方法.join;

import lombok.extern.slf4j.Slf4j;

import java.io.Serializable;
import java.sql.Time;
import java.util.concurrent.TimeUnit;

/**
 * @Author zhangLe
 * @Description join -- 一个线程加入另一个线程中，终止当前线程，直到加入的线程执行任务完毕为止
 */
@Slf4j
public class Test implements Serializable {
    public static void main(String[] args) {

        Thread job2 = new Thread(() -> {
            for (char i = 'a'; i <= 'z'; i++) {
                try {
                    TimeUnit.MILLISECONDS.sleep(400);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                log.info("{} -- i :{}",Thread.currentThread().getName(),i);
            }
        });

        Thread job1 = new Thread(() -> {
            for (int i = 1; i <= 10; i++) {
                if (i == 5) {
                    try {
                        // job2线程加入，直至job2执行任务完毕
                        job2.join();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }else{
                    log.info("{} -- i :{}",Thread.currentThread().getName(),i);
                }
            }
        });
        job1.start();
        job2.start();
    }
}
