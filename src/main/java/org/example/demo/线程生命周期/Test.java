package org.example.demo.线程生命周期;

import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;

import java.io.Serializable;
import java.util.concurrent.TimeUnit;

/**
 * @Author zhangLe
 * @Description
 */
@Slf4j
public class Test implements Serializable {

    @SneakyThrows
    public static void main(String[] args) {
        Thread job = new Thread(() -> {
            test();
        });

        Thread job2 = new Thread(() -> {
            test();
        });

        job.setName("lifecycle-job");
        job2.setName("lifecycle-job2");
        log.info("{} 状态 -- {}", job.getName(), job.getState());
        log.info("{} 状态 -- {}", job2.getName(), job2.getState());

        job.start();
        log.info("{} 状态 -- {}", job.getName(), job.getState());
        log.info("{} 状态 -- {}", job2.getName(), job2.getState());

        // 确保子线程执行完成

        TimeUnit.SECONDS.sleep(5);
        log.info("{} 状态 -- {}", job.getName(), job.getState());
        log.info("{} 状态 -- {}", job2.getName(), job2.getState());



    }

    @SneakyThrows
    public static void test() {
        for (int i = 0; i < 10; i++) {
            log.info("{} 状态 -- {}", Thread.currentThread().getName(), Thread.currentThread().getState());
            synchronized (Test.class){
                TimeUnit.MILLISECONDS.sleep(200);
                log.info("{} -- i : {}", Thread.currentThread().getName(),i);
            }
        }
    }
}
