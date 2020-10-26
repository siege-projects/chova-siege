package com.oxford.core.design.proxy;

/**
 * 代理模式测试
 *
 * @author Chova
 * @date 2020/10/26
 */
public class ProxyTest {

    public static void main(String[] args) {
        ProxySubject proxy = new ProxySubject(new RealSubject());
        proxy.request();
    }
}
