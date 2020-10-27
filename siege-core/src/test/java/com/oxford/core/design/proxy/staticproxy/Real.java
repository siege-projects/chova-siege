package com.oxford.core.design.proxy.staticproxy;

/**
 * 静态代理 - 真实对象,被代理的对象
 *
 * @author Chova
 * @date 2020/10/27
 */
public class Real implements Interface {

    @Override
    public void doTask() {
        System.out.println("执行任务操作");
    }
}
