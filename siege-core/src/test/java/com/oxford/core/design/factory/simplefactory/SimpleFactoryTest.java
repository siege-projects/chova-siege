package com.oxford.core.design.factory.simplefactory;

/**
 * 简单工厂方法 - 测试类
 *
 * @author Chova
 * @date 2020/11/24
 */
public class SimpleFactoryTest {

    public static void main(String[] args) {
        Product product = Factory.factory("A");
        product.produce();
    }
}
