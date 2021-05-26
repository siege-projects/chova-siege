package com.oxford.core.design.singleton;

/**
 * 单例模式 - 静态内部类
 *
 * @author Chova
 * @date 2020/12/30
 */
public class StaticNest {

    private static class Nest {
        private static final StaticNest INSTANCE = new StaticNest();
    }

    private StaticNest() {
    }

    /**
     * 使用静态内部类实现单例模式
     *
     * @return StaticNest 一个单例实例
     */
    public static StaticNest getInstance() {
        return Nest.INSTANCE;
    }
}
