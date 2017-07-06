package crackingTheCodingInterview;

import java.util.Deque;
import java.util.LinkedList;

/**
 * Author by darcy
 * Date on 17-7-6 上午9:17.
 * Description:
 * 请编写一个函数，检查链表是否为回文。
 * 给定一个链表ListNode* pHead，请返回一个bool，代表链表是否为回文。
 *　{1,2,3,2,1}
 * 返回：true
 * {1,2,3,2,3}
 * 返回：false
 *
 * 解法１: 利用一个栈存储半个list.
 * 解法2: 递归求解
 * 解法3: 反转链表
 */
public class P827_palindromicList {
    class ListNode {
        int val;
        ListNode next = null;

        ListNode(int val) {
            this.val = val;
        }
    }

    /**
     * 利用栈存储半个链表
     * 如果链表长度已知,那么for遍历半个list即可.
     * 如果链表长度未知,那么fast-slow pointer, 将slow所指向的节点入栈.
     *
     * 入栈完毕, 判断是否链表长度是否是奇偶数, 决定slow是否移动一个节点,后面的就是链表节点和stack中的节点的比较
     *
     * @param pHead
     * @return
     */
    public boolean isPalindrome(ListNode pHead) {
        // write code here
        ListNode slow = pHead;
        ListNode fast = pHead;
        Deque<ListNode> stack = new LinkedList<>();
        while (fast != null && fast.next != null) {
            stack.addLast(slow);
            slow = slow.next;
            fast = fast.next.next; // fast指针每次移动两步.
        }

        // here. 如果此时fast != null 但是fast.next == null, 说明链表长度为奇数,
        // 那么中间节点不参与比较.
        if (fast != null) {
            slow = slow.next;
        }

        ListNode top = null;
        while (slow != null) { // !stack.isEmpty()
            top = stack.removeLast();
            if (top.val != slow.val) {
                return false;
            }
            slow = slow.next;
        }
        return true;
    }

    /**
     * 递归解法
     * @param pHead
     * @return
     */
    public boolean isPalindrome(ListNode pHead) {
        
    }


    /**
     * 解法3: 反转链表, 然后进行比较.但是这需要构造一个链表的副本.
     * tips: 只需要比较前半部分即可.
     * @param pHead
     * @return
     */
    public boolean isPalindrome3(ListNode pHead) {
        // write code here
        return false;
    }

    private ListNode ReverseList(ListNode head) {
        if (head == null) {
            return null;
        }

        if (head.next == null) {
            return head;
        }

        // 维持三个指针:前驱, current, 后继;
        ListNode previous = null;
        ListNode current = head;
        ListNode next = head.next;

        while (current.next != null) {
            next = current.next;
            current.next = previous;
            previous = current;
            current = next;
        }

        current.next = previous;

        return current;
    }
}
