package eopi.ch17_dp;

import java.util.Arrays;

/**
 * Author by darcy
 * Date on 17-8-24 下午10:30.
 * Description:
 * 从二维数组的左上角到右下角有多少种走法.
 *
 * input
 * 5*5
 * output
 * 70
 *
 * 思路: (i-1, j), (i, j-1) ->(i, j)
 *
 */
public class P5_2DArray {

  /**
   * 递归的方式迭代所有的路径.
   * @param n
   * @param m
   * @return
   */
  public static int bruteForceSolution(int n, int m) {

    if (n == 0 || m == 0) {
      return 1;
    }

    // 3 * 3的矩阵a[1][1]会被重复计算.
    // 所以这里是不行的，那么可以使用标志数组表示特定的位置是否访问过，
    // 但是这样跟下面的dp就没有区别，而且连结果也没有缓存起来.
    int fromTop = n == 0 ? 0 : bruteForceSolution(n - 1, m);
    int fromLeft = m == 0 ? 0 : bruteForceSolution(n, m - 1);

    return fromTop + fromLeft;
  }


  /**
   *
   * dp解决:其实就是在原来的算法的基础之上添加了缓存中间结果.
   * The time complexity is 0(nm), and the space complexity is 0(nm), where n is the
   * number of rows and m is the number of columns.
   *
   *
   * @param n
   * @param m
   * @return
   */
  public static int dpSolution(int n, int m) {
    int[][] array = new int[n][m];
    array[0][0] = 1;

    int result = dpSolution(n - 1, m - 1, array);

    for (int[] items : array) {
      for (int i : items) {
        System.out.printf("%6d", i);
      }
      System.out.println();
    }

    return result;
  }

  private static int dpSolution(int n, int m, int[][] array) {
    if (n == 0 || m == 0) {
      /*return 1;*/
      array[n][m] = 1;
    }

    // 既能够避免重复计算, 同时也能缓存要共同计算的结果。
    // 其实是达到了同一个目的.
    if (array[n][m] == 0) {
      int fromTop = n == 0 ? 0 : dpSolution(n - 1, m, array);
      int fromLeft = m == 0 ? 0 : dpSolution(n, m - 1, array);

      array[n][m] = fromLeft + fromTop;
    }

    return array[n][m];

  }


  /**
   * 解法3:数学的方法直接计算更快.
   * 总共要走n + m - 2步, 水平方向走n-1步，　垂直方向走m-1步.
   *
   * (n - m - 2, n - 1) = (n - m - 2, m -1) = (n - m - 2)! / (n - 1)! * (m - 1)!
   *
   * 注意阶乘可能导致的溢出.
   *
   * @param n
   * @param m
   * @return
   */
  public static long mathmaticalSolution(long n, long m) {
    return permutation(n + m - 2) / (permutation(n - 1) * permutation(m - 1));
  }

  private static long permutation(long i) {

    if (i == 1) {
      return 1;
    }

    return i * permutation(i - 1);
  }

  public static void main(String[] args) {
    int paths = bruteForceSolution(5, 5);
    System.out.println(paths);

    System.out.println(dpSolution(10, 10));

    System.out.println();

    System.out.println(mathmaticalSolution(10, 10));
  }
}

/*
Variant: Solve the same problem using 0(min(n, m )) space.

Variant: Solve the same problem in the presence of obstacles, specified by a Boolean
2D array, where the presence of a true value represents an obstacle. (有障碍的)

Variant: A fisherman is in a rectangular sea. The value of the fish at point (i, j) in the
sea isspecified by an n * m 2D array A. Write a program that computes the maximum
value of fish a fisherman can catch on a path from the upper leftmost point to the
lower rightmost point. The fisherman can only move down or right, as illustrated in
Figure 17.7 on the next page.　(一条路径找最大总和, 如果有多条路径, 求出所有这些路径.)

Variant:Solve the same problem when the fisherman can begin and end at any point.
He must still move down or right. (Note that the value at (i, j) may be negative.)


Variant: A decimal number is a sequence of digits, i.e., a sequence over {0,1, 2,...,9).
The sequence has to be of length 1 or more, and the first element in the sequence
cannot be 0. Call a decimal number D monotone if D[i] < D[i + 1],0 < i < |D|. Write
a program which takes as input a positive integer k and computes the number of
decimal numbers of length k that are monotone(单调的).

Variant: Call a decimal number D, as defined above, strictly monotone if D[i] < D[i +
i], 0<i< |D|. Write a program which takes as input a positive integer k and computes
the number of decimal numbers of length k that are strictly monotone.
 */
