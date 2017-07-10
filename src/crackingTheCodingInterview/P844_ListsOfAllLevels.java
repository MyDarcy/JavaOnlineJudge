package crackingTheCodingInterview;

import java.util.ArrayList;
import java.util.LinkedList;

/**
 * Author by darcy
 * Date on 17-7-10 下午3:42.
 * Description:
 * 创建某一深度上包含所有节点的链表
 */
public class P844_ListsOfAllLevels {
    class TreeNode {
        int val = 0;
        TreeNode left = null;
        TreeNode right = null;

        public TreeNode(int val) {
            this.val = val;
        }
    }

    /**
     * 前序遍历的思路
     * @param root
     * @return
     */
    ArrayList<LinkedList<TreeNode>> createLeveledList(TreeNode root) {
        ArrayList<LinkedList<TreeNode>> lists = new ArrayList<>();
        createLeveledList(root, lists, 0);
        return lists;
    }

    private void createLeveledList(TreeNode root, ArrayList<LinkedList<TreeNode>> lists, int level) {
        if (root == null) {
            return;
        }
        LinkedList<TreeNode> list = null;
        if (lists.size() == level) {
            // 因为是改造的先序遍历,所以size == level的实时,是第一次访问该level.
            list = new LinkedList<>();
        } else {
            list = lists.get(level);
        }
        list.add(root);
        createLeveledList(root, lists, level + 1);
        createLeveledList(root, lists, level + 1);
    }

    /**
     * 层次化的遍历.
     * @param root
     * @return
     */
    ArrayList<LinkedList<TreeNode>> createLeveledList2(TreeNode root) {
        ArrayList<LinkedList<TreeNode>> lists = new ArrayList<>();
        LinkedList<TreeNode> current = new LinkedList<>();
        if (root != null) {
            current.add(root);
        }
        LinkedList<TreeNode> parent = null;

        while (!current.isEmpty()) {
            lists.add(current);
            parent = current;
            current = new LinkedList<>();
            for (TreeNode node : parent) {
                if (node.left != null) {
                    current.add(node.left);
                }
                if (node.right != null) {
                    current.add(node.right);
                }
            }
        }
        return lists;
    }

}
