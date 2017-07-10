package crackingTheCodingInterview;

/**
 * Author by darcy
 * Date on 17-7-10 下午4:54.
 * Description:
 * 二叉查找树中指定节点的后继节点.
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

    /**
     * 在二叉搜索树中找最小的大于某个key值的节点
     * @param root
     * @param key
     * @return
     */
    // 迭代实现
    TreeNode  FindCeiling(TreeNode root, int key)
    {
        TreeNode  ceiling = null;
        TreeNode  current = root;
        while(current != null)
        {
            if(current.val <= key)
                current = current.right;
            else
            {
                ceiling = current;
                current = current.left;
            }
        }
        return ceiling;
    }

// 递归实现
    TreeNode FindCeiling2(TreeNode root, int key)
    {
        if(root == null)
            return null;
        if(root.val <= key)
            return FindCeiling2(root.right, key);
        else
        {
            TreeNode ceiling = FindCeiling(root.left, key);
            return ceiling != null ? ceiling : root;
        }
    }


}
