package com.oxford.core.design.proxy.cglibproxy;

/**
 * CGLIB代理 - 测试类
 *
 * @author Chova
 * @date 2020/11/6
 */
public class CglibProxyTest {

    public static void main(String[] args) {
        // 真实对象
        Real real = new Real();
        //代理对象
        Real proxy = (Real) new CglibProxy(real).getProxyInstance();
        // 执行代理操作
        proxy.request();
    }
}
