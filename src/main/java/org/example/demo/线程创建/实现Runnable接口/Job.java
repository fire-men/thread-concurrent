package org.example.demo.线程创建.实现Runnable接口;

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
        log.info("当前线程 -- {},处理任务中.............");
        TimeUnit.SECONDS.sleep(2);
        log.info("当前线程 -- {},处理任务完毕.............");

    }
}
