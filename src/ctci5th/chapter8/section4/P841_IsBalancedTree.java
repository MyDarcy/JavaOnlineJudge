package ctci5th.chapter8.section4;

/**
 * Author by darcy
 * Date on 17-7-10 下午2:22.
 * Description:
 * 给定的二叉树是否是平衡二叉树.
 */
public class P841_IsBalancedTree {

    class TreeNode {
        int val = 0;
        TreeNode left = null;
        TreeNode right = null;

        public TreeNode(int val) {
            this.val = val;
        }
    }

    /**
     * height会被重复调用计算同一个节点的值. 时间复杂度O(NlgN)
     *
     * @param root
     * @return
     */
    public boolean isBalance(TreeNode root) {
        // write code here
        if (root == null) {
            return true;
        }

        int diff = height(root.left) - height(root.right);
        if (Math.abs(diff) <= 1) {
            return isBalance(root.left) && isBalance(root.right);
        } else {
            return false;
        }
    }

    private int height(TreeNode root) {
        if (root == null) {
            return 0;
        }
        return Math.max(height(root.left), height(root.right)) + 1;
    }

    /**
     * 往下递归的获取子树的高度.如果子树是平衡的,那么返回子数的实际高度,否则,checkHeight返回-1;
     * 事件复杂度为O(N), 空间复杂度O(H)
     * @param root
     * @return
     */
    public boolean isBalance2(TreeNode root) {
        int i = checkHeight(root);
        if (i == -1) {
            return false;
        } else {
            return true;
        }
    }

    private int checkHeight(TreeNode root) {
        if (root == null) {
            return 0;
        }
        int left = checkHeight(root.left);
        if (left == -1) {
            return -1;
        }

        int right = checkHeight(root.right);
        if (right == -1) {
            return -1;
        }

        if (Math.abs(right - left) > 1) {
            return -1;
        } else {
            // 返回当前节点的实际高度.
            return Math.max(left, right) + 1;
        }
    }
}
