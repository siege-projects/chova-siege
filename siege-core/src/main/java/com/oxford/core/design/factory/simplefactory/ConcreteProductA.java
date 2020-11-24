package com.oxford.core.design.factory.simplefactory;

/**
 * 具体产品 - A
 *
 * @author Chova
 * @date 2020/11/24
 */
public class ConcreteProductA implements Product {

    @Override
    public void produce() {
        System.out.println("产品A生产方法...");
    }
}
