package jianzhioffer;

import java.util.ArrayList;

/**
 * Created by darcy
 * 2017/4/23--1:01
 * Description:
 * 输入一颗二叉树和一个整数，打印出二叉树中结点值的和为输入整数的所有路径。
 * 路径定义为从树的根结点开始往下一直到叶结点所经过的结点形成一条路径。
 */
public class P25_SumFromRootToLeaf {

    public class TreeNode {
        int val = 0;
        TreeNode left = null;
        TreeNode right = null;

        public TreeNode(int val) {
            this.val = val;

        }

    }

    /*
       思路：维护一个全局变量，同时进入下一层递归的时候，
       需要新建一个ArrayList，否则更新后的list对后面的递归有影响。
     */
    private ArrayList<ArrayList<Integer>> lists = new ArrayList<>();

    public ArrayList<ArrayList<Integer>> FindPath(TreeNode root, int target) {

        //
        if (root == null) {
            return lists;
        }

        ArrayList<Integer> list = new ArrayList<>();

        FindPath(root, list, 0, target);

        return lists;
    }

    private void FindPath(TreeNode root, ArrayList<Integer> list, int current, int target) {

        if (root != null && root.left == null && root.right == null) {
            if (current + root.val == target) {
                ArrayList<Integer> result = new ArrayList<>(list);
                result.add(root.val);
                lists.add(result);
            }
        }

        // 每次进入下一轮都创建一个新的lsit，否则list就会不断的增大；
        ArrayList<Integer> temp = new ArrayList<>(list);
        temp.add(root.val);
        if (root != null && root.left != null) {
            FindPath(root.left, temp, current + root.val, target);
        }

        // 因为上面进入下一层递归不会修改temp，所以这里可以复用。
        if (root != null && root.right != null) {
            FindPath(root.right, temp, current + root.val, target);
        }

    }


    /*  链接：https://www.nowcoder.com/questionTerminal/b736e784e3e34731af99065031301bca
    来源：牛客网
    */

    /*
    private ArrayList<ArrayList<Integer>> listAll = new ArrayList<ArrayList<Integer>>();
    private ArrayList<Integer> list = new ArrayList<Integer>();

    public ArrayList<ArrayList<Integer>> FindPath(TreeNode root, int target) {
        if (root == null) return listAll;
        list.add(root.val);
        target -= root.val;
        if (target == 0 && root.left == null && root.right == null)
            listAll.add(new ArrayList<Integer>(list));
        FindPath(root.left, target);
        FindPath(root.right, target);
        list.remove(list.size() - 1);
        return listAll;
    }*/


}

/*
//非递归版本
//思路：
1.按先序遍历把当前节点cur的左孩子依次入栈同时保存当前节点，每次更新当前路径的和sum；
2.判断当前节点是否是叶子节点以及sum是否等于expectNumber，如果是，把当前路径放入结果中。
3.遇到叶子节点cur更新为NULL，此时看栈顶元素，如果栈顶元素的把栈顶元素保存在last变量中，同时弹出栈顶元素，当期路径中栈顶元素弹出，sum减掉栈顶元素，这一步骤不更改cur的值；
4.如果步骤3中的栈顶元素的右孩子存在且右孩子之前没有遍历过，当前节点cur更新为栈顶的右孩子，此时改变cur=NULL的情况。
             
    #include<iostream>
    #include<vector>
 
    using namespace std;
 
    struct TreeNode

    {
    int val;
    TreeNode * left;
    TreeNode * right;
    TreeNode( int x) :val(x), left(NULL), right(NULL) {
    }
    }
 

    vector<vector<int>> FindPath(TreeNode *root, int expectNumber) {
    vector<vector<int>> res;   
    if (root == NULL)
        return res;
    stack<TreeNode *>s;
    s.push(root);
    int sum = 0; //当前和
    vector<int> curPath; //当前路径
    TreeNode * cur = root; //当前节点
    TreeNode * last = NULL; //保存上一个节点
    while (!s.empty()) {
        if (cur == NULL) {
            TreeNode * temp = s.top();
            if (temp -> right != NULL && temp -> right != last) {
                cur = temp -> right; //转向未遍历过的右子树
            } else {
                last = temp; //保存上一个已遍历的节点
                s.pop();
                curPath.pop_back(); //从当前路径删除
                sum -= temp -> val;
            } }
        else{
            s.push(cur);
            sum += cur -> val;
            curPath.push_back(cur -> val);
            if (cur -> left == NULL && cur -> right == NULL && sum == expectNum) {
                res.push_back(curPath);
            }
            cur = cur -> left; //先序遍历，左子树先于右子树
        }
    }
    return res;
    }

 */