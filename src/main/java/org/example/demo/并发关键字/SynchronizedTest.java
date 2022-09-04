package org.example.demo.并发关键字;

import lombok.extern.slf4j.Slf4j;

import java.io.Serializable;
import java.sql.Time;
import java.util.concurrent.TimeUnit;

/**
 * @Author zhangLe
 * @Description synchronized -- 线程同步
 */
@Slf4j
public class SynchronizedTest implements Serializable {

    public static void main(String[] args) {

        Job job = new Job();
        for (int i = 1; i <= 2; i++) {
            new Thread(job).start();
        }
    }

    static class Job implements Runnable{
        @Override
        public void run() {
            log.info("{} -- state :{}",Thread.currentThread().getName(),Thread.currentThread().getState());
            synchronized (SynchronizedTest.class){
                try {
                    TimeUnit.SECONDS.sleep(2);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

}
