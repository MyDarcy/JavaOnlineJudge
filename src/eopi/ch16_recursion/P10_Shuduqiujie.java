package eopi.ch16_recursion;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Author by darcy
 * Date on 17-8-27 下午8:35.
 * Description:
 *
 * sudoku solver : 数独求解
 *
 * 树独问题:
 * Sudoku isa popular logic-based combinatorial number placement puzzle. The objective
 * is tofill a 9 X 9 grid with digits subject to the constraint that each column, each row,
 * and each of the nine 3x3 sub-grids that compose the grid contains unique integers
 * in [1,9].
 *
 * Check whether a 9 X 9 2D array representing a partially completed Sudoku is valid.
 * Specifically, check that no row, column, or 3 X 3 2D subarray contains duplicates. A
 * 0-value in the 2D array indicates that entry is blank; every other entry is in [1,9].
 *
 * Hint: Directly test the constraints. Use an array to encode sets.
 *
 *
 */
public class P10_Shuduqiujie {

  public static boolean bruteForceShudu(List<List<Integer>> assignments) {

    return false;
  }

  /**
   *
   * the problem of solving Sudoku generalized to n * n grids is NP-complete, it
   * should not be difficult to prove that the generalization of this algorithm to n * n grids
   * has exponential time complexity.
   *
   * @param assignments
   * @return
   */
  public static boolean solveShudu(List<List<Integer>> assignments) {

    return solveShudu(0, 0, assignments);
  }

  private static final int EMPTY_ENTRY = 0;

  /**
   *
   * @param i
   * @param j
   * @param assignments
   * @return
   */
  private static boolean solveShudu(int i, int j, List<List<Integer>> assignments) {
    // 行列都到了末尾,就是一个满足条件的数独了.
    if (i == assignments.size()) {
      // 开始新行.
      i = 0;
      if (++j == assignments.get(i).size()) {
        return true; // 填充完毕,没有任何冲突.
      }
    }

    // 跳过非空的entry.
    if (assignments.get(i).get(j) != EMPTY_ENTRY) {
      return solveShudu(i + 1, j, assignments);
    }

    for (int val = 1; val <= assignments.size(); val++) {
      // 先检查冲突性，然后再添加元素.因为添加entry之前的都是满足要求的, 限制添加了新的元素只需要进行少量的判定。
      // (i, j)放val是否会导致冲突.
      if (validToAddVal(assignments, i, j, val)) {
        // 在(i, j)处添加元素是否会导致冲突.
        assignments.get(i).set(j, val);
        if (solveShudu(i + 1, j, assignments)) {
          return true;
        }
      }
    }

    assignments.get(i).set(j, EMPTY_ENTRY);
    return false;
  }

  private static boolean validToAddVal(List<List<Integer>> assignments, int i, int j, int val) {
    // 检查行约束.
    // 所有行的第j列是否冲突.
    for (List<Integer> item : assignments) {
      if (val == item.get(j)) {
        return false;
      }
    }

    // 检查列约束.
    // 第i行的任意k列是否冲突.
    for (int k = 0; k < assignments.size(); k++) {
      if (val == assignments.get(i).get(k)) {
        return false;
      }
    }

    int regionSize = (int) Math.sqrt(assignments.size());
    int I = i / regionSize;
    int J = j / regionSize;
    for (int k = 0; k < regionSize; k++) {
      for (int l = 0; l < regionSize; l++) {
        if (val == assignments.get(regionSize * I + k).get(regionSize * J + l)) {
          return false;
        }
      }
    }
    return true;
  }

  public static void main(String[] args) {
    List<List<Integer>> assignment = new ArrayList<>();
    int number = 9;
    for (int i = 0; i < 9; i++) {
      ArrayList<Integer> list = new ArrayList<>(9);
      for (int j = 0; j < number; j++) {
        list.add(EMPTY_ENTRY);
      }
      assignment.add(list);
    }
    solveShudu(assignment);
    for (List<Integer> item : assignment) {
      System.out.println(item);
    }


  }

}
