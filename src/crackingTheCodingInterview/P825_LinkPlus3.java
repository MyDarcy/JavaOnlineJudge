package crackingTheCodingInterview;

/**
 * Created by darcy
 * 2017/7/3--0:07
 * Description:
 *
 * 有两个用链表表示的整数，每个结点包含一个数位。这些数位是正向存放的，
 *。编写函数对这两个整数求和，并用链表形式返回结果。
 * 给定两个链表ListNode* A，ListNode* B，请返回A+B的结果(ListNode*)。
 *
 * 数位是正向存放的。
 *
 * 测试样例：
 * {1,2,3, 4},{5, 6, 7}
 * 返回：
 * {1, 8, 0, 1}
 */
public class P825_LinkPlus3 {

    class ListNode {
        int val;
        ListNode next = null;
        ListNode prev = null;

        ListNode(int val) {
            this.val = val;
        }
    }
    
    class PartialSum {
        ListNode sum = null;
        int carry = 0;
    }

    /**
     * 正向存放和逆向不同，因为长度不同的话，对应的位次也不一样。
     * 所以可以考虑先填充0;
     *
     * @param a
     * @param b
     * @return
     */
    public ListNode addLists(ListNode a, ListNode b) {
        ListNode resultHead = null;
        ListNode resultIter = null;

        int lengthA = length(a);
        int lengthB = length(b);
        if (lengthA < lengthB) {
            a = paddingList(a, lengthB - lengthA);
        } else {
            b = paddingList(b, lengthA - lengthB);
        }

        PartialSum sum = addListsHelper(a, b);

        // 有进位，插入到链表首部，否则直接返回。
        if (sum.carry == 0) {
            return sum.sum;
        } else {
            ListNode result = insertBefore(sum.sum, sum.carry);
            return result;
        }
    }

    private PartialSum addListsHelper(ListNode a, ListNode b) {
        if (a == null && b == null) {
            PartialSum sum = new PartialSum();
            return sum;
        }

        // 将低位递归求和.
        PartialSum sum = null;
        if (a == null) {
            sum = addListsHelper(null, b.next);
        } else if (b == null) {
            sum = addListsHelper(a.next, null);
        } else {
            sum = addListsHelper(a.next, b.next);
        }

        // ParsialSum sum = addListsHelper(a.next, b.next)// 有问题。

        int value = sum.carry + a.val + b.val;

        ListNode result = insertBefore(sum.sum, value % 10);
        sum.sum = result;
        sum.carry = value / 10;
        return sum;
    }

    /**
     * 将data数据所代表的节点插入到listNode的前面。
     * @param listNode
     * @param data
     * @return
     */
    private ListNode insertBefore(ListNode listNode, int data) {
        ListNode node = new ListNode(data);
        if (listNode != null) {
            listNode.prev = node;
            node.next = listNode;
        }
        return node;
    }

    /**
     * 填充链表到较长的那一个。
     * @param a
     * @param paddingLength
     * @return
     */
    private ListNode paddingList(ListNode a, int paddingLength) {
        ListNode head = a;
        for (int i = 0; i < paddingLength; i++) {
            ListNode p = new ListNode(0);
            head.prev = p;
            p.next = head;
            head = p;
        }
        return head;
    }

    private int length(ListNode b) {
        int length = 0;
        ListNode iter = b;
        while (iter != null) {
            iter = iter.next;
            length++;
        }
        return length;
    }


}
