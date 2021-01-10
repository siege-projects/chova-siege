package com.oxford.core.design.factory.abstractfactory;

/**
 * 工厂实现 - 产品B
 *
 * @author Chova
 * @date 2020/12/07
 */
public class ConcreteFactoryB implements AbstractFactory {

    @Override
    public AbstractProductA factoryA() {
        return new ConcreteProductAb();
    }

    @Override
    public AbstractProductB factoryB() {
        return new ConcreteProductBb();
    }
}
