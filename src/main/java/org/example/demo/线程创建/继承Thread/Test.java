package org.example.demo.线程创建.继承Thread;

import java.io.Serializable;

/**
 * @Author zhangLe
 * @Description
 */
public class Test implements Serializable {
    public static void main(String[] args) {
        Job job = new Job();
        job.setName("thread-job");
        job.start();
    }
}
