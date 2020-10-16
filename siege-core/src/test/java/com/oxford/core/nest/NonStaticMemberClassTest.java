package com.oxford.core.nest;

/**
 * Java嵌套类 - 非静态成员类
 *
 * @author Chova
 * @date 2020/10/16
 */
public class NonStaticMemberClassTest {

    private int nonStaticMemberVar1 = 6;
    private static int staticNonStaticMemberVar = 8;

    private class NonStaticMemberClass {

        /**
         * 非静态成员类中不能创建静态成员变量
         * 非静态成员类可以创建与外部类同名的成员变量
         */
        private int nonStaticMemberVar = 9;

        public void execute() {
            // this引用的是当前非静态成员类
            System.out.println("访问非静态成员类中的成员变量:" + this.nonStaticMemberVar);
            /*
             * 在非静态成员类中使用this可以访问外部类的私有成员
             */
            System.out.println("在非静态成员类中访问外部类的私有成员变量:" + NonStaticMemberClassTest.this.nonStaticMemberVar1);
            System.out.println("在非静态成员类中访问外部类的静态成员变量:" + NonStaticMemberClassTest.this.staticNonStaticMemberVar);
        }
    }

    public void execute() {
        // 在外部类中创建非静态成员类实例
        NonStaticMemberClass nonStaticMemberClass = new NonStaticMemberClass();
        nonStaticMemberClass.execute();
    }

    public static void main(String[] args) {
        NonStaticMemberClassTest nonStaticMemberClassTest = new NonStaticMemberClassTest();
        nonStaticMemberClassTest.execute();
    }
}
