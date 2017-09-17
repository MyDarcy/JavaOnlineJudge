package eopi.ch6_arrays;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * Author by darcy
 * Date on 17-9-17 下午9:07.
 * Description:
 * <p>
 * 数独问题: 9*9的方格, 每行,每列，每个3×3的小方格中都包含且仅包含[1...9]这9个数.
 * 9×9的大方格一开始是部分赋值的.
 * <p>
 * Check whether a 9 X 9 2D array representing a partially completed Sudoku is valid.\
 * Specifically, check that no row, column, or 3 X 3 2D subarray contains duplicates. A
 * 0-value in the 2D array indicates that entry is blank; every other entry is in [1,9].
 */
public class P16_SodukuCheckProblem {


  /**
   * 验证一个数独是否是合法的.
   *
   * @param patialAssignment
   * @return
   */
  public static boolean isValidSudoku(List<List<Integer>> patialAssignment) {
    // 检查行.
    for (int i = 0; i < patialAssignment.size(); i++) {
      if (hasDuplicate(patialAssignment, i, i + 1, 0, patialAssignment.size())) {
        return false;
      }
    }

    // 检查列.
    for (int i = 0; i < patialAssignment.size(); i++) {
      if (hasDuplicate(patialAssignment, 0, patialAssignment.size(), i, i + 1)) {
        return false;
      }
    }

    // 检查3×3
    int size = (int) Math.sqrt(patialAssignment.size());
    for (int i = 0; i < size; i++) {
      for (int j = 0; j < size; j++) {
        if (hasDuplicate(patialAssignment,
            size * i, size * (i + 1), size * j, size * (j + 1))) {
          return false;
        }
      }
    }

    return true;
  }

  private static boolean hasDuplicate(List<List<Integer>> patialAssignment,
                                      int rowStart, int rowEnd, int colStart, int colEnd) {
    List<Boolean> presents = new ArrayList<Boolean>(Collections.nCopies(patialAssignment.size() + 1, false));
    for (int i = rowStart; i < rowEnd; i++) {
      for (int j = colStart; j < colEnd; j++) {
        // 之前presents已经被设置过了.
        if (patialAssignment.get(i).get(j) != 0
            && presents.get(patialAssignment.get(i).get(j))) {
          return true;
        }
      }
    }
    return false;
  }

  public static void main(String[] args) {
    List<List<Integer>> list =  new ArrayList<>();
    list.add(Arrays.asList(5, 3, 4, 6, 7, 8, 9, 1, 2));
    list.add(Arrays.asList(6, 7, 2, 1, 9, 5, 3, 4, 8));
    list.add(Arrays.asList(1, 9, 8, 3, 4, 2, 5, 6, 7));
    list.add(Arrays.asList(8, 5, 9, 7, 6, 1, 4, 2, 3));
    list.add(Arrays.asList(4, 2, 6, 8, 5, 3, 7, 9, 1));
    list.add(Arrays.asList(7, 1, 3, 9, 2, 4, 8, 5, 6));
    list.add(Arrays.asList(9, 6, 1, 5, 3, 7, 2, 8, 4));
    list.add(Arrays.asList(2, 8, 7, 4, 1, 9, 6, 3, 5));
    list.add(Arrays.asList(3, 4, 5, 2, 8, 6, 1, 7, 9));

    boolean result = isValidSudoku(list);
    System.out.println(result);
  }

}
