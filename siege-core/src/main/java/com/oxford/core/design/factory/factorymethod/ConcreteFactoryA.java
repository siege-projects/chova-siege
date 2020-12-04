package com.oxford.core.design.factory.factorymethod;

/**
 * 工厂实现 - A
 *
 * @author Chova
 * @date 2020/12/4
 */
public class ConcreteFactoryA implements Factory {

    @Override
    public Product factory() {
        return new ConcreteProductA();
    }
}
