package org.example.demo.线程常用方法.waitAndNotify;

import lombok.extern.slf4j.Slf4j;

import java.io.Serializable;
import java.util.concurrent.TimeUnit;

/**
 * @Author zhangLe
 * @Description 交替打印奇数和偶数
 *
 */
@Slf4j
public class Test implements Serializable {
    private static Integer num = 1;
    private static Object obj = new Object();

    public static void main(String[] args) {

        Thread t1 = new Thread(() -> {
            synchronized (obj) {
                while (true) {
                    if (num % 2 != 0) {
                        log.info("{} -- {}", Thread.currentThread().getName(), num++);
                        obj.notify();
                        try {
                            TimeUnit.MILLISECONDS.sleep(400);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    } else {
                        try {
                            obj.wait();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        });
        Thread t2 = new Thread(() -> {
            synchronized (obj) {
                while (true) {
                    if (num % 2 == 0) {
                        log.info("{} -- {}", Thread.currentThread().getName(), num++);
                        obj.notify();
                        try {
                            TimeUnit.MILLISECONDS.sleep(400);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    } else {
                        try {
                            obj.wait();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        });
        t1.setName("奇数");
        t1.setPriority(10);
        t2.setName("偶数");
        t2.setPriority(2);
        t1.start();
        t2.start();
    }
}
