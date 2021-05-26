package com.oxford.core.thread.produceconsume;

/**
 * 生产者
 *
 * @author Chova
 * @date 2020/09/23
 */
public class Producer {
    private Depot depot;

    public Producer(Depot depot) {
        this.depot = depot;
    }

    /**
     * 新建一个生产者线程，用于生产产品
     *
     * @param val 需要生产的数量
     */
    public void produce(final int val) {
        new Thread(() -> depot.produce(val)).start();
    }
}
