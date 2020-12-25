package com.oxford.core.design.singleton;

/**
 * 单例模式 - 双检锁
 *
 * @author Chova
 * @date 2020/12/25
 */
public class DoubleCheckLock {

    private volatile static DoubleCheckLock instance;

    private DoubleCheckLock() {
    }

    /**
     * 使用双检锁实现单例模式
     *
     * @return DoubleCheckLock 一个单例实例
     */
    public static DoubleCheckLock getInstance() {
        if (null == instance) {
            synchronized (DoubleCheckLock.class) {
                if (null == instance) {
                    instance = new DoubleCheckLock();
                }
            }
        }
        return instance;
    }
}
