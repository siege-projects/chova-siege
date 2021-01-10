package com.oxford.core.design.factory.factorymethod;

/**
 * 工厂
 *
 * @author Chova
 * @date 2020/12/04
 */
public interface Factory {

    /**
     * 产品生产方法
     *
     * @return Product 生产的产品
     */
    Product factory();
}
