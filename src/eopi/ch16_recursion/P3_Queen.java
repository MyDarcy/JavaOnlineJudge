package eopi.ch16_recursion;

import java.util.ArrayList;
import java.util.List;

/**
 * Author by darcy
 * Date on 17-8-27 上午11:07.
 * Description:
 *
 * 8皇后问题.
 *
 * ctci5th.chapter8.section9.P909_EightQueen
 * jianzhioffer.P28_EightQueen
 *
 * A nonattacking placement of queens is one in which no two queens are in
 * the same row, column, or diagonal.
 *
 * Write a program which returns all distinct nonattacking placements of n queens
 * on an n*n chessboard, where n is an input to the program.
 *
 * Hint: If the first queen is placed at (i, j),where can the remaining queens definitely not be placed?
 *
 *
 *
 */
public class P3_Queen {


  /**
   * 暴力破解方法,考虑nQueue所有可能的的放置位置.
   * 总的可能情况是C(n * n, n)
   *
   *
   * @param nQueue
   * @return
   */
  public static List<List<Integer>> bruteForceSolution(int nQueue) {
    return null;
  }


  /**
   * array[n] -- ith entry代表第i行的元素存放于哪一列.
   *
   *
   * @param nQueue
   * @return
   */
  public static List<List<Integer>> solution(int nQueue) {
    List<List<Integer>> result = new ArrayList<>();
    // may here transfer new ArrayList<Integer>(8)
    solution(nQueue, 0, new ArrayList<Integer>(), result);
    return result;
  }

  private static void solution(int nQueue, int row, ArrayList<Integer> placement, List<List<Integer>> result) {
    // 已经放置到了最后一行.
    if (nQueue == row) {
      result.add(new ArrayList<>(placement));
    } else {
      //
      for (int columnNumber = 0; columnNumber < nQueue; columnNumber++) {
        // 新行的元素放在哪一列.
        placement.add(columnNumber);
        if (isValid(placement)) {
          solution(nQueue, row + 1, placement, result);
        }
        // 移除本行的本次的放置.
        // 可以通过list.set(i, number)的设置避免本次操作. 用set方法替代add方法.
        placement.remove(placement.size() - 1);
      }
    }
  }

  private static boolean isValid(ArrayList<Integer> placement) {
    int row = placement.size() - 1;
    for (int i = 0; i < row; i++) {
      int diff = Math.abs(placement.get(i) - placement.get(row));
      // 同一列或者对角线.
      if (diff == 0 || diff == (row - i)) {
        return false;
      }
    }
    return true;
  }

  public static void main(String[] args) {
    List<List<Integer>> solution = solution(10);
    System.out.println(solution.size());
    /*for (int i = 0; i < solution.size(); i++) {
      System.out.println(solution.get(i));
    }*/


    for (int i = 3; i < 20; i++) {
      long start = System.currentTimeMillis();
      System.out.println(i + " " + solution(i).size() + " time:" + (System.currentTimeMillis() - start) + "ms");

    }

  }

}
/*
Variant: Compute the number of nonattacking placements of n queens on an n*n chessboard.

Variant: Compute the smallest number of queens that can be placed to attack each
uncovered square.

Variant: Compute a placement of 32 knights, or 14 bishops, 16 kings or 8 rooks on an
8x8 chessboard in which no two pieces attack each other.
 */