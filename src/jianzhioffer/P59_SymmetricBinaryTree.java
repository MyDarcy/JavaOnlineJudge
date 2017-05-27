package jianzhioffer;

/**
 * Created by darcy
 * 2017/5/25--0:13
 * Description:
 * 请实现一个函数，用来判断一颗二叉树是不是对称的。
 * 注意，如果一个二叉树同此二叉树的镜像是同样的，定义其为对称的。
 */
public class P59_SymmetricBinaryTree {

    public class TreeNode {
        int val = 0;
        TreeNode left = null;
        TreeNode right = null;

        public TreeNode(int val) {
            this.val = val;

        }

    }

    /**
     * 比较二叉树的前序遍历序列和对称前序遍历序列来断定二叉树是否是对称的。
     *
     * @param pRoot
     * @return
     */
    boolean isSymmetrical(TreeNode pRoot) {
        return isSymmetrical(pRoot, pRoot);
    }

    private boolean isSymmetrical(TreeNode root1, TreeNode root2) {
        // 这样的判定就能去掉三种情况;
        if (root1 == null && root2 == null) {
            return true;
        }

        if (root1 == null || root2 == null) {
            return false;
        }

        if (root1.val != root2.val) {
            return false;
        }

        // left -> right,  right -> left. 肯定可以保证节点的个数是相等的，如果是对称的话。
        return isSymmetrical(root1.left, root2.right)
                && isSymmetrical(root1.right, root2.left);
    }


    /*
     * 链接：https://www.nowcoder.com/questionTerminal/ff05d44dfdb04e1d83bdbdab320efbcb
     */

    boolean isSymmetrical2(TreeNode pRoot) {
        if (pRoot == null) {
            return true;
        }
        return comRoot(pRoot.left, pRoot.right);
    }

    private boolean comRoot(TreeNode left, TreeNode right) {
// TODO Auto-generated method stub
        if (left == null) return right == null;
        if (right == null) return false;
        if (left.val != right.val) return false;
        return comRoot(left.right, right.left) && comRoot(left.left, right.right);
    }


    /*
     * C++ 迭代版本的实现
       链接：https://www.nowcoder.com/questionTerminal/ff05d44dfdb04e1d83bdbdab320efbcb
     */


}
