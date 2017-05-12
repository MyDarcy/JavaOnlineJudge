package jianzhioffer;

import java.util.Deque;
import java.util.LinkedList;
import java.util.Queue;

/**
 * Created by darcy
 * 2017/5/10--23:35
 * Description:
 * 输入一棵二叉树，求该树的深度。
 * 从根结点到叶结点依次经过的结点（含根、叶结点）形成树的一条路径，最长路径的长度为树的深度。
 */


public class P39_DepthOfBinaryTree {

    public class TreeNode {
        int val = 0;
        TreeNode left = null;
        TreeNode right = null;

        public TreeNode(int val) {
            this.val = val;

        }

    }

    /**
     * 遍历左右子树;
     *
     * @param root
     * @return
     */
    public int TreeDepth(TreeNode root) {
        if (root == null) {
            return 0;
        }

        if (root.left == null && root.right == null) {
            return 1;
        }

        int left = 0;
        if (root.left != null) {
            left = TreeDepth(root.left);
        }

        int right = 0;
        if (root.right != null) {
            right = TreeDepth(root.right);
        }

        return left > right ? left + 1 : right + 1;
    }

    /**
     * @param root
     * @return
     */
    public int TreeDepth2(TreeNode root) {
        if (root == null) {
            return 0;
        }
        return Math.max(1 + TreeDepth2(root.left), 1 + TreeDepth2(root.right));
    }

    /**
     * 层次式遍历的思想;
     *
     * @param root
     * @return
     */
    private int TreeDepth3(TreeNode root) {
        if (root == null) {
            return 0;
        }
        Deque<TreeNode> queue = new LinkedList<>();

        queue.addLast(root);
        int count = 0;
        while (!queue.isEmpty()) {
            count++;
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                TreeNode node = queue.pollFirst();
                if (node.left != null) {
                    queue.addLast(node.left);
                }
                if (node.right != null) {
                    queue.addLast(node.right);
                }
            }
        }
        return count;

    }

    public int TreeDepth4(TreeNode pRoot) {
        if (pRoot == null) {
            return 0;
        }
        Queue<TreeNode> queue = new LinkedList<TreeNode>();
        queue.add(pRoot);
        int depth = 0, count = 0, nextCount = 1;
        while (queue.size() != 0) {
            TreeNode top = queue.poll();
            count++;
            if (top.left != null) {
                queue.add(top.left);
            }
            if (top.right != null) {
                queue.add(top.right);
            }
            if (count == nextCount) {
                nextCount = queue.size();
                count = 0;
                depth++;
            }
        }
        return depth;
    }


}
