package com.oxford.core.thread.produceconsume;

/**
 * 生产者消费者测试
 *
 * @author Chova
 * @date 2020/9/23
 */
public class ProducerConsumerTest {
    public static void main(String[] args) {
        Depot depot = new Depot(100);
        System.out.println("仓库容量为100");
        Producer producer = new Producer(depot);
        Consumer consumer = new Consumer(depot);

        producer.produce(60);
        System.out.println("生产者生产60个产品");
        producer.produce(120);
        System.out.println("生产者生产120个产品");
        consumer.consume(90);
        System.out.println("消费者消费90个产品");
        consumer.consume(150);
        System.out.println("消费者消费150个产品");
        producer.produce(110);
        System.out.println("生产者生产110个产品");
    }
}
