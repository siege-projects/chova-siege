package com.oxford.core.thread.juc.condition;

/**
 * 缓存数据的生产和消费的测试类
 *
 * @author Chova
 * @date 2021/05/08
 */
public class BoundBufferConditionTest {

    /**
     * 缓冲数据的生产和消费的绑定类的实例
     */
    private static BoundBuffer buffer = new BoundBuffer();


    /**
     * 缓冲区数据的生产者
     */
    static class Producer extends Thread {

        /**
         * 缓冲区中需要放入的数据
         */
        private static Object data;

        public Producer(String name, Object data) {
            super(name);
            this.data = data;
        }

        public void run() {
            buffer.put(data);
        }
    }

    /**
     * 缓存区数据的消费者
     */
    static class Consumer extends Thread {


        public Consumer(String name) {
            super(name);
        }

        public void run() {
            buffer.take();
        }
    }

    public static void main(String[] args) {
        /**
         * 缓冲区中需要放入的数据
         */
        Object[] data = {"产品1", "产品2", "产品3"};

        for (int i = 0; i < data.length; i++) {
            /**
             * 生产者
             */
            Producer producer = new Producer("生产者", data[i]);
            producer.run();

            /**
             * 消费者
             */
            Consumer consumer = new Consumer("消费者");
            consumer.run();
        }
    }
}
