package com.oxford.core.design;

import com.oxford.core.design.prototype.ConcretePrototype;

/**
 * 原型模式 - 客户端
 *
 * @author Chova
 * @date 2021/1/8
 */
public class Client {
    public static void main(String[] args) {
        // 创建原型的实例
        ConcretePrototype prototypeInstance = new ConcretePrototype();
        // 根据原型的实例拷贝新的对象
        for (int i = 0; i < 10; i++) {
            ConcretePrototype cloneObject = (ConcretePrototype) prototypeInstance.clone();
            cloneObject.doSomething();
        }
    }
}
