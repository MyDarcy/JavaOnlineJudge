package jianzhioffer;

import java.util.Arrays;

/**
 * Created by darcy
 * 2017/3/26--23:46
 * Description:
 * 输入某二叉树的前序遍历和中序遍历的结果，
 * 请重建出该二叉树。假设输入的前序遍历和中序遍历的结果中都不含重复的数字。
 * 例如输入前序遍历序列{1,2,4,7,3,5,6,8}和中序遍历序列{4,7,2,1,5,3,8,6}，
 * 则重建二叉树并返回。
 */


class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;

    TreeNode(int val) {
        this.val = val;
    }
}

public class P6_RebuildBinaryTree {

    public TreeNode reConstructBinaryTree(int[] pre, int[] in) {
        if (pre == null || pre.length == 0) {
            return null;
        }

        return reConstructBinaryTree(pre, 0, pre.length - 1, in, 0, in.length - 1);



        /*int rootValue = pre[0];
        int rootIndex = 0;
        for (int i = 0; i < in.length; i++) {
            if (in[i] == rootValue) {
                rootIndex = i;
                break;
            }
        }

        TreeNode root = new TreeNode(rootValue);

//        root = reConstructBinaryTree(root, in, 0, rootIndex - 1, in, rootIndex + 1, in.length - 1);
        root = reConstructBinaryTree(root, pre, )
        return root;*/
    }

    private TreeNode reConstructBinaryTree(int[] pre, int preFrom, int preTo,
                                           int[] in, int inFrom, int inTo) {
        if (preFrom > preTo || inFrom > inTo) {
            return null;
        }

        int rootValue = pre[preFrom];
        TreeNode root = new TreeNode(rootValue);

        int indexOfRootValue = inFrom;
        for (; indexOfRootValue <= inTo; indexOfRootValue++) {
            if (in[indexOfRootValue] == rootValue) {
                break;
            }
        }

        // 没有左节点了;
        /*if (indexOfRootValue == inFrom) {
            return root;
        }*/

        int leftTreeNodeNumber = indexOfRootValue - inFrom;

        // 什么时候有左左子树;
//        if (leftTreeNodeNumber > 0 && indexOfRootValue > inFrom) {
            root.left = reConstructBinaryTree(pre, preFrom + 1, preFrom + leftTreeNodeNumber,
                    in, inFrom, indexOfRootValue - 1);
//        }


        // 什么时候有右子树；
//        if (preFrom + leftTreeNodeNumber + 1 <= inTo && indexOfRootValue + 1 <= inTo) {
            root.right = reConstructBinaryTree(pre, preFrom + leftTreeNodeNumber + 1, preTo,//inTo
                    in, indexOfRootValue + 1, inTo); //inTo
//        }

        return root;
    }


//    链接：https://www.nowcoder.com/questionTerminal/8a19cbe657394eeaac2f6ea9b0f6fcf6

    //前序遍历{1,2,4,7,3,5,6,8}和中序遍历序列{4,7,2,1,5,3,8,6} 
    private static TreeNode reConstructBinaryTree2(int[] pre, int startPre, int endPre, int[] in, int startIn, int endIn) {
        if (startPre > endPre || startIn > endIn)
            return null;
        TreeNode root = new TreeNode(pre[startPre]);

        for (int i = startIn; i <= endIn; i++)
            // 每一轮都从中间遍历中找节点;
            if (in[i] == pre[startPre]) {
                root.left = reConstructBinaryTree2(pre, startPre + 1, startPre + i - startIn,
                        in, startIn, i - 1);
                root.right = reConstructBinaryTree2(pre, i - startIn + startPre + 1, endPre,
                        in, i + 1, endIn);
            }
        return root;
    }

//    链接：https://www.nowcoder.com/questionTerminal/8a19cbe657394eeaac2f6ea9b0f6fcf6

    public TreeNode reConstructBinaryTree3(int [] pre,int [] in) {
        if(pre.length == 0||in.length == 0){
            return null;
        }
        TreeNode node = new TreeNode(pre[0]);
        for(int i = 0; i < in.length; i++){
            if(pre[0] == in[i]){
                node.left = reConstructBinaryTree(Arrays.copyOfRange(pre,1,i+1), Arrays.copyOfRange(in, 0, i));
                node.right = reConstructBinaryTree(Arrays.copyOfRange(pre, i+1, pre.length), Arrays.copyOfRange(in, i+1,in.length));
            }
        }
        return node;
    }



    public static void main(String[] args) {

    }
}