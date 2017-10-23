package com.darcy.main.cleancode_v1_0_3.DynamicProgramming;

/**
 * Author by darcy
 * Date on 17-9-4 下午2:54.
 * Description:
 *
 * 找连续子数组的最大和.
 *
 * Find the contiguous subarray within an array (containing at least one number) that has the
 * largest sum.
 * For example, given the array [2, 1, –3, 4, –1, 2, 1, –5, 4],
 * The contiguous array [4, –1, 2, 1] has the largest sum = 6.
 *
 */
public class P45_MaximumSumSubarray {

  /**
   * f(i) 以元素i结尾的最大值.
   * f(i) = f(i - 1) + A[i]
   *
   * O(n)的时间复杂度,O(1)的空间复杂度.
   *
   * maxEndingHere and maxSoFar; the former is the maximum sum of subarray that
   * must end at index k, while the latter is the global maximum subarray sum.
   *
   * @param A
   * @return
   */
  public static int maxSubArray(int[] A) {
    int maxEndingHere = A[0], maxSoFar = A[0];
    for (int i = 1; i < A.length; i++) {
      // 连续性. maxEndingHere + A[i]来保证.
      // 是和之前的元素一起组成max还是当前元素另器炉灶得到max.
      maxEndingHere = Math.max(maxEndingHere + A[i], A[i]);
      maxSoFar = Math.max(maxEndingHere, maxSoFar);
    }
    return maxSoFar;
  }


  /**
   *
   * 分治算法.
   *
   * @param A
   * @return
   */
  public static int maxSubArray2(int[] A) {
    return maxSubArrayHelper(A, 0, A.length - 1);
  }
  private static int maxSubArrayHelper(int[] A, int L, int R) {
    if (L > R) return Integer.MIN_VALUE;
    int M = (L + R) / 2;
    int leftAns = maxSubArrayHelper(A, L, M - 1);
    int rightAns = maxSubArrayHelper(A, M + 1, R);
    int lMaxSum = 0;
    int sum = 0;
    for (int i = M - 1; i >= L; i--) {
      sum += A[i];
      lMaxSum = Math.max(sum, lMaxSum);
    }
    int rMaxSum = 0;
    sum = 0;
    for (int i = M + 1; i <= R; i++) {
      sum += A[i];
      rMaxSum = Math.max(sum, rMaxSum);
    }
    return Math.max(lMaxSum + A[M] + rMaxSum, Math.max(leftAns, rightAns));
  }

  public static void main(String[] args) {
    int[] array = {2, 1, -3, 4, -1, 2, 1, -5, 4};
    int result = maxSubArray(array);
    System.out.println(result);

    int[] array2 = {1, 2, 3, 4, -1, -2, -3, -4, 5, 6};
    int result2 = maxSubArray(array2);
    System.out.println(result2);
  }

}
