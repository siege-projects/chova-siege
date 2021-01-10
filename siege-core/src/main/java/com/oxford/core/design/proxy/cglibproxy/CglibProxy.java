package com.oxford.core.design.proxy.cglibproxy;

import org.springframework.cglib.proxy.Enhancer;
import org.springframework.cglib.proxy.MethodInterceptor;
import org.springframework.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

/**
 * CGLIB代理 - 代理对象
 *
 * @author Chova
 * @date 2020/11/06
 */
public class CglibProxy implements MethodInterceptor {

    /**
     * 维护一个真实对象
     */
    private Real real;

    public CglibProxy(Real real) {
        this.real = real;
    }

    /**
     * 创建一个代理对象
     *
     * @return Object 代理对象
     */
    public Object getProxyInstance() {
        Enhancer enhancer = new Enhancer();
        // 配置父类
        enhancer.setSuperclass(real.getClass());
        // 设置回调函数
        enhancer.setCallback(this);
        // 返回创建的类作为真实对象的子类代理对象
        return enhancer.create();
    }

    @Override
    public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {
        before();
        Object result = method.invoke(real, objects);
        after();
        return result;
    }

    private void before() {
        System.out.println("执行任务之前的操作");
    }

    private void after() {
        System.out.println("执行任务之后的操作");
    }
}
