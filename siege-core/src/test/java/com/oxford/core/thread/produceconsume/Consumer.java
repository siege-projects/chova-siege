package com.oxford.core.thread.produceconsume;

/**
 * 消费者
 *
 * @author Chova
 * @date 2020/09/23
 */
public class Consumer {
    private Depot depot;

    public Consumer(Depot depot) {
        this.depot = depot;
    }

    /**
     * 新建一个消费者，用于消费产品
     *
     * @param val 需要消费的数量
     */
    public void consume(final int val) {
        new Thread() {
            public void run() {
                depot.consume(val);
            }
        }.start();
    }
}
