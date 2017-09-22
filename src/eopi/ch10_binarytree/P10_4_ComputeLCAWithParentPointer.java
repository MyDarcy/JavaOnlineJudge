package eopi.ch10_binarytree;

/**
 * Author by darcy
 * Date on 17-9-22 上午10:36.
 * Description:
 *
 * 同上, 不过有了额外的指向父节点的指针.
 *
 * Given two nodes in a binary tree, design an algorithm that computes their LCA.
 * Assume that each node has a parent pointer.
 *
 * Hint: The problem is easy if both nodes are the same distance from the root.
 *
 */
public class P10_4_ComputeLCAWithParentPointer {

  public class BinaryTreeNode<T> {
    public T data;
    public BinaryTreeNode<T> left, right, parent;

    public BinaryTreeNode(T data) {
      this.data = data;
    }

    public BinaryTreeNode(T data, BinaryTreeNode<T> left, BinaryTreeNode<T> right, BinaryTreeNode<T> parent) {
      this.data = data;
      this.left = left;
      this.right = right;
      this.parent = parent;
    }
  }

  /**
   * 时间复杂度O(H), 空间复杂度O(1).
   * 链表找公共节点.
   * @param root
   * @return
   */
  public static BinaryTreeNode<Integer> LCA(BinaryTreeNode<Integer> root,
                                            BinaryTreeNode<Integer> node1,
                                            BinaryTreeNode<Integer> node2) {

    int depth1 = depth(root, node1);
    int depth2 = depth(root, node2);

    if (depth1 < depth2) {
      BinaryTreeNode<Integer> temp = node1;
      node1 = node2;
      node2 = temp;
    }

    for (int i = 0; i < depth1 - depth2; i++) {
      node1 = node1.parent;
    }

    while (node1 != node2) {
      node1 = node1.parent;
      node2 = node2.parent;
    }

    return node1;
  }

  private static int depth(BinaryTreeNode<Integer> root, BinaryTreeNode<Integer> node1) {
    int depth = 0;
    // node.parent
    while (node1.parent != root) {
      depth++;
      node1 = node1.parent;
    }

    return depth;
  }
}
