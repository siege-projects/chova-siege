package com.oxford.core.thread.callable;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * Future中get()方法的阻塞性验证
 *
 * @author Chova
 * @date 2020/12/15
 */
public class FutureGetTest {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        ExecutorService executor = Executors.newFixedThreadPool(3);
        for (int i = 0; i < 100; i++) {
            // 任务提交之后,返回结果Future. 此时任务还没有结束,阻塞等待返回值返回
            Future<String> future = executor.submit(new CallableThread());
            // 返回值返回后输出返回值,此时线程池没有效果
            System.out.println(future.get());
        }
        executor.shutdown();
    }
}
