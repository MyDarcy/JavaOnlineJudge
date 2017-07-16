package ctci5th.chapter8.section4;

/**
 * Author by darcy
 * Date on 17-7-16 下午7:59.
 * Description:
 * 同样一题,但是节点中没有指向父节点的指针.
 */
public class P847_FirstParentOfTwoNodeInTree3 {

    class TreeNode {
        int val = 0;
        TreeNode left = null;
        TreeNode right = null;

        public TreeNode(int val) {
            this.val = val;
        }
    }

    class Result {
        TreeNode node;
        boolean isAncestor;

        public Result(TreeNode node, boolean isAncestor) {
            this.node = node;
            this.isAncestor = isAncestor;
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

        Result result = commonAncestorHelper(root, p, q);
        if (result.isAncestor) {
            return result.node;
        }
        return null;
    }


    /**
     * 递归访问整棵树,
     * 如果root的子树含有p而不是q,返回p;
     * 如果root的子树含有q而不是p,返回q;
     * 如果p,q都不在子树中,返回null.
     * 否则,返回p,q的共同祖先.
     *
     * 不太懂这个.
     * @param root
     * @param p
     * @param q
     * @return
     */
    private Result commonAncestorHelper(TreeNode root, TreeNode p, TreeNode q) {
        if (root == null) {
            return new Result(null, false);
        }


        if (root == p || root == q) {
            return new Result(root, true);
        }

        Result resultLeft = commonAncestorHelper(root.left, p, q);
        if (resultLeft.isAncestor) {
            return resultLeft;
        }

        Result resultRight = commonAncestorHelper(root.right, p, q);
        if (resultRight.isAncestor) {
            return resultRight;
        }

        if (resultLeft.node != null && resultRight.node != null) {
            return new Result(root, true);
        } else if (root == p || root == q) {
            // 当前位于p或者q,并且发现其中一个节点位于子树上.
            boolean isAncestor = resultLeft.node != null || resultRight.node != null ? true : false;
            return new Result(root, isAncestor);
        } else {
            return new Result(resultLeft.node != null ? resultLeft.node : resultRight.node, false);
        }
    }


}
