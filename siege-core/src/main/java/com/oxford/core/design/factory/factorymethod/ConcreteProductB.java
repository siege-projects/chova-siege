package com.oxford.core.design.factory.factorymethod;

/**
 * 具体产品 - B
 *
 * @author Chova
 * @date 2020/12/4
 */
public class ConcreteProductB implements Product {

    @Override
    public void produce() {
        System.out.println("产品B生产方法...");
    }
}
