package eopi.ch17_dp;

/**
 * Author by darcy
 * Date on 17-8-24 下午3:24.
 * Description:
 * find the maximum sum over all subarrays of a given array of integer.
 *
 * input
 * 904 40 523 12 -335 -385 -124 481 -31
 * output
 * 第0~3个数和加起来最大.
 */
public class P2_MaximumSumOfSubArrayInArray {

  /**
   * O(n^3)的时间复杂度, 因为总共有n(n + 1) / 2个子数组，
   * 每个子数组的求和是O(n)的复杂度.
   * @param array
   * @return
   */
  public static int bruteForceSolution(int[] array) {
    int max = Integer.MIN_VALUE;
    for (int i = 0; i < array.length; i++) {
      for (int j = i; j < array.length; j++) {
        int temp = sum(array, i, j);
        if (temp > max) {
          max = temp;
        }
      }
    }
    return max;
  }

  private static int sum(int[] array, int i, int j) {
    int result = 0;
    for (int k = i; k <= j; k++) {
      result += array[k];
    }
    return result;
  }

  /**
   * 使用O(n)的空间存储所有S[j] = sum-array[0:j]的和
   * 然后array[i:j] = S[j] - S[i - 1],
   * 当i=0时, S[-1] = 0
   *
   * 总的时间复杂度是O(n^2), 空间复杂度O(n)
   * @param array
   * @return
   */
  public static int betterBruteForceSolution(int[] array) {
    int[] partialSum = new int[array.length];

    // 计算部分和的事件复杂度是O(n^2)
    for (int i = 0; i < array.length; i++) {
      partialSum[i] = sum(array, 0, i);
    }

    int max = Integer.MIN_VALUE;
    int temp = 0;

    /**
     * 求子数组最大和的时间复杂度也是O(n^2)
     */
    for (int i = 0; i < array.length; i++) {
      for (int j = i; j < array.length; j++) {
        if (i != 0) {
          temp = partialSum[j] - partialSum[i - 1];
        } else {
          temp = partialSum[j];
        }

        if (temp > max) {
          max = temp;
        }
      }
    }

    return max;
  }

  /**
   * Dynamic Pragramming 动归的解法;
   * 以j结尾的最大子数组的和 = S[j] - minS[k] (k <=j), 即目前的所有数的总和 - 以k结尾的最小子数组和.
   * 所以需要记录到目前为止的最小子数组的和.
   *
   * 最大子数组的和 = 当前总和 - 当前最小和
   *
   * 官方吐槽
   * A natural thought is to assume we have the solution for the subarray A[0 : n — 2].
   * However, even if we knew the largest sum subarray for subarray A[0 : n — 2], it
   * does not help us solve the problem for A[0 : n — 1], Instead we need to know the
   * subarray amongst all subarrays A[0 : i], i < n - 1, with the smallest subarray sum.
   * The desired value is S[n - 1] minus this subarray's sum. We compute this value by
   * iterating through the array. For each index j, the maximum subarray sum ending at
   * j is equal to S[j] - minS[k] (k<=j). During the iteration, we track the minimum S[k]
   * we have seen so far and compute the maximum subarray sum for each index. The time
   * spent per index is constant, leading to an O(n) time and O(1) space solution. It is
   * legal for all array entries to be negative, or the array to be empty. The algorithm
   * handles these input cases correctly.
   * @param array
   * @return
   */
  public static int dpSolution(int[] array) {
    int sum = 0;
    // 注意: 如果min初始化为Integer的最大值, 那么执行第一次判定的时候,
    // min一定被设置为第一个数的值, 但是它实际上应该是0;

    /*int min = Integer.MAX_VALUE;
    int max = Integer.MIN_VALUE;*/

    int min = 0;
    int max = 0;
    for (int i = 0; i < array.length; i++) {
      sum += array[i];
      // 记录到目前为止的最小子数组的和.
      if (sum < min) {
        min = sum;
      }

      // 求目前为止的最大子数组的和.
      if (sum - min > max) {
        max = sum - min;
      }
    }
    return max;
  }

  public static void main(String[] args) {
//    int[] array = {904, 40, 523, 12, -335, -385, -124, 481, -31};
    int[] array = {904, 40, 523, -335, 350, -385, -124, 481, -31};
    System.out.println(bruteForceSolution(array));

    System.out.println(betterBruteForceSolution(array));

    System.out.println(dpSolution(array));
  }

}
