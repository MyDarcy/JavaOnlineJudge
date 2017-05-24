package jianzhioffer;

/**
 * Created by darcy
 * 2017/5/24--0:02
 * Description:
 * 给定一个二叉树和其中的一个结点，请找出中序遍历顺序的下一个结点并且返回。
 * 注意，树中的结点不仅包含左右子结点，同时包含指向父结点的指针。
 */
public class P58_FollowingNodeInBinaryTree {
    public class TreeLinkNode {
        int val;
        TreeLinkNode left = null;
        TreeLinkNode right = null;
        TreeLinkNode next = null;

        TreeLinkNode(int val) {
            this.val = val;
        }
    }

    /**
     * 思路：分析当前节点的位置;
     * 1. 有右子树，那么下一个节点就是右子树的最左子节点。
     * 2. 没有右子树，并且当前节点是父节点的左节点，那么父节点当前节点的下一个节点。
     * 3. 没有右子树，并且当前节点是父节点的右节点，那么一直沿着父节点往上找，直到父节点是其自己父节点的左子树，
     * 此时返回父节点自己的父节点，父节点为null, 则范围null。
     *
     * @param pNode
     * @return
     */
    public TreeLinkNode GetNext(TreeLinkNode pNode) {

        if (pNode == null) {
            return null;
        }

        if (pNode.right != null) {
            TreeLinkNode temp = pNode.right;
            while (temp.left != null) {
                temp = temp.left;
            }
            return temp;
        }

        // 右子树为null, 又是父节点的左节点。
        if (pNode.next != null && pNode.next.left == pNode) {
            return pNode.next;
        }

        // 右子树为null, 并且是父节点的右节点。
        if (pNode.next != null && pNode.next.right == pNode) {
            TreeLinkNode parent = pNode.next;
            while (parent != null) {
                if (parent.next != null && parent.next.left == parent) {
                    return parent.next;
                } else if (parent.next != null && parent.next.right == parent) {
                    parent = parent.next;
                } else {
                    return null;
                }
            }
        }

        // 父节点为null, 右节点为null,
        //
        return null;
    }

/*    链接：https://www.nowcoder.com/questionTerminal/9023a0c988684a53960365b889ceaf5e
    来源：牛客网*/

    TreeLinkNode GetNext2(TreeLinkNode node) {
        if (node == null) return null;
        if (node.right != null) { //如果有右子树，则找右子树的最左节点
            node = node.right;
            while (node.left != null) node = node.left;
            return node;
        }
        while (node.next != null) { //没右子树，则找第一个当前节点是父节点左孩子的节点
            if (node.next.left == node) return node.next;
            node = node.next;
        }
        return null;//退到了根节点仍没找到，则返回null
    }

}
