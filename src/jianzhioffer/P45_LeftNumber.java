package jianzhioffer;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by darcy
 * 2017/5/14--0:13
 * Description:
 * 0到n-1这n个数排成一个圆圈，从数字0开始从这个圆圈中删除第m个数，那么这个圆圈中剩下的最后一个数是哪一个;
 */

public class P45_LeftNumber {

    /**
     * 模拟圆环.
     * @param n
     * @param m
     * @return
     */
    public int LastRemaining_Solution(int n, int m) {
        if (n < 1 || m < 1) {
            return -1;
        }

        List<Integer> list = new LinkedList<>();
        for (int i = 0; i < n; i++) {
            list.add(i);
        }

        // m >= 2;
        int index = 0;
        while (list.size() > 1) {

            for (int i = 1; i < m; i++) {
                index++;
                // 已经到了最末，调到最前;
                if (index == list.size()) {
                    index = 0;
                }
            }

            // m == 1, 直接删除第一个元素;
            int temp = ++index;
            int size = list.size();

            // 因为刚刚index指向了后一个元素;
            list.remove(--index);
            if (temp == size) { // 删除的原list的末尾的元素，那么现在的起点是第一个元素;
                index = 0;

                // 否则现在的起点是之前的元素;
            } else {
                index = --temp;
            }

        }

        return list.get(0);
    }

    public static void main(String[] args) {
        P45_LeftNumber demo = new P45_LeftNumber();
        System.out.println(demo.LastRemaining_Solution(4, 1));;
    }

}



















/*
public class P45_LeftNumber {

    public static class  Node {
        int value;
        Node next;
        Node() {
        }

        Node(int value) {
            this.value = value;
        }
    }

    public int LastRemaining_Solution(int n, int m) {
        if (n <= 1) {
            return 0;
        }

        if (m < 1) {
            throw new RuntimeException("Wrong parameters.");
        }

        Node head = null;
        Node previous = null;
        int i = 0;
        while (true) {
            if (i == 0) {
                head = new Node(0);
                previous = head;
            } else {
                previous.next = new Node(i);
                previous = previous.next;
            }

            i++;
            if (i >= n) {
                break;
            }
        }
        previous.next = head;
        */
/*Node iter = head;
        print(iter, n);*//*


        previous = head;
        while (true) {

        }

        return 0;
    }

    private void print(Node iter, int n) {
        int i = 0;
        while (i < n) {
            System.out.println(iter.value);
            iter = iter.next;
            i++;
        }
    }

    public static void main(String[] args) {
        P45_LeftNumber demo = new P45_LeftNumber();
        System.out.println(demo.LastRemaining_Solution(10, 4));
    }
}
*/
