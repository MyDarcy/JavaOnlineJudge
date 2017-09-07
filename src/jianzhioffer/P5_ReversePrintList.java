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

  static class ListNode {
    int val;
    ListNode next = null;

    ListNode(int val) {
      this.val = val;
    }

    ListNode(int val, ListNode node) {
      this.val = val;
      this.next = node;
    }
  }

  /**
   * LinkedList模拟一个栈。
   * 或者直接使用Stack。
   *
   * @param listNode
   * @return
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


  static ArrayList<Integer> list = new ArrayList<>();

  /**
   * 递归的思路.
   * 回退到上一层栈的时候打印结果.
   *
   * @param listNode
   * @return
   */
  public static ArrayList<Integer> printListFromTailToHead2(ListNode listNode) {
    if (listNode != null) {
      printListFromTailToHead2(listNode.next);
      list.add(listNode.val);
    }
    return list;
  }


  public static void main(String[] args) {
    ListNode head =
        new ListNode(1,
            new ListNode(2,
                new ListNode(3,
                    new ListNode(4,
                        new ListNode(5,
                            new ListNode(6,
                                new ListNode(7)))))));

    ArrayList<Integer> result = printListFromTailToHead2(head);
    System.out.println(result);
  }
}
