package jianzhioffer;

import java.util.*;

/**
 * Created by darcy
 * 2017/4/24--0:16
 * Description:
 * 输入一个复杂链表（每个节点中有节点值，以及两个指针，
 * 一个指向下一个节点，另一个特殊指针指向任意一个节点），
 * 返回结果为复制后复杂链表的head。（注意，输出结果中请不要返回参数中的节点引用，否则判题程序会直接返回空）
 */
public class P26_CopyOfComplicatedList {

    public class RandomListNode {
        int label;
        RandomListNode next = null;
        RandomListNode random = null;

        RandomListNode(int label) {
            this.label = label;
        }
    }

    /**
     * 思路: 每次都copy一个节点的时候，都把原list到copyList上对应的节点放入到map中;
     * 那么这样定位节点
     * @param pHead
     * @return
     */
    public RandomListNode Clone(RandomListNode pHead){

        if (pHead == null) {
            return null;
        }


        Map<RandomListNode, RandomListNode> map = new HashMap<>();
        RandomListNode cloneHead = new RandomListNode(-1); // never changed copy list head;
        RandomListNode cloneHead2 = cloneHead; // for modify random pointer;
        RandomListNode iterPtr = cloneHead; // for copy of list;

        RandomListNode head = pHead; // 维持一个原链表的head;
        while (pHead != null) {
            RandomListNode temp = new RandomListNode(pHead.label);
            iterPtr.next = temp;
            map.put(pHead, temp);
            pHead = pHead.next;
            iterPtr = iterPtr.next;
        }

        // 维持了原链表节点到copy list节点的映射;
        while (head != null) {
            if (cloneHead2.next != null) {
                cloneHead2.next.random = map.get(head.random);
            }
            head = head.next;
            cloneHead2 = cloneHead2.next;
        }

        return cloneHead.next;

    }

}
