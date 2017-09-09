package com.darcy.main.review;

import java.util.HashMap;
import java.util.Map;

/**
 * Author by darcy
 * Date on 17-9-9 上午10:19.
 * Description:
 */

class Node {
  Node next;
  int data;

  Node(int data) {
    this.data = data;
  }

  Node(int data, Node next) {
    this.data = data;
    this.next = next;
  }
}

public class MyLinkedList {

  private Node head;

  /**
   * 新增一个节点.
   *
   * @param data
   */
  public void add(int data) {
    Node node = new Node(data);
    if (head == null) {
      head = node;
      return;
    }

    Node iter = head;
    while (iter.next != null) {
      iter = iter.next;
    }
    iter.next = node;
  }

  public boolean deleteNode(int index) {
    if (index < 1 || index > length()) {
      return false;
    }

    if (index == 1) {
      head = head.next;
      return true;
    }

    Node iter = head.next;
    Node prev = head;
    int count = 1;
    while (iter != null) {
      count++;
      if (count == index) {
        prev.next = iter.next;
        return true;
      }
      prev = prev.next;
      iter = iter.next;
    }
    return false;
  }

  /**
   * 链表长度
   * @return
   */
  public int length() {
    Node iter = head;
    int count = 0;
    while (iter != null) {
      count++;
      iter = iter.next;
    }
    return count;
  }

  public Node sort() {
    Node iter = null;
    int temp = 0;
    Node current = head;
    while (current != null) {
      iter = current.next;
      while (iter != null) {
        if (current.data > iter.data) {
          temp = current.data;
          current.data = iter.data;
          iter.data = temp;
        }
        iter = iter.next;
      }
      current = current.next;
    }
    return head;
  }

  public void print() {
    Node iter = head;
    while (iter != null) {
      System.out.print(iter.data + "\t");
      iter = iter.next;
    }
    System.out.println();
  }

  /**
   * 删除重复节点.
   */
  public void deleteDuplicate() {
    Map<Integer, Integer> map = new HashMap<>();
    Node iter = head;
    Node prev = null;
    while (iter != null) {
      if (map.containsKey(iter.data)) {
        prev.next = iter.next;
      } else {
        map.put(iter.data, 1);
        prev = iter;
      }
      iter = iter.next;
    }
  }

  /**
   * 删除重复值的节点.
   */
  public void deleteDuplicate2() {
    Node iter = head;
    while (iter != null) {
      Node q = iter;
      // 维护前后节点之间的关系.
      while (q.next != null) {
        if (q.next.data == iter.data) {
          q.next = q.next.next;
        } else {
          q = q.next;
        }
      }
    }
  }

  /**
   * 倒数第k个节点.
   *
   * @param k
   * @return
   */
  public Node kthFromRear(int k) {
    if (k < 1 && k > length()) {
      return null;
    }
    Node iter = head;
    for (int i = 0; i < k - 1; i++) {
      iter = iter.next;
    }
    Node target = head;
    while (iter != null) {
      iter = iter.next;
      target = target.next;
    }
    return target;
  }

  /**
   * 链表的反转.
   *
   * @return
   */
  public Node reverse() {

    if (head == null) {
      return null;
    }

    if (head.next == null) {
      return head;
    }

    /*
    维持三个指针:
    前驱, current, 后继;
     */
    Node previous = null;
    Node current = head;
    Node next = head.next;

    while (current.next != null) {
      next = current.next;
      current.next = previous;
      previous = current;
      current = next;
    }

    current.next = previous;

    return current;
  }

  /**
   * 从尾部打印节点.
   */
  public void printFromRear() {
    Node iter = head;
    printFromRear(iter);

  }

  private void printFromRear(Node iter) {
    if (iter != null) {
      printFromRear(iter.next);
      System.out.println(iter.data);
    }
  }

  /**
   * 搜索中间节点.
   *
   * 1. lenght lenth / 2;
   * 2. 双向链表
   * 3. 快慢指针.
   * @return
   */
  public Node searchMid() {
    Node slow = head;
    Node fast = head;
    while (fast != null && fast.next != null && fast.next.next != null) {
      slow = slow.next;
      fast = fast.next.next;
    }
    return slow;
  }

