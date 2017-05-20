package jianzhioffer;

/**
 * Created by darcy
 * 2017/5/20--0:59
 * Description:
 * 一个链表中包含环，请找出该链表的环的入口结点。
 */
public class P56_EntryNodeInList {

    public class ListNode {
        int val;
        ListNode next = null;

        ListNode(int val) {
            this.val = val;
        }
    }

    /**
     * 快慢指针的思想，先判断一个链表是否存在环，有环，判定环的长度N，然后让第二个指针先走N步，然后同时走。OK。
     * @param pHead
     * @return
     */
    public ListNode EntryNodeOfLoop(ListNode pHead) {

        ListNode node = nodeInCircle(pHead);
        if (node != null) {
            int size = sizeOfCircle(node);
            ListNode first = pHead;
            ListNode second = pHead;
            int count = 0;
            while (true) {
                second = second.next;
                count++;
                if (count == size) {
                    break;
                }
            }

            while (first != second) {
                first = first.next;
                second = second.next;
            }
            return first;

        }

        return null;

    }

    private int sizeOfCircle(ListNode node) {
        ListNode head = node;
        ListNode iter = node;
        int count = 0;
        while (iter.next != head) {
            count++;
            iter = iter.next;
        }

        return count + 1;
    }

    public ListNode nodeInCircle(ListNode pHead) {
        if (pHead == null) {
            return null;
        }

        if (pHead.next == null) {
            return null;
        }

        ListNode first = pHead;
        ListNode second = pHead.next;
        while (second != null && second.next != null && second.next.next != null) {
            first = first.next;
            second = second.next.next;
            if (first == second) {
                return first;
            }
        }
        return null;
    }
}
