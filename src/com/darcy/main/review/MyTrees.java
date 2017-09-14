package com.darcy.main.review;

import java.util.Deque;
import java.util.LinkedList;

/**
 * Author by darcy
 * Date on 17-9-14 下午3:01.
 * Description:
 * <p>
 * 1. 节点的度: 节点所拥有的子树(孩子)的个数称为该节点的度.
 * 2. 叶子节点: 度为0的节点.
 * 3. 节点的层次: 根节点的层次为1. 其余节点的层次为父节点的层次+1.
 * 4. 树的深度: 所有节点的最大层数称为树的深度.
 * 5. 树的度: 树中各个节点度的最大值称为树的度.叶子节点的度为0
 * 6. 满二叉树: 除叶子结点外的所有结点均有两个子结点。节点数达到最大值。所有叶子结点必须在同一层上.
 * 7. 完全二叉树: 若一棵二叉树至多只有最下面的两层上的结点的度数可以小于2，并且最下层上的结点都集中
 * 在该层最左边的若干位置上，则此二叉树成为完全二叉树。
 * 8. 对于一棵非空的二叉树, 度为0的节点(叶子节点)总比度为2的节点多一个.即n0 = n2 + 1
 * 证明: 总个数= n0 + n1 + n2, 而二叉树的性质n = n1 + 2 * n2 + 1, 所以n2 = n0 + 1
 * 9. 具有n个节点的完全二叉树的深度为　log2n + 1
 * 10. 完全二叉树上有1001个节点.其中叶子节点的个数是501个
 * 证明：　n = n0 + n1 + n2 = n0 + n1 + (n0 - 1) = 2 * n0 + n1 - 1 = 1001. (n1 = 1 或者 0)
 * 11. 具有1000个节点的树中, 其边的个数是99: 因为每个节点一条入边.
 */
public class MyTrees {

  static class Node {
    int data;
    Node left;
    Node right;
    int leftMaxDistance;
    int rightMaxDistance;

    public Node(int data, Node left, Node right) {
      this.data = data;
      this.left = left;
      this.right = right;
    }

    public Node(int data) {

      this.data = data;
    }
  }

  private Node root;

  /**
   * 插入节点
   *
   * @param data
   */
  public void insert(int data) {
    Node newNode = new Node(data);
    if (root == null) {
      root = newNode;
    } else {
      Node current = root;
      Node parent = null;
      while (true) {
        parent = current;
        if (data < current.data) {
          current = current.left;
          if (current == null) {
            parent.left = newNode;
            return;
          }
        } else {
          current = current.right;
          if (current == null) {
            parent.right = newNode;
            return;
          }
        }
      }
    }
  }

  /**
   * 建树.
   *
   * @param array
   */
  public void buildTree(int[] array) {
    for (int i = 0; i < array.length; i++) {
      insert(array[i]);
    }
  }

  /**
   *
   */
  public void inOrder() {
    inOrder(root);
  }

  /**
   * @param root
   */
  private void inOrder(Node root) {
    if (root != null) {
      inOrder(root.left);
      System.out.print(root.data + "\t");
      inOrder(root.right);
    }
  }

  /**
   *
   */
  public void preOrder() {
    preOrder(root);
  }

  private void preOrder(Node root) {
    if (root != null) {
      System.out.print(root.data + "\t");
      preOrder(root.left);
      preOrder(root.right);
    }
  }

  /**
   * 后续遍历.
   */
  public void postOrder() {
    postOrder(root);
  }

  public void postOrder(Node root) {
    if (root != null) {
      postOrder(root.left);
      postOrder(root.right);
      System.out.print(root.data + "\t");
    }
  }

  /**
   * 层次遍历.
   */
  public void levelOrder() {
    levelOrder(root);
  }

