package org.example.demo.juc.atomic.ABA解决;

import lombok.extern.slf4j.Slf4j;

import java.io.Serializable;
import java.util.concurrent.atomic.AtomicStampedReference;

/**
 * @Author zhangLe
 * @Description Cas中aba问题解决
 */
@Slf4j
public class AtomicStampReferenceTest implements Serializable {
    private static final AtomicStampedReference<Integer> test = new AtomicStampedReference<Integer>(0,0);

    public static void main(String[] args) {
        // int stamp = test.getStamp();
        log.info(" 当前时间戳为 ：{}",test.getStamp());

        log.info(" 当前内容为 ：{}",test.getReference());

        // 修改数据
        test.set(100, test.getStamp()+1);
        log.info(" 修改后的数据为 ：{}",test.getReference());
        log.info(" 当前时间戳为 ：{}",test.getStamp());

        // cas操作
        if (test.compareAndSet(100,200,0,2)) {
            log.info(" cas修改后的数据为 ：{}",test.getReference());
            log.info(" cas当前时间戳为 ：{}",test.getStamp());
        }

    }
}
