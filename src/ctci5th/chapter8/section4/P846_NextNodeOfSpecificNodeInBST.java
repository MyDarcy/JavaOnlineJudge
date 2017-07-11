package ctci5th.chapter8.section4;

import java.util.Stack;

/**
 * Author by darcy
 * Date on 17-7-10 下午4:54.
 * Description:
 * <p>
 * 请设计一个算法，寻找**二叉树**中指定结点的下一个结点（即中序遍历的后继）。
 * 给定树的根结点指针TreeNode* root和结点的值int p，请返回值为p的结点的后继结点的值。
 * 保证结点的值大于等于零小于等于100000且没有重复值，若不存在后继返回-1。
 * <p>
 * <ctci>一书中二叉查找树中指定节点的后继节点, 表示每个节点都有指向父节点的指针.
 */
public class P846_NextNodeOfSpecificNodeInBST {

    class TreeNode {
        int val = 0;
        TreeNode left = null;
        TreeNode right = null;

        public TreeNode(int val) {
            this.val = val;
        }
    }

    class TreeNodeWithParent {
        int val = 0;
        TreeNodeWithParent left = null;
        TreeNodeWithParent right = null;
        TreeNodeWithParent parent = null;

        public TreeNodeWithParent(int val) {
            this.val = val;
        }
    }

    /**
     * 特定节点的后继.
     * 思路: 左子树->target->右子树
     * 如果有右子树的话,那么target的后继就是其右子树的最左子节点.
     * 没有右子树, 那么target的子树已经遍历完毕了,需要回退到父节点parent:
     * 1. 如果target是parent的左孩子,那么target的后继就是其父节点.
     * 2. 如果target是parent右孩子,那么需要往上找,直到某个节点是其父节点的左子树(不是其父节点的右子树).
     * 那么该节点就是后继节点.(考虑退化的只有右节点的子树.) 因为右节点在中序中最后被访问的, 其父节点先于它被访问.
     * 3. 如果往上遍历都没有发现左节点,那么target已经是右子数的最右边了,那么其后继就是null了.
     *
     * @param target
     * @return
     */
    public TreeNodeWithParent inOrderSuccessor(TreeNodeWithParent target) {
        if (target == null) {
            return null;
        }
        if (target.right != null) {
            return mostLeftNodeWithParent(target.right);
        } else {
            TreeNodeWithParent current = target;
            TreeNodeWithParent parent = current.parent;
            while (parent != null && parent.left != current) {
                current = parent;
                parent = parent.parent;
            }
            // 可能的情况是到了根节点,但是parent =null, 那么也是满足第三种情况的.
            return parent;
        }
    }

    private TreeNodeWithParent mostLeftNodeWithParent(TreeNodeWithParent root) {
        if (root == null) {
            return null;
        }
        while (root.left != null) {
            root = root.left;
        }
        return root;
    }

    private TreeNode pre = new TreeNode(-1);

    /**
     * nowcoder questions
     * 前一个节点，令此引用按照中序遍历的来移动，利用它来进行逆向移动 即原来只能是  根→左  现在可以  左→根
     *
     * @param root
     * @param p
     * @return
     */
    public int findSucc(TreeNode root, int p) {
        // write code here
        //空则表示没有
        if (root == null)
            return -1;

        //一路向左，到达最左边的叶子
        int left = findSucc(root.left, p);

        if (left == -1) {
            //最开始到达最左节点后，令pre指向该节点，同时root为pre的后继节点
            ////其后按照左根右的顺序移动

            //如果上一个节点中找到p，则该节点后其后继节点
            if (pre.val == p) {
                return root.val;
            }
            pre = root; //如果前一个节点不是，则pre指向root的对象，
            return findSucc(root.right, p);
        }

        //如果找不到则返回上一层的根节点，方向为 左→根
        return left;

    }

/*    链接：https://www.nowcoder.com/questionTerminal/60231d6931d543d4aadcb67851b21e4a
    来源：牛客网*/

    public int findSucc2(TreeNode root, int p) {
        boolean isFound = false;
        Stack<TreeNode> stack = new Stack<TreeNode>();
        TreeNode cur = root;
        while (cur != null || !stack.isEmpty()) {
            while (cur != null) {
                stack.add(cur);
                cur = cur.left;
            }
            if (!stack.isEmpty()) {
                TreeNode q = stack.pop();
                if (isFound) return q.val;
                else if (q.val == p) isFound = true;
                cur = q.right;
            }
        }
        return -1;
    }

    /**
     * 在二叉搜索树中找最小的大于某个key值的节点
     * 思路:
     * 当前节点的值 <= key, 那么只能去右子树中找.
     * 否则,当前节点比key要大,那么只能往左子树中取找.
     * tree: 20 30 25 , key:24
     *
     * @param root
     * @param key
     * @return
     */
    // 迭代实现
    TreeNode FindCeiling(TreeNode root, int key) {
        TreeNode ceiling = null;
        TreeNode current = root;
        while (current != null) {
            if (current.val <= key)
                current = current.right;
            else {
                ceiling = current;
                current = current.left;
            }
        }
        return ceiling;
    }

    /**
     * 递归实现
     *
     * @param root
     * @param key
     * @return
     */
    TreeNode FindCeiling2(TreeNode root, int key) {
        if (root == null)
            return null;
        if (root.val <= key)
            return FindCeiling2(root.right, key);
        else {
            TreeNode ceiling = FindCeiling2(root.left, key);
            // 到了目标25时, root.left == null, 所以这个时候ceiling == null, 那么返回的就是root, 就是
            // 当前的目标25节点.
            return ceiling != null ? ceiling : root;
        }
    }


}
