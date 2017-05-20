package jianzhioffer;

/**
 * Created by darcy
 * 2017/5/21--0:21
 * Description:
 *
 * 在一个排序的链表中，存在重复的结点，请删除该链表中重复的结点，重复的结点不保留，返回链表头指针。
 * 例如，链表1->2->3->3->4->4->5 处理后为 1->2->5
 */
public class P56_DeleteDuplicateNodeInList {

    public class ListNode {
        int val;
        ListNode next = null;

        ListNode(int val) {
            this.val = val;
        }
    }

    public ListNode deleteDuplication(ListNode pHead) {

        if (pHead == null) {
            return null;
        }

        if (pHead.next == null) {
            return pHead;
        }

        ListNode firstOccurence = pHead;
        ListNode previous = pHead;
        ListNode iter = pHead.next;

        while (iter != null) {
            if (iter.val == previous.val) {
                iter = iter.next;
                previous = previous.next;
            } else {
                firstOccurence.next = iter;
                firstOccurence = firstOccurence.next;
                iter = iter.next;
                previous = previous.next;
            }
        }

        return pHead;

    }
}
