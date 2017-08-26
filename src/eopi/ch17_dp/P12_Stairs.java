package eopi.ch17_dp;

/**
 * Author by darcy
 * Date on 17-8-26 上午9:59.
 * Description:
 *
 * 爬楼梯, 每次可以爬1...k个台阶,那么总共有多少个方案.
 *
 * Write a program which takes as inputs n and k and returns the number of ways in
 * which you can get to your destination.
 *
 * Hint: How many ways are there in which you can take the last step?
 *
 * Since the first advance can be one step, two steps,..., k steps, and all of these lead
 * to different ways to get to the top, we can write the following equation for the number
 * of steps F(n,k)
 *
 * 所以F(n, k) = sum(F(n-i, k)) i = 1...k
 *
 * total = 4, k = 2;
 *
 * F(4, 2) = F(4-2, 2) + F(4-1, 2). Recursing, F(4-2, 2) = F(4-2-2, 2) + F(4-2-1, 2).
 * Both F(0, 2) and F(1, 2) are base-cases, with a value of 1,so F(4-2, 2) = 2.
 * Continuing with F(4-1,2), F(4-1, 2) = F(4-1-2, 2) + F(4-1-1,2).
 * The first term is a base-case, with a value of 1. The second term has already been
 * computed—its value is 2. Therefore, F(4 — 1, 2) = 3, and F(4, 2) = 3 + 2.
 *
 */
public class P12_Stairs {

  /**
   * dp方法
   *
   * cache values of F(i,k), 0 < i < n to improve time complexity
   *
   * We take O(k) time to fill in each entry, so the total time complexity is0(kn).
   * The space complexity is O(n).
   * @param n
   * @param k
   * @return
   */
  public static int dpSolution(int n, int k) {
    int[] ways = new int[n + 1];
    return dpSolution(n, k, ways);
  }

  private static int dpSolution(int n, int k, int[] ways) {
    if (n <= 1) {
      return 1;
    }

    if (ways[n] == 0) {
      // n - i >= 0; 一般而言, n 是大于k的。
      for (int i = 1; i <= k && (n - i) >= 0; i++) {
        ways[n] += dpSolution(n - i, k, ways);
      }
    }

    return ways[n];
  }

  public static void main(String[] args) {
    int k = 2;
    for (int i = 0; i <= 25; i++) {
      System.out.println(dpSolution(i, k));
    }
  }

}
