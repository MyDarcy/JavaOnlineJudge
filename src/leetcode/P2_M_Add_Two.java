package leetcode;

/**
 * Author by: darcy
 * Created on 17-3-4-上午11:54.
 * Description:
 * You are given two non-empty linked lists representing two non-negative integers.
 * The digits are stored in reverse order and each of their nodes contain a single digit.
 * Add the two numbers and return it as a linked list.
 *
 * You may assume the two numbers do not contain any leading zero, except the number 0 itself.
 *
 * Example:
 *
 * Input: (2 -> 4 -> 3) + (5 -> 6 -> 4)
 * Output: 7 -> 0 -> 8
 * Explanation: 342 + 465 = 807.
 */
/*Definition for singly-linked list.*/
class ListNode {
    int val;
    ListNode next;
    ListNode(int x) { val = x; }
}
public class P2_M_Add_Two {

    /**
     * 1. 是在原列表的基础上改动还是创建新的链表；
     * 2. previous指针指向前一个节点,维护链表之间的关系
     * @param l1
     * @param l2
     * @return
     */
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        if (l1 == null) {
            return l2;
        }
        if (l2 == null) {
            return l1;
        }
        int gainOne = 0;
        ListNode previousL1 = l1; // 用来维护链接关系;
        ListNode first = l1;
        while (l1 != null && l2 != null) {
            int val = l1.val + l2.val + gainOne;
            if (val >= 10) {
                l1.val = val - 10;
                gainOne = 1;

            } else {
                l1.val = val;
                gainOne = 0;
            }
            previousL1 = l1;
            l1 = l1.next;
            l2 = l2.next;
        }

        // 重要的是维持链表的关系;
        // 如果不加一个previous的话, 那么遍历到两个节点都结束时, 那么必须由前一个节点来维系的链接关系就没有了。
        if (l1 == null) {
            previousL1.next = l2;
        }
        l1 = previousL1.next; // 要被处理的节点;
        while (l1 != null) {
            int val = l1.val + gainOne;
            if (val >= 10) {
                l1.val = val - 10;
                gainOne = 1;
            } else {
                l1.val = val;
                gainOne = 0;
            }
            previousL1 = l1;
            l1 = l1.next;
        }

        if (gainOne == 1) {
            previousL1.next = new ListNode(1);
        }

        return first;
    }

    /*
      solution from leetcode.com
      Two things to make the code simple:
        Whenever one of the two ListNode is null, replace it with 0.
        Keep the while loop going when at least one of the three conditions is met.
        只要满足三个条件一个，就可以创建新节点了；

      Note that we use a dummy head to simplify the code. Without a dummy head,
      you would have to write extra conditional statements to initialize the head's value.

      Time complexity : O(max(m,n)). Assume that mm and nn represents the length of l1l1 and l2l2 respectively, the algorithm above iterates at most \max(m, n)max(m,n) times.

      Space complexity : O(max(m,n)). The length of the new list is at most max(m,n) + max(m,n)+1.
     */
    public ListNode addTwoNumbers2(ListNode l1, ListNode l2) {
        ListNode prev = new ListNode(0);
        ListNode head = prev;
        int carry = 0;
        while (l1 != null || l2 != null || carry != 0) {
            ListNode cur = new ListNode(0);
            int sum = ((l2 == null) ? 0 : l2.val) + ((l1 == null) ? 0 : l1.val) + carry;
            cur.val = sum % 10;
            carry = sum / 10;
            prev.next = cur;
            prev = cur;

            l1 = (l1 == null) ? l1 : l1.next;
            l2 = (l2 == null) ? l2 : l2.next;
        }
        return head.next;
    }

    /**
     * 哨兵节点：
     * sum /= 10 相当于进位; sum % 10 相当于个位数;
     * @param l1
     * @param l2
     * @return
     */
    public ListNode addTwoNumbers3(ListNode l1, ListNode l2) {
        ListNode c1 = l1;
        ListNode c2 = l2;
        ListNode sentinel = new ListNode(0);
        ListNode d = sentinel;
        int sum = 0;
        while (c1 != null || c2 != null) {
            sum /= 10;
            if (c1 != null) {
                sum += c1.val;
                c1 = c1.next;
            }
            if (c2 != null) {
                sum += c2.val;
                c2 = c2.next;
            }
            d.next = new ListNode(sum % 10);
            d = d.next;
        }
        if (sum / 10 == 1)
            d.next = new ListNode(1);
        return sentinel.next;
    }
}
