package com.oxford.core.thread.queue;

import java.util.concurrent.*;

/**
 * 阻塞队列实现生产者消费者测试
 *
 * @author Chova
 * @date 2020-09-25
 */
public class BlockingQueueTest {

    private static Producer producer = new Producer();
    private static Consumer consumer = new Consumer();

    public static void main(String[] args) {
        // 使用ThreadPoolExecutor创建线程池测试成功，但是会抛出异常
        BlockingQueueTest.threadPoolExecutorTest();
        // 使用ExecutorService创建线程池测试不会抛出异常
        BlockingQueueTest.executorServiceTest();
    }

    /**
     * 使用ThreadPoolExecutor创建线程池测试
     * 推荐使用ThreadPoolExecutor创建线程池
     */
    private static void threadPoolExecutorTest() {
        ThreadPoolExecutor poolExecutor = new ThreadPoolExecutor(2, 3, 10, TimeUnit.SECONDS, new SynchronousQueue<>());

        poolExecutor.execute(producer);
        poolExecutor.execute(consumer);
        // 执行完毕后，线程休眠2秒
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            throw new RuntimeException();
        }
        // 关闭线程池中的线程
        poolExecutor.shutdownNow();
    }

    /**
     * 使用ExecutorService创建线程池测试
     *
     * @deprecated 不推荐使用ExecutorService创建线程池，推荐使用ThreadPoolExecutor{@link BlockingQueueTest#threadPoolExecutorTest()}
     */
    @Deprecated
    private static void executorServiceTest() {
        ExecutorService service = Executors.newCachedThreadPool();
        service.submit(producer);
        service.submit(consumer);
        // 执行完毕后，线程休眠2秒
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            throw new RuntimeException();
        }
        // 关闭线程池中的线程
        service.shutdownNow();
    }
}
