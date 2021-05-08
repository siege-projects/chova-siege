package com.oxford.core.thread.juc.condition;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 缓存数据的生产和消费的绑定类
 *
 * @author Chova
 * @date 2021/04/28
 */
public class BoundBuffer {

    /**
     * 定义一个独占锁
     */
    public static final Lock lock = new ReentrantLock();

    /**
     * 定义锁的条件，表示缓存不是满的
     */
    public static final Condition notFull = lock.newCondition();

    /**
     * 定义锁的条件，表示缓存不是空的
     */
    public static final Condition notEmpty = lock.newCondition();

    /**
     * 定义一个缓存含义的数组，用于放置和取出数据
     */
    public static final Object[] buffer = new Object[10];

    /**
     * 定义缓存中数据的个数，缓存放置数据的位置，缓存取出数据的位置
     */
    int count, putIndex, takeIndex;


    /**
     * 生产方法
     * 将数据放入缓冲区中
     *
     * @param data 数据
     */
    public void put(Object data) {
        // 获取锁
        lock.lock();

        try {
            while (count == buffer.length) {
                // 如果缓冲区的数量已满，则进行等待。直到缓冲区不是满的，才将数据继续放入缓冲区中
                notFull.await();
            }
            // 将数据放入缓冲区中
            buffer[putIndex] = data;
            // 放入数据的索引增加1
            if (++putIndex == buffer.length) {
                // 如果缓冲区已满之后，则将放入数据的索引初始化，以便进行下一次数据的放入
                putIndex = 0;
            }
            // 缓冲区中数据的个数增加1
            ++count;
            // 此时缓冲区数据不为空，唤醒处于等待条件不为空状态的线程
            notEmpty.signal();

            System.out.println("生产：" + data);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            // 释放锁
            lock.unlock();
        }
    }

    /**
     * 消费方法
     * 从缓冲区中取出数据
     *
     * @return Object 缓冲区消费取出的数据
     */
    public Object take() {

        /**
         * 缓冲区消费取出的数据
         */
        Object data = null;

        // 获取锁
        lock.lock();

        try {
            while (count == 0) {
                // 如果缓冲区的数量为空，则进行等待。直到缓冲区不是空的，才从缓冲区中继续取出数据
                notEmpty.await();
            }
            // 将数据从缓冲区中取出
            data = buffer[takeIndex];
            // 取出数据的索引增加1
            if (++takeIndex == buffer.length) {
                // 如果缓冲区的数据都之后，则将取出数据的索引初始化，以便进行下一次数据的取出
                takeIndex = 0;
            }
            // 缓冲区数据的个数减少1
            --count;
            // 此时缓冲区中的数据不是满的，唤醒处于等待条件不是满的状态的线程
            notFull.signal();

            System.out.println("消费：" + data);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            // 释放锁
            lock.unlock();
        }
        return data;
    }
}