package com.oxford.core.design.factory.abstractfactory;

/**
 * 抽象工厂模式 - 测试类
 *
 * @author Chova
 * @date 2020/12/7
 */
public class AbstractFactoryTest {
    public static void main(String[] args) {
        AbstractFactory factoryA = new ConcreteFactoryA();
        AbstractProductA productAa = factoryA.factoryA();
        productAa.produceA();
        AbstractProductB productBa = factoryA.factoryB();
        productBa.produceB();

        AbstractFactory factoryB = new ConcreteFactoryB();
        AbstractProductA productAb = factoryB.factoryA();
        productAb.produceA();
        AbstractProductB productBb = factoryB.factoryB();
        productBb.produceB();
    }
}
