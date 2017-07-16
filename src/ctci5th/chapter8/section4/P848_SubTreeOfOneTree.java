package ctci5th.chapter8.section4;

import com.sun.org.apache.bcel.internal.generic.I2F;

/**
 * Author by darcy
 * Date on 17-7-16 下午8:56.
 * Description:
 * T1是一棵非常大的树,T2是比较小的树,判断T2是否是T1的子树.
 */
public class P848_SubTreeOfOneTree {

    class TreeNode {
        int val = 0;
        TreeNode left = null;
        TreeNode right = null;

        public TreeNode(int val) {
            this.val = val;
        }
    }

    /**
     * 解法1: 创建字符串表示前序遍历结果, 创建字符串表示中序遍历的结果;
     * 分别是子字符串则满足要求. 但是要标记出叶子节点.
     * @param t1
     * @param t2
     * @return
     */
    boolean subTree2(TreeNode t1, TreeNode t2) {

        return false;
    }

    /**
     * 思路: 每当t2的根节点出现在在t1中时,就开始进行匹配.事件复杂度O(n + km) -- n是t1节点的个数.
     * @param t1
     * @param t2
     * @return
     */
    boolean containsTree(TreeNode t1, TreeNode t2) {
        if (t2 == null) {
            return true;
        }

        return subTree(t1, t2);
    }

    private boolean subTree(TreeNode t1, TreeNode t2) {
        // big tree is empty. 此时还没有找到子树.
        if (t1 == null) {
            return false;
        }

        if (t1.val == t2.val) {
            if (matchTree(t1, t2)) {
                return true;
            }
        }

        return subTree(t1.left, t2) || subTree(t1.right, t2);
    }

    private boolean matchTree(TreeNode t1, TreeNode t2) {
        // 两棵树都没有节点了, 返回true.
        if (t1 == null && t2 == null) {
            return true;
        }

        // 只是其中一棵树为null, 但是根据上条,又不同时为null, 返回false.
        if (t1 == null || t2 == null) {
            return false;
        }

        if (t1.val != t2.val) {
            return false;
        }

        return matchTree(t1.left, t2.left)
                && matchTree(t1.right, t2.right);

    }
}
