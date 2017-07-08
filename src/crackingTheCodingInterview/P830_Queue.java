package crackingTheCodingInterview;

/**
 * Author by darcy
 * Date on 17-7-6 下午10:03.
 * Description:
 */
public class P830_Queue {
    class Node {
        Integer data;
        Node next;

        public Node(Integer data) {
            this.data = data;
        }
    }

    Node head, tail;

    void enqueue(Integer data) {
        if (head == null) {
            head = tail = new Node(data);
        } else {
            tail.next = new Node(data);
            tail = tail.next;
        }
    }

    Integer dequeue() {
        if (head != null) {
            Integer item = head.data;
            head = head.next;
            return item;
        }
        return null;
    }
}
