package com.oxford.core.thread.juc.condition;

/**
 * Object中的线程锁测试类
 *
 * @author Chova
 * @date 2021/04/26
 */
public class ObjectThreadTest {

    /**
     * 线程类
     */
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
            // 获取当前对象的同步锁。Object中的线程相关方法是和同步锁synchronized绑定使用的，比如notify()方法等
            synchronized (this) {
                notify();
                System.out.println(Thread.currentThread().getName() + "正在唤醒等待线程...");
            }
        }
    }

    public static void main(String[] args) {
        LocalThread thread = new LocalThread("thread");

        // 获取定义的线程对象的同步锁。Object中的线程相关方法是和同步锁synchronized绑定使用的，比如start(),wait()方法等
        synchronized (thread) {
            thread.start();
            System.out.println(thread.getName() + "开始执行...");
            try {
                thread.wait();
                System.out.println(thread.getName() + "处于等待状态...");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
