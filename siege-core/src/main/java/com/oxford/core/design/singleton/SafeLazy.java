package com.oxford.core.design.singleton;

/**
 * 单例模式 - 线程安全懒汉
 *
 * @author Chova
 * @date 2020/12/25
 */
public class SafeLazy {

    private static SafeLazy instance;

    private SafeLazy() {
    }

    public static synchronized SafeLazy getInstance() {
        if (null == instance) {
            instance = new SafeLazy();
        }
        return instance;
    }
}
