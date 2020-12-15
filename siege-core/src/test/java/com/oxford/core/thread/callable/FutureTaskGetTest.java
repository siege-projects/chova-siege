package com.oxford.core.thread.callable;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

/**
 * FutureTask测试
 *
 * @author Chova
 * @date 2020/12/15
 */
public class FutureTaskGetTest {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        for (int i = 0; i < 100; i++) {
            // 通过Callable创建FutureTask
            FutureTask<String> futureTask = new FutureTask<>(new CallableThread());
            // 因为Future实现了Runnable接口,所以可以使用FutureTask创建一个线程对象
            Thread thread = new Thread(futureTask);
            thread.start();
            System.out.println(futureTask.get());
        }
    }
}
