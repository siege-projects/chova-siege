package com.oxford.core.design.factory.factorymethod;

/**
 * 工厂方法模式 - 测试类
 *
 * @author Chova
 * @date 2020/12/04
 */
public class FactoryMethodTest {

    public static void main(String[] args) {
        Factory factory = new ConcreteFactoryA();
        Product product = factory.factory();
        product.produce();
    }
}
