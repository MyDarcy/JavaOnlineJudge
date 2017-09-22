package eopi.ch10_binarytree;

import com.sun.org.apache.bcel.internal.generic.INEG;

import java.util.Deque;
import java.util.LinkedList;
import java.util.List;

/**
 * Author by darcy
 * Date on 17-9-22 下午1:04.
 * Description:
 *
 * 实现中序遍历, 不用递归.
 *
 * Write a program which takes as input a binary tree and performs an inorder traversal
 * of the tree. Do not use recursion. Nodes do not contain parent references.
 *
 * Hint: Simulate the function call stack
 *
 */
public class P10_7_InOrderTraversalWithoutRecursion {

  /**
   * The recursive solution is trivial—first traverse the left subtree, then visit
   the root, and finally traverse the right subtree. This algorithm can be converted into a
   iterative algorithm by using an explicit stack. Several implementations are possible;
   the one below is noteworthy in that it pushes the current node, and not its right child.
   Furthermore, it does not use a visited field
   * @param root
   * @return
   */
  public static List<Integer> solution(BinaryTreeNode<Integer> root) {
    Deque<BinaryTreeNode<Integer>> stack = new LinkedList<>();
    BinaryTreeNode<Integer> current = root;
    List<Integer> result = new LinkedList<>();

    while (!stack.isEmpty() || current != null) {
      if (current != null) {
        stack.addLast(current);
        // 进入左子树.
        current = current.left;
      } else {
        // 退出, 处理数据, 进入右子树.
        current = stack.removeLast();
        result.add(current.data);
        current = current.right;
      }
    }

    return result;
  }

  public static void main(String[] args) {
    BinaryTreeNode<Integer> root = new BinaryTreeNode<Integer>(10,
        new BinaryTreeNode<Integer>(20,
            new BinaryTreeNode<Integer>(30), new BinaryTreeNode<Integer>(40)),
        new BinaryTreeNode<Integer>(100,
            new BinaryTreeNode<Integer>(200), new BinaryTreeNode<Integer>(300)));
    List<Integer> result = solution(root);
    System.out.println(result);
  }
}
