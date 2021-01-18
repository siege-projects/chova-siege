package com.oxford.framework.spring.bean.instance.staticfactory;

/**
 * 静态Bean工厂
 *
 * @author Chova
 * @date 2021/01/13
 */
public class StaticBeanFactory {

    /**
     * 静态Bean工厂类中获取Bean实例的静态方法
     *
     * @return Bean Bean类的Bean实例
     */
    public static Bean getBean() {
        return new Bean();
    }
}
