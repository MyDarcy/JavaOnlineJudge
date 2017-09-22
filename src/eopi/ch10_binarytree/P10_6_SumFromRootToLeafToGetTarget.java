package eopi.ch10_binarytree;

/**
 * Author by darcy
 * Date on 17-9-22 上午11:22.
 * Description:
 *
 * 从根节点到叶子节点的和等于目标值的路径.
 *
 * Write a program which takes as input an integer and a binary tree with integer node
 * weights, and checks if there exists a leaf whose path weight equals the given integer.
 *
 * Hint: What do you need to know about the rest of the tree when checking a specific subtree?
 *
 */
public class P10_6_SumFromRootToLeafToGetTarget {


  /**
   * 不断的统计和.
   * O(N)的时间复杂度, O(H)的空间复杂度.
   *
   * A better approach is to traverse the tree, keeping track of the root-to-node path
   * sum. The first time we encounter a leaf whose weight equals the target weight, we
   * have succeeded at locating a desired leaf. Short circuit evaluation of the check ensures
   * that we do not process additional leaves.
   *
   * @param root
   * @return
   */
  public static boolean solution(BinaryTreeNode<Integer> root, int target) {
    return solution(root, 0, target);
  }

  private static boolean solution(BinaryTreeNode<Integer> root, int partial, int target) {
    if (root == null) {
      return false;
    }

    partial += root.data;
    if (root.left == null && root.right == null) {
      return partial == target;
    }

    return solution(root.left, partial, target) && solution(root.right, partial, target);
  }

}
