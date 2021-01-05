package com.oxford.core.design.proxy.staticproxy;

/**
 * 静态代理 - 代理对象
 *
 * @author Chova
 * @date 2020/10/27
 */
public class Proxy implements Interface {

    private Real real;

    public Proxy(Real real) {
        this.real = real;
    }


    @Override
    public void doTask() {
        before();
        real.doTask();
        after();
    }

    public void before() {
        System.out.println("执行任务之前的操作");
    }

    public void after() {
        System.out.println("执行任务之后的操作");
    }
}
