package com.darcy.main.cleancode_v1_0_3.BinaryTree;

/**
 * Author by darcy
 * Date on 17-9-3 下午4:21.
 * Description:
 *
 * 二叉树的最大路径和. 起始节点可以从任何节点开始, 结束节点也是任意的.
 *     1
 *    / \
 *   2   4
 *  / \ / \
 * 2  3
 * 3 + 2 + 1 + 4 = max = 10;
 *
 * Given a binary tree, find the maximum path sum.
 * The path may start and end at any node in the tree.
 *
 * Example Questions Candidate Might Ask:
 * Q: What if the tree is empty?
 * A: Assume the tree is non-empty.
 * Q: How about a tree that contains only a single node?
 * A: Then the maximum path sum starts and ends at the same node.
 * Q: What if every node contains negative value?
 * A: Then you should return the single node value that is the least negative.
 * Q: Does the maximum path have to go through the root node?
 * A: Not necessarily. For example, the below tree yield 6 as the maximum path sum and does not
 * go through root.
 *
 * Hint:
 * Anytime when you found that doing top down approach uses a lot of repeated
 * calculation, bottom up approach usually is able to be more efficient.
 *
 */
public class P31_BinaryTreeMaximumPathSum {

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
   * At each node, the potential maximum path could be one of
   * these cases:
   * i. max(left subtree) + node
   * ii. max(right subtree) + node
   * iii. max(left subtree) + max(right subtree) + node
   * iv. the node itself
   *
   * Then, we need to return the maximum path sum that goes through this node and to either
   * one of its left or right subtree to its parent. There’s a little trick here: If this maximum
   * happens to be negative, we should return 0, which means: “Do not include this subtree as
   * part of the maximum path of the parent node”, which greatly simplifies our code.
   *
   * @param root
   * @return
   */
  private static int maxSum;
  public static int solution(TreeNode root) {
    maxSum = Integer.MIN_VALUE;
    findMax(root);
    return maxSum;
  }

  private static int findMax(TreeNode root) {
    if (root == null) {
      return 0;
    }
    int leftMax = findMax(root.left);
    int rightMax = findMax(root.right);
    // 遍历左右子树的过程中都会适时的更新maxSum变量.
    // 而root.val + leftMax + rightMax就是考虑左右联通的情况,
    // 虽然不一定比其子树的maxSum大.
    // 只要左右子树有max < 0的, 那么联接的时候其max是被赋值为0的，即另一个分支不在考虑范围之内.
    // 这就相当于考虑了只考虑为max的情况, 因为如果当前节点连接的左右max>0,那么一定是联接起来是最大的.
    maxSum = Math.max(root.val + leftMax + rightMax, maxSum);
    int currentMax = root.val + Math.max(leftMax, rightMax);
    return currentMax > 0 ? currentMax : 0;
  }

  public static void main(String[] args) {
    TreeNode root =
        new TreeNode(1,
            new TreeNode(2,
                new TreeNode(2),
                new TreeNode(3)),
            new TreeNode(4));
    int max = solution(root);
    System.out.println(max);
  }

}
