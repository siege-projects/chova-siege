package com.oxford.framework.spring.bean.proxy;

import org.springframework.beans.factory.FactoryBean;

/**
 * 代理Bean操作 - 代理工厂
 *
 * @author Chova
 * @date 2021/04/06
 */
public class ProxyFactory<T> implements FactoryBean<T> {

    private Class<T> beanInterfaces;

    public ProxyFactory(Class<T> beanInterfaces) {
        this.beanInterfaces = beanInterfaces;
    }

    /**
     * 获取绑定的代理Bean对象
     *
     * @return T 代理Bean对象
     * @throws Exception 获取异常
     */
    @Override
    public T getObject() throws Exception {
        return (T) new Proxy().bind(beanInterfaces);
    }

    @Override
    public Class<?> getObjectType() {
        return beanInterfaces;
    }

    /**
     * 单例模式
     *
     * @return boolean 是否为单例
     */
    @Override
    public boolean isSingleton() {
        return true;
    }

    public Class<T> getInterfaces() {
        return beanInterfaces;
    }

    public void setInterfaces(Class<T> interfaces) {
        this.beanInterfaces = interfaces;
    }
}
