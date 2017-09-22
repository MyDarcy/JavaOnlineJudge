package eopi.ch10_binarytree;

/**
 * Author by darcy
 * Date on 17-9-22 上午9:59.
 * Description:
 *
 * 计算最低公共祖先.
 * Any two nodes in a binary tree have a common ancestor, namely the root. The lowest
 * common ancestor (LCA) of any two nodes in a binary tree is the node furthest from
 * the root that is an ancestor of both nodes.
 *
 * Design an algorithm for computing the LCA of two nodes in a binary tree in which
 * nodes do not have a parent field.
 *
 * Hint: When is the root the LCA?
 */
public class P10_3_ComputeTheLowestCommonAncestorInABinaryTree {

  static class Status {
    public int numberOfTargetNodes;
    public BinaryTreeNode<Integer> ancestor;

    public Status(int numberOfTargetNodes, BinaryTreeNode<Integer> ancestor) {
      this.numberOfTargetNodes = numberOfTargetNodes;
      this.ancestor = ancestor;
    }

    public BinaryTreeNode<Integer> LCA(BinaryTreeNode<Integer> tree, BinaryTreeNode<Integer> node1,
                                       BinaryTreeNode<Integer> node2) {
      return LCAHelper(tree, node1, node2).ancestor;
    }

    /**
     *
     * 时间复杂度O(N), 空间复杂度O(H);
     *
     * The insight to a better time complexity is that we do not need to perform multiple
     * passes. If the two nodes are in a subtree, we can compute the LCA directly, instead
     * of simply returning a Boolean indicating that both nodes are in that subtree. The
     * program below returns an object with two fields—the first is an integer indicating
     * how many of the two nodes were present in that subtree, and the second is their LCA,
     * if both nodes were present
     * @param tree
     * @param node1
     * @param node2
     * @return
     */
    private Status LCAHelper(BinaryTreeNode<Integer> tree, BinaryTreeNode<Integer> node1, BinaryTreeNode<Integer> node2) {
      if (tree == null) {
        return new Status(0, null);
      }

      Status left = LCAHelper(tree.left, node1, node2);
      if (left.numberOfTargetNodes == 2) {
        return left;
      }

      Status right = LCAHelper(tree.right, node1, node2);
      if (right.numberOfTargetNodes == 2) {
        return right;
      }

      // 汇总左右子树包含的目标节点的数目.
      // 如果当前根节点等于其中一个目标节点, 那么汇总到当前节点为根的子树中目标节点的个数+1;
      int numberOfTarget = left.numberOfTargetNodes + right.numberOfTargetNodes +
          ((tree == node1) ? 1 : 0) + ((tree == node2) ? 1 : 0);

      return new Status(numberOfTargetNodes, numberOfTarget == 2 ? tree : null);
    }
  }

}
