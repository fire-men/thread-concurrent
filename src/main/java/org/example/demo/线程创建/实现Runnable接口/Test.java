package org.example.demo.线程创建.实现Runnable接口;

import org.example.demo.线程创建.实现Runnable接口.Job;

import java.io.Serializable;

/**
 * @Author zhangLe
 * @Description
 */
public class Test implements Serializable {
    public static void main(String[] args) {
        Thread job = new Thread(new Job());
        job.setName("runnable-job");
        job.start();
    }
}
