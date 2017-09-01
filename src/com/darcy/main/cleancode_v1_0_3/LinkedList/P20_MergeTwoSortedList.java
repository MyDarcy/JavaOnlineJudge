package com.darcy.main.cleancode_v1_0_3.LinkedList;

import java.awt.geom.Line2D;
import java.util.List;

/**
 * Created by hzq19
 * Date on 2017/9/2 0:44.
 * Description:
 */

class ListNode {
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


public class P20_MergeTwoSortedList {

  /**
   * 多增加一个头节点。方便进行处理。
   *
   * We insert a dummy head before the new list so we don’t have to deal with special cases
   such as initializing the new list’s head. Then the new list’s head could just easily be
   returned as dummy head’s next node.
   * @param listNode1
   * @param listNode2
   * @return
   */
  public static ListNode solution(ListNode listNode1, ListNode listNode2) {
    // 预分配一个头结点。
    ListNode preHead = new ListNode(-1);
    ListNode iter = preHead;
    while (listNode1 != null && listNode2 != null) {
      if (listNode1.val < listNode2.val) {
        iter.next = listNode1;
        listNode1 = listNode1.next;
      } else {
        iter.next = listNode2;
        listNode2 = listNode2.next;
      }
      iter = iter.next;
    }

    if (listNode1 != null) {
      iter.next = listNode1;
    }

    if (listNode2 != null) {
      iter.next = listNode2;
    }

    return preHead.next;
  }

}
