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
   * 如果原节点-副本节点链建立起来后, 那么副本节点的随机域就是原节点的随机域的下一个节点.
   * 这个也好理解,因为原节点和副本节点就是相邻的.
   * node.next.random = node.random.next; // 副本节点的随机域 = 原节点随机域的下  一个节点(该随机域节点的副本)
   *
   * i. Create a copy of each of the original node and insert them in between two
   *    original nodes in an alternate fashion.
   * ii. Assign random pointer of each node copy.
   * iii. Restore the input to its original configuration.
   *
   * @param head
   * @return
   */
  public static RandomListNode copyRandomList(RandomListNode head) {
    RandomListNode p = head;

    // 创建原始链表的每个节点的一个副本. 节点副本链接在原始节点后边.
    // p1 -> dp1 -> p2 -> dp2 -> p3 -> dp3...
    while (p != null) {
      RandomListNode next = p.next;
      RandomListNode copy = new RandomListNode(p.val);
      p.next = copy;
      copy.next = next;
      p = next;
    }
    p = head;

    // 建立随机域的关联.
    while (p != null) {
      // 原来链表中每一个节点p的副本节点是p.next; 因为上面的复制是副本链在原节点后面.
      // p.next.random : p节点的副本节点的random域.
      // p.random : p的random指针可能指向的节点(也可能为null); p.random 的副本节点就是其下一个节点,
      // 即p.random.next;
      // p.next是p节点的副本节点.
      // p.random是.next是p.random节点的副本节点.
      p.next.random = (p.random != null) ? p.random.next : null;
      // 下一个原节点.
      p = p.next.next;
    }
    p = head;
    // 复制链表的头节点.
    RandomListNode headCopy = (p != null) ? p.next : null;
    // 针对原链表进行迭代.
    while (p != null) {
      // p不为null,那么副本节点必须存在.
      RandomListNode copy = p.next;
      p.next = copy.next;
      p = p.next;
      // 建立副本节点之间的连接.
      // 每一次迭代只建立前后两个副本节点之间的关联. 但是头节点已知,
      // 所以迭代过程中只需要建立前后两个副本节点之间的连接关系.
      copy.next = (p != null) ? p.next : null;
    }
    return headCopy;
  }
}
