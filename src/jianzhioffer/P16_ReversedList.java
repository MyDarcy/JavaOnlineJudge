package jianzhioffer;

/**
 * Created by darcy
 * 2017/4/11--0:31
 * Description:
 */
public class P16_ReversedList {

    class ListNode {
        int val;
        ListNode next = null;

        ListNode(int val) {
            this.val = val;
        }
    }

    public ListNode ReverseList(ListNode head) {
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
        ListNode previous = null;
        ListNode current = head;
        ListNode next = head.next;

        while (current.next != null) {
            next = current.next;
            current.next = previous;
            previous = current;
            current = next;
        }

        current.next = previous;

        return current;
    }
}
