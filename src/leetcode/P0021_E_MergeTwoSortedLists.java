package leetcode;

/**
 * Merge two sorted linked lists and return it as a new list. The new list should be made by splicing together the nodes of the first two lists.
 *
 * Example:
 *
 * Input: 1->2->4, 1->3->4
 * Output: 1->1->2->3->4->4
 */
public class P0021_E_MergeTwoSortedLists {

  static class ListNode {
        int val;
        ListNode next;
        ListNode(int x) { val = x; }
  }

  public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
    if (l1 == null) return l2;
    if (l2 == null) return l1;

  }

  /**
   * 链表的递归性质;
   * @param l1
   * @param l2
   * @return
   */
  public ListNode mergeTwoLists2(ListNode l1, ListNode l2){
    if(l1 == null) return l2;
    if(l2 == null) return l1;
    if(l1.val < l2.val){
      l1.next = mergeTwoLists(l1.next, l2);
      return l1;
    } else{
      l2.next = mergeTwoLists(l1, l2.next);
      return l2;
    }
  }

}
