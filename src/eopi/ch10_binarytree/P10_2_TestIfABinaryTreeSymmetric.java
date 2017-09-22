package eopi.ch10_binarytree;

/**
 * Author by darcy
 * Date on 17-9-22 上午9:45.
 * Description:
 *
 * 检查一个二叉树是否是对称的.
 */
public class P10_2_TestIfABinaryTreeSymmetric {

  public static boolean isSymmetric(BinaryTreeNode root) {
    /*if (root == null) {
      return true;
    }

    if (root.left != null && root.right == null) {
      return false;
    } else if (root.left == null && root.right != null) {
      return false;
    } else if (root.left == null && root.right == null) {
      return true;
    }

    return root.left == root.right && isSymmetric(root.left) && isSymmetric(root.right);*/

    // 注意是左右子树是否对称.
    return root == null || isSymmetric(root.left, root.right);

  }

  /**
   * 时间复杂度是O(N), 空间复杂度也是O(N)
   *
   * @param left
   * @param right
   * @return
   */
  private static boolean isSymmetric(BinaryTreeNode left, BinaryTreeNode right) {
    if (left == null && right == null) {
      return true;
    } else if (left != null && right != null) {
      return left.data == right.data && isSymmetric(left.left, right.right)
          && isSymmetric(left.right, right.left);
    }

    // 有一个不为null.
    return false;
  }


  public static void main(String[] args) {
    BinaryTreeNode binaryTreeNode =
        new BinaryTreeNode(1,
            new BinaryTreeNode(3,
                new BinaryTreeNode(5,
                    new BinaryTreeNode(1),
                    new BinaryTreeNode(7)), null),
            new BinaryTreeNode(3,
                null, new BinaryTreeNode(5,
                    new BinaryTreeNode(7),
                    new BinaryTreeNode(1))));

    boolean symmetric = isSymmetric(binaryTreeNode);
    System.out.println(symmetric);

  }
}
