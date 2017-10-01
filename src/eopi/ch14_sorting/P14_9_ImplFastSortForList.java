package eopi.ch14_sorting;

/**
 * Author by darcy
 * Date on 17-9-30 下午9:19.
 * Description:
 * <p>
 * 为链表实现快速排序.
 * <p>
 * Implement a routine which sorts lists efficiently. It should be a stable sort, i.e., the
 * relative positions of equal elements must remain unchanged.
 * <p>
 * Hint: In what respects are lists superior to arrays?
 */
public class P14_9_ImplFastSortForList {

  public static class ListNode {
    int val;
    ListNode next;

    public ListNode(int val) {
      this.val = val;
    }

    public ListNode(int val, ListNode next) {
      this.val = val;
      this.next = next;
    }
  }

  /**
   * iter是在原来的链表是进行迭代遍历.
   * 找到的小节点的值就插入到前面合适的位置. 因为没有创建链表, 原链表也只是链接在dummyHead的后面.
   * <p>
   * The brute-force approach is to repeatedly delete the smallest element in the
   * list and add it to the end of a new list. The time complexity is O(n^2) and the additional
   * space complexity is O(n), where n is the number of nodes in the list. We can refine
   * the simple algorithm to run in O(1) space by reordering the nodes, instead of creating
   * new ones.
   *
   * @param head
   * @return
   */
  public static ListNode insertionSort(final ListNode head) {
    ListNode dummyNode = new ListNode(0, head);
    ListNode iter = head;
    while (iter != null && iter.next != null) {
      // 当前节点比后面的节点要大.
      if (iter.val > iter.next.val) {
        ListNode target = iter.next;
        ListNode pre = dummyNode;

        // pre.next的这种用法,能找到边界条件,同时还维护了previous指针.
        while (pre.next.val < target.val) {
          pre = pre.next;
        }

        // pre.next.val >= target.val
        // pre.val < target.val;
        ListNode temp = pre.next;
        pre.next = target;
        // 因为target的链接关系已经改变了, 相当于是数组的插入排序将小值节点插入到合适的位置.
        // 后面继续要处理的仍然是 iter, iter.next
        iter.next = target.next;
        target.next = temp;
      } else {

        // 前面小于后面, 那么当前节点后移动.
        iter = iter.next;
      }
    }
    return dummyNode.next;
  }


  /**
   * 归并排序.
   * 快慢指针将链表分成两部分.
   * 然后递归的归并结果.
   * 时间复杂度O(NlogN), 空间复杂度O(logN)
   * <p>
   * Quicksort is the best all round sorting algorithm for arrays—it runs in time O(nlogn), and is
   * in-place. However, it is not stable. Mergesort applied to arrays is a stable O(nlogn)
   * algorithm. However, it is not in-place, since there is no way to merge two sorted
   * halves of an array in-place in linear time.
   * <p>
   * Unlike arrays, lists can be merged in-place—conceptually, this is because insertion
   * into the middle of a list is an O(1) operation.
   * <p>
   * We decompose the list into two equal-sized sublists around the node in the middle of
   * the list. We find this node by advancing two iterators through the list, one twice as
   * fast as the other. When the fast iterator reaches the end of the list, the slow iterator
   * is at the middle of the list.
   *
   * @param head
   * @return
   */
  public static ListNode stableMergeSort(ListNode head) {
    if (head == null || head.next == null) {
      return head;
    }

    // 快慢指针, 同时记录慢指针的前一个节点.
    ListNode preSlow = null, slow = head, fast = head;
    while (fast != null && fast.next != null) {
      preSlow = slow;
      slow = slow.next;
      fast = fast.next.next;
    }

    // 相当于断开了链表的连接关系.
    preSlow.next = null;
    return mergeTwoSortedLists(stableMergeSort(head), stableMergeSort(slow));
  }

  /**
   * 只需要改变链表节点的指向关系即可.
   *
   * @param L1 指向前半部分的指针.
   * @param L2 指向后半部分的指针.
   * @return 指向排序后的头节点的指针.
   */
  public static ListNode mergeTwoSortedLists(ListNode L1, ListNode L2) {
    // Creates a placeholder for the result.
    ListNode dummyHead = new ListNode(0, null);
    ListNode current = dummyHead;
    ListNode p1 = L1, p2 = L2;
    while (p1 != null && p2 != null) {
      if (p1.val <= p2.val) {
        current.next = p1;
        p1 = p1.next;
      } else {
        current.next = p2;
        p2 = p2.next;
      }
      current = current.next;
    }
    // Appends the remaining nodes of p1 or p2.
    current.next = p1 != null ? p1 : p2;
    return dummyHead.next;
  }

  public static void main(String[] args) {
    ListNode head = new ListNode(9,
        new ListNode(8,
            new ListNode(7,
                new ListNode(6,
                    new ListNode(5,
                        new ListNode(4,
                            new ListNode(3,
                                new ListNode(2,
                                    new ListNode(1)))))))));
    ListNode sortedHead = insertionSort(head);
    while (sortedHead != null) {
      System.out.print(sortedHead.val + "\t");
      sortedHead = sortedHead.next;
    }

    System.out.println("\n-----merge sort------");


    ListNode head2 = new ListNode(9,
        new ListNode(18,
            new ListNode(7,
                new ListNode(16,
                    new ListNode(5,
                        new ListNode(4,
                            new ListNode(13,
                                new ListNode(2,
                                    new ListNode(1)))))))));
    ListNode sortedHead2 = insertionSort(head2);
    while (sortedHead2 != null) {
      System.out.print(sortedHead2.val + "\t");
      sortedHead2 = sortedHead2.next;
    }


  }

}
