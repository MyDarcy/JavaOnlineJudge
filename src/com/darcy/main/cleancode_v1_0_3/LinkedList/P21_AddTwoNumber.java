package com.darcy.main.cleancode_v1_0_3.LinkedList;

import java.util.LinkedList;
import java.util.List;

/**
 * Author by darcy
 * Date on 17-9-2 上午9:34.
 * Description:
 *
 * 链表代表的两个数字相加.
 *
 * Input: (2 -> 4 -> 3) + (5 -> 6 -> 4)
 * Output: 7 -> 0 -> 8
 */



public class P21_AddTwoNumber {

  public static class ListNode {
    Integer val;
    ListNode next;

    public ListNode() {

    }

    public ListNode(Integer val, ListNode next) {
      this.val = val;
      this.next = next;
    }

    public ListNode(Integer val) {
      this.val = val;
      this.next = null;
    }


  }

  /**
   *
   * @param listNode1
   * @param listNode2
   * @return
   */
  public static ListNode solution(ListNode listNode1, ListNode listNode2) {
    ListNode p1 = listNode1;
    ListNode p2 = listNode2;
    ListNode preHead = new ListNode(-1);
    ListNode iter = preHead;
    int carry = 0;
    while (p1 != null && p2 != null) {
      int total = p1.val + p2.val + carry;
      if (total >= 10) {
        iter.next = new ListNode(total - 10);
        carry = total / 10;
      } else {
        iter.next = new ListNode(total);
        carry = 0;
      }

      p1 = p1.next;
      p2 = p2.next;

      iter = iter.next;
    }

    ListNode temp = p1 != null ? p1 : p2 != null ? p2 : null;
    if (temp == null) {
      if (carry != 0) {
        iter.next = new ListNode(1);
        return preHead.next;
      }
    }

    if (temp != null) {
      while (temp != null) {
        int val = temp.val + carry;
        if (val >= 10) {
          iter.next = new ListNode(val - 10);
          carry = val / 10;
        } else {
          iter.next = new ListNode(val);
          carry = 0;
        }
        temp = temp.next;
        iter = iter.next;
      }
    }

    if (carry != 0) {
      iter.next = new ListNode(1);
    }
    return preHead.next;

  }

  /**
   * original answer.
   *
   * 将共同部分的处理和较长子串的处理统一了起来.
   *
   * 1. 设置一个边界头节点
   * 2. while(p1 != null || p2 != null)将链表的所有处理操作统一起来.
   *
   * @param l1
   * @param l2
   * @return
   */
  public static ListNode addTwoNumbers(ListNode l1, ListNode l2) {
    ListNode dummyHead = new ListNode(0);
    ListNode p = l1, q = l2, curr = dummyHead;
    int carry = 0;
    while (p != null || q != null) {
      int x = (p != null) ? p.val : 0;
      int y = (q != null) ? q.val : 0;
      int digit = carry + x + y;
      carry = digit / 10;
      curr.next = new ListNode(digit % 10);
      curr = curr.next;
      if (p != null) p = p.next;
      if (q != null) q = q.next;
    }
    if (carry > 0) {
      curr.next = new ListNode(carry);
    }
    return dummyHead.next;
  }


  public static void main(String[] args) {
    ListNode p1 = new ListNode(2, new ListNode(4, new ListNode(3)));
    ListNode p2 = new ListNode(5, new ListNode(6, new ListNode(4)));
    ListNode result = addTwoNumbers(p1, p2);
    StringBuilder sb = new StringBuilder();
    while (result != null) {
      sb.append(result.val + "->");
      result = result.next;
    }

    if (sb.length() > 2) {

      System.out.println(sb.toString().substring(0, sb.length() - 2));
    } else {
      System.out.println(sb.toString());
    }


  }

}
