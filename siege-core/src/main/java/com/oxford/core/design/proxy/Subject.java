package com.oxford.core.design.proxy;

/**
 * 抽象角色Subject
 *
 * @author Chova
 * @date 2020/10/26
 */
public interface Subject {
    /**
     * 抽象角色中定义的代理角色和真实角色共同的接口方法
     */
    void request();
}
