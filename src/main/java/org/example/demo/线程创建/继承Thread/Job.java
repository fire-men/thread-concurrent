package org.example.demo.线程创建.继承Thread;

import lombok.extern.slf4j.Slf4j;

import java.io.Serializable;
import java.sql.Time;
import java.util.concurrent.TimeUnit;

/**
 * @Author zhangLe
 * @Description
 */
@Slf4j
public class Job extends Thread implements Serializable {

    @Override
    public void run() {
        log.info("threadName :{},正在执行任务中...",Thread.currentThread().getName());
    }
}
