package eopi.ch17_dp;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * Author by darcy
 * Date on 17-8-25 下午5:23.
 * Description:
 * 一系列的int[], 组成了如下的三角形
 *     2
 *    4 4
 *   8 5 6
 *  4 2 6 2
 * 1 5 2 3 4
 * path定义为从top层到bottom层的邻接点组成的序列。要找出权重最小的一条路径.
 * 如上的三角形 2->4->5->2->2组成的路径最小，为15.
 *
 * Write a program that takes as input a triangle of numbers and returns the weight of
 * a minimum weight path. For example, the minimum weight path for the number
 * triangle in Figure 17.10 is shown in bold face, and its weight is 15.
 *
 * Hint: What property does the prefix of a minimum weight path have?
 *
 */
public class P10_Triangle {


  /**
   * 暴力破解算法的话需要迭代所有可能的路径.
   * 那么其事件复杂度是2^n;
   * @param triangle
   * @return
   */
  public static int bruteForceSolution(List<List<Integer>> triangle) {

    return 0;
  }

  /**
   *
   *
   * 官方吐槽:
   *  if you look at the minimum weight path ending at it, the part of the path that ends at the
   *  previous row must also be a minimum weight path. This gives us a DP solution.(子问题) We
   *  **iteratively compute the minimum weight of a path ending at each entry in Row i**.
   *  Since after we complete processing Row i, we do not need the results for Row i —1 to
   *  process Row i + 1, we can reuse storage.
   * @param triangle
   * @return
   */
  public static int dpSolution(List<List<Integer>> triangle) {
    if (triangle == null) {
      return 0;
    }

    List<Integer> previousRow = new ArrayList<>(triangle.get(0));

    for (int i = 1; i < triangle.size(); i++) {
      List<Integer> currentRow = new ArrayList<>(triangle.get(i));
      // 从上一层到达本层的第一个节点只有一条路径.
      currentRow.set(0, currentRow.get(0) + previousRow.get(0));

      for (int j = 1; j < currentRow.size() - 1; j++) {
        currentRow.set(j, currentRow.get(j)
            + Math.min(previousRow.get(j - 1), previousRow.get(j)));
      }

      // 从上一层到达最后一层也只有一条路径.
      currentRow.set(currentRow.size() - 1, currentRow.get(currentRow.size() - 1) + previousRow.get(previousRow.size() - 1));

      previousRow = currentRow;

    }

    return Collections.min(previousRow);
  }

  public static void main(String[] args) {
    List<List<Integer>> list = new ArrayList<>();
    list.add(Arrays.asList(    2));
    list.add(Arrays.asList(   4, 4));
    list.add(Arrays.asList(  8, 5, 6));
    list.add(Arrays.asList( 4, 2, 6, 2));
    list.add(Arrays.asList(1, 5, 2, 3, 4));
    System.out.println(dpSolution(list));
  }
}
