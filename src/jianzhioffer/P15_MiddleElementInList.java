package jianzhioffer;

/**
 * Created by darcy
 * 2017/4/10--23:58
 * Description:找链表的中间节点;
 * 思路：快慢指针; 快指针走两步，慢指针走一步;
 */
public class P15_MiddleElementInList {
    static class ListNode {
        int val;
        ListNode next;

        ListNode(int val) {
            this.val = val;
        }
    }

    public ListNode solution(ListNode head) {
        if (head == null || head.next == null) {
            return null;
        }

        ListNode leadingNode = head.next.next;
        ListNode rearNode = head;

        if (leadingNode == null) {
            return rearNode;
        }

        // 奇数个, 返回中间节点；
        // 偶数个，返回中间节点任意一个;
        while (true) {
            if (leadingNode.next != null) {
                leadingNode = leadingNode.next;
            } else {
                return rearNode;
            }

            if (leadingNode.next.next != null) {
                leadingNode = leadingNode.next.next;
                rearNode = rearNode.next;
            } else {
                rearNode = rearNode.next;
                return rearNode;
            }
        }
    }
}
