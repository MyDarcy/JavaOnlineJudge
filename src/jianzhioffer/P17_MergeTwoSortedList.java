package jianzhioffer;

/**
 * Created by darcy
 * 2017/4/11--0:57
 * Description:
 * 输入两个单调递增的链表，输出两个链表合成后的链表，当然我们需要合成后的链表满足单调不减规则。
 */
public class P17_MergeTwoSortedList {

  class ListNode {
    int val;
    ListNode next = null;

    ListNode(int val) {
      this.val = val;
    }
  }

  public ListNode Merge(ListNode list1, ListNode list2) {

    // 都为null，直接返回;
    if (list1 == null && list2 == null) {
      return null;
    }

        /*
        思路：构建一个虚拟头节点，更容易维护;
         */
    ListNode head = new ListNode(-1);
    ListNode current = head;

    while (list1 != null && list2 != null) {
      if (list1.val < list2.val) {
        current.next = new ListNode(list1.val);
        list1 = list1.next;
        current = current.next;
      } else {
        current.next = new ListNode(list2.val);
        list2 = list2.next;
        current = current.next;
      }
    }

    if (list1 != null || list2 != null) {
      list1 = (list1 != null ? list1 : list2);
      for (; list1 != null; list1 = list1.next) {
        current.next = new ListNode(list1.val);
        current = current.next;
      }
    }

    if (head.next != null) {
      return head.next;
    }

    return null;

  }

  /*
      递归版本
      1, 3
      2
   */
  public ListNode Merge2(ListNode list1, ListNode list2) {
    if (list1 == null) {
      return list2;
    }

    if (list2 == null) {
      return list1;
    }

    if (list1.val < list2.val) {
      list1.next = Merge2(list1.next, list2);
      return list1;
    } else {
      list2.next = Merge2(list1, list2.next);
      return list2;
    }
  }
}
