package com.oxford.framework.spring.bean.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * 代理Bean操作 - 代理类
 *
 * @author Chova
 * @date 2021/03/23
 */
public class Proxy implements InvocationHandler {

    /**
     * 代理的接口类
     */
    private Class<?> interfaces;

    /**
     * 使用JDK自带的动态代理方法实现对接口的代理操作
     * 代理对象只能是接口,不能是普通的类
     *
     * @param proxy  需要代理的类
     * @param method 方法操作
     * @param args   参数
     * @return Object 代理操作后的类
     * @throws Throwable 抛出异常
     */
    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        return "执行请求操作...";
    }

    /**
     * 将指定的类绑定到代理的接口类中
     *
     * @param clazz 需要代理的类
     * @return Object 代理类实例
     */
    public Object bind(Class<?> clazz) {
        this.interfaces = clazz;
        return java.lang.reflect.Proxy.newProxyInstance(clazz.getClassLoader(), new Class[]{interfaces}, this);
    }
}
