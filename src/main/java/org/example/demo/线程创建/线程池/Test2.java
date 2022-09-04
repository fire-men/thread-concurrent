package org.example.demo.线程创建.线程池;

import lombok.extern.slf4j.Slf4j;
import org.example.demo.线程创建.实现Callable接口带返回值.Job;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.concurrent.*;

/**
 * @Author zhangLe
 * @Description
 */
@Slf4j
public class Test2 implements Serializable {
    public static void main(String[] args) {
        // 创建线程池
        ThreadPoolExecutor executor = null;
        try {
            executor = new ThreadPoolExecutor(3,
                    5,
                    1000,
                    TimeUnit.MILLISECONDS,
                    new ArrayBlockingQueue(10),
                    new ThreadFactory() {
                        @Override
                        public Thread newThread(Runnable r) {
                            Thread job = new Thread(r);
                            // job.setName("threadPool-job");
                            return job;
                        }
                    },
                    new ThreadPoolExecutor.AbortPolicy());

            // 线程个数不能超过15个，超过之后，直接拒绝
            ArrayList<Long> sumList = new ArrayList<>();
            for (int i = 0; i < 16; i++) {
                Future<Long> future = executor.submit(new Job(1L, 100000000L));
                // 阻塞等待执行结果(知道当前线程执行完毕后，其他线程才可以执行任务)
                Long r = future.get();
                sumList.add(r);
            }

           System.out.println("共有结果："+sumList.size()+"个，数据为"+sumList);
        } catch (Exception e) {
            log.error(e.getMessage());
        } finally {
            executor.shutdown();
        }
    }


}
