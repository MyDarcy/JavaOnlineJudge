package jianzhioffer;

import java.util.ArrayList;
import java.util.List;

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

    public RandomListNode Clone(RandomListNode pHead){

        if (pHead == null) {
            return null;
        }

        /*RandomListNode cloneHead = new RandomListNode(-1);
        RandomListNode iterPtr = cloneHead;
        while (pHead != null) {
            iterPtr.next = new RandomListNode(pHead.label);
            pHead = pHead.next;
            iterPtr = iterPtr.next;
        }*/



        List<RandomListNode> listNodes = new ArrayList<>();
//        Map<Integer, RandomListNode> map = new HashMap<>();

        RandomListNode cloneHead = new RandomListNode(-1);
        RandomListNode iterPtr = cloneHead;
        int i = 0;
        RandomListNode head = pHead;
        while (pHead != null) {
            RandomListNode temp = new RandomListNode(pHead.label);
            iterPtr.next = temp;
            listNodes.add(temp);
            pHead = pHead.next;
            iterPtr = iterPtr.next;
        }

        while (head != null) {
            if (head.random != null) {
                RandomListNode temp = head.random;
            }
        }

        return null;

    }

}
