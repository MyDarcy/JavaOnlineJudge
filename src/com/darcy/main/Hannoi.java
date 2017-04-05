package com.darcy.main;

/**
 * Author by: darcy
 * Created on 17-3-25-下午4:40.
 * Description:
 */
public class Hannoi {
    // n个盘子从from 到 to,　借助ax;
    public static void hannoi(int n, char from, char to, char ax) {
        if (n == 1) {
            System.out.println(n + " from " + from + " to " + to);
        } else {
            hannoi(n - 1, from, ax, to);
            System.out.println(n + " from " + from + " to " + to);
            hannoi(n - 1, ax, to, from);
        }
    }

    /**
     * @param args
     */
    public static void main(String[] args) {
        hannoi(3, 'A', 'C', 'B');
    }
}
