package org.example.demo.juc.lock.基于CAS实现自定义lock锁;

import cn.hutool.core.util.ObjectUtil;
import lombok.extern.slf4j.Slf4j;

import javax.xml.ws.Holder;
import java.io.Serializable;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicStampedReference;

/**
 * @Author zhangLe
 * @Description 基于CAS实现自定义锁
 */
@Slf4j
public class Test implements Serializable {
    private static final MyLock lock = new MyLock(new AtomicInteger(0),Integer.MAX_VALUE);

    public static void main(String[] args) {

        for (int i = 0; i < 10; i++) {
            new Thread(() -> {
                lock.lock();
                try {
                    log.info(" {} -- 执行业务操作.................", Thread.currentThread());
                    // TimeUnit.SECONDS.sleep(2);
                } catch (Exception e) {
                    e.printStackTrace();
                } finally {
                    lock.unlock();
                }
            }).start();
        }

    }

    static class MyLock {
        private AtomicInteger state;
        private Thread holdLockThread;
        private AtomicInteger retryNum;
        private Integer maxRetryNum;

        public MyLock(AtomicInteger state) {
            this.state = state;
            this.retryNum = new AtomicInteger(0);
            this.maxRetryNum = 5;
        }

        public MyLock(AtomicInteger state, Integer maxRetryNum) {
            this.state = state;
            this.retryNum = new AtomicInteger(0);
            this.maxRetryNum = maxRetryNum;
        }

        public void lock() {
            if (state.compareAndSet(0, 1)) {
                holdLockThread = Thread.currentThread();
                log.info(" {} -- 获取到了锁..............", Thread.currentThread().getName());
            } else {
                log.info(" {} -- 正在重试获取锁操作...............", Thread.currentThread());
                retryNum.incrementAndGet();
                retry();
            }
        }

        public void retry() {
            if (retryNum.get() >= maxRetryNum) {
                log.info("{} -- 重试了{}次，即将退出...", Thread.currentThread().getName(), retryNum.get());
                throw new RuntimeException("重试了" + retryNum + "后退出了");
            }
            lock();
        }


        public void unlock() {
            if (state.compareAndSet(1, 0) && holdLockThread == Thread.currentThread()) {
                log.info(" {} --准备释放锁................", Thread.currentThread().getName());
                // this.holdLockThread = null;
            }
        }

    }
}
