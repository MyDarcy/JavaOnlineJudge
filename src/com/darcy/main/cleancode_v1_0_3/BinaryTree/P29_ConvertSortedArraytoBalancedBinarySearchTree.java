package com.darcy.main.cleancode_v1_0_3.BinaryTree;

/**
 * Author by darcy
 * Date on 17-9-3 下午2:47.
 * Description:
 *
 *
 * 给定排序好的数组, 转为一棵高度平衡的二叉搜索树.
 *
 * Given an array where elements are sorted in ascending order,
 * convert it to a height balanced BST.
 *
 */
public class P29_ConvertSortedArraytoBalancedBinarySearchTree {

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
   * 中间元素代表的是平衡二叉搜索树根节点值, 创建根节点.
   * 然后递归的创建左子树和右子树.
   *
   * 原问题的子问题.
   *
   * 时间复杂度O(n), 空间复杂度O(logn)
   *
   * @param sortedArray
   * @return
   */
  public static TreeNode solution(int[] sortedArray) {
    return solution(sortedArray, 0, sortedArray.length - 1);
  }

  private static TreeNode solution(int[] sortedArray, int start, int end) {
    if (start > end) {
      return null;
    }
    int middle = (start + end) / 2;
    TreeNode root = new TreeNode(sortedArray[middle]);
    root.left = solution(sortedArray, start, middle - 1);
    root.right = solution(sortedArray, middle + 1, end);

    return root;
  }

}
