package com.oxford.core.design.prototype;

/**
 * 原型模式 - 抽象原型
 *
 * @author Chova
 * @date 2021/1/8
 */
public class Prototype implements Cloneable {

    /**
     * 重写Clonable中的clone()方法,修改为public类型
     *
     * @return Prototype 拷贝原型实例的对象
     */
    @Override
    public Prototype clone() {
        try {
            Prototype prototype = (Prototype) super.clone();
            return prototype;
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }
        return null;
    }
}
