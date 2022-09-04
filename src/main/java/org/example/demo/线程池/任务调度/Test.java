package org.example.demo.线程池.任务调度;

import lombok.extern.slf4j.Slf4j;

import java.io.Serializable;
import java.util.concurrent.*;

/**
 * @Author zhangLe
 * @Description 任务调度线程池 -- xxl-job
 */
@Slf4j
public class Test implements Serializable {
    public static void main(String[] args) {
        ScheduledThreadPoolExecutor she = null;
        try {
            she = new ScheduledThreadPoolExecutor(
                    3,
                    new ThreadFactory() {
                        @Override
                        public Thread newThread(Runnable r) {
                            return new Thread(r);
                        }
                    },
                    new ThreadPoolExecutor.AbortPolicy());

            // 从当前时间开始延迟xx开始执行任务.............
            // ScheduledFuture<?> scheduledFuture = she.schedule(new Job(), 2, TimeUnit.SECONDS);
            // 获取剩余的延迟执行时间
            // long delay = scheduledFuture.getDelay(TimeUnit.SECONDS);
            // log.info("剩余的延迟执行时间为 :{}", delay);

            // 在延迟5s后，以固定间隔5s执行任务
            ScheduledFuture<?> scheduledFuture = she.scheduleAtFixedRate(new Job(), 5, 5, TimeUnit.SECONDS);
            // 必须调用get方法，否则不会执行任务.............
            Object o = scheduledFuture.get();

        } catch (Exception e) {
            log.error(e.getMessage());
        } finally {
            she.shutdown();
        }
    }
}
