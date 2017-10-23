package com.darcy.main.cleancode_v1_0_3.BinaryTree;

/**
 * Author by darcy
 * Date on 17-9-3 上午10:15.
 * Description:
 *
 * 给定二叉树,判定是否是BST树.
 * Given a binary tree, determine if it is a valid Binary Search Tree (BST)
 *
 * First, you must understand the difference between Binary Tree and BST. Binary tree is a
 * tree data structure in which each node has at most two child nodes. A BST is based on
 * binary tree, but with the following additional properties:
 *  - The left subtree of a node contains only nodes with keys less than the node’s key.
 *  - The right subtree of a node contains only nodes with keys greater than the node’s
 *    key.
 *  - Both the left and right subtrees must also be binary search trees.
 *
 */
public class P25_ValidateBinarySearchTree {

  static class TreeNode {
    Integer val;
    TreeNode left;
    TreeNode right;
  }

  /**
   *
   * 这种解法的问题就是大量的重复计算,每次都计算所有左子树的节点的值都比当前根节点的值小.
   * 所有右子树的节点的值都比当前根节点的值大。然后还要去重复判定子节点。
   *
   * O(n^2)的时间复杂度.O(n)的空间复杂度。
   *
   * @param root
   * @return
   */
  public static boolean bruteSolution(TreeNode root) {
    if (root == null) {
      return true;
    }

    return smallThan(root.left, root.val)
        && biggerThan(root.right, root.val)
        && bruteSolution(root.left)
        && bruteSolution(root.right);

  }

  private static boolean biggerThan(TreeNode root, Integer val) {
    if (root == null) {
      return true;
    }

    return root.val > val
        && biggerThan(root.left, val)
        && biggerThan(root.right, val);
  }

  private static boolean smallThan(TreeNode root, Integer val) {
    if (root == null) {
      return true;
    }
    return root.val < val
        && smallThan(root.left, val)
        && smallThan(root.right, val);
  }


  /**
   * 每次判定子树是否为 bst 的时候, 传入该节点的上下限.
   *
   * O(n)的时间复杂度,O(1)的空间复杂度.但是传入的Integer.MIN_VALUE或者Integer.MAX_VALUE可能并不能覆盖
   * 节点的取值范围.
   *
   * @param root
   * @return
   */
  public static boolean betterSolution(TreeNode root) {
    return betterIsValid(root, Integer.MIN_VALUE, Integer.MAX_VALUE);
  }

  private static boolean betterIsValid(TreeNode root, int minValue, int maxValue) {
    if (root == null) {
      return true;
    }

    // 每个左节点的值的一定是 <  父节点的值; 右节点的值一定是大于父节点的值.
    // 所以看节点的值是否在传进来的范围内.
    return root.val > minValue && root.val < maxValue
        && betterIsValid(root.left, minValue, root.val)
        && betterIsValid(root.right, root.val, maxValue);
  }

  /**
   * 上面的改进版本.
   * @param root
   * @return
   */

  public static boolean betterSolution2(TreeNode root) {
    return betterIsValid2(root, null, null);  
  }

  private static boolean betterIsValid2(TreeNode root, Integer low, Integer high) {

    if (root == null) {
      return true;
    }

    return (low == null || root.val > low) && (high == null || root.val < high)
        && betterIsValid2(root.left, low, root.val)
        && betterIsValid2(root.right, root.val, high);
  }


  // 记录上一次访问的节点.
  private static TreeNode prev;

  /**
   * 中序遍历的结果应该遵循一个严格递增的偏序关系。
   * @param root
   * @return
   */
  public static boolean tranverseSolution(TreeNode root) {
    prev = null;
    return isMonotonicIncreasing(root);
  }

  private static boolean isMonotonicIncreasing(TreeNode p) {
    if (p == null) return true;

    // 左边是有序的,然后判定右边.
    // 符合左右根的遍历顺序.
    if (isMonotonicIncreasing(p.left)) {
      if (prev != null && p.val <= prev.val) return false;
      prev = p;
      return isMonotonicIncreasing(p.right);
    }
    return false;
  }

  public static void main(String[] args) {

  }

}
