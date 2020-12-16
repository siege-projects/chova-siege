package com.oxford.core.design.factory.simplefactory;

/**
 * 工厂
 *
 * @author Chova
 * @date 2020/11/24
 */
public class Factory {

    /**
     * 产品类型 - A
     */
    public static final String PRODUCT_A = "A";

    /**
     * 产品类型 - B
     */
    public static final String PRODUCT_B = "B";

    /**
     * 根据对应的产品类型执行相应的产品生产方法
     *
     * @param productType 产品类型
     * @return Product 产品生产方法
     */
    public static Product factory(String productType) {
        if (PRODUCT_A.equals(productType)) {
            return new ConcreteProductA();
        } else if (PRODUCT_B.equals(productType)) {
            return new ConcreteProductB();
        } else {
            System.out.println("请输入正确的产品类型");
            return null;
        }
    }
}
