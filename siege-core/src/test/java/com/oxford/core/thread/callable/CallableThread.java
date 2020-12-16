package com.oxford.core.thread.callable;

import java.util.Random;
import java.util.concurrent.Callable;

/**
 * Callable执行的线程
 *
 * @author Chova
 * @date 2020/12/14
 */
public class CallableThread implements Callable<String> {

    /**
     * 返回线程的序列
     *
     * @return String 线程的序列
     * @throws Exception
     */
    @Override
    public String call() throws Exception {
        Thread.sleep(2000);
        int n = new Random().nextInt(10);
        return Thread.currentThread().getName() + "-" + n;
    }
}
