package org.example.demo.线程池.任务调度;

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
public class Job implements Serializable, Runnable {

    @SneakyThrows
    @Override
    public void run() {
        log.info("{} 正在执行任务中..........", Thread.currentThread().getName());
        StopWatch stopWatch = StopWatch.create("schedule-job-timer");
        stopWatch.start();

        // 模拟执行业务操作
        TimeUnit.SECONDS.sleep(2);
        stopWatch.stop();
        log.info("{} 执行任务完毕..........", Thread.currentThread().getName());

    }
}
