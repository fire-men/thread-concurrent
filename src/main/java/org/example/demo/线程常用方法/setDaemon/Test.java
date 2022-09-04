package org.example.demo.线程常用方法.setDaemon;

import lombok.extern.slf4j.Slf4j;

import java.io.Serializable;
import java.sql.Time;
import java.util.concurrent.TimeUnit;

/**
 * @Author zhangLe
 * @Description setDaemon() --- 守护线程
 * <p>
 * main线程执行完毕，守护线程也跟着over了
 */
@Slf4j
public class Test implements Serializable {
    public static void main(String[] args) throws InterruptedException {

        Thread thread = new Thread(() -> {
            while (true) {
                log.info("{} --执行中.................", Thread.currentThread().getName());
                try {
                    TimeUnit.MILLISECONDS.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        thread.setDaemon(true);
        thread.start();

        // main线程等待2s,观察守护线程thread是否关闭
         TimeUnit.SECONDS.sleep(2);

    }
}
