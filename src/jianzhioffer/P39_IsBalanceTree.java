package jianzhioffer;

/**
 * Created by darcy
 * 2017/5/11--0:07
 * Description:
 */
public class P39_IsBalanceTree {

    public class TreeNode {
        int val = 0;
        TreeNode left = null;
        TreeNode right = null;

        public TreeNode(int val) {
            this.val = val;

        }

    }


    public static class Depth{
        int depth;
    }
    /**
     * 后续遍历，记录每个节点的深度（它到叶子节点的路径长度）
     * 但是java的参数是传引用的，所以不要直接使用传递int或者Integer型的变量来解决问题；
     *
     * 思路：后续遍历，子节点的值传递到上层节点;
     * @param root
     * @return
     */

    public boolean isBalanceTree1(TreeNode root) {
        Depth aDepth = new Depth();
        aDepth.depth = 0;
        return isBalanceTree(root, aDepth);
    }

    public boolean isBalanceTree(TreeNode root, Depth aDepth) {
        if (root == null) {
            aDepth.depth = 0;
            return true;
        }

        Depth left = new Depth();
        Depth right = new Depth();

        if (isBalanceTree(root.left, left) && isBalanceTree(root.right, right)) {
            int diff = Math.abs(left.depth - right.depth);
            if (diff <= 1) {
                aDepth.depth = 1 + ((left.depth > right.depth) ? left.depth : right.depth);
                return true;
            }
        }
        return false;

    }

    /**
     * 获取左右子树的高度，然后判断高度差;
     * 问题是会遍历节点多次;
     * @param root
     * @return
     */
    public boolean isBalanceTree(TreeNode root) {
        if (root == null) {
            return true;
        }

        int left = 0;
        if (root.left != null) {
            left = solution(root.left);
        }

        int right = 0;
        if (root.right != null) {
            right = solution(root.right);
        }

        // 注意不是 <= 1就返回true
        if (Math.abs(left - right) > 1) {
            return false;
        }

        return isBalanceTree(root.left) && isBalanceTree(root.right);

    }

    private int solution(TreeNode root) {
        if (root == null) {
            return 0;
        }

        return 1 + Math.max(solution(root.left), solution(root.right));
    }

}
