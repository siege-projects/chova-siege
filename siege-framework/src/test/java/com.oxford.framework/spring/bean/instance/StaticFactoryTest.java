package com.oxford.framework.spring.bean.instance;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * 静态工厂实例化Bean测试类
 *
 * @author Chova
 * @date 2021/01/18
 */
public class StaticFactoryTest {
    public static void main(String[] args) {
        // 定义Spring的xml配置文件application.xml的路径
        String xmlPath = "spring/bean/instance/staticfactory/applicationContext.xml";

        // 初始化Spring容器，加载xml配置文件并对Bean进行实例化
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext(xmlPath);

        // 通过Spring容器获取Bean实例
        System.out.println("Bean实例:" + applicationContext.getBean("bean"));
    }
}
