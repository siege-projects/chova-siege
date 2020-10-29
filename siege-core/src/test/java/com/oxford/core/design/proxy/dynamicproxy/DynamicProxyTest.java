package com.oxford.core.design.proxy.dynamicproxy;

/**
 * 动态代理 - 测试类
 *
 * @author Chova
 * @date 2020/10/29
 */
public class DynamicProxyTest {

    public static void main(String[] args) {
        // 创建目标对象
        Real real = new Real();
        // 创建动态代理对象
        Interface proxy = (Interface) new DynamicProxy(real).getProxyInstance();
        // 执行动态代理对象方法
        proxy.request();
    }
}
