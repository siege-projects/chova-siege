package com.oxford.core.design.proxy.staticproxy;

/**
 * 静态代理测试类
 *
 * @author Chova
 * @date 2020/10/27
 */
public class StaticProxyTest {

    public static void main(String[] args) {
        Proxy proxy = new Proxy(new Real());
        proxy.doTask();
    }
}
