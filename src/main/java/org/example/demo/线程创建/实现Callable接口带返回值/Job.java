package org.example.demo.线程创建.实现Callable接口带返回值;

import cn.hutool.core.date.StopWatch;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;

import java.io.Serializable;
import java.util.concurrent.Callable;
import java.util.concurrent.TimeUnit;

/**
 * @Author zhangLe
 * @Description
 */
@Slf4j
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = false)
@AllArgsConstructor
@NoArgsConstructor
public class Job implements Serializable, Callable<Long> {
    Long start;
    Long end;

    // --------------计算指定范围数据之和-------------------
    @Override
    public Long call() throws Exception {
        log.info("当前线程 -- {} 正在计算任务中.................",Thread.currentThread().getName());
        StopWatch stopWatch = StopWatch.create("callable-job-timer");
        stopWatch.start();
        Long sum = 0L;
        for (Long i = start; i <= end; i++) {
            sum += i;
        }
        stopWatch.stop();
        log.info("当前线程 -- {} 正在计算任务完毕，耗时：{}ms.................",Thread.currentThread().getName(),stopWatch.getTotal(TimeUnit.MILLISECONDS));

        return sum;

    }
}
