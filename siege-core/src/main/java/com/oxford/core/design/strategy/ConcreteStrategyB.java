package com.oxford.core.design.strategy;

/**
 * 策略模式 - 具体策略B
 *
 * @author Chova
 * @date 2021/1/5
 */
public class ConcreteStrategyB implements Strategy {

    @Override
    public void strategyInterface() {
        System.out.println("具体策略B的执行算法...");
    }
}
