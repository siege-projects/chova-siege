package com.oxford.core.design.factory.abstractfactory;

/**
 * 工厂实现 - 产品A
 *
 * @author Chova
 * @date 2020/12/7
 */
public class ConcreteFactoryA implements AbstractFactory {

    @Override
    public AbstractProductA factoryA() {
        return new ConcreteProductAa();
    }

    @Override
    public AbstractProductB factoryB() {
        return new ConcreteProductBa();
    }
}
