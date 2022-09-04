package org.example.demo.threadLocal使用;

import java.io.Serializable;
import java.util.concurrent.TimeUnit;

/**
 * @Author zhangLe
 * @Description
 *
 * ThreadLocal只能获取和存放当前线程中的数据
 */
public class ThreadLocalTest implements Serializable {
    public static void main(String[] args) throws InterruptedException {

        Thread job1 = new Thread(() -> {
            while (true) {
                ThreadLocalUtil.set("job1");
            }
        });

        Thread job2 = new Thread(() -> {
            while (true) {
                ThreadLocalUtil.set("job2");
            }
        });

        job1.start();
        job2.start();

        // 从main线程获取
        TimeUnit.SECONDS.sleep(2);
        // Object o = ThreadLocalUtil.get();
        // System.out.println(o); // null

        ThreadLocalUtil.set("main");
        ThreadLocalUtil.set("main2");
        Object o = ThreadLocalUtil.get();
        System.out.println(o);

    }
}
