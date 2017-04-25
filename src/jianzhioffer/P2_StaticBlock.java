package jianzhioffer;

/**
 * Created by MrDar on 2017/3/25.
 */

class A {
    public A(String s) {
        System.out.println(s);
    }
}

class B {
    static A a1 = new A("a1");
    A a2 = new A("a2");

    static {
        a1 = new A("a3");
    }

    public B() {
        a2 = new A("a4");
    }
}


public class P2_StaticBlock {
    public static void main(String[] args) {
        new B();
    }
}
