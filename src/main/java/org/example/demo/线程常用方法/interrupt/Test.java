package org.example.demo.线程常用方法.interrupt;

import lombok.extern.slf4j.Slf4j;

import java.io.Serializable;
import java.sql.Time;
import java.util.concurrent.TimeUnit;

/**
 * @Author zhangLe
 * @Description interrupt -- 中断线程
 */
@Slf4j
public class Test implements Serializable {
    public static void main(String[] args) {

        new Thread(() -> {
            for (int i = 1; i <= 10; i++) {
                // 检查是否线程已经中断
                if (!Thread.currentThread().isInterrupted()) {
                    try {
                        TimeUnit.MILLISECONDS.sleep(400);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    // 中断线程(仅仅一个标志)
                    if (i == 5) {
                        Thread.currentThread().interrupt();
                    }
                    log.info("当前i为 ：{}", i);
                }
            }
        }).start();
    }
}
