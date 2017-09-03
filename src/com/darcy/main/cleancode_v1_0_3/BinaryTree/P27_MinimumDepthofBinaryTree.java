package com.darcy.main.cleancode_v1_0_3.BinaryTree;

import java.util.LinkedList;
import java.util.Queue;

/**
 * Author by darcy
 * Date on 17-9-3 上午11:37.
 * Description:
 *
 * 二叉搜索树的最小深度.
 *
 * Given a binary tree, find its minimum depth.
 * The minimum depth is the number of nodes along the shortest path from the root node
 * down to the nearest leaf node.
 *
 * Similar to Question [26. Maximum Depth of Binary Tree], here we need to find the
 * minimum depth instead.
 *
 */
public class P27_MinimumDepthofBinaryTree {


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
   * 注意: 如果节点一子树为null,那么并不是简单的返回1, 而是返回另一个子树的最小高度+1;
   *
   * i. The node itself is a leaf node. The minimum depth is one.
   * ii. Node that has one empty sub-tree while the other one is non-empty. Return
   * the minimum depth of that non-empty sub-tree.
   *
   * O(n)的时间复杂度, O(logn)的空间复杂度.
   *
   * @param root
   * @return
   */
  public static int solution(TreeNode root) {

    if (root == null) {
      return 0;
    }

    if (root.left == null) {
      return solution(root.right) + 1;
    }

    if (root.right == null) {
      return solution(root.left) + 1;
    }

    return Math.min(solution(root.left), solution(root.right)) + 1;
  }

  /**
   * 实际题目要找的就是根节点要最近的叶子节点的距离.
   * @param root
   * @return
   */
  public static int solution2(TreeNode root) {
    if (root == null) return 0;
    Queue<TreeNode> q = new LinkedList<>();
    q.add(root);
    // 当前层的最右边节点.
    TreeNode rightMost = root;
    int depth = 1;
    while (!q.isEmpty()) {
      TreeNode node = q.poll();
      // 第一次遇到的叶子节点.
      // 循环的性质保证了到了下一层那么上一层就不会再有叶子节点了。
      if (node.left == null && node.right == null) break;
      if (node.left != null) q.add(node.left);
      if (node.right != null) q.add(node.right);
      // 执行到了这里,那么该节点必定存在一棵子树.
      if (node == rightMost) {
        depth++;
        rightMost = (node.right != null) ? node.right : node.left;
      }
    }
    return depth;
  }

}
