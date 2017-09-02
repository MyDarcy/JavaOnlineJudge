package com.darcy.main.cleancode_v1_0_3.LinkedList;

import java.util.*;

/**
 * Author by darcy
 * Date on 17-9-2 上午10:44.
 * Description:
 *
 * 合并k个已序的链表.分析复杂度.
 *
 * Merge k sorted linked lists and return it as one sorted list.
 * Analyze and describe itscomplexity
 *
 */
public class P23_MergeKSortedLinkedLists {

  public static class ListNode {
    Integer val;
    ListNode next;

    public ListNode() {}

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
   * 暴力方法, 每次合并一个。
   * （l1, l2, l3, l4） -> (l1 + l2, l3, l4) -> (l1 + l2 + l3, l4) -> (l1 + l2 + l3 + l4)
   *
   * 假设每个list的长度是n,那么k个链表的总的复杂度是O(2n) + O(3n) + O(kn) = O(k^2 * n)
   *
   * The brute force approach is to merge a list one by one. For example, if the
   * lists = [l1, l2,l3, l4], we first merge l1 and l2, then merge the result with l3, and finally l4.
   *
   * @param lists
   * @return
   */
  public static ListNode solution1(List<ListNode> lists) {

    return null;
  }


  /**
   * 用堆来解决问题.
   * O(k)的空间复杂度,O(nklogk)的时间复杂度。
   *
   * We could use a min heap of size k. The heap is first initialized with the smallest element
   * from each list. Then as we extract the nodes out from the heap, we must remember to
   * insert its next node into the heap. As each insert operation into the heap costs log(k) and
   * there are a total of nk elements, the total runtime complexity is O(nk log k)
   * @param lists
   * @return
   */
  public static ListNode heapSolution(List<ListNode> lists) {
    if (lists == null) {
      return null;
    }

    Queue<ListNode> heap = new PriorityQueue<>(lists.size(), new Comparator<ListNode>() {
      @Override
      public int compare(ListNode o1, ListNode o2) {
        // 不考虑溢出问题.
        return o1.val - o2.val;
      }
    });

    // 先将n个节点的头节点入heap中.
    for (ListNode node : lists) {
      heap.add(node);
    }

    ListNode preHead = new ListNode(-1);
    ListNode iter = preHead;
    while (!heap.isEmpty()) {
      ListNode temp = heap.poll();
      iter.next = temp;
      iter = iter.next;

      // 这里的处理,当出heap的节点的后一个节点不为null的时候，将后一个节点入heap,
      // 避免不知道什么后续节点入heap的尴尬情况, 而且该节点的后续节点也极有可能是下一次出heap的。
      if (temp.next != null) {
        heap.add(temp.next);
      }
    }

    return preHead.next;
  }

  /**
   *
   * 利用merge归并排序的思想。
   * 相当于是一种分治的机制.
   *
   * @param lists
   * @return
   */
  public static ListNode mergeSolution(List<ListNode> lists) {

    return null;
  }

  /**
   *
   * @param lists
   * @return
   */
  public ListNode mergeKLists(List<ListNode> lists) {
    if (lists.isEmpty()) return null;
    int end = lists.size() - 1;
    while (end > 0) {
      int begin = 0;
      while (begin < end) {
        lists.set(begin, merge2Lists(lists.get(begin),
            lists.get(end))); // 两两归并.直到链表中只剩下一个元素.
        begin++;
        end--;
      }
    }
    return lists.get(0);
  }

  /**
   *
   * 合并两个节点.
   *
   * @param l1
   * @param l2
   * @return
   */
  private ListNode merge2Lists(ListNode l1, ListNode l2) {
    ListNode dummyHead = new ListNode(0);
    ListNode p = dummyHead;
    while (l1 != null && l2 != null) {
      if (l1.val < l2.val) {
        p.next = l1;
        l1 = l1.next;
      } else {
        p.next = l2;
        l2 = l2.next;
      }
      p = p.next;
    }
    if (l1 != null) p.next = l1;
    if (l2 != null) p.next = l2;
    return dummyHead.next;
  }

  public static void main(String[] args) {
    ListNode listNode1 =
        new ListNode(1,
          new ListNode(5,
            new ListNode(9,
                new ListNode(13))));

    ListNode listNode2 =
        new ListNode(2,
          new ListNode(6,
            new ListNode(10,
                new ListNode(14))));

    ListNode listNode3 =
        new ListNode(3,
          new ListNode(7,
            new ListNode(11,
                new ListNode(15))));

    List<ListNode> lists = Arrays.asList(listNode1, listNode2, listNode3);
    ListNode head1 = heapSolution(lists);
    StringBuilder sb = new StringBuilder();
    while (head1 != null) {
      sb.append(head1.val + "->");
      head1 = head1.next;
    }
    if (sb.length() > 2) {
      System.out.println(sb.substring(0, sb.length() - 2));
    } else {
      System.out.println(sb);
    }
  }

}
