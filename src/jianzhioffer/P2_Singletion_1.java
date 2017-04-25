package jianzhioffer;

/**
 * Created by MrDar on 2017/3/25.
 * not good. 只适用于单线程;

 */
public class P2_Singletion_1 {




    /*
        线程安全, 但效率不够; 每次创建对象都需要枷锁;
     */
    /*private static P2_Singletion_1 p2_singletion_1 = null;

    private P2_Singletion_1() {

    }

    public static P2_Singletion_1 getInstance() {
        synchronized (P2_Singletion_1.class) {
            if (p2_singletion_1 == null) {
                p2_singletion_1 = new P2_Singletion_1();
            }
        }
        return p2_singletion_1;
    }*/

    /*
        线程安全, double-check; 第一次检查为null时, 才需要加锁,其他情况都不需要加锁,直接返回值；

     */
    /*private static P2_Singletion_1 p2_singletion_1 = null;

    private P2_Singletion_1() {

    }

    public static P2_Singletion_1 getInstance() {
        if (p2_singletion_1 == null) {
            synchronized (P2_Singletion_1.class) {
                if (p2_singletion_1 == null) {
                    p2_singletion_1 = new P2_Singletion_1();
                }
            }
        }
        return p2_singletion_1;
    }*/


     /*
       应该是能用于多线程的;　但是过早的创建了实例；
     */
    /*private P2_Singletion_1 p2_singletion_1 = new P2_Singletion_1();

    private P2_Singletion_1() {

    }

    public P2_Singletion_1 getP2_singletion_1() {
        return p2_singletion_1;
    }*/

    /*
       解决上面的问题; 避免过早的加载类;
       但是Java中嵌套内部类不能有static声明; 静态内部类;
     */

    public static P2_Singletion_1 getInstance() {
        return Nested.p2_singletion_1;
    }

    static class Nested {
        static P2_Singletion_1 p2_singletion_1 = new P2_Singletion_1();
    }

}
