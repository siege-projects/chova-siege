package com.oxford.core.design.strategy;

/**
 * 策略模式
 *
 * @author Chova
 * @date 2021/1/5
 */
public class StrategyTest {
    public static void main(String[] args) {
        // 选择需要执行的策略对象
        Strategy strategy = new ConcreteStrategyA();
        // 创建上下文环境
        Context context = new Context(strategy);
        // 执行操作
        context.contextInterface();
    }
}
