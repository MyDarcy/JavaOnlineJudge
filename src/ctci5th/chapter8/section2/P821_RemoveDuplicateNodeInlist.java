package ctci5th.chapter8.section2;

import java.util.HashSet;

/**
 * Created by darcy
 * 2017/6/28--0:38
 * Description:
 * 移除链表中的非重复节点。
 * 如果不能使用额外的空间呢？
 */
public class P821_RemoveDuplicateNodeInlist {

    class ListNode {
        int val;
        ListNode next = null;

        ListNode(int val) {
            this.val = val;
        }
    }

    /**
     * set记录重复节点。
     * @param head
     */
    public void deleteDuplicateNode(ListNode head) {
        HashSet<Integer> set = new HashSet<>();
        ListNode previous = null;
        ListNode iter = head;
        while (iter != null) {
            if (set.contains(iter.val)) {
                previous.next = iter.next;
            } else {
                set.add(iter.val);
            }
            iter = iter.next;
        }
    }


    /**
     * 不使用额外内存。
     * 类似两个for循环的遍历，不过这里是用两个指针。
     * @param head
     */
    public void deleteDuplicateNode2(ListNode head) {
        if (head == null) {
            return;
        }

        ListNode current = head;
        while (current != null) {
            ListNode iter = current;
            while (iter.next != null) {
                if (iter.next.val == current.val) {
                    iter.next = iter.next.next;
                } else {
                    iter = iter.next;
                }
            }
            current = current.next;
        }
    }



}
