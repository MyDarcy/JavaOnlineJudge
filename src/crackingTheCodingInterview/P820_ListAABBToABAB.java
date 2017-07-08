package crackingTheCodingInterview;

/**
 * Created by darcy
 * 2017/6/27--23:53
 * Description:
 * a1->a2->a3->a4->...->an->b1->b2->b3...->bn
 * a1->b1->a2->b2->a3->b3......->an->bn
 *
 * 思路：快慢指针的思路：p1指针每次都向前移动两步，而p2指针每次都向前移动一步，
 * 当p1达到链表末尾时候，p2达到链表中间。然后让p2和p1一步一步向反方向移动，并将p2
 * 指向的节点插入到p1所指向节点的后面。
 * p1快指针，p2慢指针。
 */
public class P820_ListAABBToABAB {

    private Node head = null;

    class Node {
        String str;
        Node next;

        public Node(String str) {
            this.str = str;
        }
    }

    public void append(String str) {

        if (head == null) {
            head = new Node(str);
            return;
        }

        Node iter = head;
        while (iter.next != null) {
            iter = iter.next;
        }

        iter.next = new Node(str);
    }

    public void print() {
        Node iter = head;
        while (iter != null) {
            System.out.print(iter.str + "\t");
            iter = iter.next;
        }
    }

    public void transform() {
        int length = length();
        // iter1表示尾节点。
        Node iter1 = head;
        while (iter1.next != null) {
            iter1 = iter1.next;
        }

        // iter2表示中间节点。
        Node iter2 = head;
        for (int i = 0; i < length / 2 - 1; i++) {
            iter2 = iter2.next;
        }

        // 反方向移动？双指针节点？
        // 链表逆序。然后？？
    }

    public int length() {
        Node iter = head;

        int number = 0;

        while (iter != null) {
            number++;
            iter = iter.next;
        }

        return number;
    }

    public static void main(String[] args) {
        P820_ListAABBToABAB demo = new P820_ListAABBToABAB();
        demo.append("a1");
        demo.append("a2");
        demo.append("a3");
        demo.append("b1");
        demo.append("b2");
        demo.append("b3");

        demo.print();

    }
}
