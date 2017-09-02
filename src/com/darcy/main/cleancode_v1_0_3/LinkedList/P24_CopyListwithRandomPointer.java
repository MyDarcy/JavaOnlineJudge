package com.darcy.main.cleancode_v1_0_3.LinkedList;

import java.util.HashMap;
import java.util.Map;

/**
 * Author by darcy
 * Date on 17-9-2 下午2:22.
 * Description:
 *
 * 给定链表,每个节点还包含一个随机的指针。
 * 创建一个这样的链表的副本.
 *
 * A linked list is given such that each node contains an additional
 * random pointer thatcould point to any node in the list or null.
 * Return a deep copy of the list
 *
 */
public class P24_CopyListwithRandomPointer {

  private static class RandomListNode {
    Integer val;
    RandomListNode next;
    RandomListNode random;

    public RandomListNode(Integer val) {
      this.val = val;
    }

    public RandomListNode() {
    }
  }


  /**
   * O(n)的时间复杂度,O(n)的空间复杂度。
   * 使用hash来存储节点和副本中的对应节点的关联关系.
   *
   * 时间复杂度O(n), 空间复杂度O(n);
   *
   * @param head
   * @return
   */
  public static RandomListNode solution1(RandomListNode head) {
    Map<RandomListNode, RandomListNode> map = new HashMap<>();
    RandomListNode p = head;
    RandomListNode dummy = new RandomListNode(0);
    RandomListNode q = dummy;
    while (p != null) {
      q.next = new RandomListNode(p.val);
      // 原始链表中的节点p和副本链表中的对应节点q.next建立映射关系.
      // p1->copy list p1
      // p2->copy list p2
      // ...
      map.put(p, q.next);
      p = p.next;
      q = q.next;
    }
    p = head;
    q = dummy;
    while (p != null) {
      // p.random指向的是原始链表中的节点. map中以它作为key的则是指向副本链表中的对应节点。
      q.next.random = map.get(p.random);
      p = p.next;
      q = q.next;
    }
    return dummy.next;
  }

  /**
   * modify the next node of the original node to point to its own copy.
   *
   * @param head
   * @return
   */
  public static RandomListNode copyRandomList(RandomListNode head) {
    RandomListNode p = head;
    while (p != null) {
      RandomListNode next = p.next;
      RandomListNode copy = new RandomListNode(p.val);
      p.next = copy;
      copy.next = next;
      p = next;
    }
    p = head;
    while (p != null) {
      p.next.random = (p.random != null) ? p.random.next : null;
      p = p.next.next;
    }
    p = head;
    RandomListNode headCopy = (p != null) ? p.next : null;
    while (p != null) {
      RandomListNode copy = p.next;
      p.next = copy.next;
      p = p.next;
      copy.next = (p != null) ? p.next : null;
    }
    return headCopy;
  }


}
