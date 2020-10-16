package com.oxford.core.nest;

/**
 * Java嵌套类 - 局部内部类
 *
 * @author Chova
 * @date 2020/10/16
 */
public class MethodInnerClassTest {

    public void execute() {
        /*
         * 局部内部类只能访问方法内final修饰符的成员变量
         * 局部内部类不能访问方法内非final修饰符的成员变量
         */
        final int finalMethodVar = 6;

        class MethodInnerClass {

            public void execute() {
                System.out.println("在方法内部类中访问方法内final修饰符的局部变量:" + finalMethodVar);
            }
        }
        // 局部内部类只能在方法内部实例化
        MethodInnerClass methodInnerClass = new MethodInnerClass();
        methodInnerClass.execute();
    }

    public static void main(String[] args) {
        MethodInnerClassTest methodInnerClassTest = new MethodInnerClassTest();
        methodInnerClassTest.execute();
    }
}
