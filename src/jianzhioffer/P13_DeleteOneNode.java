package jianzhioffer;

/**
 * Created by darcy
 * 2017/4/9--23:15
 * Description:
 * 删除链表中的单个节点
 */

class P13_ListNode {
    int value;
    P13_ListNode next;

    P13_ListNode(int value) {
        this.value = value;
    }
}

public class P13_DeleteOneNode {
    public static void main(String[] args) {
        P13_DeleteOneNode demo = new P13_DeleteOneNode();
        P13_ListNode head = null;
        P13_ListNode toBeDeleted = null;
        demo.deleteOneNode(head, toBeDeleted);
    }

    public void deleteOneNode(P13_ListNode head, P13_ListNode toBeDeleted) {

        if (toBeDeleted == null) {
            throw new RuntimeException("to be deleted is null");
        }

        if (toBeDeleted != null && toBeDeleted.next != null) {
            toBeDeleted.value = toBeDeleted.next.value;
            toBeDeleted.next = toBeDeleted.next.next;
            toBeDeleted = null;
        } else {
            P13_ListNode previous = null;
            for (P13_ListNode i = head; i != null ; i = i.next) {
                previous = i;
            }
            previous.next = null;
        }
    }



}
