package jianzhioffer;

import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedList;

/**
 * Created by darcy
 * 2017/3/26--0:31
 * Description:
 * 输入一个链表，从尾到头打印链表每个节点的值。
 */

public class P5_ReversePrintList {

    class ListNode {
        int val;
        ListNode next = null;

        ListNode(int val) {
            this.val = val;
        }
    }

     /*
         放入stack;
      */
    public ArrayList<Integer> printListFromTailToHead(ListNode listNode) {

        Deque<Integer> list = new LinkedList<>();

        while (listNode != null) {
            int value = listNode.val;
            list.addLast(value);
            listNode = listNode.next;
        }

        ArrayList<Integer> result = new ArrayList<>();

        while (!list.isEmpty()) {
            result.add(list.pollLast());
        }

        return result;

    }

    /*
        递归写法;
     */
    ArrayList<Integer> list = new ArrayList<>();
    public ArrayList<Integer> printListFromTailToHead2(ListNode listNode) {
        if (listNode != null) {
            printListFromTailToHead2(listNode.next);
            list.add(listNode.val);
        }
        return list;
    }


    public static void main(String[] args) {

    }
}
