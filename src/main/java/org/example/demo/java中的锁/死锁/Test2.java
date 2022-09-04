package org.example.demo.java中的锁.死锁;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

import java.io.Serializable;

/**
 * @Author zhangLe
 * @Description 解决死锁问题
 *  保证多个线程获取锁和释放锁的顺序保持一致
 */
@Slf4j
public class Test2 implements Serializable {
    private static final Object lock1 = new Object();
    private static final Object lock2 = new Object();

    public static void main(String[] args) {

        new Thread(new Job1(lock1,lock2)).start();

        new Thread(new Job2(lock1,lock2)).start();


    }

    @Data
    @AllArgsConstructor
    static class Job1 implements Runnable{
        private Object lock1;
        private Object lock2;

        @Override
        public void run() {
            synchronized (lock1){
                log.info(" {} -- 占据了锁 : {}...",Thread.currentThread().getName(),"lock1");
                synchronized (lock2){
                    log.info(" {} -- 占据了锁 : {}...",Thread.currentThread().getName(),"lock2");
                }
                log.info(" {} -- 释放了锁 : {}...",Thread.currentThread().getName(),"lock2");
            }
            log.info(" {} -- 释放了锁 : {}...",Thread.currentThread().getName(),"lock1");
        }
    }

    @Data
    @AllArgsConstructor
    static class Job2 implements Runnable{
        private Object lock1;
        private Object lock2;

        @Override
        public void run() {
            synchronized (lock1){
                log.info(" {} -- 占据了锁 : {}...",Thread.currentThread().getName(),"lock1");
                synchronized (lock2){
                    log.info(" {} -- 占据了锁 : {}...",Thread.currentThread().getName(),"lock2");
                }
                log.info(" {} -- 释放了锁 : {}...",Thread.currentThread().getName(),"lock2");
            }
            log.info(" {} -- 释放了锁 : {}...",Thread.currentThread().getName(),"lock1");
        }
    }
}
