package ctci5th.chapter8.section2;

/**
 * Created by darcy
 * 2017/6/27--23:44
 * Description:
 *
 * 链表：
 * 1. 技巧1-- 快慢指针，快指针先走几步，或者与慢指针相差固定的步数.
 *
 */
public class P820_Node {
    class Node {
        int value;
        Node next;

        public Node(int d) {
            this.value = value;
        }

        public void append(int d) {
            Node temp = new Node(d);
            Node p = this;
            while (p.next != null) {
                p = p.next;
            }
            p.next = temp;
        }

        public Node deleteNode(Node head, int d) {
            Node iter = head;
            /*if (iter == null) {
                return head;
            }*/
            if (iter.value == d) {
                return iter.next;
            }

            while (iter.next != null) {
                if (iter.next.value == d) {
                    iter.next = iter.next.next;
                    // 返回表头节点。
                    return head;
                }
                iter = iter.next;
            }

            return head;
        }
    }
}
