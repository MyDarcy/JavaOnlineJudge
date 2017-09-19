package eopi.ch9_stackQueue;

import java.util.Deque;
import java.util.LinkedList;

/**
 * Author by darcy
 * Date on 17-9-19 上午11:33.
 * Description:
 *
 * 每个节点除了next指针以外都有额外的指针jump指向随机一个节点.
 *
 * jump-first order: 遇到一个节点, 先去看next指针是否访问过. 是的话那么再去访问next域.
 *
 * One way to enumerate the nodes in a postings list is to iteratively follow the next
 * field. Another is to always first follow the jump field if it leads to a node that has
 * not been explored previously, and then search from the next node. Call the order in
 * which these nodes are traversed the jump-first order.
 *
 * Write recursive and iterative routines that take a postings list, and compute the
 * jump-first order. Assume each node has an integer-valued field that holds the order,
 * and is initialized to -1.
 *
 * Hint: Recursion makes the problem trivial. Mimic the recursion with a stack.
 */

class PostingListNode {
  Integer val;
  Integer order = -1;
  PostingListNode next;
  PostingListNode jump;

  public PostingListNode() {
  }

  public PostingListNode(Integer val, PostingListNode next, PostingListNode jump) {
    this.val = val;
    this.next = next;
    this.jump = jump;
  }
}
public class P5_SearchPostingList {

  // 递归的方式设置访问顺序.

  /**
   * jump域存在,那么先访问jump域, 否则访问next域.
   * @param L
   */
  public static void setJumpOrder(PostingListNode L) {
    setJumpOrder(L, 0);
  }

  private static int setJumpOrder(PostingListNode L, int order) {
    if (L != null && L.order != -1) {
      L.order = order++;
      order = setJumpOrder(L.jump, order);
      order = setJumpOrder(L.next, order);
    }
    return order;
  }


  /**
   * 以栈来模拟上面的先访问jump域,然后访问next域.
   *
   * 可以添加一个Map来避免next和jump都指向同一个节点.
   *
   * The iterative solution uses a stack to simulate the recursive algorithm. The keyinsight
   * is that for every node, we want to visit its next node after visiting its jump node. A
   * stack works well because of its last-in, first-out semantics. Specifically, when processing
   * a node, we push its next node on to the stack and then we push its jump node on to the
   * stack. This way we process the jump node before the next node.
   *
   * O(n), O(n)
   * @param L
   */
  public static void setJumpOrderRecursion(PostingListNode L) {
    Deque<PostingListNode> stack = new LinkedList<>();
    stack.addFirst(L);
    int order = 0;
    while (!stack.isEmpty()) {
      PostingListNode current = stack.removeFirst();
      if (current != null && current.order == -1) {
        current.order = order++;
        stack.addFirst(L.next);
        stack.addFirst(L.jump);
      }
    }
  }
}
