package eopi.ch10_binarytree;

/**
 * Author by darcy
 * Date on 17-9-22 上午10:52.
 * Description:
 *
 * 计算从根节点到叶子节点所表示的二进制数字的和(所有的路径).
 * Design an algorithm to compute the sum of the binary numbers represented by the
 * root-to-leaf paths.
 *
 * Hint: Think of an appropriate way of traversing the tree.
 */
public class P10_5_SumFromRootToLeaf {

  /**
   * We compute the leaves, and store the childparent mapping in a hash table, e.g.,
   * via an inorder walk. Afterwards, we traverse　from each of the leaves to the
   * root using the child-parent map. <B>Each leaf-to-root path yields a binary integer</B>
   * , with the leaf's bit being the LSB.
   *
   * 时间复杂度O(LH), 空间复杂度O(N)
   *
   * @param root
   * @return
   */
  public static int bruteForceSolution(BinaryTreeNode<Integer> root) {

    return 0;
  }

  /**
   * 上面的方法没有用到一个性质. 从根节点进入左节点路径代表的值 *2; 从根节点进入右节点路径代表的值 *2 + 1;
   *
   * The insight to improving complexity is to recognize that paths share nodes and
   * that it is not necessary to repeat computations across the shared nodes. To compute
   * the integer for the path from the root to any node, we take the integer for the node's
   * parent, double it, and add the bit at that node. For example, the integer for the path
   * from A to L is 2 * (1100)2 +1 = (11001)2.
   *
   * Therefore, we can compute the sum of all root to leaf node as follows. Each time
   * we visit a node, we compute the integer it encodes using the number for its parent.
   * If the node is a leaf we return its integer. If it is not a leaf, we return the sum of the
   * results from its left and right children.
   *
   * @param root
   * @return
   */
  public static int solution(BinaryTreeNode<Integer> root) {
    return solution(root, 0);
  }

  private static int solution(BinaryTreeNode<Integer> root, int partial) {
    if (root == null) {
      return 0;
    }

    partial = partial * 2 + root.data; // 题目的意思没有加上这个data值吧???
    if (root.left == null && root.right == null) {
      return partial;
    }

    return solution(root.left, partial) + solution(root.right, partial);
  }


}
