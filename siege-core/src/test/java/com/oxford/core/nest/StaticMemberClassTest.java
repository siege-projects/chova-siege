package com.oxford.core.nest;

/**
 * Java嵌套类 - 静态成员类示例
 *
 * @author Chova
 * @date 2020/10/16
 */
public class StaticMemberClassTest {

    private int memberVar1 = 6;

    private static int staticMemberVar1 = 9;

    /**
     * 在类的内部创建静态成员类
     * 静态成员类可以使用public, protected或者private修饰
     */
    private static class StaticMemberClass {

        /**
         * 在类的内部的静态成员类中可以创建与外部类同名的成员变量
         */
        private int memberVar1 = 1;
        private int memberVar = 6;

        private static int staticMemberVar1 = 8;
        private static int staticMemberVar = 9;

        /**
         * 在类内部的静态成员类中可以创建与外部类同名的方法
         */
        public void execute() {
            // this引用的是类内部的静态成员类
            System.out.println("访问类内部静态成员类的非静态变量memberVar1:" + this.memberVar1);
            System.out.println("访问类内部静态成员类的静态变量memberVar1:" + this.staticMemberVar1);
            // 在类内部的静态成员类中访问外部类的静态成员
            System.out.println("在类内部的静态成员类中访问外部类的静态成员变量staticMemberVar1:" + StaticMemberClassTest.staticMemberVar1);

            /*
             * 在类内部的静态成员类中不能直接访问外部类的非静态变量
             * 通过创建外部类实例访问外部类的非静态成员变量
             */
            StaticMemberClassTest staticMemberClassTest = new StaticMemberClassTest();
            System.out.println("创建外部类实例:" + staticMemberClassTest);
            System.out.println("在类内部的静态成员类中不能直接访问外部类的非静态成员变量,通过外部类实例访问外部类的非静态成员变量:" + staticMemberClassTest.memberVar1);
        }

    }

    public void execute() {
        // 在外部类中创建类内部的静态成员类的实例
        StaticMemberClass staticMemberClass = new StaticMemberClass();
        System.out.println("创建类内部静态成员类实例:" + staticMemberClass);

        /*
         * 外部类不能直接访问类内部静态成员类中的成员变量,包括非静态成员变量和静态成员变量
         * 通过创建类内部静态成员类实例访问静态成员类中的成员变量
         */
        System.out.println("通过创建类内部静态成员类实例访问静态成员类中的非静态成员变量:" + staticMemberClass.memberVar);
        System.out.println("通过创建类内部静态成员类实例访问静态成员类中的静态成员变量:" + staticMemberClass.staticMemberVar);
    }

    public static void main(String[] args) {
        StaticMemberClassTest staticMemberClassTest = new StaticMemberClassTest();
        staticMemberClassTest.execute();
    }
}
