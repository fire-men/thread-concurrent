package org.example.demo.线程创建.线程池;

import cn.hutool.core.thread.RejectPolicy;
import lombok.extern.slf4j.Slf4j;

import java.io.Serializable;
import java.util.concurrent.*;

/**
 * @Author zhangLe
 * @Description
 */
@Slf4j
public class Test implements Serializable {
    public static void main(String[] args) {

        // 创建线程池
        ThreadPoolExecutor executor = null;
        try {
            executor = new ThreadPoolExecutor(3, // 核心线程数
                    5, // 最大线程数
                    1000, // 当线程数大于核心时，这是多余的空闲线程在终止前等待新任务的最长时间。
                    TimeUnit.MILLISECONDS,
                    new ArrayBlockingQueue(10), // 存放任务的阻塞队列
                    new ThreadFactory() {  // 自定义线程工程
                        @Override
                        public Thread newThread(Runnable r) {
                            Thread job = new Thread(r);
                            // job.setName("threadPool-job");
                            return job;
                        }
                    },
                    // new ThreadPoolExecutor.DiscardPolicy() //直接丢弃当前线程任务，不做任何处理
                    new ThreadPoolExecutor.AbortPolicy() // 直接抛出异常
                  //  new ThreadPoolExecutor.DiscardOldestPolicy() // 丢弃最早的任务
                  //  new ThreadPoolExecutor.CallerRunsPolicy() // 如果被丢弃的线程任务没有关闭，则执行该线程任务

            ); // 拒绝策略

            // 线程个数不能超过15个，超过之后，直接拒绝
            for (int i = 0; i < 200; i++) {
                executor.execute(new Job());
            }

        } catch (Exception e) {
            log.error(e.getMessage());
        } finally {
            executor.shutdown();
        }
    }

}
