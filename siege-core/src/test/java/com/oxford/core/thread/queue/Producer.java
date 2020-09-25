package com.oxford.core.thread.queue;

/**
 * 生产者
 *
 * @author Chova
 * @date 2020-09-25
 */
public class Producer implements Runnable {

    QueueContainer container = new QueueContainer();

    @Override
    public void run() {
        try {
            while (true) {
                System.out.println(System.currentTimeMillis() + "：生产者开始生产物品");
                container.produce();
                System.out.println(System.currentTimeMillis() + "：生产者生产物品完毕");
                System.out.println("容器中物品数量：" + container.getSize());
                // 生产者休眠3秒
                Thread.sleep(3000);
            }
        } catch (InterruptedException e) {
            throw new RuntimeException(e.getMessage());
        }
    }
}
