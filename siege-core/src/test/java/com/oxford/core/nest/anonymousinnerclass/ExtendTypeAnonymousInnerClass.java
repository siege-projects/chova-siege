package com.oxford.core.nest.anonymousinnerclass;

import com.oxford.core.nest.anonymousinnerclass.service.ExtendType;

/**
 * Java嵌套类 - 继承式匿名内部类
 *
 * @author Chova
 * @date 2020/10/16
 */
public class ExtendTypeAnonymousInnerClass {

    public static void main(String[] args) {

        ExtendType extendType = new ExtendType() {
            public void extendMethod() {
                System.out.println("继承式匿名内部类实现方法");
            }
        };
        // 继承式ExtendType引用的变量不是ExtendType的对象,而是ExtendType子匿名内部类对象
        extendType.extendMethod();
    }
}
