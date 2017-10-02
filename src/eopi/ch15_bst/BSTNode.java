package eopi.ch15_bst;

import org.omg.CORBA.INTERNAL;

/**
 * Author by darcy
 * Date on 17-10-1 下午7:59.
 * Description:
 */
public class BSTNode<T> {
  public T data;
  public BSTNode<T> left, right;

  public BSTNode(T data) {
    this.data = data;
  }

  public BSTNode(T data, BSTNode<T> left, BSTNode<T> right) {
    this.data = data;
    this.left = left;
    this.right = right;
  }

  public static <T> BSTNode<T> searchBST(BSTNode<T> tree, T key) {
    if (tree == null || tree.data == key) {
      return tree;
    }

    /*return key < tree.data ?
        searchBST(tree.left, key) :
        searchBST(tree.right, key);*/
    return null;
  }

  public static final BSTNode<Integer> HEAD =
      new BSTNode<Integer>(19,
          new BSTNode<Integer>(7,
              new BSTNode<Integer>(3,
                  new BSTNode<Integer>(2),
                  new BSTNode<Integer>(5)),
              new BSTNode<Integer>(11,
                  null,
                  new BSTNode<Integer>(17,
                      new BSTNode<Integer>(13),
                      null))),
          new BSTNode<Integer>(43,
              new BSTNode<Integer>(23,
                  null,
                  new BSTNode<Integer>(37,
                      new BSTNode<Integer>(29,
                          null,
                          new BSTNode<Integer>(31)),
                      new BSTNode<Integer>(41))),
              new BSTNode<Integer>(47,
                  null,
                  new BSTNode<Integer>(53))));


}
