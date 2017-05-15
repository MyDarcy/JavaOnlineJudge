package jianzhioffer;

/**
 * Created by darcy
 * 2017/5/16--0:28
 * Description:
 */
public class P50_LastCommonParentNode<T> {

    class BinaryNode<T> {
        T element;
        BinaryNode<T> left;
        BinaryNode<T> right;

    }

    /**
     * 求解node1 和 node2 的最低公共父结点
     * @param node1
     * @param node2
     * @return 最低公共父结点
     */
    public BinaryNode<T> commonNode(BinaryNode<T> node1, BinaryNode<T> node2, BinaryNode<T> root){
        if(root == null)
            return null;
        if(node1.element == root.element || node2.element == root.element)
            return root;
        /*
         * 若 left==null, node1,node2 都不在 root.left子树中
         * 若right==null,node1,node2 都不在root.right子树中
         */
        BinaryNode<T> left = commonNode(node1, node2, root.left);
        BinaryNode<T> right = commonNode(node1, node2, root.right);

        if(left != null && right != null)
            return root;
        return left == null ? right : left;
    }
}
