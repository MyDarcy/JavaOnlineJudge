package jianzhioffer;

/**
 * Created by darcy
 * 2017/4/10--23:28
 * Description:
 * <p>
 * 输入一个链表，输出该链表中倒数第k个结点。
 */

class P15_ListNode {
  int val;
  P15_ListNode next = null;

  P15_ListNode(int val) {
    this.val = val;
  }
}

public class P15_TheKthElementFromRear {
  public P15_ListNode FindKthToTail(P15_ListNode head, int k) {
    if (head == null || k <= 0) {
      return null;
    }

    // 先遍历到第k个节点，比后面一个节点领先k个节点;
    // 考虑k比链表长的情况;
    P15_ListNode kLeading = head;
    for (int i = 1; i < k; i++) {
      kLeading = kLeading.next;

      if (kLeading == null) {
        return null;
      }
    }

    P15_ListNode target = head;
    while (kLeading.next != null) {
      kLeading = kLeading.next;
      target = target.next;
    }
    return target;
  }
}
