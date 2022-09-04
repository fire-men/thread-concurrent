package org.example.demo.并发关键字;

import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;

import java.io.Serializable;
import java.sql.Time;
import java.util.concurrent.TimeUnit;

/**
 * @Author zhangLe
 * @Description volatile测试
 * 保证可见性 + 有序性 ，不能保证原子性
 *
 * 测试结果：
 * 共享变量没有被volatile关键字修饰，一个线程修改变量后，其他线程一样可以发现
 *
 * 待验证volatile.............
 */
@Slf4j
public class VolatileTest implements Serializable {
    // private static  Boolean flag = true;
    private static Integer num = 0;

    @SneakyThrows
    public static void main(String[] args) {

        new Thread(() -> {
            while (num == 0) {
                try {
                    TimeUnit.MILLISECONDS.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                log.info("{} 执行业务.........", Thread.currentThread().getName());
            }
        }).start();

        new Thread(() -> {
            try {
                TimeUnit.SECONDS.sleep(2);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            num = 2;
        }).start();

        // main线程修改共享变量flag
        // TimeUnit.SECONDS.sleep(2);
        // flag = false;
        // num = 1;

    }
}
