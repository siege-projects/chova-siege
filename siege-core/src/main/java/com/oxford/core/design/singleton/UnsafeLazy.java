package com.oxford.core.design.singleton;

/**
 * 单例模式 - 线程不安全懒汉
 *
 * @author Chova
 * @date 2020/12/25
 */
public class UnsafeLazy {

    private static UnsafeLazy instance;

    private UnsafeLazy() {
    }

    public static UnsafeLazy getInstance() {
        if (null == instance) {
            instance = new UnsafeLazy();
        }
        return instance;
    }
}
