package ctci5th.chapter8.section2;

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
public class P825_LinkPlus {

    class ListNode {
        int val;
        ListNode next = null;

        ListNode(int val) {
            this.val = val;
        }
    }

    /**
     * 遍历两个链表，各位相加即可。
     * @param a
     * @param b
     * @return
     */
    public ListNode plusAB(ListNode a, ListNode b) {
        // write code here
        ListNode resultHead = null;
        ListNode resultIter = null;
        if (a == null) {
            return b;
        }

        if (b == null) {
            return a;
        }

        ListNode iteratorA = a;
        ListNode iteratorB = b;

        int carry = 0;
        int sum = 0;
        ListNode temp = null;

        while (iteratorA != null && iteratorB != null) {
            sum = iteratorA.val + iteratorB.val + carry;
            if (sum < 10) {
                carry = 0;
                if (resultHead == null) {
                    resultHead = resultIter = new ListNode(sum);
                } else {
                    temp = new ListNode(sum);
                    resultIter.next = temp;
                    resultIter = temp;
                }
            } else {
                carry = 1;
                sum -= 10;
                if (resultHead == null) {
                    resultHead = resultIter = new ListNode(sum);
                } else {
                    temp = new ListNode(sum);
                    resultIter.next = temp;
                    resultIter = temp;
                }
            }
            iteratorA = iteratorA.next;
            iteratorB = iteratorB.next;
        }


        ListNode iter = null;
        if (iteratorA == null) {
            iter = iteratorB;
        }
        if (iteratorB == null) {
            iter = iteratorA;
        }
        if (iter == null) {
            if (carry != 0) {
                resultIter.next = new ListNode(carry);
            }
            return resultHead;
        }

        while (iter != null) {
            sum = iter.val + carry;
            if (sum < 10) {
                carry = 0;
                if (resultHead == null) {
                    return iter;
                } else {
                    temp = new ListNode(sum);
                    resultIter.next = temp;
                    resultIter = temp;
                }
            } else {
                carry = 1;
                sum -= 10;
                if (resultHead == null) {
                    return iter;
                } else {
                    temp = new ListNode(sum);
                    resultIter.next = temp;
                    resultIter = temp;
                }
            }
            iter = iter.next;
        }

        if (carry != 0) {
            resultIter.next = new ListNode(0);
        }

        return resultHead;
    }


}
