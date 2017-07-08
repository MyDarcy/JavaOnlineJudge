package crackingTheCodingInterview;

/**
 * Author by darcy
 * Date on 17-7-6 下午10:00.
 * Description:
 */
public class P830_Stack {
    class Node {
        Integer data;
        Node next;

        public Node(Integer data) {
            this.data = data;
        }
    }

    Node top;

    Integer pop() {
        if (top != null) {
            Integer item = top.data;
            top = top.next;
            return item;
        }
        return null;
    }

    void push(Integer data) {
        Node node = new Node(data);
        node.next = top;
        top = node;
    }

    Integer peek() {
        if (top == null) {
            return null;
        }
        return top.data;
    }
}
