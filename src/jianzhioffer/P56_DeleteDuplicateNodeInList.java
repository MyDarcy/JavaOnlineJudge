package jianzhioffer;

/**
 * Created by darcy
 * 2017/5/21--0:21
 * Description:
 * <p>
 * 在一个排序的链表中，存在重复的结点，请删除该链表中重复的结点，重复的结点不保留，返回链表头指针。
 * 例如，链表1->2->3->3->4->4->5 处理后为 1->2->5
 */
public class P56_DeleteDuplicateNodeInList {

    public static class ListNode {
        int val;
        ListNode next = null;

        ListNode(int val) {
            this.val = val;
        }
    }


    /**
     * 思路: 考虑头结点需要删除的情况; 从当前结点(开始赋值为头结点)开始迭代，如果后面的节点跟当前节点值相等，那么就
     * 需要代表有重复了，需要删除。把所有重复的节点都找出来，找到下一个不重复的节点，链到前一个节点。
     *
     * @param pHead
     * @return
     */
    public ListNode deleteDuplication2(ListNode pHead) {
        if (pHead == null || pHead.next == null) {
            return pHead;
        }

        // 记录前一个节点和迭代的节点;
        ListNode previous = null;
        ListNode iter = pHead;

        // 迭代当前节点;
        while (iter != null) {
            // 可能重复的节点;
            ListNode following = iter.next;
            boolean needDeleted = false;
            if (following != null && following.val == iter.val) {
                needDeleted = true;
            }

            // 维护好指针，进入下一层循环。
            if (!needDeleted) {
                previous = iter;
                iter = iter.next;
                // 有重复节点需要删除;
            } else {
                // 肯定是有重复节点的，那么把重复节点的值缓存起来。直到找到第一个不重复的节点。
                int toBeDeletedValue = iter.val;
                ListNode toBeDeletedNode = iter;
                while (toBeDeletedNode != null && toBeDeletedNode.val == toBeDeletedValue) {
                    following = toBeDeletedNode.next;
                    toBeDeletedNode = null;
                    toBeDeletedNode = following;
                }

                // 第一次设置。
                if (previous == null) {
                    pHead = following;
                    //
                } else {
                    previous.next = following;
                }
                iter = following;
            }
        }
        return pHead;
    }


    /*
     * 链接：https://www.nowcoder.com/questionTerminal/fc533c45b73a41b0b44ccba763f866ef
     */

    public ListNode deleteDuplication3(ListNode pHead) {
        if (pHead == null)
            return null;
        if (pHead != null && pHead.next == null)
            return pHead;

        ListNode current = null;

        if (pHead.next.val == pHead.val) {
            current = pHead.next.next;
            while (current != null && current.val == pHead.val)
                current = current.next;
            return deleteDuplication(current);
        } else {
            current = pHead.next;
            pHead.next = deleteDuplication(current);
            return pHead;
        }
    }

    /*
     * 增加临时头结点的思路真的是厉害。
    链接：https://www.nowcoder.com/questionTerminal/fc533c45b73a41b0b44ccba763f866ef
    来源：牛客网*/

    public static ListNode deleteDuplication4(ListNode pHead) {

        ListNode first = new ListNode(-1);//设置一个trick

        first.next = pHead;

        ListNode p = pHead;
        ListNode last = first;
        while (p != null && p.next != null) {
            if (p.val == p.next.val) {
                int val = p.val;
                while (p != null && p.val == val)
                    p = p.next;
                last.next = p;
            } else {
                last = p;
                p = p.next;
            }
        }
        return first.next;
    }

    /**
     * @param pHead
     * @return TODO not solved...
     */
    public ListNode deleteDuplication(ListNode pHead) {
        if (pHead == null) {
            return null;
        }

        if (pHead.next == null) {
            return pHead;
        }


        ListNode previous = pHead;
        ListNode iter = pHead.next;
        // 直到找到一个唯一的头结点。
        while (true) {
            int pCount = 1;
            while (pHead != null && pHead.next != null && pHead.val == pHead.next.val) {
                previous = pHead.next;
                iter = pHead.next.next;
                pHead = pHead.next;
                pCount++;
            }

            if (pHead == null) {
                return null;
            }

            // 整个链表只有一种数字;
            if (pHead != null && pCount > 1 && pHead.next == null) {
                return null;
            }

            //
            if (pHead == null || pHead.next == null) {
                return pHead;
            }

            // 头结点重复了。
            if (pCount > 1) {
                previous = pHead.next;
                iter = pHead.next.next;
                pHead = pHead.next;
            } else {
                break;
            }
        }

        //
        if (pHead == null || pHead.next == null) {
            return pHead;
        }

        while (iter != null) {
            int count = 1;
            while (true) {
                if (iter.next != null && iter.val == iter.next.val) {
                    count++;
                    iter = iter.next;
                } else {
                    break;
                }

            }
            // 节点唯一;
            if (count == 1) {
                previous.next = iter;
                iter = iter.next;
                // 这个时候previous也要更新.
                previous = previous.next;
            } else {
                // 统计的都是相同的元素;
                iter = iter.next;
            }
        }

        return pHead;
    }


    private static ListNode createList(int[] array) {
        if (array.length == 0) {
            return null;
        }

        ListNode pHead, pre;
        pHead = pre = new ListNode(array[0]);

        for (int i = 1; i < array.length; i++) {
            pre.next = new ListNode(array[i]);
            pre = pre.next;
        }

        return pHead;
    }

    private static void printList(ListNode pHead) {
        while (pHead != null) {
            System.out.print(pHead.val + ", ");
            pHead = pHead.next;
        }
    }

    public static void main(String[] args) {
        int[] array = {2, 4, 5, 5};
        ListNode list = createList(array);
        ListNode pHead = list;
        /*while (list != null) {
            System.out.print(list.val + ", ");
            list = list.next;
        }*/

        P56_DeleteDuplicateNodeInList demo = new P56_DeleteDuplicateNodeInList();
        ListNode listNode = demo.deleteDuplication(pHead);

        printList(listNode);


    }


    /**
     * 留下了重复节点第一个节点，
     */

    /*public ListNode deleteDuplication(ListNode pHead) {

        if (pHead == null) {
            return null;
        }

        if (pHead.next == null) {
            return pHead;
        }

        ListNode firstOccurence = pHead;
        ListNode previous = pHead;
        ListNode iter = pHead.next;

        while (iter != null) {
            if (iter.val == previous.val) {
                iter = iter.next;
                previous = previous.next;
            } else {
                firstOccurence.next = iter;
                firstOccurence = firstOccurence.next;
                iter = iter.next;
                previous = previous.next;
            }
        }

        return pHead;

    }*/


}

/*
测试用例:
{1,2,3,3,4,4,5}

对应输出应该为:

{1,2,5}

你的输出为:

{1,5}

测试用例:
{1,1,1,1,1,1,2}

对应输出应该为:

{2}

你的输出为:

{1,2}


测试用例:
{1,1,1,1,1,1,1}

对应输出应该为:

{}

你的输出为:

{1}

测试用例:
{1,1,2,3,3,4,5,5}

对应输出应该为:

{2,4}

你的输出为:

{2,4,5,5}
 */