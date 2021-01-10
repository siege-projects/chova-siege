package com.oxford.core.design.factory.abstractfactory;

/**
 * 抽象工厂
 *
 * @author Chova
 * @date 2020/12/07
 */
public interface AbstractFactory {

    /**
     * A类产品生产方法
     *
     * @return AbstractProductA A类产品
     */
    AbstractProductA factoryA();

    /**
     * B类产品生产方法
     *
     * @return AbstractProductB B类产品
     */
    AbstractProductB factoryB();
}
