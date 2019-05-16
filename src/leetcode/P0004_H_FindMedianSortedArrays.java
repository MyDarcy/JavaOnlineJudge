package leetcode;

/**
 * There are two sorted arrays nums1 and nums2 of size m and n respectively.
 *
 * Find the median of the two sorted arrays. The overall run time complexity should be O(log (m+n)).
 *
 * You may assume nums1 and nums2 cannot be both empty.
 *
 * Example 1:
 *
 * nums1 = [1, 3]
 * nums2 = [2]
 *
 * The median is 2.0
 * Example 2:
 *
 * nums1 = [1, 2]
 * nums2 = [3, 4]
 *
 * The median is (2 + 3)/2 = 2.5
 *
 * 思考；
 * 两个已序数组找中位数，时间复杂度 O(log (m+n))；
 * 1. 不能遍历或者排序；
 * 2. 类似二分的机制；
 * 3. 如何整体当做已序考虑；
 *
 * https://leetcode.com/problems/median-of-two-sorted-arrays/discuss/2481/Share-my-O(log(min(mn))-solution-with-explanation
 */
public class P0004_H_FindMedianSortedArrays {
  public double findMedianSortedArrays(int[] nums1, int[] nums2) {

  }
}
