package com.oxford.core.thread.juc.locksupport;

import java.util.concurrent.locks.LockSupport;

/**
 * LockSupport测试类
 *
 * @author Chova
 * @date 2021/05/13
 */
public class LockSupportTest {

    /**
     * 主线程
     */
    private static Thread mainThread = Thread.currentThread();

    public static void main(String[] args) {
        /**
         * 自定义线程
         */
        LocalThread thread = new LocalThread("thread");

        thread.start();
        System.out.println("当前线程：" + Thread.currentThread().getName() + "启动自定义线程thread...");

        // 阻塞主线程
        System.out.println("当前线程：" + Thread.currentThread().getName() + "即将阻塞...");
        LockSupport.park(mainThread);

        System.out.println("当前线程：" + Thread.currentThread().getName() + "继续执行...");
    }

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
            LockSupport.unpark(mainThread);
            System.out.println("当前线程：" + Thread.currentThread().getName() + "正在唤醒其余线程...");
        }
    }
}
