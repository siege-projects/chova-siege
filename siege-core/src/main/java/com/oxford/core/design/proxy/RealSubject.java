package com.oxford.core.design.proxy;

/**
 * 真实角色RealSubject
 *
 * @author Chova
 * @date 2020/10/26
 */
public class RealSubject implements Subject {

    @Override
    public void request() {
        System.out.println("调用真实对象的请求方法...");
    }
}
