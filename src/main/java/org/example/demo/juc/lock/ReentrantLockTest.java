package org.example.demo.juc.lock;

import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;

import java.io.Serializable;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @Author zhangLe
 * @Description
 */
@Slf4j
public class ReentrantLockTest implements Serializable {
    private static final ReentrantLock lock = new ReentrantLock();

    private static  Long tickets = 10L;

    public static void main(String[] args) {

        Job job = new Job();
        for (int i = 0; i < 3; i++) {
            new Thread(job).start();
        }

    }

    static class Job implements Runnable {
        @SneakyThrows
        @Override
        public void run() {
            while (true) {
                TimeUnit.MILLISECONDS.sleep(200);
                lock.lock();
                try {
                    if(tickets == 0){
                        break;
                    }
                    log.info(" {} -- 抢票成功，剩余{}张票", Thread.currentThread().getName(), --tickets);
                    Thread.yield();
                } catch (Exception e) {
                    log.error(e.getMessage());
                } finally {
                    lock.unlock();
                }
            }
        }
    }
}
