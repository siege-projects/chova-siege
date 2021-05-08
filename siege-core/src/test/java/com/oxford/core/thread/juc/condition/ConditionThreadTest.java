package com.oxford.core.thread.juc.condition;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Condition中的线程锁测试类
 *
 * @author Chova
 * @date 2021/04/28
 */
public class ConditionThreadTest {

    /**
     * 定义一个独占锁
     */
    private static Lock lock = new ReentrantLock();

    /**
     * 定义锁的条件
     */
    private static Condition condition = lock.newCondition();

    static class LocalThread extends Thread {

        /**
         * 自定义线程的构造函数，根据指定的名称构造线程
         *
         * @param name 线程的名称
         */
        public LocalThread(String name) {
            super(name);
        }

        public void run() {
            // 获取锁
            lock.lock();

            try {
                condition.signal();
                System.out.println(Thread.currentThread().getName() + "正在唤醒等待线程...");
            } finally {
                // 释放锁
                lock.unlock();
            }
        }
    }

    public static void main(String[] args) {
        Thread thread = new LocalThread("thread");

        // 获取锁
        lock.lock();

        thread.start();
        System.out.println(thread.getName() + "开始执行...");
        try {
            condition.await();
            System.out.println(thread.getName() + "处于等待状态...");
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            // 释放锁
            lock.unlock();
        }
    }
}
