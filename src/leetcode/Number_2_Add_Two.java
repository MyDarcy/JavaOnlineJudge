package leetcode;

/**
 * Author by: darcy
 * Created on 17-3-4-上午11:54.
 * Description:
 */
/*Definition for singly-linked list.*/
class ListNode {
    int val;
    ListNode next;
    ListNode(int x) { val = x; }
}
public class Number_2_Add_Two {
    /*public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        int gainOne = 0;
        ListNode previous = null;
        ListNode first = null;
        while (l1 != null && l2 != null) {
            int val = l1.val + l2.val + gainOne;
            if (val >= 10) {
                previous = first;
                first = new ListNode(val - 10);
                first.next = previous;
                gainOne = 1;
            } else {
                previous = first;
                first = new ListNode(val);
                first.next = previous;
                gainOne = 0;
            }
        }
        l1 = (l1 != null)? l1 : l2;
        if (l1 != null) {
            while (l1 != null) {
                int x = l1.val + gainOne;
                if (x >= 10) {
                    previous = first;
                    first = new ListNode(x - 10);
                    first.next = previous;
                    gainOne = 1;
                } else {
                    previous = first;
                    first = new ListNode(x);
                    first.next = previous;
                    gainOne = 0;
                }
            }
        }

        if (gainOne == 1) {
            previous = first;
            first = new ListNode(1);
            first = previous;
        }
        return first;
    }*/


    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        if (l1 == null) {
            return l2;
        }
        if (l2 == null) {
            return l1;
        }
        int gainOne = 0;
        ListNode previousL1 = l1;
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
        l1 = previousL1.next;
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
}
