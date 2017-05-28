package jianzhioffer;

import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedList;

/**
 * Created by darcy
 * 2017/5/28--10:29
 * Description:
 * 请实现一个函数按照之字形打印二叉树，即第一行按照从左到右的顺序打印，
 * 第二层按照从右至左的顺序打印，第三行按照从左到右的顺序打印，其他行以此类推。
 */
public class P61_PrintTreeInZStryle {

    public class TreeNode {
        int val = 0;
        TreeNode left = null;
        TreeNode right = null;

        public TreeNode(int val) {
            this.val = val;

        }

    }

    /**
     * 思路: 入的顺序是从左到右，而出的输出顺序则是从右到左(本层和下层顺序相反，
     * 这里下层的入的顺序和上层相同，当时出的顺序由于是之字则相反)，刚好相反。
     * 反过来也一样，其实就是一种栈的结构。
     * @param pRoot
     * @return
     */
    public ArrayList<ArrayList<Integer>> Print(TreeNode pRoot) {
        if (pRoot == null) {
            return new ArrayList<>();
        }
        ArrayList<ArrayList<Integer>> result = new ArrayList<>();
        Deque<TreeNode> stack1 = new LinkedList<>();
        Deque<TreeNode> stack2 = new LinkedList<>();
        Deque<TreeNode> temp = null;
        stack1.add(pRoot);
        // 本层尚需打印的个数。
        int toBePrint = 1;
        // 下层的节点个数。
        int nextLevel = 0;
        // true代表下层节点是从左到右的加入到队列中。
        boolean direction = true;
        ArrayList<Integer> list = new ArrayList<>();

        while (!stack1.isEmpty()) {
            TreeNode node = stack1.removeLast();
            list.add(node.val);
            toBePrint--;
            if (direction) {
                if (node.left != null) {
                    stack2.addLast(node.left);
                    nextLevel++;
                }

                if (node.right != null) {
                    stack2.addLast(node.right);
                    nextLevel++;
                }
            } else {
                if (node.right != null) {
                    stack2.addLast(node.right);
                    nextLevel++;
                }

                if (node.left != null) {
                    stack2.addLast(node.left);
                    nextLevel++;
                }
            }

            if (toBePrint == 0) {
                toBePrint = nextLevel;
                nextLevel = 0;
                result.add(list);
                list = new ArrayList<>();
                temp = stack1;
                stack1 = stack2;
                stack2 = temp;
                direction = !direction;
            }

        }
        return result;
    }

    /**
     *书上的做法, 两个index的想法不错。
     * @param pRoot
     * @return
     */
    public ArrayList<ArrayList<Integer>> PrintInBook(TreeNode pRoot) {
        if (pRoot == null) {
            return new ArrayList<>();
        }

        Deque<TreeNode>[] stacks = new LinkedList[2];
        for (int i = 0; i < 2; i++) {
            stacks[i] = new LinkedList<>();
        }

        ArrayList<ArrayList<Integer>> result = new ArrayList<>();
        ArrayList<Integer> list = new ArrayList<>();

        int current = 0;
        int next = 1;
        stacks[current].addLast(pRoot);
        while (!stacks[0].isEmpty() || !stacks[1].isEmpty()) {
            TreeNode node = stacks[current].removeLast();
            list.add(node.val);
            if (current == 0) {
                if (node.left != null) {
                    stacks[next].addLast(node.left);
                }
                if (node.right != null) {
                    stacks[next].addLast(node.right);
                }
            } else {
                if (node.right != null) {
                    stacks[next].addLast(node.right);
                }
                if (node.left != null) {
                    stacks[next].addLast(node.left);
                }
            }
            if (stacks[current].isEmpty()) {
                current = 1 - current;
                next = 1 - next;
                result.add(list);
                list = new ArrayList<>();
            }
        }
        return result;
    }
}
