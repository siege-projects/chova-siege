package com.oxford.framework.spring.bean.instance.instancefactory;

/**
 * 实例化Bean工厂
 *
 * @author Chova
 * @date 2021/01/18
 */
public class InstanceBeanFactory {

    /**
     * 实例化Bean工厂类中获取Bean实例
     *
     * @return Bean Bean类的Bean实例
     */
    public Bean getBean() {
        return new Bean();
    }
}
