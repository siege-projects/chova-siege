package com.oxford.core.thread.produceconsume;

/**
 * 仓库类
 *
 * @author Chova
 * @date 2020/09/23
 */
public class Depot {

    /**
     * 仓库容量
     */
    private int capacity;
    /**
     * 仓库的实际容量
     */
    private int size = 0;

    public Depot(int capacity) {
        this.capacity = capacity;
        this.size = 0;
    }

    /**
     * 生产方法
     *
     * @param val 需要生产的数量
     */
    public synchronized void produce(int val) {


        try {
            // want表示想要生产的数量，因为可能想要生产的数量太多，需要进行多次生产
            int want = val;
            while (want > 0) {
                // 如果库存已满时，等待消费者消费产品
                while (size >= capacity) {
                    wait();
                }
                /*
                 * 获取实际生产的数量，即新增的产品数量
                 * 	- 如果库存和想要生产的数量之和大于仓库容量时，则实际生产数量等于总的容量减去当前容量
                 * 	- 如果库存和想要生产的数量之和小于等于仓库容量，则实际生产数量等于想要生产的数量
                 */
                int increment = (size + want) > capacity ? (capacity - size) : want;
                size += increment;
                want -= increment;
                System.out.printf("线程%s : 生产%3d个产品 --> 生产者想要生产%3d个产品，生产者实际生产%3d个产品，产品库存数量为%3d个\n", Thread.currentThread().getName(), val, want, increment, size);
                // 生产完成通知消费者消费
                notifyAll();
            }
        } catch (InterruptedException e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    /**
     * 消费方法
     *
     * @param val 消费的数量
     */
    public synchronized void consume(int val) {
        try {
            // want表示客户消费的数量，因为想要消费的数量太多，需要进行多次消费
            int want = val;
            while (want > 0) {
                // 如果库存为空时，等待生产者生产产品
                while (size <= 0) {
                    wait();
                }
                /*
                 * 获取实际消费的数量，即减少的数量
                 * 	- 如果库存的数量小于想要消费的数量，则实际消费数量等于库存量
                 * 	- 如果库存的数量等于或者大于想要消费的数量，则实际消费数量等于想要消费的数量
                 */
                int decrease = (size < want) ? size : want;
                size -= decrease;
                want -= decrease;
                System.out.printf("线程%s : 消费%3d个产品 <-- 消费者想要消费%3d个产品，消费者实际消费%3d个产品，产品库存数量为%3d个\n", Thread.currentThread().getName(), val, want, decrease, size);
                // 消费完成通知生产者生产
                notifyAll();
            }
        } catch (InterruptedException e) {
            throw new RuntimeException(e.getMessage());
        }
    }
}
