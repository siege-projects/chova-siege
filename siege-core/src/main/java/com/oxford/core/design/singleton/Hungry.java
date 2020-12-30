package com.oxford.core.design.singleton;

/**
 * 单例模式 - 饿汉
 *
 * @author Chova
 * @date 2020/12/30
 */
public class Hungry {

    /**
     * 饿汉 - 在类加载时就完成初始化
     */
    private static final Hungry instance = new Hungry();

    private Hungry() {
    }

    /**
     * 使用饿汉实现单例模式
     *
     * @return Hungry 一个单例实例
     */
    public static Hungry getInstance() {
        return instance;
    }
}