  /**
   * 层次化打印结果.
   * 层次遍历:
   * 2
   * 1	8
   * 7	9
   * 4	7
   * 3	6
   * 5
   * <p>
   * <p>
   * 2
   * /  \
   * 1    8
   * / \
   * 7   9
   * /  |
   * 4   7
   * /  |
   * 3   6
   * /
   * 5
   *
   * @param root
   */
  private void levelOrder(Node root) {
    Node rightNode = null;
    Node mostRightNode = root;
    Deque<Node> queue = new LinkedList<>();
    queue.push(root);
    while (!queue.isEmpty()) {
      Node temp = queue.pollFirst();
      if (temp.left != null) {
        queue.addLast(temp.left);
        rightNode = temp.left;
      }

      if (temp.right != null) {
        queue.addLast(temp.right);
        rightNode = temp.right;
      }

      System.out.print(temp.data + "\t");

      if (temp == mostRightNode) {
        System.out.println();
        mostRightNode = rightNode;
      }
    }
  }

  /**
   * 已知前序和中序,求后序遍历的结果.
   *
   * @param preOrder
   * @param inOrder
   */
  // private Node postTreeRoot;
  public void getPostOrderWhenKnownProOrderAndInOrder(int[] preOrder, int[] inOrder) {
    root = init(preOrder, 0, preOrder.length - 1, inOrder, 0, inOrder.length - 1);
  }

  private Node init(int[] preOrder, int preStart, int preEnd, int[] inOrder, int inStart, int inEnd) {
    if (preStart > preEnd || inStart > inEnd) {
      return null;
    }

    Node root = new Node(preOrder[preStart]);
    int index = findIndex(inOrder, preOrder[preStart], inStart, inEnd);
    int leftLength = index - inStart;
    // 各个范围都是要要借助offset在自己的序列中确定范围.
    root.left = init(preOrder, preStart + 1, preStart + leftLength,
        inOrder, inStart, inStart + leftLength - 1);
    root.right = init(preOrder, preStart + leftLength + 1, preEnd,
        inOrder, index + 1, inEnd);
    return root;
  }

  private int findIndex(int[] inOrder, int target, int inStart, int inEnd) {
    for (int i = inStart; i <= inEnd; i++) {
      if (inOrder[i] == target) {
        return i;
      }
    }
    return -1;
  }

  /**
   * 相距最远的两个节点之间的距离.
   *
   * @return
   */
  private int maxResult;

  public void maxDistance() {
    maxDistance(root);
  }

  private void maxDistance(Node root) {
    if (root == null) {
      return;
    }

    if (root.left == null) {
      root.leftMaxDistance = 0;
    }

    if (root.right == null) {
      root.rightMaxDistance = 0;
    }

    if (root.left != null) {
      maxDistance(root.left);
    }

    if (root.right != null) {
      maxDistance(root.right);
    }

    if (root.left != null) {
      root.leftMaxDistance = Math.max(root.left.leftMaxDistance, root.left.rightMaxDistance) + 1;
    }

    if (root.right != null) {
      root.rightMaxDistance = Math.max(root.right.leftMaxDistance, root.right.rightMaxDistance) + 1;
    }

    if (root.leftMaxDistance + root.rightMaxDistance + 1 > maxResult) {
      maxResult = root.leftMaxDistance + root.rightMaxDistance + 1;
    }
  }


  public static void main(String[] args) {
    MyTrees myTrees = new MyTrees();
    int[] array = {2, 8, 7, 4, 9, 3, 1, 6, 7, 5};
    myTrees.buildTree(array);
    System.out.println("前序:");
    myTrees.preOrder();
    System.out.println();

    System.out.println("中序:");
    myTrees.inOrder();
    System.out.println();

    System.out.println("后序:");
    myTrees.postOrder();
    System.out.println();

    System.out.println("层次遍历:");
    myTrees.levelOrder();
    System.out.println();

    myTrees.maxDistance();
    System.out.println("maxDistance:" + myTrees.maxResult + "\n");


    System.out.println("已知前序和中序,求后序:");
    int[] preOrder = {1, 2, 4, 8, 9, 5, 10, 3, 6, 7};
    int[] inOrder = {8, 4, 9, 2, 10, 5, 1, 6, 3, 7};
    MyTrees demo2 = new MyTrees();
    demo2.getPostOrderWhenKnownProOrderAndInOrder(preOrder, inOrder);
    demo2.postOrder();
  }
}
