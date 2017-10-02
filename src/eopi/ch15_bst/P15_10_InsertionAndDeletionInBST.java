package eopi.ch15_bst;

/**
 * Author by darcy
 * Date on 17-10-2 下午8:51.
 * Description:
 * <p>
 * BST树中的插入和删除操作.
 */
public class P15_10_InsertionAndDeletionInBST {

  public static class BinarySearchTree {
    public static class TreeNode {
      public Integer data;
      public TreeNode left, right;

      public TreeNode(Integer data, TreeNode left, TreeNode right) {
        this.data = data;
        this.left = left;
        this.right = right;
      }
    }

    private TreeNode root = null;

    /**
     * @param key
     * @return
     */
    public boolean insert(Integer key) {
      if (root == null) {
        root = new TreeNode(key, null, null);
      } else {
        TreeNode current = root;
        TreeNode parent = current;
        while (current != null) {
          parent = current;
          int cmp = Integer.compare(key, current.data);
          // key已经存在.
          if (cmp == 0) {
            return false;
          } else if (cmp < 0) {
            current = current.left;
          } else {
            current = current.right;
          }
        }

        // 根据key和parent的数据决定插入节点到什么地方.
        if (Integer.compare(key, parent.data) < 0) {
          parent.left = new TreeNode(key, null, null);
        } else {
          parent.right = new TreeNode(key, null, null);
        }
      }

      return true;
    }

    /**
     * @param key
     * @return
     */
    public boolean delete(Integer key) {
      TreeNode current = root;
      TreeNode parent = null;
      while (current != null && Integer.compare(current.data, key) != 0) {
        parent = current;
        current = Integer.compare(key, current.data) < 0 ? current.left : current.right;
      }

      if (current == null) {
        return false;
      }

      TreeNode keyNode = current;
      if (keyNode.right != null) {
        // Find the minimum of the right subtree.
        TreeNode rKeyNode = keyNode.right;
        TreeNode rParent = keyNode;
        while (rKeyNode.left != null) {
          rParent = rKeyNode;
          rKeyNode = rKeyNode.left;
        }
        keyNode.data = rKeyNode.data;
        // Move links to erase the node.
        if (rParent.left == rKeyNode) {
          rParent.left = rKeyNode.right;
        } else { // rParent.left != rKeyNode.
          rParent.right = rKeyNode.right;
        }
        rKeyNode.right = null;
      } else {
        // Update root link if needed.
        if (root == keyNode) {
          root = keyNode.left;
          keyNode.left = null;
        } else {
          if (parent.left == keyNode) {
            parent.left = keyNode.left;
          } else {
            parent.right = keyNode.left;
          }
          keyNode.left = null;
        }
      }
      return true;
    }
  }
}
