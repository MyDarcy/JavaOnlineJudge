package ctci5th.chapter8.section4;

/**
 * Author by darcy
 * Date on 17-7-11 下午10:32.
 * Description:
 * 找到二茶树中某个节点的第一个共同祖先.
 * 不能将额外的节点存储在另一个数据结构中.
 */
public class P847_FirstParentOfTwoNodeInTree {
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
     * 如果有共同指向父节点的指针,那么相当于是两个链表的相交问题.
     * @param root
     * @param p
     * @param q
     * @return
     */
    public TreeNodeWithParent commonAncestor(TreeNodeWithParent root,
                                             TreeNodeWithParent p,
                                             TreeNodeWithParent q) {
        int pLength = lengthFromNodeToRoot(root, p);
        int qLength = lengthFromNodeToRoot(root, q);

        if (pLength > qLength) {
            for (int i = 0; i < pLength - qLength; i++) {
                p = p.parent;
            }
        } else if (pLength < qLength) {
            for (int i = 0; i < qLength - pLength; i++) {
                q = q.parent;
            }
        }

        for (int i = 0; i < Math.min(pLength, qLength); i++) {
            p = p.parent;
        }
        return p;

    }

    private int lengthFromNodeToRoot(TreeNodeWithParent root, TreeNodeWithParent p) {
        
            
        int count = 1;
        TreeNodeWithParent temp = p;
        while (temp.parent != root) {
            count++;
            temp = temp.parent; 
        }
        return count;
        
    }
}
