package eopi.ch16_recursion;

import java.util.ArrayList;
import java.util.List;

/**
 * Author by darcy
 * Date on 17-8-27 下午8:10.
 * Description:
 * <p>
 * 返回二叉树中所有由k个节点构成的二叉树.
 * <p>
 * Write a program which returns all distinct binary trees with a specified number of
 * nodes. For example, if the number of nodes is specified to be three, 返回所有三个节点构成的树.
 * <p>
 * Hint: Can two binary trees whose left subtrees differ in size be the same?
 */

class BinaryTreeNode<T> {
  T value;
  BinaryTreeNode left;
  BinaryTreeNode right;

  public BinaryTreeNode(T value, BinaryTreeNode left, BinaryTreeNode right) {
    this.value = value;
    this.left = left;
    this.right = right;
  }

  public BinaryTreeNode() {
  }
}

public class P9_BinaryTreeKNode {

  public static List<BinaryTreeNode<Integer>> bruteForceSolution(int n) {
    return null;
  }

  /**
   *
   * 其实不太明白这题这样返回值的意义。
   * t       t             t          t        t
   *  \       \           / \        /        /
   *   t       t         t   t      t        t
   *    \      /                   /         \
   *     t    t                   t           t
   *
   *  If the left child has k nodes, we should only use right children with n — 1 — k nodes,
   *  to get binary trees with n nodes that have that left child. Specifically, we get all
   *  binary trees on n nodes by getting all left subtrees on i nodes, and right subtrees on
   *  n-1- i nodes, for i between 0 and n — 1.
   *
   * @param n
   * @return
   */
  public static List<BinaryTreeNode<Integer>> solution(int n) {
    List<BinaryTreeNode<Integer>> result = new ArrayList<>();
    if (n == 0) {
      result.add(null);
    }

    for (int numberOfLeftTreeNode = 0; numberOfLeftTreeNode < n; numberOfLeftTreeNode++) {
      int numberOfRightTreeNode = n - 1 - numberOfLeftTreeNode;

      List<BinaryTreeNode<Integer>> leftSubTree = solution(numberOfLeftTreeNode);
      List<BinaryTreeNode<Integer>> rightSubTree = solution(numberOfRightTreeNode);
      for (BinaryTreeNode<Integer> left : leftSubTree) {
        for (BinaryTreeNode<Integer> right : rightSubTree) {
          result.add(new BinaryTreeNode<>(0, left, right));
        }
      }
    }

    return result;
  }

  public static void main(String[] args) {
    final int n = 10;
    List<BinaryTreeNode<Integer>> result = solution(n);
  }
}
