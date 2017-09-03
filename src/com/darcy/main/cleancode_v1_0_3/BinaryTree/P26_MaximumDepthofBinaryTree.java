package com.darcy.main.cleancode_v1_0_3.BinaryTree;

/**
 * Author by darcy
 * Date on 17-9-3 上午11:25.
 * Description:
 *
 * Given a binary tree, find its maximum depth.
 *
 * The maximum depth is the number of nodes along the longest path from the root node
 * down to the farthest leaf node.
 *
 */
public class P26_MaximumDepthofBinaryTree {
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
   * O(n)的时间复杂度: 因为每个节点只会遍历一次.
   * O(logn)的空间复杂度: 树高.
   * @param root
   * @return
   */
  public static int solution(TreeNode root) {

    if (root == null) {
      return 0;
    }

    return Math.max(solution(root.left), solution(root.right)) + 1;
  }


  public static void main(String[] args) {
    TreeNode root =
        new TreeNode(100,
          new TreeNode(2,
            new TreeNode(100,
                new TreeNode(1),
                null),
            new TreeNode(200,
                new TreeNode(100,
                    new TreeNode(1000,
                        new TreeNode(1),
                        null), null),
                new TreeNode(2000))),
           new TreeNode(20, null, null));
    int depth = solution(root);
    System.out.println(depth);


  }

}
