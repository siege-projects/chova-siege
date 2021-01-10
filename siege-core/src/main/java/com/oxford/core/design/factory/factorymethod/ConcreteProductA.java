package com.oxford.core.design.factory.factorymethod;

/**
 * 具体产品 - A
 *
 * @author Chova
 * @date 2020/12/04
 */
public class ConcreteProductA implements Product {

    @Override
    public void produce() {
        System.out.println("产品A生产方法...");
    }
}
