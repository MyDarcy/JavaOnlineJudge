package eopi.ch12_search;

import java.util.List;

/**
 * Author by darcy
 * Date on 17-9-24 下午7:45.
 * Description:
 * <p>
 * 排序数组中找index = value的 index.
 * <p>
 * Design an efficient algorithm that takes a sorted array of distinct integers, and returns
 * an index i such that the element at index i equals i. For example, when the input is
 * (-2,0, 2,3,6,7,9) your algorithm should return 2 or 3.
 */
public class P12_2_SearchASortedArrayForEntryEqualsToItsIndex {

  /**
   * 根据mid索引和array[mid]之间的关系确定候选集区间.
   * 时间复杂度O(logN)
   * <p>
   * <p>
   * Observe that if A[j] > j, then no entry after j can satisfy the given criterion.
   * This is because each element in the array is at least1 greater than the previous
   * element. For the same reason, if A[j] < j, no entry before j can satisfy the given
   * criterion.
   *
   * @param A
   * @return
   */
  public static int searchEntryEqualToItsIndex(List<Integer> A) {
    int left = 0, right = A.size() - 1;
    while (left <= right) {
      int mid = left + ((right - left) / 2);
      int difference = A.get(mid) - mid;
      // A.get (mid) == mid if and only if difference == 8.
      if (difference == 0) {
        return mid;
        // 在左边.
      } else if (difference > 0) {
        right = mid - 1;

        // 在右边.
      } else { // difference < 8.
        left = mid + 1;
      }
    }
    return -1;
  }

}
