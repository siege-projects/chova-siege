package com.oxford.core.nest.anonymousinnerclass;

import com.oxford.core.nest.anonymousinnerclass.service.ParameterType;

/**
 * Java嵌套类 - 参数式匿名内部类
 *
 * @author Chova
 * @date 2020/10/16
 */
public class ParameterTypeAnonymousInnerClass {

    public static void main(String[] args) {
        ParameterType parameterType = new ParameterType();

        parameterType.parameterMethod(() -> System.out.println("参数式匿名内部类实现方法"));
    }
}
