package com.darcy.main.cleancode_v1_0_3.BinarySearch;

/**
 * Author by darcy
 * Date on 17-9-4 下午6:45.
 * Description:
 *
 * 给定已序数组, rotate后找出最小的数.
 *
 * Suppose a sorted array is rotated at some pivot unknown to you beforehand.
 * (i.e., 0 1 2 4 5 6 7 might become 4 5 6 7 0 1 2).
 * Find the minimum element.
 * You may assume no duplicate exists in the array.
 *
 */
public class P49_FindMinimuminSortedRotatedArray {

  /**
   *
   *  Let’s say we subdivide the array at point k to two
   *  subarrays [AL, AL+1, …, Ak], [Ak+1, …, AR].
   *
   * Let’s assume we choose M1 as the dividing point. Since AM1 > AR, we know that each
   * element in [AL … AM1] is greater than AR (Remember that AL > AR?). Therefore, the
   * minimum value must locate in [AM1+1 … AR].
   *
   * On the other hand, let’s assume we choose M2 as the dividing point. Since AM2 ≤ AR, we
   * know that each element in [AM2+1 … AR] is greater than AM2. Therefore, the minimum
   * point must locate in [AL … AM2].
   * @param A
   * @return
   */
  public static int findMin(int[] A) {
    int L = 0, R = A.length - 1;
    while (L < R && A[L] >= A[R]) {
      int M = (L + R) / 2;
      // 切分元素比末尾元素大, 那么切分点是在前面的递增序列中
      // 所以L = M + 1;
      if (A[M] > A[R]) {// 最小元素肯定不是A[M]
        L = M + 1;
      } else { // 最小元素可能是A[R]
        // A[M] <= A[R], 那么切分点在后面的递增序列中。
        // 即最小值的index <= M;
        R = M;
      }
    }
    return A[L];
  }

  public static void main(String[] args) {
    int[] array = {8, 9, 10, 11, 1, 2, 3, 4, 5, 6, 7};
    int result = findMin(array);
    System.out.println(result);
  }

}
