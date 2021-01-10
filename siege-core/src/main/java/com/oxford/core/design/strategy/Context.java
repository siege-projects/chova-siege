package com.oxford.core.design.strategy;

/**
 * 策略模式 - 环境
 *
 * @author Chova
 * @date 2021/01/05
 */
public class Context {

    /**
     * 持有一个抽象策略Strategy的引用
     */
    private Strategy strategy;

    public Context(Strategy strategy) {
        this.strategy = strategy;
    }

    /**
     * 策略模式 - 上下文方法
     */
    public void contextInterface() {
        strategy.strategyInterface();
    }
}
