package interview;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.swing.tree.TreeNode;
import java.util.*;

public class BinaryTreeZOut {

  @Builder
  @AllArgsConstructor
  @NoArgsConstructor
  @Data
  static class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;

    TreeNode(int x) {
      val = x;
    }
  }

  public static void solution1(TreeNode root) {
    if (root == null) return;

    Stack<TreeNode> stack1 = new Stack<>();
    Stack<TreeNode> stack2 = new Stack<>();
    boolean left2Right = true;

    stack1.push(root);
    List<List<Integer>> result = new ArrayList<>();
    while (!stack1.isEmpty() || !stack2.isEmpty()) {
      List<Integer> levelResult = new ArrayList<>();
      // 从左向右(利用栈来逆序)
      if (left2Right) {

        while (!stack1.isEmpty()) {
          TreeNode node = stack1.pop();
          levelResult.add(node.val);
          if (node.left != null) {
            stack2.push(node.left);
          }
          if (node.right != null) {
            stack2.push(node.right);
          }
        }
        // 从右向左
      } else {

        while (!stack2.isEmpty()) {
          TreeNode node = stack2.pop();
          levelResult.add(node.val);
          if (node.right != null) {
            stack1.push(node.right);
          }
          if (node.left != null) {
            stack1.push(node.left);
          }
        }
      }
      left2Right = !left2Right;
      result.add(levelResult);
    }

    for (List<Integer> level : result) {
      System.out.println(level);
    }

  }

  /**
   * offer offerLast
   * poll pollFirst
   *
   * add addList
   * poll pollFirst
   *
   * @param root
   */
  public static void solution2(TreeNode root) {
    if (root == null) {
      return;
    }
    List<List<Integer>> res = new ArrayList<>();
    Deque<TreeNode> deque = new LinkedList<>();
    deque.offer(root); // 队尾插入原始
    int flag = 1;
    while (!deque.isEmpty()) {
      int size = deque.size();
      List<Integer> list = new ArrayList<>();
      for (int i = 0; i < size; i++) {
        if (flag == 1) {
          TreeNode out = deque.pollLast();
          list.add(out.val);
          if (out.left != null) {
            deque.offerFirst(out.left);
          }
          if (out.right != null) {
            deque.offerFirst(out.right);
          }
        } else {
          TreeNode out = deque.pollFirst();
          list.add(out.val);
          // 出队列的每个元素从右到左入队列，转上一个分支中
          if (out.right != null) {
            deque.offerLast(out.right);
          }
          if (out.left != null) {
            deque.offerLast(out.left);
          }
        }
      }
      flag *= -1;
      res.add(list);
    }
    for (List<Integer> list : res) {
      System.out.println(list);
    }
  }


  public static void solution3(TreeNode root) {
    if (root == null) {
      return;
    }
    Queue<TreeNode> queue = new LinkedList<>();
    List<List<Integer>> result = new ArrayList<>();
    queue.add(root);
    int level = 1;
    while(!queue.isEmpty()) {
      List<Integer> levelResult = new ArrayList<>();
      for(int i = queue.size(); i > 0; i--) {
        TreeNode node = queue.poll(); // poll first
        levelResult.add(node.val);
        if(node.left != null) queue.add(node.left);
        if(node.right != null) queue.add(node.right);
      }
      if(level % 2 == 0) Collections.reverse(levelResult);
      level++;
      result.add(levelResult);
    }
    for (List<Integer> levelList : result) {
      System.out.println(levelList);
    }
  }


  /**
   *        3
   *    9       20
   * 99  999  15   7
   * @param args
   */
  public static void main(String[] args) {
    TreeNode root = new TreeNode(
        3,
        new TreeNode(
            9,
            new TreeNode(99),
            new TreeNode(999)),
        new TreeNode(
            20,
            new TreeNode(15),
            new TreeNode(7)));

    solution1(root);
    System.out.println();
    solution2(root);
    System.out.println();
    solution3(root);


  }

}
