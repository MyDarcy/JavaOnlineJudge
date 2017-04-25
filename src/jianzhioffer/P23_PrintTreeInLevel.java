package jianzhioffer;

import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedList;

/**
 * Created by darcy
 * 2017/4/20--0:34
 * Description:
 * 从上往下打印出二叉树的每个节点，同层节点从左至右打印。
 */
public class P23_PrintTreeInLevel {


    public class TreeNode {
        int val = 0;
        TreeNode left = null;
        TreeNode right = null;

        public TreeNode(int val) {
            this.val = val;

        }

    }

    public ArrayList<Integer> PrintFromTopToBottom(TreeNode root) {
        // 这里返回的是空的ArrayList, 一般也推荐这样，由上层去处理list中没有元素的情况；
        if (root == null) {
            return new ArrayList<Integer>();
        }

        ArrayList<Integer> list = new ArrayList<>();

        Deque<TreeNode> queue = new LinkedList<>();
        queue.addLast(root);
        while (!queue.isEmpty()) {
            TreeNode node = queue.pollFirst();
            list.add(node.val);
            if (node.left != null) {
                queue.addLast(node.left);
            }
            if (node.right != null) {
                queue.addLast(node.right);
            }
        }
        return list;

    }


}
