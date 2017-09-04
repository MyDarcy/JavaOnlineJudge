package com.darcy.main.cleancode_v1_0_3.DynamicProgramming;

import java.util.Arrays;

/**
 * Author by darcy
 * Date on 17-9-4 上午9:06.
 * Description:
 *
 * 从左上节点出发, 向右或者向下到达右下节点，总共有多少条路径.
 *
 * A robot is located at the top-left corner of a m × n grid (marked ‘Start’ in the diagram
 * below). The robot can only move either down or right at any point in time. The robot is
 * trying to reach the bottom-right corner of the grid (marked ‘Finish’ in the diagram
 * below). How many possible unique paths are there?
 *
 */
public class P43_UniquePaths {

  /**
   *
   * 递归的做法.
   *
   * O(C(n + m, n))的时间复杂度, O(m + n)的空间复杂度.
   *
   * @param n 行
   * @param m 列
   * @return
   */
  public static int solution(int n, int m) {
    return solution(n, 1, m, 1);
  }

  private static int solution(int n, int nStart, int m, int mStart) {
    if (n == nStart && m == mStart) {
      return 1;
    }

    if (nStart > n || mStart > m) {
      return 0;
    }

    return solution(n, nStart + 1, m, mStart)
        + solution(n, nStart, m, mStart + 1);

  }

  /**
   * dp
   * 缓存中间结果.
   * @param n
   * @param m
   * @return
   */
  public static int solution2(int n, int m) {
    int[][] cache = new int[n][m];
    solution2(n, n - 1, m, m - 1, cache);
    return cache[n - 1][m - 1];
  }

  private static int solution2(int n, int nStart, int m, int mStart, int[][] cache) {
    if (nStart == 0 || mStart == 0) {
      return 1;
    }

    if (cache[nStart][mStart] == 0) {
      cache[nStart][mStart] = solution2(n, nStart - 1, m, mStart, cache)
          + solution2(n, nStart, m, mStart - 1, cache);
    }

    return cache[nStart][mStart];
  }

  /**
   * 跟上面的dp是一个思路.
   * @param n
   * @param m
   * @return
   */
  public static int solution3(int n, int m) {
    int[][] array = new int[n][m];
//    Arrays.fill(array, -1);
    array[0][0] = 1;
    solution3(n - 1, m - 1, array);
    return array[n - 1][m - 1];
  }

  private static int solution3(int nStart, int mStart, int[][] array) {
    if (nStart == 0 || mStart == 0) {
      array[nStart][mStart] = 1;
    }

    if (array[nStart][mStart] == 0) {
      // nStart == 0 一定是
      /*int fromTop = nStart == 0 ? 0 : solution3(nStart - 1, mStart, array);
      int fromLeft = mStart == 0 ? 0 : solution3(nStart, mStart - 1, array);
      array[nStart][mStart] = fromLeft + fromTop;*/
      array[nStart][mStart] = solution3(nStart - 1, mStart, array)
          + solution3(nStart, mStart - 1, array);
    }

    return array[nStart][mStart];
  }


  /**
   * 结果汇总到array[0][0]处, 而不是上面的方案到array[n-1][m-1]
   * @param m
   * @param n
   * @return
   */
  public static int uniquePaths(int m, int n) {
    return backtrack(0, 0, m, n);
  }

  private static int backtrack(int r, int c, int m, int n) {
    if (r == m - 1 && c == n - 1)
      return 1;
    if (r >= m || c >= n)
      return 0;
    return backtrack(r + 1, c, m, n) + backtrack(r, c + 1, m, n);
  }

  /**
   * O(C(n+m, n))的时间复杂度, O(n+m)的空间复杂度.
   * book solution2
   * 缓存的方案.避免重复计算.
   *
   * 逆向思维, 终节点作为头节点开始移动.
   *
   * @param m
   * @param n
   * @return
   */
  public static int uniquePaths2(int m, int n) {
    int[][] mat = new int[m + 1][n + 1];
    /*int[][] mat = new int[m][n];*/
    for (int i = 0; i < mat.length; i++) {
      for (int j = 0; j < mat[0].length; j++) {
        mat[i][j] = -1;
      }
    }
    // 基本类型的填充是有问题的.
    //  Arrays.fill(mat, -1);
    return backtrack2(0, 0, m, n, mat);
  }

  /**
   * 是这样的,
   * 譬如array[n-2][m-1] = array[n-1][m-1] + array[n-2][m];
   * 所以创建的数组的大小是 (n+1)*(m+1)的.
   * @param r
   * @param c
   * @param m
   * @param n
   * @param mat
   * @return
   */
  private static int backtrack2(int r, int c, int m, int n, int[][] mat) {
    // 这里是&&条件.  这里换成 || 条件也可以,而且更容易理解,.
    if (r == m - 1 && c == n - 1)
      return 1;
    if (r >= m || c >= n)
      return 0;
    if (mat[r + 1][c] == -1)
      mat[r + 1][c] = backtrack2(r + 1, c, m, n, mat);
    if (mat[r][c+1] == -1)
      mat[r][c + 1] = backtrack2(r, c + 1, m, n, mat);
    return mat[r + 1][c] + mat[r][c + 1];
  }

  /**
   * dp思路. bottom-up的思路.
   * 𝑶(𝒎𝒏) runtime, 𝑶(𝒎𝒏) space
   * @param m
   * @param n
   * @return
   */
  public static int uniquePaths3(int m, int n) {
    int[][] mat = new int[m + 1][n + 1];
    // 其实设置的元素是边界外的元素, 它提供的作用是初始化a[m-1][n-1], 避免单独的初始化.
    mat[m - 1][n] = 1;
    for (int r = m - 1; r >= 0; r--) {
      for (int c = n - 1; c >= 0; c--) {
        mat[r][c] = mat[r + 1][c] + mat[r][c + 1];
      }
    }
    return mat[0][0];
  }

  public  static void main(String[] args) {
    /*int result = solution(4, 4);
    System.out.println("solution1:" + result);

    int result2 = solution2(4, 4);
    System.out.println("solution2:" + result2);


    int result3 = solution3(4, 4);
    System.out.println("solution3:" + result3);

    int result4 = uniquePaths(4, 4);
    System.out.println("uniquePaths:" + result4);

    int result5 = uniquePaths2(4, 4);
    System.out.println("uniquePaths2:" + result5);

    int result6 = uniquePaths3(4, 4);
    System.out.println("uniquePaths3:" + result6);*/

    for (int i = 10; i < 12; i++) {
      for (int j = 12; j < 15; j++) {
        System.out.println("i=" + i + ", j=" + j);

        int result = solution(i, j);
        System.out.println("solution1:" + result);

        int result2 = solution2(i, j);
        System.out.println("solution2:" + result2);


        int result3 = solution3(i, j);
        System.out.println("solution3:" + result3);

        int result4 = uniquePaths(i, j);
        System.out.println("uniquePaths:" + result4);

        int result5 = uniquePaths2(i, j);
        System.out.println("uniquePaths2:" + result5);

        int result6 = uniquePaths3(i, j);
        System.out.println("uniquePaths3:" + result6);

        System.out.println("---------------------------");
      }
    }

  }
}
