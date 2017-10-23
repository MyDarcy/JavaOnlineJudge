package com.darcy.main.cleancode_v1_0_3.BinaryTree;

/**
 * Author by darcy
 * Date on 17-9-3 下午3:06.
 * Description:
 * <p>
 * 将一棵节点值按照升序排序好的链表转为高度平衡的二叉搜索树.
 * <p>
 * Given a singly linked list where elements are sorted in ascending order,
 * convert it to a height balanced BST.
 * <p>
 * Hint:
 * Things get a little more complicated when you have a singly linked list instead of an
 * array. Please note that in linked list, you no longer have random access to an element in
 * O(1) time.
 * <p>
 * How about inserting nodes following the list’s order? If we can achieve this, we no
 * longer need to find the middle element, as we are able to traverse the list while inserting
 * nodes to the tree.
 */
public class P30_ConvertSortedListtoBalancedBinarySearchTree {


  public static class ListNode {
    Integer val;
    ListNode next;

    public ListNode() {

    }

    public ListNode(Integer val, ListNode next) {
      this.val = val;
      this.next = next;
    }

    public ListNode(Integer val) {
      this.val = val;
      this.next = null;
    }
  }

  static class TreeNode {
    Integer val;
    TreeNode left;
    TreeNode right;

    public TreeNode(Integer val) {
      this.val = val;
    }

    public TreeNode(Integer val, TreeNode left, TreeNode right) {
      this.val = val;
      this.left = left;
      this.right = right;
    }

    public TreeNode() {
    }
  }

  /**
   * 按照上一题的思路, 每次都找到中间节点,然后在递归解决问题.
   * 时间复杂度O(nlogn) (logn层, 每一层n/2次遍历), 空间复杂度O(logn);
   *
   * @return
   */
  public static TreeNode bruteForceSolution() {

    return null;
  }


  private static ListNode listNode;

  /**
   * @param head
   * @return
   */
  public static TreeNode solution(ListNode head) {
    ListNode iter = head;
    int n = 0;
    while (iter != null) {
      iter = iter.next;
      n++;
    }
    listNode = head;
    return solution(0, n - 1);
  }

  // 打两个断点推一下.
  private static TreeNode solution(int start, int end) {
    if (start > end) { // 断点1
      return null;
    }
    int middle = (start + end) / 2; // 断点2
    TreeNode left = solution(start, middle - 1);
    TreeNode parent = new TreeNode(listNode.val);
    parent.left = left;
    listNode = listNode.next;
    parent.right = solution(middle + 1, end);
    return parent;
  }

  public static void main(String[] args) {
    ListNode first =
        new ListNode(10,
            new ListNode(20,
                new ListNode(30,
                    new ListNode(40,
                        new ListNode(50,
                            new ListNode(60,
                                new ListNode(70)))))));

    TreeNode root = solution(first);

  }

}
