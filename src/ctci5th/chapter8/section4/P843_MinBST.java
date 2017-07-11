package ctci5th.chapter8.section4;

/**
 * Author by darcy
 * Date on 17-7-10 下午3:14.
 * Description:
 * 对于一个元素各不相同且按升序排列的有序序列，请编写一个算法，创建一棵高度最小的二叉查找树。
 * 给定一个有序序列int[] vals,请返回创建的二叉查找树的高度。
 * <p>
 * 思路: 类似二分搜索的思路. 左右子树尽可能个数相同的时候构造的二叉查找树高低最低.
 */
public class P843_MinBST {
    class TreeNode {
        int val = 0;
        TreeNode left = null;
        TreeNode right = null;

        public TreeNode(int val) {
            this.val = val;
        }
    }

    public int buildMinimalBST(int[] vals) {
        // write code here
        if (vals == null) {
            return 0;
        }
        TreeNode root = buildMinimalBST(vals, 0, vals.length - 1);
        int height = height(root);
        return height;
    }

    private int height(TreeNode root) {
        if (root == null) {
            return 0;
        }
        int left = height(root.left);
        int right = height(root.right);
        return Math.max(left, right) + 1;
    }

    /**
     * 步骤:
     * 1. 中间元素插入到树中.
     * 2. 数组左半部分插入到左子树中.
     * 3. 数组右半部分插入到右子树中.
     * 4. 继续递归处理.
     *
     * @param vals
     * @param start
     * @param end
     * @return
     */
    private TreeNode buildMinimalBST(int[] vals, int start, int end) {
        if (end < start) {
            return null;
        }
        int mid = start + (end - start) / 2;
        TreeNode root = new TreeNode(vals[mid]);
        root.left = buildMinimalBST(vals, 0, mid - 1);
        root.right = buildMinimalBST(vals, mid + 1, end);
        return root;
    }

    /**
     * 链接：https://www.nowcoder.com/questionTerminal/01a12f94988649e39b554d95c45bfa6f
     * @param vals
     * @return
     */
    public int buildMinimalBST2(int[] vals) {
        if (vals == null || vals.length < 1)
            return 0;
        return buildMinimalBSTCore(vals, 0, vals.length - 1);
    }

    public int buildMinimalBSTCore(int[] vals, int start, int end) {

        if (end < start)
            return 0;
        int mid = (start + end) / 2;
        int leftH = 1 + buildMinimalBSTCore(vals, start, mid - 1);
        int rightH = 1 + buildMinimalBSTCore(vals, mid + 1, end);

        return Math.max(leftH, rightH);
    }
}
