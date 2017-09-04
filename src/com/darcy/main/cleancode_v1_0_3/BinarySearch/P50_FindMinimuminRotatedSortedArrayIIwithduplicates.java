package com.darcy.main.cleancode_v1_0_3.BinarySearch;

/**
 * Author by darcy
 * Date on 17-9-4 下午7:08.
 * Description:
 *
 * 已序的包含重复元素的数组, rotate之后找出最小值.
 *
 * If the rotated sorted array could contain duplicates? Is your algorithm still O(log n) in
 * runtime complexity?
 *
 */
public class P50_FindMinimuminRotatedSortedArrayIIwithduplicates {

  /**
   * For case where AL == AM == AR, the minimum could be on AM’s left or right side (eg,
   * [1, 1, 1, 0, 1] or [1, 0, 1, 1, 1]). In this case, we could not discard either subarrays and
   * therefore such worst case degenerates to the order of O(n).
   * @param A
   * @return
   */
  public static int findMin(int[] A) {
    int L = 0, R = A.length - 1;
    while (L < R && A[L] >= A[R]) {
      int M = (L + R) / 2;
      // A[M] > A[R], M在前面的递增序列中
      // 最小值肯定不是A[M]
      if (A[M] > A[R]) {
        L = M + 1;
        // A[M] < A[L], 在后面的递增序列中.
        // A[M]可能就是最小值.
      } else if (A[M] < A[L]) {
        R = M;
        // A[M] == A[L],
        // 重复元素的情况.
      } else { // A[L] == A[M] == A[R]
        L = L + 1;
      }
    }
    return A[L];
  }

  public static void main(String[] args) {
    int[] array = {1, 1, 0, 1};
    int min = findMin(array);
    System.out.println(min);

  }
}
