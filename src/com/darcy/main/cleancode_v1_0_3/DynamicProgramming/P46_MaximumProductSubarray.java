package com.darcy.main.cleancode_v1_0_3.DynamicProgramming;

/**
 * Author by darcy
 * Date on 17-9-4 下午3:22.
 * Description:
 *
 * 给定数组,找到连续子数组的乘积最大.
 *
 * Find the contiguous subarray within an array of integers that has the largest product. For
 * example, given the array [2,3,-2,4], the contiguous subarray [2,3] has the largest product
 * = 6.
 *
 */
public class P46_MaximumProductSubarray {

  public static int solution(int[] array) {
    int maxContiguous = array[0];
    int maxCurrent = array[0];
    for (int i = 1; i < array.length; i++) {
      maxContiguous = Math.max(maxContiguous * array[i], array[i]);
      maxCurrent = Math.max(maxContiguous, maxCurrent);
    }

    return maxCurrent;
  }

  /**
   *
   * 记录连续乘积最大值的同时记录连续乘积最小值.
   *
   * Besides keeping track of the largest product, we also need to keep track of
   * the smallest product. Why? The smallest product, which is the largest in the negative
   * sense could become the maximum when being multiplied by a negative number.
   * Let us denote that:
   *
   * f(k) = Largest product subarray, from index 0 up to k.
   * Similarly,
   * g(k) = Smallest product subarray, from index 0 up to k.
   * Then,
   * f(k) = max( f(k-1) * A[k], A[k], g(k-1) * A[k] )
   * g(k) = min( g(k-1) * A[k], A[k], f(k-1) * A[k] )
   * There we have a dynamic programming formula. Using two arrays of size n, we could
   * deduce the final answer as f(n-1). Since we only need to access its previous elements at
   * each step, two variables are sufficient.
   * @param A
   * @return
   */
  public static int maxProduct(int[] A) {
    assert A.length > 0;
    int max = A[0], min = A[0], maxAns = A[0];
    for (int i = 1; i < A.length; i++) {
      int mx = max, mn = min;
      max = Math.max(Math.max(A[i], mx * A[i]), mn * A[i]);
      min = Math.min(Math.min(A[i], mx * A[i]), mn * A[i]);
      maxAns = Math.max(max, maxAns);
    }
    return maxAns;
  }

  public static void main(String[] args) {
    int[] array = {2, 3, -2, -2,  4};
//    int[] array = {2, 3, -2,,  4};
    int result = solution(array);
    System.out.println(result);

    // 记录最小值的意义就在于这里.
    int result2 = maxProduct(array);
    System.out.println(result2);
  }
}
