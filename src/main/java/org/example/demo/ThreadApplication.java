package org.example.demo;

import lombok.extern.slf4j.Slf4j;
import java.io.Serializable;

/**
 * @Author zhangLe
 * @Description 多线程与高并发实战
 */
@Slf4j
public class ThreadApplication implements Serializable {
    public static void main(String[] args) {
        log.info("多线程与高并发实战练习........, args -- {}", args);
    }
}
