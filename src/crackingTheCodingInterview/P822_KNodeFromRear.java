package crackingTheCodingInterview;

/**
 * Created by darcy
 * 2017/6/28--23:50
 * Description:
 */
public class P822_KNodeFromRear {

    class ListNode {
        int val;
        ListNode next = null;

        ListNode(int val) {
            this.val = val;
        }
    }

    /**
     * 快慢指针，快快指针先走k-1步，走到第k个节点，然后两个节点个迭代，直到快指针的next为null。
     * @param head
     * @param k
     * @return
     */
    public ListNode FindKthToTail(ListNode head,int k) {
        if (head == null || k <= 0) {
            return null;
        }

        ListNode kLeading = head;
        for (int i = 1; i < k; i++) {
            kLeading = kLeading.next;

            if (kLeading == null) {
                return null;
            }
        }

        ListNode target = head;
        while (kLeading.next != null) {
            kLeading = kLeading.next;
            target = target.next;
        }
        return target;

    }

    class Wrapper {
        int value = 0;
    }

    /**
     * 算法直接递归遍历完整个链表，当到尾节点的时候，包装器值为0，每调用完一次包装器值+1，当包装器值为k的时候，
     * hea就是指向了第k个节点。
     *
     * 每调用一次方法就访问一个节点，回退栈帧的时候更新k的值。
     *
     * 递归调用的栈帧中访问某个类似全局变量的值，对其进行更新。
     * 递归的精髓。
     * @param head
     * @param k
     * @param wrapper
     * @return
     */
    public ListNode solution(ListNode head,int k, Wrapper wrapper) {
        if (head == null || k <= 0) {
            return null;
        }

        ListNode node = solution(head.next, k, wrapper);
        wrapper.value += 1;
        if (wrapper.value == k) {
            return head;
        }

        return node;
    }

}
