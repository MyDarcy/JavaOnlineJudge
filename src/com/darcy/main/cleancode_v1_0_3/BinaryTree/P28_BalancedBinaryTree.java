package com.darcy.main.cleancode_v1_0_3.BinaryTree;

/**
 * Author by darcy
 * Date on 17-9-3 下午1:14.
 * Description:
 *
 * 判定给定的二叉树是否是平衡树.
 *
 * Given a binary tree, determine if it is height-balanced.
 * For this problem, a height-balanced binary tree is defined as a binary tree in which the
 * depth of the two subtrees of every node never differs by more than 1.
 *
 */
public class P28_BalancedBinaryTree {

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
   *
   * 暴力方法.
   * 每一个节点都需要判定其高度.
   *
   * O(n^2)的时间复杂度, O(1)的空间复杂度.
   *
   * @param root
   * @return
   */
  public static boolean bruteSolution(TreeNode root) {
    if (root == null) {
      return true;
    }

    return Math.abs(depth(root.left) - depth(root.right)) <= 1
        && bruteSolution(root.left)
        && bruteSolution(root.right);
  }

  // 求以root为根节点的最大深度.
  public static int depth(TreeNode root) {
    if (root == null) {
      return 0;
    }
    return Math.max(depth(root.left), depth(root.right)) + 1;
  }

  /**
   * 跟上面应该是一样的意思.
   * @param root
   * @return
   */
  public static boolean solution2(TreeNode root) {
    if (root == null) {
      return true;
    }

    int left = depth2(root.left);
    int right = depth2(root.right);

    return Math.abs(left - right) <= 0 && solution2(root.left) && solution2(root.right);

  }

  private static int depth2(TreeNode root) {
    if (root == null) {
      return 0;
    }
    int left = depth2(root.left);
    int right = depth2(root.right);
    return Math.max(left, right);
  }

  /**
   * @param root
   * @return
   */
  public static boolean solution3(TreeNode root) {
    return maxDepth(root) != -1;
  }

  private static int maxDepth(TreeNode root) {
    if (root == null) {
      return 0;
    }

    int L = maxDepth(root.left);
    if (L == -1) {
      return -1;
    }

    int R = maxDepth(root.right);
    if (R == -1) {
      return -1;
    }

    return (Math.abs(L - R) <= 1) ? Math.max(L, R) + 1 : -1;

  }
}
