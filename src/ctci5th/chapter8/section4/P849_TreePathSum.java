package ctci5th.chapter8.section4;

import sun.reflect.generics.tree.Tree;

/**
 * Author by darcy
 * Date on 17-7-16 下午9:31.
 * Description:
 * 打印节点数值总和等于某个给定值的所有路径.
 * 而且路径不一定要从根节点开始或者到达叶节点结束.
 */
public class P849_TreePathSum {

    class TreeNode {
        int val = 0;
        TreeNode left = null;
        TreeNode right = null;

        public TreeNode(int val) {
            this.val = val;
        }
    }


    /**
     * 不再要求从这个这个节点开始是否有总和为给定值的路径;
     *  而是关注于这个节点是否是总和为给定节点值的某条路径的某端节点.
     * @param node
     * @param sum
     */
    public void findSum(TreeNode node, int sum) {
        int depth = depth(node);
        int[] path = new int[depth];
        findSum(node, sum, path, 0);
    }

    public void findSum(TreeNode node, int sum, int[] path, int levle) {
        if (node == null) {
            return;
        }
        // 当前节点插入路径
        // 当前层的任意节点都可以设置.
        path[levle] = node.val;

        int t = 0;
        for (int i = levle; i >= 0; i--) {
            t += path[i];
            if (t == sum) {
                print(path, i, levle);
            }
        }

        // 从后继节点中查找.
        findSum(node.left, sum, path, levle + 1);
        findSum(node.right, sum, path, levle + 1);

        // 从路径中移除这个节点.
        // 忽略即可.
        path[levle] = Integer.MIN_VALUE;
    }

    private void print(int[] path, int start, int end) {
        for (int i = start; i <= end; i++) {
            System.out.print(path[i] + ' ');
        }
        System.out.println();
    }

    public int depth(TreeNode root) {
        if (root == null) {
            return 0;
        }
        return 1 + Math.min(depth(root.left), depth(root.right));
    }

}