  /**
   * 链表中是否有环.
   * @return
   */
  public boolean circleInList() {
    Node fast = head;
    Node slow = head;
    if (fast == null) {
      return false;
    }

    while (fast != null && fast.next != null) {
      fast = fast.next.next;
      slow = slow.next;
      if (fast == slow) {
        return true;
      }
    }

    // return !(fast == null || fast.next == null);
    return false;
  }

  /**
   * 找环的入口节点.
   *
   * 先找到环中的相遇点, 然后慢指针从头节点开始, 快指针从相遇点开始.
   * 相遇点就是入口节点.
   *
   * 整个链表长度为L, head到环入口距离a, 环入口到相遇点是x, 环长度是r. 慢指针走的距离为s.
   * 2s = s + nr // 快指针走的距离 = 慢指针走的距离 + n环长度.
   * a + x = nr // 慢指针走的距离,
   * a + x = (n-1)r + r = (n -1)r + (L -a)
   * a = (n - 1)r + (L - a - x) // head到入口的距离 = 相遇点到环入口的距离 + (n-1)环长度.
   *
   * @return
   */
  public Node loopEnterNode() {
    Node fast = head;
    Node slow = head;
    while (fast != null && fast.next != null) {
      fast = fast.next.next;
      slow = slow.next;

      if (fast == slow) {
        break;
      }
    }

    if (fast == null && fast.next == null) {
      return null;
    }

    // 同时走, 慢指针从头节点开始走, 快指针从相遇点开始走. 相遇点就是入口点.
    slow = head;
    while (slow != fast) {
      slow = slow.next;
      fast = fast.next;
    }

    return slow;

  }

  /**
   * 判断两个链表是否相交.
   * 即末尾节点是否相同.
   * @param head1
   * @param head2
   * @return
   */
  public static boolean isTwoListComeAccross(Node head1, Node head2) {
    Node iter1 = head1;
    Node iter2 = head2;
    while (iter1.next != null) {
      iter1 = iter1.next;
    }

    while (iter2.next != null) {
      iter2 = iter2.next;
    }

    return iter1 == iter2;
  }

  /**
   *
   * 相交的两个链表的第一个节点
   *
   * 先判断是否相遇,然后长链表先走(lengthBigger - lengthSmaller)步
   * 然后和短链表头一起走, 直到遇到了相遇的节点.
   *
   * @param head1
   * @param head2
   * @return
   */
  public static Node firstNodeComeAccross(Node head1, Node head2) {
    Node iter1 = head1;
    Node iter2 = head2;
    int count1 = 1;
    while (iter1.next != null) {
      count1++;
      iter1 = iter1.next;
    }

    int count2 = 1;
    while (iter2.next != null) {
      count2++;
      iter2 = iter2.next;
    }

    if (iter1 != iter2) {
      return null;
    }


    iter1 = head1;
    iter2 = head2;
    if (count1 > count2) {
      int d = count1 - count2;
      while (d != 0) {
        iter1 = iter1.next;
        count1--;
      }
    } else {
      int d = count2 - count1;
      while (d != 0) {
        iter2 = iter2.next;
        count2--;
      }
    }

    while (iter1 != iter2) {
      iter1 = iter1.next;
      iter2 = iter2.next;
    }

    return iter1;
  }

  /**
   * 没有给定头节点, 删除指定的节点.
   * @param given
   * @return
   */
  public static boolean deleteNode(Node given) {
    if (given == null || given.next == null) {
      return false;
    }

    // 后面的节点的值和pointer都赋值到当前节点。
    int temp = given.data;
    given.data = given.next.data;
    given.next = given.next.next;
    return true;
  }

  public static void main(String[] args) {
    Node head = new Node(5, new Node(4, new Node(3, new Node(2, new Node(1)))));
    MyLinkedList demo = new MyLinkedList();
    demo.head = head;
    demo.print();

    demo.sort();

    demo.print();
  }
}
