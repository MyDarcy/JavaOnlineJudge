package eopi.ch15_bst;

import java.util.Deque;
import java.util.LinkedList;

/**
 * Author by darcy
 * Date on 17-10-1 下午8:49.
 * Description:
 * <p>
 * 判断一棵二叉树是否满足BST的特性.
 * <p>
 * Write a program that takes as input a binary tree and checks if the tree satisfies the
 * BST property.\
 * <p>
 * Hint: Is it correct to check for each node that its key is greater than or equal to
 * the key at its left child and less than or equal to the key at its right child?
 */
public class P15_1_TestIfABinaryTreeSatifyTheBSTProperty {

  /**
   * 获取左子树的最大值lmax, 右子树的最小值 rmin, 看lmax < root <= rmin
   * 不满足, 返回false,满足则继续往左右子树遍历.
   * <p>
   * 问题在于每次子节点的计算都是重复计算的.
   * <p>
   * A directapproach, based on the definition of a BST, is tobegin with the root,
   * and compute the maximum key stored in the root's left subtree, and the minimum
   * key in the root's right subtree. We check that the key at the root is greater than or
   * equal to the maximum from the left subtree and less than or equal to the minimum
   * from the right subtree. If both these checks pass, we recursively check the root's left
   * and right subtrees. If either check fails, we return false.
   *
   * @param tree
   * @return
   */
  public static boolean bruteForceSolution(BSTNode<Integer> tree) {

    return false;
  }

  /**
   * 传入上下界.
   * <p>
   * if all nodes in a tree must have keys in the range [l,u], and the key at the root is w
   * (which itself must be between [l,u],otherwise the requirement is violated at the root
   * itself), then all keys in the left subtree must be in the range [Z, w], and all keys stored
   * in the right subtree must be in the range [w, u],
   *
   * @param tree
   * @return
   */
  public static boolean solution(BSTNode<Integer> tree) {
    return solution(tree, Integer.MIN_VALUE, Integer.MAX_VALUE);
  }

  private static boolean solution(BSTNode<Integer> tree, int minValue, int maxValue) {
    if (tree == null) {
      return true;

      // 当前根节点的值不满足定义.
    } else if (Integer.compare(tree.data, minValue) < 0
        || Integer.compare(tree.data, maxValue) > 0) {
      return false;
    }

    // 继续递归左右子树.
    return solution(tree.left, minValue, tree.data)
        && solution(tree.right, tree.data, maxValue);
  }


  public static class QueueEntry {
    public BSTNode<Integer> treeNode;
    public Integer lowerBound, upperBound;

    public QueueEntry(BSTNode<Integer> treeNode, Integer lowerBound, Integer upperBound) {
      this.treeNode = treeNode;
      this.lowerBound = lowerBound;
      this.upperBound = upperBound;
    }
  }

  /**
   * 检测是否存在冲突.
   * 使用队列是要把上一层的上下界的信息传入到下一层.
   * <p>
   * use a queue, where each queue entry contains a node, as well as an upper and a lower
   * bound on the keys stored at the subtree rooted at that node. The queue is initialized
   * to the root, with lower bound -oo and upper bound +oo. We iteratively check the
   * constraint on each node. If it violates the constraint we stop the BST property has been
   * violated. Otherwise, we add its children along with the corresponding constraint.
   *
   * @param head
   * @return
   */
  public static boolean testBST(BSTNode<Integer> head) {
    Deque<QueueEntry> BFSQueue = new LinkedList<>();
    BFSQueue.addLast(new QueueEntry(head, Integer.MIN_VALUE, Integer.MAX_VALUE));

    QueueEntry iter = null;
    while ((iter = BFSQueue.pollFirst()) != null) {
      if (iter.treeNode != null) {
        if (iter.treeNode.data < iter.lowerBound || iter.treeNode.data > iter.upperBound) {
          return false;
        }

        // 进入左子树的上界.
        BFSQueue.addLast(new QueueEntry(iter.treeNode.left, iter.lowerBound, iter.treeNode.data));

        // 进入右子树的下界.
        BFSQueue.addLast(new QueueEntry(iter.treeNode.right, iter.treeNode.data, iter.upperBound));
      }
    }
    return true;
  }

  public static void main(String[] args) {
    boolean result = testBST(BSTNode.HEAD);
    System.out.println(result);
  }
}
