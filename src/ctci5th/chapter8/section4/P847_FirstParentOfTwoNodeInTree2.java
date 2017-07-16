package ctci5th.chapter8.section4;

/**
 * Author by darcy
 * Date on 17-7-16 下午7:59.
 * Description:
 * 同样一题,但是节点中没有指向父节点的指针.
 */
public class P847_FirstParentOfTwoNodeInTree2 {

    class TreeNode {
        int val = 0;
        TreeNode left = null;
        TreeNode right = null;

        public TreeNode(int val) {
            this.val = val;
        }
    }

    /**
     * 如果p和q都在节点的左边,那么在左子树中查找共同祖先.
     * 如果p, q都在节点的右边,那么在右子树中查找共同的祖先.
     * 如果p和q不在同一边,那么表示已经找到了第一个共同的祖先.
     * @param root
     * @param p
     * @param q
     * @return
     */
    public TreeNode commonAncestor(TreeNode root,
                                   TreeNode p,
                                   TreeNode q) {
        if (!covers(root, p) || !covers(root, q)) {
            return null;
        }
        return commonAncestorHelper(root, p, q);
    }

    /**
     * 检测树中是否存在节点p;
     * @param root
     * @param p
     * @return
     */
    private boolean covers(TreeNode root, TreeNode p) {
        // 已经遍历到叶子节点的左右null节点,没有找到了.
        if (root == null) {
            return false;
        }
        // 遍历到某个节点就是p节点.
        if (root == p) {
            return true;
        }
        return covers(root.left, p) || covers(root.right, p);
    }

    private TreeNode commonAncestorHelper(TreeNode root, TreeNode p, TreeNode q) {
        if (root == null) {
            return null;
        }

        // 因为能运行commonAncestorHelper表示root中一定能找到p或者q节点.
        // 如果root指向p,或者q, 同一边,先到其中一个节点,那么就是共同的父节点了.
        if (root == p || root == q) {
            return root;
        }

        boolean pOnLeft = covers(root.left, p);
        boolean qOnLeft = covers(root.left, q);

        // 不在同一边.
        if (pOnLeft != qOnLeft) {
            return root;
        }

        // 在同一边.
        TreeNode childSide = pOnLeft ? root.left : root.right;
        return commonAncestorHelper(childSide, p, q);
    }


}
