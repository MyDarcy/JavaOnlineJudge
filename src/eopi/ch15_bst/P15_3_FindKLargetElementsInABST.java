package eopi.ch15_bst;

import java.util.ArrayList;
import java.util.List;

/**
 * Author by darcy
 * Date on 17-10-1 下午9:48.
 * Description:
 *
 * 在BST中找到K个最大的值.
 *
 * Write a program that takes as input a BST and an integer k, and returns the k largest
 * elements in the BST in decreasing order. For example, if the input is the BST in
 * Figure 15.1 on Page 255 and k = 3, your program should return (53,47,43).
 * Hint: What does an inorder traversal yield?
 *
 *
 */
public class P15_3_FindKLargetElementsInABST {

  /**
   * 1. 解法1,中序遍历,然后获取后面的topK,因为已经是有序的了.
   * 2. 解法2,
   * @param tree
   * @return
   */
  public static List<Integer> solution(BSTNode<Integer> tree, int k) {
    List<Integer> result = new ArrayList<>();
    solution(tree, k, result);
    return result;
  }

  /**
   * 执行逆序的中序遍历.
   *
   * A better approach is to begin with the desired nodes, and work backwards. We do
   * this by recursing first on the right subtree and then on the left subtree. This amounts
   * to a reverse-inorder traversal. For the BST in Figure 15.1 on Page 255, the reverse
   * inorder visit sequence is (P, O, I,N,K,M,L,/, A,G,H,F,B,E,C,D)
   * We store newer nodes at the end of the array
   *
   * To find the five biggest keys in the tree in Figure 15.1 on Page 255, we would
   * recurse on A,I,O,P, in that order. Returning from recursive calls, we would visit
   * P,O, I, in that order, and add their keys to the result. Then we would recurse on
   * J, K,N, in that order. Finally, we would visit N and then K, adding their keys to the
   * result. Then we would stop, since we have five keys in the array.
   *
   * 时间复杂度O(h + k),
   *
   * @param tree
   * @param k
   * @param result
   */
  private static void solution(BSTNode<Integer> tree, int k, List<Integer> result) {
    // Perform reverse inorder traversal
    if (tree != null && result.size() < k) {
      // 先递归右子树.
      solution(tree.right, k, result);
      // 仍然不满足k个,那么加入当前根节点的值, 然后进入左子树
      if (result.size() < k) {
        result.add(tree.data);
        solution(tree.left, k, result);
      }
    }
  }

  public static void main(String[] args) {
    List<Integer> result = solution(BSTNode.HEAD, 5);
    System.out.println(result);
  }

}
