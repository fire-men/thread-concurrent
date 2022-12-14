package org.example.demo.forkjoin并发框架;

import lombok.SneakyThrows;

import java.io.Serializable;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ForkJoinTask;
import java.util.concurrent.RecursiveTask;

/**
 * @Author zhangLe
 * @Description
 */
public class Test implements Serializable {
    /*阈值*/
    private static Integer threshold = 100000000 / 8;

    @SneakyThrows
    public static void main(String[] args) {
        System.out.println("=========forkjoin框架方式==========");
        Integer from = 1;
        Integer to = 100000000;
        ForkJoinPool pool = new ForkJoinPool(getProcessor());
        long startTime = System.currentTimeMillis();
        ForkJoinTask<Integer> task = pool.submit(new SumJob(from, to));
        long endTime = System.currentTimeMillis();
        System.out.println(from + "-" + to + "求和, result=" + task.get() + ",共耗时" + (endTime - startTime) + "ms");

        System.out.println("=========for循环方式==========");
        startTime = System.currentTimeMillis();
        int sum = 0;
        for (int i = 1; i <= 100000000; i++) {
            sum += i;
        }
        endTime = System.currentTimeMillis();
        System.out.println(from + "-" + to + "求和, result=" + sum + ",共耗时" + (endTime - startTime) + "ms");




    }


    //获取cpu处理器个数
    public static Integer getProcessor() {
        return Runtime.getRuntime().availableProcessors();
    }

    static class SumJob extends RecursiveTask<Integer> {
        private Integer from;
        private Integer to;

        public SumJob(Integer from, Integer to) {
            this.from = from;
            this.to = to;
        }

        @Override
        protected Integer compute() {
            Integer sum = 0;
            if (to - from < threshold) {
                for (int i = from; i <= to; i++) {
                    sum += i;
                }
            } else {
                //二分法拆分
                Integer middle = (from + to) / 2;
                //递归
                SumJob leftTask = new SumJob(from, middle);
                SumJob rightTask = new SumJob(middle + 1, to);
                leftTask.fork();
                rightTask.fork();
                sum = leftTask.join() + rightTask.join();
            }
            return sum;
        }
    }
}
