package ctci5th.chapter8.section4;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * Author by darcy
 * Date on 17-7-8 下午12:01.
 * Description:
 *
 * 树的前序中序后序遍历
 * from:
 * http://blog.csdn.net/yangfeisc/article/details/44497429
 */
public class P840_TreeTraversal {

    class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int x) {
            val = x;
        }
    }

    List<Integer> result = new ArrayList<Integer>();

    /**
     * 前序遍历: 迭代实现，维护一个栈，因为入栈顺序按照根右左进行入栈，因此首先将根出栈，然后出栈左子节点，
     * 最后出栈右子节点。
     * @param root
     * @return
     */
    public List<Integer> preorderTraversal(TreeNode root) {
        if(root == null)
            return result;
        Stack<TreeNode> stack = new Stack<TreeNode>();
        stack.push(root);
        while(!stack.isEmpty()) {
            TreeNode node = stack.pop();
            result.add(node.val);
            if(node.right != null)
                stack.push(node.right);
            if(node.left != null)
                stack.push(node.left);
        }
        return result;
    }

    /**
     * 中序遍历: 迭代实现，首先依次将左子节点全部加入栈中，所以第一个while循环后栈顶元素对应一个子树的最
     * 左子节点，然后将该元素出栈加入list，并判断该元素的遍历该节点的右子树。
     * @param root
     * @return
     */
    public List<Integer> inorderTraversal(TreeNode root) {
        if(root == null)
            return result;
        Stack<TreeNode> stack = new Stack<TreeNode>();
        do {
            //依次将左节点均加入栈中
            while(root != null) {
                stack.push(root);
                root = root.left;
            }
            if (!stack.isEmpty()) {
                root = stack.pop();
                result.add(root.val);
                root = root.right;
            }
        } while(!stack.isEmpty() || root != null);
        return result;
    }

    /**
     * 后续遍历: 迭代实现，使用栈实现，出栈得到节点顺序为根右左，每次向list最开头插入元素
     * @param root
     * @return
     */
    public List<Integer> postorderTraversal(TreeNode root) {
        if(root == null)
            return result;
        Stack<TreeNode> stack = new Stack<TreeNode>();
        stack.push(root);   //首先将根节点压栈
        while(!stack.isEmpty()) {
            TreeNode ele = stack.pop(); //首先出栈的为根节点，其后先出右子节点，后出左子节点
            if(ele.left != null)
                stack.push(ele.left);  //将左子节点压栈
            if(ele.right != null) {
                stack.push(ele.right); //将右子节点压栈
            }
            result.add(0, ele.val); //因为出栈顺序为“根右左”，所以需要每次将元素插入list开头
        }
        return result;
    }

}
