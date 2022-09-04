package org.example.demo.juc.atomic.基本类型;

import lombok.extern.slf4j.Slf4j;

import java.io.Serializable;
import java.util.concurrent.atomic.AtomicLong;

/**
 * @Author zhangLe
 * @Description AtomicLong测试
 * <p>
 * AtomicLong如何保证线程安全的
 * 1、通过Unsafe调用cas操作，保证原子性
 * 2、通过volatile保证了可见性及有序性
 */
@Slf4j
public class AtomicLongTest implements Serializable {
    private static final AtomicLong counter = new AtomicLong(0);

    public static void main(String[] args) {

        // 获取数据
        long l = counter.get();
        log.info(" i -- {}", l);

        // 设置数据
        counter.set(1);
        log.info(" i -- {}", counter.get());

        // ---------------原子操作------------------
        // 自增操作
        log.info(" 自增后的结果为 ：{}", counter.incrementAndGet());
        // 自减操作
        log.info(" 自减后的结果为 ：{}", counter.decrementAndGet());
        // 添加指定数据并返回
        log.info(" 自定义添加数据后结果为 ：{}", counter.addAndGet(4));
        // 更新指定的数据
        log.info(" 更新指定数据后结果为 ：{}", counter.updateAndGet(x -> {
            return 1;
        }));


    }
}
