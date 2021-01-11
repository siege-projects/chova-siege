package com.oxford.core.design.template;

/**
 * 模板方法模式 - 具体模板
 *
 * @author Chova
 * @date 2021/01/11
 */
public class ConcreteTemplate extends AbstractTemplate {

    @Override
    public void abstractMethod() {
        System.out.println("执行模板方法模式中具体模板算法逻辑...");
    }

    @Override
    public void hookMethod() {
        System.out.println("执行模板方法模式中扩展方法逻辑...");
    }
}
