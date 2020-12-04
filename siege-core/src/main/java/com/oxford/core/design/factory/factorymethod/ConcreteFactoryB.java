package com.oxford.core.design.factory.factorymethod;

/**
 * 工厂实现 - B
 *
 * @author Chova
 * @date 2020/12/4
 */
public class ConcreteFactoryB implements Factory {

    @Override
    public Product factory() {
        return new ConcreteProductB();
    }
}
