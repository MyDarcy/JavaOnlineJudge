package jianzhioffer;

import java.util.Deque;
import java.util.LinkedList;

/**
 * Created by darcy
 * 2017/4/14--23:59
 * Description:
 * 操作给定的二叉树，将其变换为源二叉树的镜像。
 */
public class P19_MirrorTree {

    class  TreeNode {
        int val = 0;
        TreeNode left = null;
        TreeNode right = null;

    public TreeNode(int val) {
            this.val = val;

        }

    }
    /*
        思路; 递归，如果当前节点的左右子树有一不为null,那么交换左右指针的执行;
        然后递归左子树（之前的右子树）；递归右子树（之前的左子树;）
        先序遍历的应用
     */

    public void Mirror(TreeNode root) {
        if (root == null) {
            return;
        }

        if (root.left == null && root.right == null) {
            return;
        }

        TreeNode temp = root.left;
        root.left = root.right;
        root.right = temp;

        if (root.left != null) {
            Mirror(root.left);
        }

        if (root.right != null) {
            Mirror(root.right);
        }

    }

    /*
        思路: 非递归的解决方案;
        用栈的先序遍历的思路; 有节点的左右子树不为null时，就交换指针;
     */

    public void Mirror2(TreeNode root) {
        if (root == null) {
            return;
        }

        Deque<TreeNode> stack = new LinkedList<>();
        stack.addLast(root);
        while (!stack.isEmpty()) {
            TreeNode node = stack.pollLast();
            if (node.left != null || node.right != null) {
                TreeNode temp = node.right;
                node.right = node.left;
                node.left = temp;
            }

            if (node.left != null) {
                stack.addLast(node.left);
            }

            if (node.right != null) {
                stack.addLast(node.right);
            }
        }
    }
}
