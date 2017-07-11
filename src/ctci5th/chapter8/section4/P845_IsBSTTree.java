package ctci5th.chapter8.section4;

/**
 * Author by darcy
 * Date on 17-7-10 下午4:07.
 * Description:
 * 给定一棵二叉查找树是否是BST树.
 */
public class P845_IsBSTTree {
    class TreeNode {
        int val = 0;
        TreeNode left = null;
        TreeNode right = null;
        public TreeNode(int val) {
            this.val = val;
        }
    }

    /**
     * 中序遍历的思路将所有的节点复制到数组中.
     * @param root
     * @return
     */
    public boolean checkBST(TreeNode root) {
        // write code here
        int treeSize = getTreeSize(root);
        int[] array = new int[treeSize];
        copyBST2Array(root, array);
        for (int i = 1; i < treeSize; i++) {
            if (array[i] <= array[i - 1]) {
                return false;
            }
        }
        return true;
    }

    private int index = 0;
    private void copyBST2Array(TreeNode root, int[] array) {
        if (root == null) {
            return;
        }
        copyBST2Array(root, array);
        array[index] = root.val;
        index++;
        copyBST2Array(root, array);
    }

    private int getTreeSize(TreeNode root) {
        if (root == null) {
            return 0;
        }

        int left = getTreeSize(root.left);
        int right = getTreeSize(root.right);
        return left + right + 1;
    }

    /**
     * 不需要单独维护一个数组,而是记录上一次访问的值,进行比较.
     *
     * @param root
     * @return
     */
    private int lastVisit = Integer.MIN_VALUE;
    public boolean checkBST2(TreeNode root) {
        // write code here
        if (root == null) {
            return true;
        }

        if (!checkBST2(root.left)) {
            return false;
        }
        if (root.val <= lastVisit) {
            return false;
        }

        lastVisit = root.val;

        if (!checkBST2(root.right)) {
            return false;
        }

        return true;
    }

    /**
     * 二叉树应该满足: left <= root < right;
     * 可以根据二叉查找树的定义来判断，二叉树的定义，所有左子树的节点小于根节点，
     * 所有右子树的节点大于根节点，并且左右子树也是二叉查找树。所以在递归的过程中，
     * 我们只需要传递两个参数（当前根节点对应的二叉树的所有节点的最大值和最小值），
     * 同时不断的更新这两个参数，如果当前节点的值不在这两个数范围中，则直接返回false，否则接着递归便可。
     * @param root
     * @return
     */
    public boolean checkBST3(TreeNode root) {
        return checkBST3(root, Integer.MIN_VALUE, Integer.MAX_VALUE);
    }

    private boolean checkBST3(TreeNode root, int minValue, int maxValue) {
        if (root == null) {
            return true;
        }
        if (root.val < minValue || root.val >= maxValue) {
            return false;
        }
        if (!checkBST3(root.left, minValue, root.val)
                || !checkBST3(root.right, root.val, maxValue)) {
            return false;
        }
        return true;
    }

}
