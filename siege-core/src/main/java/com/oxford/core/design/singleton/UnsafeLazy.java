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

    /**
     * 使用线程不安全懒汉实现单例模式
     *
     * @return UnsafeLazy 一个单例实例.多线程可能会返回多个实例,无法正常工作
     */
    public static UnsafeLazy getInstance() {
        if (null == instance) {
            instance = new UnsafeLazy();
        }
        return instance;
    }
}
