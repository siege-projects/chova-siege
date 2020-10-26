package com.oxford.core.design.proxy;

/**
 * 代理角色ProxySubject
 *
 * @author Chova
 * @date 2020/10/26
 */
public class ProxySubject implements Subject {

    private RealSubject realSubject;

    public ProxySubject(RealSubject realSubject) {
        this.realSubject = realSubject;
    }

    @Override
    public void request() {
        before();
        realSubject.request();
        after();
    }

    public void before() {
        System.out.println("在调用真实角色请求方法之前执行处理逻辑...");
    }

    public void after() {
        System.out.println("在调用真实角色请求方法之后执行处理逻辑...");
    }
}
