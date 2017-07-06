package crackingTheCodingInterview;

/**
 * Author by darcy
 * Date on 17-7-6 上午8:00.
 * Description:
 * 给定一个有环链表，实现一个算法返回环路的开始节点.
 * input: a->b-c->d->e->f->c
 * output c
 */
public class P826_HeadOfCircumList {
    class ListNode {
        char val;
        ListNode next = null;

        ListNode(char val) {
            this.val = val;
        }
    }

    /**
     * 当慢指针经过k步进入到环首部时, 快指针走了2k步刚好走到距离环首部 m = k%LoopSize步处.此时, slow比fast
     * 慢了m步, 也可以认为是fast比slow慢了LoopSize-m步. 之后每走一步, fast就靠近slow一步. slow和fast将在
     * LoopSize-m步后相遇.即慢指针此时从环路首部走了LoopSize-m步之后, 将和fast相遇.根据闭环规则, 相遇点和
     * 环路头部相距k步. (不失一般性, 可以认为k比LoopSize小)
     * @param head
     * @return
     */
    ListNode findBeginning(ListNode head) {
        if (head == null) {
            return null;
        }

        ListNode slow = head;
        ListNode fast = head;

        // 找到碰撞处.
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
            if (slow == fast) {
                break;
            }
        }

        // 没有碰撞处
        if (fast == null || fast.next == null) {
            return null;
        }

        // 从头部和碰撞处各走k步.
        ListNode first = head;
        while (first != fast) {
            first = first.next;
            fast = fast.next;
        }

        return first;
    }
}
