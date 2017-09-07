package jianzhioffer;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Deque;
import java.util.LinkedList;

/**
 * Created by darcy
 * 2017/4/20--0:34
 * Description:
 * 从上往下打印出二叉树的每个节点，同层节点从左至右打印。
 */
public class P23_PrintTreeInLevel {


  public static class TreeNode {
    int val = 0;
    TreeNode left = null;
    TreeNode right = null;

    public TreeNode(int val) {
      this.val = val;

    }

    public TreeNode(int val, TreeNode left, TreeNode right) {
      this.val = val;
      this.left = left;
      this.right = right;
    }
  }

  public static ArrayList<Integer> PrintFromTopToBottom(TreeNode root) {
    // 这里返回的是空的ArrayList, 一般也推荐这样，由上层去处理list中没有元素的情况；
    if (root == null) {
      return new ArrayList<Integer>();
    }

    ArrayList<Integer> list = new ArrayList<>();

    Deque<TreeNode> queue = new LinkedList<>();
    queue.addLast(root);
    while (!queue.isEmpty()) {
      TreeNode node = queue.pollFirst();
      list.add(node.val);
      if (node.left != null) {
        queue.addLast(node.left);
      }
      if (node.right != null) {
        queue.addLast(node.right);
      }
    }
    return list;

  }

  public static int[][] printTree(TreeNode root) {
    // write code here
    TreeNode last;//表示当前行的最右结点
    TreeNode nlast;//表示下一行的最右结点
    //用一个队列来装要打印的结点
    LinkedList<TreeNode> link = new LinkedList<TreeNode>();
    //用数组来装打印之后的结点
    ArrayList<ArrayList<Integer>> layer = new ArrayList();//存ArrayList数组的容器
    ArrayList<Integer> array = new ArrayList<Integer>();//ArrayList数组，用来存每一层的结点
    if (root == null)
      return null;
    last = root;//last指向根结点
    nlast = null;
    link.add(last);//将根结点添加到link队列
    while (!link.isEmpty()) {
      TreeNode tn = link.removeFirst();
      array.add(tn.val);//将结点值存入array数组
      if (tn.left != null) {
        link.add(tn.left);
        nlast = tn.left;
      }
      if (tn.right != null) {
        link.add(tn.right);
        nlast = tn.right;
      }
      if (tn == last) {//遍历到last就该换行了
        layer.add(array);//将刚刚这层结点所在的数组array添加到ArrayList容器中
        array = new ArrayList<Integer>();//将数组array置为空
        last = nlast;//令last=nlast就可以将下一层存入数组了
      }

    }

    int num[][] = new int[layer.size()][];
    for (int i = 0; i < layer.size(); i++) {
      num[i] = new int[layer.get(i).size()];
      for (int j = 0; j < layer.get(i).size(); j++) {
        num[i][j] = layer.get(i).get(j);
      }
    }

    return num;
  }

  public static ArrayList<ArrayList<Integer>> levelOrder(TreeNode root) {
    // write your code here
    TreeNode last;
    TreeNode nlast;
    LinkedList<TreeNode> link = new LinkedList<TreeNode>();//存储待打印的结点的队列
    ArrayList<ArrayList<Integer>> result = new ArrayList<ArrayList<Integer>>();
    ArrayList<Integer> array = new ArrayList<Integer>();
    if (root == null)
      return result;
    // 每一层的last节点.
    last = root;
    nlast = null;
    link.add(root);
    while (!link.isEmpty()) {
      TreeNode tn = link.removeFirst();
      array.add(tn.val);
      if (tn.left != null) {
        link.add(tn.left);
        nlast = tn.left;
      }

      if (tn.right != null) {
        link.add(tn.right);
        nlast = tn.right;
      }

      if (tn == last) {
        result.add(array);
        // 新的array存放下一层节点.
        array = new ArrayList<Integer>();
        last = nlast;
      }
    }
    return result;
  }

  public static void main(String[] args) {
    TreeNode root =
        new TreeNode(1,
            new TreeNode(2,
                new TreeNode(4),
                new TreeNode(10)),
            new TreeNode(100,
                new TreeNode(1000),
                new TreeNode(2000)));

    ArrayList<ArrayList<Integer>> result = levelOrder(root);
    for (ArrayList<Integer> list : result) {
      System.out.println(list);
    }

    System.out.println("-----");

    int[][] result2 = printTree(root);
    for (int[] item : result2) {
      System.out.println(Arrays.toString(item));
    }
  }
}
