package org.example.demo.juc.lock;

import lombok.extern.slf4j.Slf4j;

import java.io.Serializable;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * @Author zhangLe
 * @Description 读写锁
 * 读写分离
 */
@Slf4j
public class ReentrantReadWriteLockTest implements Serializable {
    private static final ReentrantReadWriteLock lock = new ReentrantReadWriteLock();

    public static void main(String[] args) {

        for (int i = 0; i < 2; i++) {
            new Thread(()->{
                readData();
            }).start();
        }

        for (int i = 0; i < 2; i++) {
            new Thread(()->{
                writeData();
            }).start();
        }

    }

    public static void writeData() {
        // 获取写锁
        lock.writeLock().lock();
        try {
            log.info(" {} -- 正在执行写数据操作..............", Thread.currentThread().getName());
            TimeUnit.SECONDS.sleep(2);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.writeLock().unlock();
        }
    }

    public static void readData() {
        // 获取读锁
        lock.readLock().lock();
        try {
            log.info(" {} -- 正在执行读取数据操作..............", Thread.currentThread().getName());
            TimeUnit.MILLISECONDS.sleep(500);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.readLock().unlock();
        }
    }


}
