package com.oxford.core.design.factory.abstractfactory;

/**
 * 具体产品 - B类产品a
 *
 * @author Chova
 * @date 2020/12/7
 */
public class ConcreteProductBa implements AbstractProductB {

    @Override
    public void produceB() {
        System.out.println("B类产品a生产方法...");
    }
}
