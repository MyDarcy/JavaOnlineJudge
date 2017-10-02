package eopi.ch15_bst;

/**
 * Author by darcy
 * Date on 17-10-1 下午9:29.
 * Description:
 * <p>
 * 在二叉搜索树中找大于给定值的最小key.
 * Write a program that takes as input a BST and a value, and returns the first key
 * that would appear in an inorder traversal which is greater than the input value. For
 * example, when applied to the BST in Figure 15.1 on Page 255 you should return 29
 * for input 23.
 * <p>
 * Hint: Perform binary search, keeping some additional state.
 */
public class P15_2_FindTheFirstKeyGreaterThanAGivenValueInBST {

  /**
   * 1. 中序遍历,找到目标结果.
   * 2. 如果当前子树根节点的值 <= 目标值, 那么进入根节点的右子树中搜索.
   * 如果当前子树的节点的值 > 目标值, 那么记录当前的根节点(可能是要找的值),然后进入根节点的左子树中.
   * <p>
   * 时间复杂度是O(H)即树高.
   * <p>
   * <p>
   * We store the best candidate for
   * the result and update that candidate as we iteratively descend the tree, eliminating
   * subtrees by comparing the keys stored at nodes with the input value. Specifically,
   * if the current subtree's root holds a value less than or equal to the input value, we
   * search the right subtree. If the current subtree's root stores a key that is greater than
   * the input value, we search in the left subtree, updating the candidate to the current
   * root. Correctness follows from the fact that whenever we first set the candidate, the\
   * desired result must be within the tree rooted at that node.
   *
   * @param root
   * @return
   */
  public static BSTNode<Integer> soltuion(BSTNode<Integer> root, Integer k) {

    BSTNode<Integer> subtree = root;
    BSTNode<Integer> target = null;

    while (subtree != null) {
      if (subtree.data > k) {
        target = subtree;
        subtree = subtree.left;
      } else {
        subtree = subtree.right;
      }
    }

    return target;
  }


  public static void main(String[] args) {
    BSTNode<Integer> result = soltuion(BSTNode.HEAD, 127);
    if (result != null) {
      System.out.println(result.data);
    } else {
      System.out.println(result);
    }
  }
}

/*
Variant: Write a program that takes as input a BST and a value, and returns the node
whose key equals the input value and appears first in an inorder traversal of the BST
 */