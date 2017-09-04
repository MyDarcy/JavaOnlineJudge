package com.darcy.main.cleancode_v1_0_3.DynamicProgramming;

/**
 * Author by darcy
 * Date on 17-9-4 上午10:37.
 * Description:
 *
 * 如上题,但是有些位置添加了障碍物,无法通过。
 * 求总的路径数目.
 *
 * Similar to Question [43. Unique Paths], but now consider if some obstacles are added to
 * the grids. How many unique paths would there be?
 * An obstacle and empty space are marked as 1 and 0 respectively in the grid.
 * For example,
 * There is one obstacle in the middle of a 3×3 grid as illustrated below.
 * [
 * [0,0,0],
 * [0,1,0],
 * [0,0,0]
 * ]
 * The total number of unique paths is 2.
 *
 */
public class P44_UniquePathsII {

  /**
   *
   * O(mn)的时间复杂度,O(mn)的空间复杂度.
   *
   * @return
   */
  public static int solution(int[][] array) {
    int[][] result = new int[array.length][array[0].length];
    solution(array.length - 1, array.length - 1, array, result);
    return result[array.length - 1][array[0].length - 1];
  }


  private static int solution(int n, int m, int[][] array, int[][] result) {
    if (n == 0 && m == 0) {
      result[0][0] = 1;
    }

    int fromTop = 0;
    int fromLeft = 0;

    if (array[n][m] != 1 && result[n][m] == 0) {
      if (n == 0 && m == 0) {
        return 1;
      }
     // fromTop = n == 0? 0 : /* array[n - 1][m] == 1 ? 0 : */result[n - 1][m];
     //  fromLeft = m == 0? 0:  /*array[n][m - 1] == 1 ? 0 : */result[n][m - 1];
      // result[n][m] = fromLeft + fromTop;
//      result[n][m] = solution(n - 1, m, array, result) + solution(n, m - 1, array, result);
      fromTop = n == 0? 0 : /* array[n - 1][m] == 1 ? 0 : */solution(n-1, m, array, result);
      fromLeft = m == 0 ? 0 :  /*array[n][m - 1] == 1 ? 0 : */solution(n, m - 1, array, result);
      result[n][m] = fromLeft + fromTop;
    } else if (array[n][m] == 1) {
      // 该位置有障碍物,直接可能通过的路径就是0;
      result[n][m] = 0;
    }

    return result[n][m];
  }

  /**
   *
   * book solution.
   * @param obstacleGrid
   * @return
   *
   */
  public static int uniquePathsWithObstacles(int[][] obstacleGrid) {
    int m = obstacleGrid.length;
    if (m == 0) return 0;
    int n = obstacleGrid[0].length;
    int[][] mat = new int[m + 1][n + 1];
    mat[m - 1][n] = 1;
    for (int r = m - 1; r >= 0; r--) {
      for (int c = n - 1; c >= 0; c--) {
        mat[r][c] = (obstacleGrid[r][c] == 1) ? 0 : mat[r][c+1] + mat[r+1][c];
      }
    }
    return mat[0][0];
  }

  public static void main(String[] args) {
    int[][] array = {
        {0, 0, 0, 1, 0},
        {0, 1, 0, 0, 0},
        {0, 0, 0, 1, 0},
        {0, 1, 0, 0, 0},
        {0, 0, 0, 0, 0}
    };

    int result = uniquePathsWithObstacles(array);
    System.out.println(result);

    int result2 = solution(array);
    System.out.println(result2);

    int[][] array2 = {
        {0, 0, 0, 1, 0, 0, 0},
        {0, 1, 0, 0, 0, 1, 0},
        {0, 0, 0, 1, 0, 0, 0},
        {0, 1, 0, 0, 0, 0, 0},
        {0, 0, 0, 0, 1, 0, 0},
        {0, 0, 0, 0, 1, 0, 0},
        {0, 0, 0, 0, 0, 0, 0}
    };

    int result3 = uniquePathsWithObstacles(array2);
    int result4 = solution(array2);
    System.out.println(result3);
    System.out.println(result4);

  }

}
