package com.oxford.core.thread.callable;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.FutureTask;

/**
 * FutureTask的线程池测试
 *
 * @author Chova
 * @date 2020/12/15
 */
public class FutureTaskGetThreadPoolTest {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        // 创建一个线程数为5的线程池
        ExecutorService executor = Executors.newFixedThreadPool(5);
        // 创建一个List用来存储返回值FutureTask集合
        List<FutureTask<String>> futureTaskList = new ArrayList<>();
        for (int i = 0; i < 100; i++) {
            FutureTask<String> futureTask = new FutureTask<>(new CallableThread());
            futureTaskList.add(futureTask);
            executor.submit(futureTask);
        }
        System.out.println("====================线程执行结束,开始获取线程返回值==============");
        for (FutureTask<String> f : futureTaskList) {
            System.out.println(f.get());
        }
        executor.shutdown();
    }
}
