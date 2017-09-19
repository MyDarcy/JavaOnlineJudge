package eopi.ch9_stackQueue;

import java.util.Deque;
import java.util.LinkedList;
import java.util.List;

/**
 * Author by darcy
 * Date on 17-9-19 下午3:23.
 * Description:
 * <p>
 * 层次化遍历二叉树.
 */
public class P7_LevelTransferBinaryTree {

  static class TreeNode {
    Integer val;
    TreeNode left;
    TreeNode right;

    public TreeNode(Integer val) {
      this.val = val;
    }

    public TreeNode(Integer val, TreeNode left, TreeNode right) {
      this.val = val;
      this.left = left;
      this.right = right;
    }
  }

  /**
   *
   * @param root
   * @return
   */
  public static List<List<Integer>> bruteForceSolution(TreeNode root) {

    return null;
  }


  /**
   *
   * @param root
   * @return
   */
  public static List<List<Integer>> solution(TreeNode root) {
    List<List<Integer>> result = new LinkedList<>();
    Deque<TreeNode> currentLevel = new LinkedList<>();

    while (!currentLevel.isEmpty()) {
      Deque<TreeNode> nextLevel = new LinkedList<>();
      List<Integer> currentResult = new LinkedList<>();
      while (!currentLevel.isEmpty()) {
        TreeNode currentElement = currentLevel.removeFirst();
        if (currentElement != null) {
          currentResult.add(currentElement.val);
          nextLevel.addLast(currentElement.left);
          nextLevel.addLast(currentElement.right);
        }
      }
      if (!currentResult.isEmpty()) {
        result.add(currentResult);
      }
      currentLevel = nextLevel;
    }
    return result;
  }

}

/*
Variant: Write a program which takes as input a binary tree and returns the keys in
top down, alternating left-to-right and right-to-left order, starting from left-to-right.
For example, if the input is the tree in Figure 10.1 on Page 150, your program should
return «314>, <6, 6>, <271,561, 2, 271>, <28,1,3,0, 28>, <17,401, 257>,<641».

Variant: Write a program which takes as input a binary tree and returns
the keys in a bottom up, left-to-right order. For example, if the in¬
put is the tree in Figure 10.1 on Page 150, your program should return
«641>, <17,401, 257}, <28, 0,3,1, 28>, <271,561, 2, 271>, <6, 6>, <314».

Variant: Write a program which takes as input a binary tree with integer keys, and
returns the average of the keys at each level. For example, if the input is the tree in
Figure 10.1 on Page 150, your program should return <314,6, 276.25,12, 225,641).
 */