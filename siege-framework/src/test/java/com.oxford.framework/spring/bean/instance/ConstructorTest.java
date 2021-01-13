package com.oxford.framework.spring.bean.instance;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * 构造器实例化Bean测试类
 *
 * @author Chova
 * @date 2021/01/13
 */
public class ConstructorTest {
    public static void main(String[] args) {

        // 定义Spring的xml配置文件applicationContext.xml的路径
        String xmlPath = "bean/instance/constructor/applicationContext.xml";

        // 初始化Spring容器,加载xml配置文件,并对Bean进行实例化
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext(xmlPath);

        // 通过Spring容器获取Bean实例
        System.out.println(applicationContext.getBean("Bean"));
    }
}
