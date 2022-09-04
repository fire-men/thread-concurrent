package org.example.demo.线程创建.实现Callable接口带返回值;

import java.io.Serializable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

/**
 * @Author zhangLe
 * @Description
 */
public class Test implements Serializable {

    public static void main(String[] args) throws ExecutionException, InterruptedException, TimeoutException {
        Long start = 1L;
        Long end = 100000000L;

        Job job1 = new Job(start, end);
        FutureTask<Long> futureTask = new FutureTask(job1);
        Thread job = new Thread(futureTask);
        job.setName("callable-job");
        job.start();

        // 阻塞等待,直至获取到结果位置
        Long result = futureTask.get();
        // Long result = futureTask.get(1000, TimeUnit.MILLISECONDS);

        System.out.println("执行的结果：" + start + " + " + end + " = " + result);
    }
}
