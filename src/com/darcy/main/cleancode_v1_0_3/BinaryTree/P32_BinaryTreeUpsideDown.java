package com.darcy.main.cleancode_v1_0_3.BinaryTree;

/**
 * Author by darcy
 * Date on 17-9-3 下午4:59.
 * Description:
 *
 * Given a binary tree where all the right nodes are either leaf nodes with a sibling (a left
 * node that shares the same parent node) or empty, flip it upside down and turn it into a tree
 * where the original right nodes turned into left leaf nodes. Return the new root.
 *
 */
public class P32_BinaryTreeUpsideDown {

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
   * We need to be very careful when reassigning current node’s left and right children.
   * Besides making a copy of the parent node, you would also need to make a copy of the
   * parent’s right child too. The reason is the current node becomes the parent node in the
   * next iteration.
   * @param root
   * @return
   */
  public static TreeNode solution1(TreeNode root) {
    TreeNode p = root, parent = null, parentRight = null;
    while (p != null) {
      TreeNode left = p.left;
      p.left = parentRight;
      parentRight = p.right;
      p.right = parent;
      parent = p;
      p = left;
    }
    return parent;
  }

  /**
   * Although the code for the top-down approach seems concise, it is actually subtle and
   * there are a lot of hidden traps if you are not careful. The other approach is thinking
   * recursively in a bottom-up fashion. If we reassign the bottom-level nodes before the
   * upper ones, we won’t have to make copies and worry about overwriting something. We
   * know the new root will be the left-most leaf node, so we begin the reassignment here
   * @param root
   * @return
   */
  public TreeNode UpsideDownBinaryTree(TreeNode root) {
    return dfsBottomUp(root, null);
  }

  private TreeNode dfsBottomUp(TreeNode p, TreeNode parent) {
    if (p == null) return parent;
    TreeNode root = dfsBottomUp(p.left, p);
    p.left = (parent == null) ? parent : parent.right;
    p.right = parent;
    return root;
  }

}
