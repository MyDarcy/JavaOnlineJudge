package com.darcy.main.cleancode_v1_0_3.DynamicProgramming;

import java.util.ArrayList;
import java.util.List;

/**
 * Author by darcy
 * Date on 17-9-4 下午3:39.
 * Description:
 *
 * n个coins 拍成一排.两个选手轮流从两端拿走货币(每次都可以从两端拿最大的那个,但是只能拿一个), 当没有coin的时候，
 * 拿走钱多的选手赢得比赛.
 *
 * There are n coins in a line. (Assume n is even). Two players take turns to take a coin
 * from one of the ends of the line until there are no more coins left. The player with the
 * larger amount of money wins.
 * 1. Would you rather go first or second? Does it matter?
 * 2. Assume that you go first, describe an algorithm to compute the maximum amount
 * of money you can win.
 *
 */
public class P47_CoinsinaLine {

  /**
   * 参考 eopi dp章节相关的部分.
   * @param array
   * @return
   */
  public static int solution(int[] array) {
    // result[a][b]代表的就是可选序列是[a,b]时, 所能取的最大值.
    int[][] result = new int[array.length][array.length];
    return solution(array, 0, array.length - 1, result);
  }

  private static int solution(int[] array, int start, int end, int[][] result) {
    if (start > end) {
      return 0;
    }

    if (result[start][end] == 0) {
      // p1选择a处的coin, 那么p2此时可以选择的位置是a+1或者b, 每次p2选择都会使得p1后面选择的值最小.
      // 所以p1再次可选的位置是[a+2, b] 或者[a+1, b-1];
      int pickHead = array[start] +
          // 取最小就模拟了p2的选择.
          Math.min(solution(array, start + 2, end, result),
              solution(array, start + 1, end - 1, result));

      // 同上.
      int pickTail =  result[start][end] = array[end] +
          Math.min(solution(array, start, end - 2, result),
              solution(array, start + 1, end - 1, result));

      // 最终的结果取最大就模拟了最大取值.
      result[start][end] = Math.max(pickHead, pickTail);
    }

    return result[start][end];
  }


  static final int MAX_N = 100;

  static void printMoves(int[][] P, int A[], int N) {
    int sum1 = 0, sum2 = 0;
    int m = 0, n = N-1;
    boolean myTurn = true;
    while (m <= n) {
      int P1 = P[m+1][n]; // If take A[m], opponent can get...
      int P2 = P[m][n-1]; // If take A[n]
      // cout << (myTurn ? "I" : "You") << " take coin no. ";
      System.out.print((myTurn ? "I" : "You") + " take coin no.");
      if (P1 <= P2) {
        // cout << m+1 << " (" << A[m] << ")";
        System.out.print((m + 1) + " (" + A[m] +  ")");
        m++;
      } else {
        // cout << n+1 << " (" << A[n] << ")";
        System.out.print(n+1 + " (" + A[n] + ")");
        n--;
      }
      // cout << (myTurn ? ", " : ".\n");
      System.out.print( (myTurn ? ", " : ".\n"));
      myTurn = !myTurn;
    }
    // cout << "\nThe total amount of money (maximum) I get is " << P[0][N-1] << ".\n";
    System.out.println("\nThe total amount of money (maximum) I get is " + P[0][N - 1] + ".");
  }
  static int maxMoney(int A[], int N) {
    int[][] P = new int[MAX_N][MAX_N];
    int a, b, c;
    for (int i = 0; i < N; i++) {
      for (int m = 0, n = i; n < N; m++, n++) {
        assert(m < N); assert(n < N);
        a = ((m+2 <= N-1) ? P[m+2][n] : 0);
        b = ((m+1 <= N-1 && n-1 >= 0) ? P[m+1][n-1] : 0);
        c = ((n-2 >= 0) ? P[m][n-2] : 0);
        P[m][n] = Math.max(A[m] + Math.min(a,b),
            A[n] + Math.min(b,c));
      }
    }
    printMoves(P, A, N);
    return P[0][N-1];
  }

  public static void main(String[] args) {
    int[] array = {3, 2, 2, 3, 1, 2};
    int result = solution(array);
    System.out.println(result);

    System.out.println("----");
    int result2 = maxMoney(array, array.length);
    System.out.println(result2);
  }

}
