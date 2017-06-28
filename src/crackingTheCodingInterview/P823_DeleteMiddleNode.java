package crackingTheCodingInterview;

/**
 * Created by darcy
 * 2017/6/28--23:56
 * Description:
 * 实现一个算法，删除单向链表中间的某个结点，假定你只能访问该结点。
 * 给定带删除的节点，请执行删除操作，若该节点为尾节点，返回false，否则返回true
 *
 * a->b->c->d->e 给定指向c的指针，那么链表变为a->b->d->e;
 */
public class P823_DeleteMiddleNode {

    class ListNode {
        int val;
        ListNode next = null;

        ListNode(int val) {
            this.val = val;
        }
    }

    /**
     * 当前节点所指向的后继节点的内容移到当前待删节点即可。
     * @param pNode
     * @return
     */
    public boolean removeNode(ListNode pNode) {
        // write code here
        // 尾节点的情况直接返回false.
        if (pNode == null || pNode.next == null) {
            return false;
        }

        pNode.val = pNode.next.val;
        pNode.next = pNode.next.next;

        return true;

    }


    /**
     * 尾节点也要删除的情况。上面是牛客网的测试题。
     * 那么尾节点也要删除的情况的话，那么需要遍历到尾节点的前一个节点，将其next域置null.
     * @param pNode
     * @return
     */
    public boolean removeNode2(ListNode pNode) {
        // write code here
        // 尾节点的情况直接返回false.
        if (pNode == null || pNode.next == null) {

            // 给定头指针的话，直接遍历当待删除节点即可。或者利用所给指针将待删节点置为flag节点。
        }

        pNode.val = pNode.next.val;
        pNode.next = pNode.next.next;

        return true;

    }

}
