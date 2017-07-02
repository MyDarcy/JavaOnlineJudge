package crackingTheCodingInterview;

/**
 * Created by darcy
 * 2017/7/3--0:07
 * Description:
 *
 * 有两个用链表表示的整数，每个结点包含一个数位。这些数位是反向存放的，
 * 也就是个位排在链表的首部。编写函数对这两个整数求和，并用链表形式返回结果。
 * 给定两个链表ListNode* A，ListNode* B，请返回A+B的结果(ListNode*)。
 * 测试样例：
 * {1,2,3},{3,2,1}
 * 返回：
 * {4,4,4}
 */
public class P825_LinkPlus2 {

    class ListNode {
        int val;
        ListNode next = null;

        ListNode(int val) {
            this.val = val;
        }
    }

    /**
     * 递归的实现链表加法。
     * @param a
     * @param b
     * @return
     */
    public ListNode plusAB(ListNode a, ListNode b) {
        return plusAB(a, b, 0);
    }

    private ListNode plusAB(ListNode a, ListNode b, int carry) {
        // 返回条件.
        if (a == null && b == null && carry == 0) {
            return null;
        }

        ListNode result = new ListNode(0);
        int value = carry;
        if (a != null) {
            value += a.val;
        }
        if (b != null) {
            value += b.val;
        }

        result.val = value % 10;

        ListNode next = plusAB(a == null ? null : a.next,
                b == null ? null : b.next,
                value >= 10 ? 1: 0);
        result.next = next;
        return result;
    }


}
