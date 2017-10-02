package eopi.ch15_bst;

import java.util.Arrays;
import java.util.List;

/**
 * Author by darcy
 * Date on 17-10-2 上午11:53.
 * Description:
 * <p>
 * 从遍历的数据中重构BST树.
 * 给定中序遍历的结果,重构BST树.
 * <p>
 * Suppose you are given the sequence in which keys are visited in an inorder traversal
 * of a BST, and all keys are distinct. Can you reconstruct the BST from the sequence?
 * If so, write a program to do so. Solve the same problem for preorder and postorder
 * traversal sequences.
 * <p>
 * Hint: Draw the five BSTs on the keys1, 2,3, and the corresponding traversal orders.
 */
public class P15_5_ReconstructBSTFromTraversalData {

  /**
   * 时间复杂度O(NlogN);
   *
   * @param preOrderSequence
   * @return
   */
  public static BSTNode<Integer> solution(List<Integer> preOrderSequence) {
    return solution(preOrderSequence, 0, preOrderSequence.size());
  }

  private static BSTNode<Integer> solution(List<Integer> preOrderSequence, int start, int end) {
    // 已经控制好了边界条件.
    if (start >= end) {
      return null;
    }

    // 确定左右子树的边界.
    // rightStart >= end在下一轮循环中也会直接退出.
    int rightStart = start + 1;
    while (rightStart < end
        // 找到右子树的第一个节点.
        && Integer.compare(preOrderSequence.get(rightStart), preOrderSequence.get(start)) < 0) {
      ++rightStart;
    }

    return new BSTNode<Integer>(preOrderSequence.get(start),
        solution(preOrderSequence, start + 1, rightStart),
        solution(preOrderSequence, rightStart, end));
  }


  private static Integer rootIndex;

  /**
   * 上面的实现需要遍历子树多次.
   * <p>
   * new solution:
   * <p>
   * 采用判定一个节点是否在一棵子树中相同的策略.即传入上下界就可以确定子树.
   * <p>
   * A better approach is to reconstruct the left subtree in the same iteration as
   * identifying the nodes which lie in it. The code shown below takes this approach. The
   * intuition is that we do not want to iterate from first entry after the root to the last entry
   * smaller than the root, only to go back and partially repeat this process for the root's
   * left subtree. We can avoid repeated passes over nodes by including the range of keys
   * we want to reconstruct the subtrees over. For example, looking at the preorder key
   * sequence (43, 23,37, 29,31,41,47,53), instead of recursing on (23,37, 29,31,41) (which
   * would involve an iteration to get the last element in this sequence). We can directly
   * recur on (23,37, 29,31, 41, 47,53), with the constraint that we are building the subtree
   * on nodes whose keys are less than 43.
   * <p>
   * <p>
   * The worst-case time complexity is O(n),since it performs a constant amount of work
   * per node. Note the similarity to Solution 25.22 on Page 470.
   * <p>
   * A postorder traversal sequence also uniquely specifies the BST, and the algorithm
   * for reconstructing the BST is very similar to that for the preorder case.(后续遍历也可以唯一
   * 确定一棵BST树.)
   *
   * @param preOrderSequence
   * @return
   */
  public static BSTNode<Integer> betterSolution(List<Integer> preOrderSequence) {
    rootIndex = 0;
    return betterSolution(preOrderSequence, Integer.MIN_VALUE, Integer.MAX_VALUE);
  }

  private static BSTNode<Integer> betterSolution(List<Integer> preOrderSequence, int minValue, int maxValue) {
    if (rootIndex == preOrderSequence.size()) {
      return null;
    }

    Integer rootValue = preOrderSequence.get(rootIndex);
    if (rootValue < minValue || rootValue > maxValue) {
      return null;
    }

    ++rootIndex;

    // 前序的当前节点一定是一棵子树的根节点.其值就是其两颗子树的上下界.
    BSTNode<Integer> left =
        betterSolution(preOrderSequence, minValue, rootValue);

    BSTNode<Integer> right =
        betterSolution(preOrderSequence, rootValue, maxValue);

    //
    return new BSTNode<Integer>(rootValue, left, right);
  }

  /**
   * 前序遍历子树.
   *
   * @param root
   */
  public static void print(BSTNode<Integer> root) {
    if (root == null) {
      return;
    } else {
      System.out.print(root.data + ", ");
      print(root.left);
      print(root.right);
    }
  }

  public static void main(String[] args) {
    // 2, 3, 5, 7, 11, 13, 17, 19, 23, 29, 31, 37, 41, 43, 47, 53,
    // 19, 7, 3, 2, 5, 11, 17, 13, 43, 23, 37, 29, 31, 41, 47, 53,
    print(BSTNode.HEAD);

    System.out.println();

    List<Integer> list = Arrays.asList(19, 7, 3, 2, 5, 11, 17, 13, 43, 23, 37, 29, 31, 41, 47, 53);
    BSTNode<Integer> newRoot = solution(list);
    print(newRoot);

    System.out.println();

    BSTNode<Integer> betterNewRoot = betterSolution(list);
    print(betterNewRoot);

  }

}
