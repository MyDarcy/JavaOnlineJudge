package eopi.ch10_binarytree;

/**
 * Author by darcy
 * Date on 17-9-19 下午5:06.
 * Description:
 * <p>
 * 检测一棵树是否是高度平衡的二叉树.
 * <p>
 * A binary tree is said to be height-balanced if for each node in the tree, the difference
 * in the height of its left and right subtrees is at most one. A perfect binary tree is
 * height-balanced, as is a complete binary tree. A height-balanced binary tree does not
 * have to be perfect or complete
 * <p>
 * Write a program that takes as input the root of a binary tree and checks whether the
 * tree is height-balanced
 */
public class P10_1_TestIfATreeBalanced {

  /**
   * height()次数调用太多了.
   *
   * @param root
   * @return
   */
  public static boolean solution(BinaryTreeNode<Integer> root) {
    if (root == null) {
      return true;
    }

    int left = height(root.left);
    int right = height(root.right);

    return Math.abs(left - right) <= 1 && solution(root.left) && solution(root.right);
  }

  private static int height(BinaryTreeNode<Integer> root) {
    if (root == null) {
      return 0;
    }

    return 1 + Math.max(height(root.left), height(root.right));
  }


  /**
   * 辅助class类.
   */
  private static class BalanceStatusWithHeight {
    public boolean balanced;
    public int height;

    public BalanceStatusWithHeight(boolean balanced, int height) {
      this.balanced = balanced;
      this.height = height;
    }
  }

  public static boolean isBalanced(BinaryTreeNode<Integer> tree) {
    return checkBalanced(tree).balanced;
  }

  private static BalanceStatusWithHeight checkBalanced(
      BinaryTreeNode<Integer> tree) {
    if (tree == null) {
      return new BalanceStatusWithHeight(true, -1); // Base case.
    }
    BalanceStatusWithHeight leftResult = checkBalanced(tree.left);
    if (!leftResult.balanced) {
      return leftResult; // Left subtree is not balanced.
    }
    BalanceStatusWithHeight rightResult = checkBalanced(tree.right);
    if (!rightResult.balanced) {
      return rightResult; // Right subtree is not balanced.
    }
    boolean isBalanced = Math.abs(leftResult.height - rightResult.height) <= 1;

    // 叶子节点的高度为0;
    int height = Math.max(leftResult.height, rightResult.height) + 1;
    return new BalanceStatusWithHeight(isBalanced, height);
  }
}
