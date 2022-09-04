package org.example.demo.线程创建.线程池;

import cn.hutool.core.date.StopWatch;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;

import java.io.Serializable;
import java.util.concurrent.TimeUnit;

/**
 * @Author zhangLe
 * @Description
 */
@Slf4j
public class Job implements Serializable,Runnable {

    @SneakyThrows
    @Override
    public void run() {
        log.info("当前线程 -- {} 正在处理任务中................",Thread.currentThread().getName());
        StopWatch stopWatch = StopWatch.create("ThreadPoolExecutor-job-timer");
        stopWatch.start();
        TimeUnit.SECONDS.sleep(2);
        stopWatch.stop();
        log.info("当前线程 -- {} 处理任务结束,耗时：{}ms................",Thread.currentThread().getName(),stopWatch.getTotal(TimeUnit.MILLISECONDS));
    }
}
