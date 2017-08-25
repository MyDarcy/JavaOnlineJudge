package eopi.ch17_dp;

/**
 * Author by darcy
 * Date on 17-8-25 上午8:31.
 * Description:
 * C(n, k) = n! / ((n-k)! * k!), 但是即使最终的计算结果没有溢出, 但是阶乘的计算很容易溢出。
 * 所以设计一个算法, 只要最终结果没有没有超过一个integer的字长，那么它永远都不会溢出.
 *
 * Design an efficient algorithm for computing C(n,k) which has the property that
 * it neveroverflows if the final result fits in the integer word size.
 *
 * 结论:
 * The number of subproblems is O(nk) and once (n-1, k) and (n -1, k -1) are known, (n, k) can be
 * computed in O(1) time, yielding an O(nk) time complexity. The space complexity is
 * also O(nk); it can easily be reduced to O(k).
 */
public class P6_NoOverflowing {

  /**
   * 暴力破解的方法.
   *
   * 直接求乘积然后除,很容易溢出.
   *
   * @param n
   * @param k
   * @return
   */
  public static long bruteForceSolution(int n, int k) {

    return permutation(n) / ( permutation(n - k) * permutation(k));

  }

  private static long permutation(long i) {

    if (i == 1) {
      return 1;
    }

    return i * permutation(i - 1);
  }

  /**
   *
   * 重要的是发现如下的公式:
   * C(n, k) = C(n - 1, k - 1) 　　+　　 C(n - 1, k)
   *           包含了最后一个元素　　　　不包含最后一个元素
   * 同时C(0, 0) = C(r, r) = 1;
   *
   * 但是直接这样的实现的时间复杂度是指数级别的.存在大量的重复调用.可以缓存中间结果
   * 用DP的思想来做.
   *
   *
   *
   * 官方吐槽:
   * Consider the nth element in the initial set. A subset of size k will either contain
   * this element, or not contain it. This is the basis for a recursive enumeration—find
   * all subsets of size k -1 amongst the first n -1 elements and add the nth element into
   * these sets, and then find all subsets of size k amongst the first n -1 elements. The
   * union of these two subsets is all subsets of size k.
   * @param n
   * @param k
   * @return
   */
  public static int dpSolution(int n, int k) {
    int[][] array = new int[n + 1][k + 1];
    return dpSolution(n, k, array);
  }

  private static int dpSolution(int n, int k, int[][] array) {
    // C(n, 0) = C(n, n) = 1;
    if (k == 0 || n == k) {
      return 1;
    }

    // （从前n - 1个元素中选择k个元素) + (从前　n - 1个元素中选择　k - 1个元素,然后选择第k个元素)
    if (array[n][k] == 0) {
      int withoutK = dpSolution(n - 1, k, array);
      int withK = dpSolution(n - 1, k - 1, array);
      array[n][k] = withK + withoutK;
    }

    return array[n][k];

  }

  /**
   *
   *
   *
   * Explanation:
   * 1==========>> n = 0, C(0,0) = 1
   * 1–1========>> n = 1, C(1,0) = 1, C(1,1) = 1
   * 1–2–1======>> n = 2, C(2,0) = 1, C(2,1) = 2, C(2,2) = 1
   * 1–3–3–1====>> n = 3, C(3,0) = 1, C(3,1) = 3, C(3,2) = 3, C(3,3)=1
   * 1–4–6–4–1==>> n = 4, C(4,0) = 1, C(4,1) = 4, C(4,2) = 6, C(4,3)=4, C(4,4)=1
   * So here every loop on i, builds i’th row of pascal triangle, using (i-1)th row
   *
   * value for those elements comes from previous iteration.In statement,
   * C[j] = C[j] + C[j-1]
   *
   * C(4, 2) = C(3, 2) + C(3, 1)
   *         = C(2, 2) + C(2, 1) + C(2, 1) + C(2, 0)
   *         = 1 + 2 * (C(1, 1) + C(1, 0)) + C(2, 0)
   *         = 1 + 2 * (1 + 1) + 1
   *         = 6
   *
   * http://www.geeksforgeeks.org/dynamic-programming-set-9-binomial-coefficient/
   *
   * @param n
   * @param k
   * @return
   */
  public static int lessSpaceComplexityDPSolution(int n, int k) {
    int[] cache = new int[k + 1];
    int i, j, result;

    cache[0] = 1;

    for (i = 1; i <= n; i++) {
      for (j = Math.min(i, k); j > 0; j-- ) {
        // j从到大到小遍历, 因为cache[j]此时为0, 而上一层的边界cache[j-1]为1;
        cache[j] = cache[j] + cache[j - 1];
      }
    }
    result = cache[k];
    return result;
  }

  public static void main(String[] args) {
    System.out.println(bruteForceSolution(25, 5));
    System.out.println(permutation(25));
    System.out.println(permutation(20) + " " + permutation(5));

    System.out.println(dpSolution(25, 5));
    System.out.println(lessSpaceComplexityDPSolution(25, 5));

    System.out.println(lessSpaceComplexityDPSolution(4, 2));

  }

}
