package jianzhioffer;

/**
 * Created by darcy
 * 2017/4/14--0:19
 * Description:
 * 输入两棵二叉树A，B，判断B是不是A的子结构。
 * （ps：我们约定空树不是任意一个树的子结构）
 */
public class P18_SubStructure {
    static class TreeNode {
        int val = 0;
        TreeNode left = null;
        TreeNode right = null;

        public TreeNode(int val) {
            this.val = val;

        }
    }

    public boolean HasSubtree(TreeNode root1,TreeNode root2) {

        if (root1 == null || root2 == null) {
            return false;
        }

        /*
        思路: 假定root2代表的树是较小的那一个，即带求证的那一个;
        如果root2是root1的子树，直接返回,否则root2是否是root1.left的子树，否则，root2是否是root1.right的子树;
         */
        return isSubTree(root1, root2)
                || HasSubtree(root1.left, root2)
                || HasSubtree(root1.right, root2);
    }

    private boolean isSubTree(TreeNode root1, TreeNode root2) {

        if (root2 == null) {
            return true;
        }
        if (root1 == null) {
            return false;
        }
        if (root1.val == root2.val) {
            return isSubTree(root1.left, root2.left) && isSubTree(root1.right, root2.right);
        } else {
            return false;
        }

    }

    /*

     */
    public boolean HasSubtree2(TreeNode root1, TreeNode root2) {
        boolean result = false;
        if (root1 != null && root2 != null) {
            result = isSubTree2(root1, root2);
            if (!result) {
                result = HasSubtree2(root1.left, root2);
            }

            if (!result) {
                result = HasSubtree2(root1.right, root2);
            }
        }

        return result;
    }

    private boolean isSubTree2(TreeNode root1, TreeNode root2) {

        if (root2 == null) {
            return true;
        }

        if (root1 == null) {
            return false;
        }

        if (root1.val == root2.val) {
            return isSubTree2(root1.left, root2.left) && isSubTree2(root1.right, root2.right);
        } else {
            return false;
        }
    }
}
