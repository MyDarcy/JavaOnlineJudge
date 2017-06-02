package jianzhioffer;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by darcy
 * 2017/5/7--23:58
 * Description:
 * 输入两个链表，找出它们的第一个公共结点。
 */
public class P37_FirstSameNodeInTwoList {
    public class ListNode {
        int val;
        ListNode next = null;

        ListNode(int val) {
            this.val = val;
        }
    }

    public ListNode FindFirstCommonNode(ListNode pHead1, ListNode pHead2) {
        int length1 = 0;
        ListNode tempHead1 = pHead1;
        while (tempHead1 != null) {
            length1++;
            tempHead1 = tempHead1.next;
        }

        int length2 = 0;
        ListNode tempHead2 = pHead2;
        while (tempHead2 != null) {
            length2++;
            tempHead2 = tempHead2.next;
        }

        // 让phead1指向长链表; pHead2指向短链表;
        if (length1 < length2) {
            int temp = length2;
            length2 = length1;
            length1 = temp;
            ListNode node = pHead2;
            pHead2 = pHead1;
            pHead1 = node;
        }

        int m = length1 - length2;
        Set<ListNode> set = new HashSet<>();
        ListNode iter = pHead2;
        while (iter != null) {
            set.add(iter);
            iter = iter.next;
        }
        for (int i = 0; i < m; i++) {
            if (set.contains(pHead1)) {
                return pHead1;
            }
            pHead1 = pHead1.next;

        }

        while (pHead1 != null && pHead2 != null) {
            if (pHead1 == pHead2) {
                return pHead1;
            }
            pHead1 = pHead1.next;
            pHead2 = pHead2.next;
        }

        return null;
    }

    /**
     * 单链表相遇后后面的一定是相同的;
     */

    /**
     * https://www.nowcoder.com/questionTerminal/6ab1d9a29e88450685099d45c9e31e46

    方法一：运用HasnMap的特性
    import java.util.HashMap;
    public class Solution {
    public ListNode FindFirstCommonNode(ListNode pHead1, ListNode pHead2) {
        ListNode current1 = pHead1;
        ListNode current2 = pHead2;
 
 
        HashMap<ListNode, Integer> hashMap = new HashMap<ListNode, Integer>();
        while (current1 != null) {
            hashMap.put(current1, null);
            current1 = current1.next;
        }
        while (current2 != null) {
            if (hashMap.containsKey(current2))
                return current2;
            current2 = current2.next;
        }
 
        return null;
 
    }
}
 
 
 
 
//方法2：
 
public ListNode FindFirstCommonNodeII(ListNode pHead1, ListNode pHead2) {
        ListNode current1 = pHead1;// 链表1
        ListNode current2 = pHead2;// 链表2
        if (pHead1 == null || pHead2 == null)
            return null;
        int length1 = getLength(current1);
        int length2 = getLength(current2);
        // 两连表的长度差
         
        // 如果链表1的长度大于链表2的长度
        if (length1 >= length2) {
            int len = length1 - length2;
            // 先遍历链表1，遍历的长度就是两链表的长度差
            while (len > 0) {
                current1 = current1.next;
                len--;
            }
 
        }
        // 如果链表2的长度大于链表1的长度
        else if (length1 < length2) {
            int len = length2 - length1;
            // 先遍历链表1，遍历的长度就是两链表的长度差
            while (len > 0) {
                current2 = current2.next;
                len--;
            }
 
        }
        //开始齐头并进，直到找到第一个公共结点
        while(current1!=current2){
            current1=current1.next;
            current2=current2.next;
        }
        return current1;
 
    }
 
    // 求指定链表的长度
    public static int getLength(ListNode pHead) {
        int length = 0;
 
        ListNode current = pHead;
        while (current != null) {
            length++;
            current = current.next;
        }
        return length;
    }
     */

    /**
     * 链接：https://www.nowcoder.com/questionTerminal/6ab1d9a29e88450685099d45c9e31e46
     来源：牛客网

         ListNode* FindFirstCommonNode( ListNode *pHead1, ListNode *pHead2) {
             ListNode *p1 = pHead1;
             ListNode *p2 = pHead2;
             while(p1!=p2){
                 p1 = (p1==NULL ? pHead2 : p1->next);
                 p2 = (p2==NULL ? pHead1 : p2->next);
             }
             return p1;
         }
     */
}
