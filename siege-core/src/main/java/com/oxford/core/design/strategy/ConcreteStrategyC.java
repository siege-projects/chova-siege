package com.oxford.core.design.strategy;

/**
 * 策略模式 - 具体策略C
 *
 * @author Chova
 * @date 2021/01/05
 */
public class ConcreteStrategyC implements Strategy {
    @Override
    public void strategyInterface() {
        System.out.println("具体策略C的执行算法...");
    }
}
