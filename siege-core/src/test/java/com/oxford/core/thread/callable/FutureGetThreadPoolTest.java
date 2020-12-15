package com.oxford.core.thread.callable;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * Future的get()方法的线程池调用测试
 *
 * @author Chova
 * @date 2020/12/15
 */
public class FutureGetThreadPoolTest {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        // 创建一个线程数为5的线程池
        ExecutorService executor = Executors.newFixedThreadPool(5);
        // 创建一个List用来返回值Future的集合
        List<Future> futureList = new ArrayList<>();
        for (int i = 0; i < 100; i++) {
            Future<String> future = executor.submit(new CallableThread());
            futureList.add(future);
        }
        System.out.println("====================线程执行结束,开始获取线程返回值==============");
        for (Future f : futureList) {
            System.out.println(f.get());
        }
        executor.shutdown();
    }
}
