package crackingTheCodingInterview;

/**
 * Created by darcy
 * 2017/6/29--0:34
 * Description:
 * 编写代码，以给定值x为基准将链表分割成两部分，所有小于x的结点排在大于或等于x的结点之前
 * 给定一个链表的头指针 ListNode* pHead，请返回重新排列后的链表的头指针。注意：分割以后保持原来的数据顺序不变。
 *
 *
 */
public class P824_PartitionList {
    class ListNode {
        int val;
        ListNode next = null;

        ListNode(int val) {
            this.val = val;
        }
    }


        // write code here
        /**
         * 思路: 遍历链表，将链表分成两个部分，< x的放在第一个链表的尾端, > x的放在第二个链表的尾端。。
         * @param pHead
         * @param x
         * @return
         */
        public ListNode partition(ListNode pHead, int x) {
        ListNode iter = pHead;

        ListNode firstHead = null;
        ListNode firstIter = null;
        ListNode secondHead = null;
        ListNode secondIter = null;

        while (iter != null) {

            // 因为 iter 变成一个孤立的节点。
            ListNode next = iter.next;
            iter.next = null;

            if (iter.val < x) {
                if (firstHead == null) {
                    firstHead = firstIter = iter;
                } else {
                    firstIter.next = iter;
                    firstIter = iter;
                }
            } else {
                if (secondHead == null) {
                    secondHead = secondIter = iter;
                } else {
                    secondIter.next = iter;
                    secondIter = iter;
                }
            }

            iter = next;
        }

        if (firstHead == null) {
            return secondHead;
        }

        firstIter.next = secondHead;

        return firstHead;
    }

    /**
     * 思路: 遍历链表，将链表分成两个部分，不过是添加在两个子链表的头部。
     * 但是这种的话相对顺序变化了。
     * @param pHead
     * @param x
     * @return
     */
    public ListNode partition2(ListNode pHead, int x) {
        ListNode iter = pHead;

        ListNode firstHead = null;
        ListNode secondHead = null;

        while (iter != null) {

            ListNode next = iter.next;

            if (iter.val < x) {
                iter.next = firstHead;
                firstHead = iter;
            } else {
                iter.next = secondHead;
                secondHead = iter;

            }

            iter = next;
        }

        if (firstHead == null) {
            return secondHead;
        }

        ListNode head = firstHead;

        while (firstHead.next != null) {
            firstHead = firstHead.next;
        }

        firstHead.next = secondHead;

        return head;
    }
}
