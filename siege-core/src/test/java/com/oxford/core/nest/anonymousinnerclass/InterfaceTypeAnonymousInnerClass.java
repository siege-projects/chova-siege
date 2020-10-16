package com.oxford.core.nest.anonymousinnerclass;

import com.oxford.core.nest.anonymousinnerclass.service.InterfaceType;

/**
 * Java嵌套类 - 接口式匿名内部类
 *
 * @author Chova
 * @date 2020/10/16
 */
public class InterfaceTypeAnonymousInnerClass {

    public static void main(String[] args) {

        /*
         * 接口式的匿名内部类并不是实例化接口,而是实现了一个接口的匿名类,并且只能实现一个接口
         */
        InterfaceType interfaceType = () -> System.out.println("接口式匿名内部类实现方法");
        interfaceType.interfaceMethod();
    }
}
