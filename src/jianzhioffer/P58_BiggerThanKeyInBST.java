package jianzhioffer;

/**
 * Created by darcy
 * 2017/5/24--23:28
 * Description:
 * 在二叉搜索树中找最小的大于某个key值的节点

 如
     8
    / \
   6  12
  /   / \
 2   11 14

 key = 8 返回11
 key = 1 返回2
 key = 16 返回NULL
 */


public class P58_BiggerThanKeyInBST {
    static class TreeNode
    {
        int val;
        TreeNode left;
        TreeNode right;
    }

    // 迭代实现
    TreeNode FindCeiling(TreeNode root, int key)
    {
        TreeNode ceiling = null;
        TreeNode current = root;
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
            TreeNode ceiling = FindCeiling2(root.left, key);
            return ceiling != null ? ceiling : root;
        }
    }
}
