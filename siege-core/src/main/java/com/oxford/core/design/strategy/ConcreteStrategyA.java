package com.oxford.core.design.strategy;

/**
 * 策略模式 - 具体策略A
 *
 * @author Chova
 * @date 2021/1/5
 */
public class ConcreteStrategyA implements Strategy {
    @Override
    public void strategyInterface() {
        System.out.println("具体策略A的执行算法...");
    }
}
