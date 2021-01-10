package com.oxford.core.design.factory.abstractfactory;

/**
 * 具体产品 - A类产品b
 *
 * @author Chova
 * @date 2020/12/07
 */
public class ConcreteProductAb implements AbstractProductA {

    @Override
    public void produceA() {
        System.out.println("A类产品b的生产方法...");
    }
}
