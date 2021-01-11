package com.oxford.core.design.template;

/**
 * 模板方法模式 - 抽象模板
 *
 * @author Chova
 * @date 2021/01/11
 */
public abstract class AbstractTemplate {

    /**
     * 模板方法
     */
    public void templateMethod() {
        abstractMethod();
        hookMethod();
        concreteMethod();
    }

    /**
     * 基本方法 - 抽象模板结构
     * - 具体实现延迟到子类中实现
     * - 必须要在子类中实现
     */
    public abstract void abstractMethod();

    /**
     * 基本方法 - 钩子方法
     * - 扩展方法
     * - 可以实现,可以不实现
     */
    protected void hookMethod() {
    }

    /**
     * 基本方法 - 共有的算法逻辑
     */
    private void concreteMethod() {
        System.out.println("执行模板方法抽象模板中共有的算法逻辑...");
    }

}
