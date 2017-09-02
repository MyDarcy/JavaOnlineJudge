package com.darcy.main.cleancode_v1_0_3.LinkedList;

import java.util.List;

/**
 * Author by darcy
 * Date on 17-9-2 上午10:21.
 * Description:
 *
 * 将List链表中的成对的相邻节点两两swap.
 *
 * Given a linked list, swap every two adjacent nodes and return its head.
 * For example,
 *
 * Given 1 -> 2 -> 3 -> 4, you should return the list as 2 -> 1 -> 4 -> 3.
 *
 * Your algorithm should use only constant space. You may not modify the values in the
 * list, only nodes itself can be changed.
 *
 * Example Questions Candidate Might Ask:
 * Q: What if the number of nodes in the linked list has only odd number of nodes?
 * A: The last node should not be swapped.
 *
 */
public class P22_SwapNodesInPair {

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
   * p, q, r are the current, next, and next’s next node.
   * 同样是加了一个头节点. 避免对prev的繁琐处理.
   *
   * To determine the new list’s head, you look at if the list contains two or more elements.
   * Basically, these extra conditional statements could be avoided by inserting an extra node
   * (also known as the dummy head) to the front of the list
   *
   * @param head
   * @return
   */
  public static ListNode swapPairs(ListNode head) {
    ListNode dummy = new ListNode(0);
    dummy.next = head;
    ListNode p = head;
    ListNode prev = dummy;
    while (p != null && p.next != null) {
      ListNode q = p.next, r = p.next.next;
      prev.next = q;
      q.next = p;
      p.next = r;
      prev = p;
      p = r;
    }
    return dummy.next;
  }

  public static void main(String[] args) {
    ListNode node = new ListNode(1,
        new ListNode(2,
            new ListNode(3,
                new ListNode(4,
                    new ListNode(5,
                        new ListNode(6))))));
    ListNode result = swapPairs(node);
    StringBuilder sb = new StringBuilder();
    while (result != null) {
      sb.append(result.val + "->");
      result = result.next;
    }

    if (sb.length() > 2) {
      System.out.println(sb.substring(0, sb.length() - 2));
    } else {
      System.out.println(sb);
    }
  }

}
