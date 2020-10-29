package com.oxford.core.design.proxy.dynamicproxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * 动态代理 - 代理对象
 *
 * @author Chova
 * @date 2020/10/29
 */
public class DynamicProxy {

    // 维护一个真实对象
    private Real real;

    public DynamicProxy(Real real) {
        this.real = real;
    }

    // 生成真实对象的代理对象
    public Object getProxyInstance() {
        return Proxy.newProxyInstance(
                real.getClass().getClassLoader(),
                real.getClass().getInterfaces(),
                new InvocationHandler() {
                    @Override
                    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                        before();
                        Object result = method.invoke(real, args);
                        after();
                        return result;
                    }
                }
        );
    }

    public void before() {
        System.out.println("执行任务之前的操作");
    }

    public void after() {
        System.out.println("执行任务之后的操作");
    }
}
