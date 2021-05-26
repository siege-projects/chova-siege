package com.oxford.core.thread.queue;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

/**
 * 定义一个容器类
 *
 * @author Chova
 * @date 2020/09/25
 */
public class QueueContainer {
    // 新建一个容量为3的队列容器
    BlockingQueue<String> queueContainer = new ArrayBlockingQueue<>(5);


    /**
     * 生产物品，放入容器
     * 如果容器已满，则等待容器被消费
     *
     * @throws InterruptedException
     */
    public void produce() throws InterruptedException {
        queueContainer.put("thing");
        System.out.println("生产一个物品放入容器");
    }

    /**
     * 消费物品，从容器中取出
     * 如果容器为空，则等待生产者生产
     *
     * @return String 消费的物品
     * @throws InterruptedException
     */
    public String consume() throws InterruptedException {
        String thing = queueContainer.take();
        System.out.println("消费一个容器中的物品");
        return thing;
    }

    /**
     * 获取容器中物品的数量
     *
     * @return int 容器中物品的数量
     */
    public int getSize() {
        return queueContainer.size();
    }
}
