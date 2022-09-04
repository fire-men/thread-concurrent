package org.example.demo.juc.lock.基于AQS实现自定义lock锁;

import lombok.extern.slf4j.Slf4j;

import java.io.Serializable;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.AbstractQueuedSynchronizer;

/**
 * @Author zhangLe
 * @Description 基于AQS实现自定义Lock锁
 */
@Slf4j
public class Test implements Serializable {

    public static void main(String[] args) {
        MyLock lock = new MyLock();

        for (int i = 0; i < 10; i++) {
            new Thread(() -> {
                lock.lock();
                try {
                    log.info(" 当前锁占有锁，还未释放...................");
                    TimeUnit.SECONDS.sleep(5);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    lock.unlock();
                }

            }).start();
        }


    }

    static class MyLock {
        private Syn syn = new Syn();

        class Syn extends AbstractQueuedSynchronizer {

            void lock() {
                if (compareAndSetState(0, 1)) {
                    setExclusiveOwnerThread(Thread.currentThread());
                } else {
                    acquire(1);
                }
            }

            @Override
            protected boolean tryAcquire(int acquires) {
                final Thread current = Thread.currentThread();
                int c = getState();
                if (c == 0) {
                    if (compareAndSetState(0, acquires)) {
                        setExclusiveOwnerThread(current);
                        return true;
                    }
                }
                else if (current == getExclusiveOwnerThread()) {
                    int nextc = c + acquires;
                    if (nextc < 0) // overflow
                    {
                        throw new Error("Maximum lock count exceeded");
                    }
                    setState(nextc);
                    return true;
                }
                return false;
            }

            @Override
            protected boolean tryRelease(int releases) {
                int c = getState() - releases;
                if (Thread.currentThread() != getExclusiveOwnerThread()) {
                    throw new IllegalMonitorStateException();
                }
                boolean free = false;
                if (c == 0) {
                    free = true;
                    setExclusiveOwnerThread(null);
                }
                setState(c);
                return free;
            }
        }

        public void lock() {
            syn.lock();
        }

        public void unlock() {
            syn.release(1);
        }
    }
}
