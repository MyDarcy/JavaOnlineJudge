package com.darcy.main.cleancode_v1_0_3.Misc;

import java.util.ArrayList;
import java.util.List;

/**
 * Author by darcy
 * Date on 17-9-3 下午8:08.
 * Description:
 *
 *Given a matrix of m ✕ n elements (m rows, n columns), return all elements of the
 * matrix in spiral(螺旋；旋涡；螺旋形之物) order.
 * For example, given the following matrix:
 * [
 * [ 1, 2, 3 ],
 * [ 4, 5, 6 ],
 * [ 7, 8, 9 ]
 * ]
 * You should return [1,2,3,6,9,8,7,4,5]
 *
 */
public class P35_SparseMatrix {


  /**
   *
   * A cleaner solution is to keep track of our current position and the number of steps in both
   * horizontal and vertical directions. As we change direction we decrement the steps in that
   * direction. When the number of steps in a direction becomes zero, we know that we have
   * finished traversing the entire matrix.
   *
   * @param matrix
   * @return
   */
  public static List<Integer> solution(int[][] matrix) {
    List<Integer> result = new ArrayList<>(matrix.length * matrix[0].length);
    int m = matrix.length; // 行数目.
    int n = matrix[0].length;
    int row = 0;
    int col = -1; // -1;
    while (true) {
      for (int i = 0; i < n; i++) {
        // 经过n次,结束时刚好 col = n-1;
        result.add(matrix[row][++col]);
      }
      m--; // 第1行已经处理完毕.
      if (m == 0) {
        break;
      }

      // 只能加m-1次了.
      for (int i = 0; i < m; i++) {
        // 结束的时候, row = m -1;
        result.add(matrix[++row][col]);
      }

      // 已经处理掉了一列.
      n--;
      if (n == 0) {
        break;
      }

      //
      for (int i = 0; i < n; i++) {
        result.add(matrix[row][--col]);
      }

      m--;
      if (m == 0) {
        break;
      }

      for (int i = 0; i < m; i++) {
        result.add(matrix[--row][col]);
      }

      n--;
      if (n == 0) {
        break;
      }
    }
    return result;
  }

  public static void main(String[] args) {
    int[][] array = {
        {1, 2, 3, 4},
        {5, 6, 7, 8},
        {9, 10, 11, 12},
        {13, 14, 15, 16}
    };

    List<Integer> result = solution(array);
    System.out.println(result);
  }

}
