package jianzhioffer;

import java.util.Stack;

/**
 * Created by darcy
 * 2017/5/29--10:21
 * Description:
 * 给定一颗二叉搜索树，请找出其中的第k大的结点。
 * 例如， 5 / \ 3 7 /\ /\ 2 4 6 8 中，按结点数值大小顺序第三个结点的值为4。
 */
public class P63_TheKNodeInBST {

    public class TreeNode {
        int val = 0;
        TreeNode left = null;
        TreeNode right = null;

        public TreeNode(int val) {
            this.val = val;

        }

    }

    /**
     * 中序遍历的思想, k--;
     *
     * @param pRoot
     * @param k
     * @return
     */
    private int current = 0;

    TreeNode KthNode(TreeNode pRoot, int k) {

        if (pRoot == null || k == 0) {
            return null;
        }

        return solution(pRoot, k);

    }

    private TreeNode solution(TreeNode pRoot, int k) {
        TreeNode node = null;
        if (pRoot.left != null) {
            node = KthNode(pRoot.left, k);
        }

        // 当前节点的左子树为null, 那么处理当前节点。
        if (node == null) {
            current++;
            if (current == k) {
                node = pRoot;
            }
        }

        if (node == null && pRoot.right != null) {
            node = KthNode(pRoot.right, k);
        }
        return node;
    }


    /*链接：https://www.nowcoder.com/questionTerminal/ef068f602dde4d28aab2b210e859150a
    来源：牛客网*/

    int index = 0; //计数器

    TreeNode KthNode3(TreeNode root, int k) {
        if (root != null) { //中序遍历寻找第k个
            TreeNode node = KthNode(root.left, k);
            if (node != null)
                return node;
            index++;
            if (index == k)
                return root;
            node = KthNode(root.right, k);
            if (node != null)
                return node;
        }
        return null;
    }

    /*
     *链接：https://www.nowcoder.com/questionTerminal/ef068f602dde4d28aab2b210e859150a
     */

    //中序非递归
    int count = 0;

    TreeNode KthNode2(TreeNode pRoot, int k) {
        if (count > k || pRoot == null)
            return null;
        TreeNode p = pRoot;
        Stack<TreeNode> LDRStack = new Stack<TreeNode>();
        TreeNode kthNode = null;
        while (p != null || !LDRStack.isEmpty()) {
            while (p != null) {
                LDRStack.push(p);
                p = p.left;
            }
            TreeNode node = LDRStack.pop();
            System.out.print(node.val + ",");
            count++;
            if (count == k) {
                kthNode = node;
            }
            p = node.right;
        }
        return kthNode;
    }

    //    private int current = 0;
   /* private TreeNode kthNode(TreeNode pRoot, int k) {
        if (pRoot.left != null) {
            KthNode(pRoot.left, k);
        }
        current++;
        if (current == k) {
            return pRoot;
        }
        if (pRoot.right != null) {
            KthNode(pRoot.right, k);
        }
        return null;
    }*/
}
