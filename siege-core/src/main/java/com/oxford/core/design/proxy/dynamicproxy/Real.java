package com.oxford.core.design.proxy.dynamicproxy;

/**
 * 动态代理 - 真实对象
 *
 * @author Chova
 * @date 2020/10/29
 */
public class Real implements Interface {

    @Override
    public void request() {
        System.out.println("执行任务操作");
    }
}
