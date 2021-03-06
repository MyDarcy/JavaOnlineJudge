package eopi.ch10_binarytree;

import java.util.function.BinaryOperator;

/**
 * Author by darcy
 * Date on 17-9-19 下午4:52.
 * Description:
 *
 * http://www.cnblogs.com/idorax/p/6441043.html
 *
 * The depth of a node n is the number of nodes on the search path from the root to n,
 * not including n itself.
 *
 * The height of a binary tree is the maximum depth of any node in that tree.
 *
 * A full binary tree is a binary tree in which every node other than the leaves has two children.
 * A perfect binary tree is a full binary tree in which all leaves are at the same depth, and in
 * which every parent has two children.
 * A complete binary tree is a binary tree in which every level, except possibly the last, is
 * completely filled, and all nodes are as far left as possible.
 */
public class BinaryTreeNode<T> {
  public T data;
  public BinaryTreeNode<T> left, right;

  public BinaryTreeNode(T data) {
    this.data = data;
  }

  public BinaryTreeNode(T data, BinaryTreeNode<T> left, BinaryTreeNode<T> right) {
    this.data = data;
    this.left = left;
    this.right = right;
  }

  public static BinaryTreeNode<Integer> ROOT =
      new BinaryTreeNode<Integer>(314,
          new BinaryTreeNode<Integer>(6,
              new BinaryTreeNode<Integer>(271,
                  new BinaryTreeNode<Integer>(28),
                  new BinaryTreeNode<Integer>(0)),
              new BinaryTreeNode<Integer>(561,
                  null,
                  new BinaryTreeNode<Integer>(3,
                      new BinaryTreeNode<Integer>(17),
                      null))),
          new BinaryTreeNode<Integer>(6,
              new BinaryTreeNode<Integer>(2,
                  null,
                  new BinaryTreeNode<Integer>(1,
                      new BinaryTreeNode<Integer>(401,
                          null,
                          new BinaryTreeNode<Integer>(641)),
                      new BinaryTreeNode<Integer>(257))),
              new BinaryTreeNode<Integer>(271,
                  null,
                  new BinaryTreeNode<Integer>(28))));
}
