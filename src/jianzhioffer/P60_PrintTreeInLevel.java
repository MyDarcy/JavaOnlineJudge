package jianzhioffer;

import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedList;

/**
 * Created by darcy
 * 2017/5/28--0:24
 * Description:
 * 从上到下按层打印二叉树，同一层结点从左至右输出。每一层输出一行。
 */
public class P60_PrintTreeInLevel {

    public class TreeNode {
        int val = 0;
        TreeNode left = null;
        TreeNode right = null;

        public TreeNode(int val) {
            this.val = val;

        }

    }

    /**
     * 因为是整数，所以以-1作为结束节点并不具有通用性。
     * @param pRoot
     * @return
     */
    ArrayList<ArrayList<Integer>> Print(TreeNode pRoot) {
        if (pRoot == null) {
            return new ArrayList<>();
        }

        ArrayList<ArrayList<Integer>> result = new ArrayList<>();

        Deque<TreeNode> queue1 = new LinkedList<>();


        queue1.addLast(pRoot);

        while (!queue1.isEmpty()) {
            ArrayList<Integer> tempList = new ArrayList<>();
            Deque<TreeNode> queue2 = new LinkedList<>();
            for (TreeNode node : queue1) {
                tempList.add(node.val);
                if (node.left != null) {
                    queue2.addLast(node.left);
                }
                if (node.right != null) {
                    queue2.addLast(node.right);
                }
            }
            result.add(tempList);
            // may lead to GC.
            queue1 = null;
            queue1 = queue2;
        }

        return result;

    }


    /**
     * 维护两个变量分别记录当前层次还要打印的数目，一个记录下一层次的节点的数目。
     * @param pRoot
     * @return
     */
    ArrayList<ArrayList<Integer>> Print3(TreeNode pRoot) {
        if (pRoot == null) {
            return new ArrayList<>();
        }

        ArrayList<ArrayList<Integer>> result = new ArrayList<>();

        Deque<TreeNode> queue1 = new LinkedList<>();
        queue1.addLast(pRoot);
        int toBePrinted = 1;
        int nextLevelNumber = 0;
        ArrayList<Integer> list = new ArrayList<>();

        while (!queue1.isEmpty()) {
            TreeNode node = queue1.removeFirst();
            list.add(node.val);
            if (node.left != null) {
                queue1.addLast(node.left);
                nextLevelNumber++;
            }

            if (node.right != null) {
                queue1.addLast(node.right);
                nextLevelNumber++;
            }
            toBePrinted--;
            if (toBePrinted == 0) {
                toBePrinted = nextLevelNumber;
                nextLevelNumber = 0;
                result.add(list);
                list = new ArrayList<>();
            }
        }

        return result;
    }

    /**
     * 这种方式报 java.util.ConcurrentModificationException
     * @param pRoot
     * @return
     */
    ArrayList<ArrayList<Integer>> Print2(TreeNode pRoot) {
        if (pRoot == null) {
            return new ArrayList<>();
        }

        ArrayList<ArrayList<Integer>> result = new ArrayList<>();

        Deque<TreeNode> queue1 = new LinkedList<>();
        Deque<TreeNode> queue2 = new LinkedList<>();


        queue1.addLast(pRoot);

        while (!queue1.isEmpty()) {
            ArrayList<Integer> tempList = new ArrayList<>();
            for (TreeNode node : queue1) {
                tempList.add(node.val);
                if (node.left != null) {
                    queue2.addLast(node.left);
                }
                if (node.right != null) {
                    queue2.addLast(node.right);
                }
            }
            result.add(tempList);
            queue1.clear();
            queue1 = queue2;
            queue2 = queue1;
        }

        return result;

    }
}
