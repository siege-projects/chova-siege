package com.oxford.core.thread.queue;

/**
 * 消费者
 *
 * @author Chova
 * @date 2020-09-25
 */
public class Consumer implements Runnable {

    QueueContainer container = new QueueContainer();

    @Override
    public void run() {
        try {
            while (true) {
                System.out.println(System.currentTimeMillis() + "：消费者开始消费物品");
                container.consume();
                System.out.println(System.currentTimeMillis() + "：消费者消费物品完毕");
                System.out.println("容器中物品数量：" + container.getSize());
                // 消费者休眠3秒
                Thread.sleep(3000);
            }
        } catch (InterruptedException e) {
            throw new RuntimeException(e.getMessage());
        }
    }
}
