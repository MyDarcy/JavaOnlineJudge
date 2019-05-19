package leetcode;

import java.util.Arrays;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.IntStream;

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
 * 2. 类似二分的机制；(如何构造这个二分的机制，)
 * 3. 如何整体当做已序考虑；
 *
 * https://leetcode.com/problems/median-of-two-sorted-arrays/discuss/2481/Share-my-O(log(min(mn))-solution-with-explanation
 */
public class P0004_H_FindMedianSortedArrays {

  /**
   * "What is the use of median". In statistics, the median is used for dividing a set into two equal length subsets,
   * that one subset is always greater than the other. If we understand the use of median for dividing,
   * we are very close to the answer.
   *
   * First let's cut A into two parts at a random position i:
   *
   *       left_A             |        right_A
   * A[0], A[1], ..., A[i-1]  |  A[i], A[i+1], ..., A[m-1]
   *
   * since A has m elements, so there are m+1 kinds of cutting( i = 0 ~ m ). And we know: len(left_A) = i,
   * len(right_A) = m - i . Note: when i = 0 , left_A is empty, and when i = m , right_A is empty.
   *
   * With the same way, cut B into two parts at a random position j:
   *
   *       left_B             |        right_B
   * B[0], B[1], ..., B[j-1]  |  B[j], B[j+1], ..., B[n-1]
   * Put left_A and left_B into one set, and put right_A and right_B into another set. Let's name them left_part and right_part :
   *
   *       left_part          |        right_part
   * A[0], A[1], ..., A[i-1]  |  A[i], A[i+1], ..., A[m-1]
   * B[0], B[1], ..., B[j-1]  |  B[j], B[j+1], ..., B[n-1]
   *
   * If we can ensure:
   *
   * 1) len(left_part) == len(right_part)
   * 2) max(left_part) <= min(right_part)
   * then we divide all elements in {A, B} into two parts with equal length, and one part is always greater than the other. Then median = (max(left_part) + min(right_part))/2.
   *
   * To ensure these two conditions, we just need to ensure:
   *
   * (1) i + j == m - i + n - j (or: m - i + n - j + 1)
   *     if n >= m, we just need to set: i = 0 ~ m, j = (m + n + 1)/2 - i
   * (2) B[j-1] <= A[i] and A[i-1] <= B[j]
   *
   * 0 < i < m (较短的序列); j = (m + n + 1) / 2 - i;  i + j = (m + n + 1) / 2 即中位数的位置;
   *
   * And we can do a binary search following steps described below:
   *
   * 算法执行伪代码;
   * <1> Set imin = 0, imax = m, then start searching in [imin, imax]
   * <2> Set i = (imin + imax)/2, j = (m + n + 1)/2 - i
   * <3> Now we have len(left_part)==len(right_part). And there are only 3 situations
   *      that we may encounter:
   *     <a> B[j-1] <= A[i] and A[i-1] <= B[j]
   *         Means we have found the object `i`, so stop searching.
   *     <b> B[j-1] > A[i]
   *         Means A[i] is too small. We must `ajust` i to get `B[j-1] <= A[i]`.
   *         Can we `increase` i?
   *             Yes. Because when i is increased, j will be decreased.
   *             So B[j-1] is decreased and A[i] is increased, and `B[j-1] <= A[i]` may
   *             be satisfied.
   *         Can we `decrease` i?
   *             `No!` Because when i is decreased, j will be increased.
   *             So B[j-1] is increased and A[i] is decreased, and B[j-1] <= A[i] will
   *             be never satisfied.
   *         So we must `increase` i. That is, we must ajust the searching range to
   *         [i+1, imax]. So, set imin = i+1, and goto <2>.
   *     <c> A[i-1] > B[j]
   *         Means A[i-1] is too big. And we must `decrease` i to get `A[i-1]<=B[j]`.
   *         That is, we must ajust the searching range to [imin, i-1].
   *         So, set imax = i-1, and goto <2>.
   *
   * When the object i is found, the median is:
   *
   * max(A[i-1], B[j-1]) (when m + n is odd)
   * or (max(A[i-1], B[j-1]) + min(A[i], B[j]))/2 (when m + n is even)
   *
   * Now let's consider the edges values i=0,i=m,j=0,j=n where A[i-1],B[j-1],A[i],B[j] may not exist. Actually this situation is easier than you think.
   * What we need to do is ensuring that max(left_part) <= min(right_part). So, if i and j are not edges values(means A[i-1],B[j-1],A[i],B[j] all exist),
   * then we must check both B[j-1] <= A[i] and A[i-1] <= B[j]. But if some of A[i-1],B[j-1],A[i],B[j] don't exist, then we don't need to check one(or both)
   * of these two conditions. For example, if i=0, then A[i-1] doesn't exist, then we don't need to check A[i-1] <= B[j]. So, what we need to do is:
   * Searching i in [0, m], to find an object `i` that:
   *     (j == 0 or i == m or B[j-1] <= A[i]) and
   *     (i == 0 or j == n or A[i-1] <= B[j])
   *     where j = (m + n + 1)/2 - i
   * And in a searching loop, we will encounter only three situations:
   *
   * <a> (j == 0 or i == m or B[j-1] <= A[i]) and
   *     (i == 0 or j = n or A[i-1] <= B[j])
   *     Means i is perfect, we can stop searching.
   *
   * <b> j > 0 and i < m and B[j - 1] > A[i]
   *     Means i is too small, we must increase it.
   *
   * <c> i > 0 and j < n and A[i - 1] > B[j]
   *     Means i is too big, we must decrease it.
   *
   * @param nums1
   * @param nums2
   * @return
   */
  public double findMedianSortedArrays(int[] nums1, int[] nums2) {

    int m = nums1.length;
    int n = nums2.length;
    if (m > n) {
      int[] temp = nums1;
      nums1 = nums2;
      nums2 = temp;
      m = nums1.length;
      n = nums2.length;
    }
    System.out.println("in func: num1:" + Arrays.toString(nums1));
    System.out.println("in func: num2:" + Arrays.toString(nums2));

    int min = 0;
    int max = m;
    int halfLength = (m + n + 1) / 2;
    double medium = 0.0;
    while (min <= max) {
      int i = (min + max) / 2; // i 的取值范围 0 ~ m
      int j = halfLength - i;  // i + j 中间元素的位置;
      if (i < m && nums2[j - 1] > nums1[i]) { // i is too small
        min = i + 1;
      } else if (i > 0 && nums1[i - 1] > nums2[j]) { // i is too large
        max = i - 1;
      } else {
        // right answer
        int leftMax = Integer.MAX_VALUE;
        if (i == 0) {
          leftMax = nums2[j - 1];
        } else if (j == 0) {
          leftMax = nums1[i - 1];
        } else {
          leftMax = Integer.max(nums1[i - 1], nums2[j - 1]);
        }

        if ((m + n) % 2 == 1) {
          medium = leftMax;
          break;
        }

        int rightMin = Integer.MIN_VALUE;
        if (i == m) {
          rightMin = nums2[j];
        } else if (j == n) {
          rightMin = nums1[i];
        } else {
          rightMin = Integer.min(nums1[i], nums2[j]);
        }

        medium = (leftMax + rightMin) / 2.0;
        break;
      }
    }
    return medium;
  }

  /**
   * 官方答案：
   * @param A
   * @param B
   * @return
   */
  public double findMedianSortedArrays2(int[] A, int[] B) {
    int m = A.length;
    int n = B.length;
    if (m > n) { // to ensure m<=n
      int[] temp = A; A = B; B = temp;
      int tmp = m; m = n; n = tmp;
    }
    int iMin = 0, iMax = m, halfLen = (m + n + 1) / 2;
    while (iMin <= iMax) {
      int i = (iMin + iMax) / 2;
      int j = halfLen - i;
      if (i < iMax && B[j-1] > A[i]){
        iMin = i + 1; // i is too small
      }
      else if (i > iMin && A[i-1] > B[j]) {
        iMax = i - 1; // i is too big
      }
      else { // i is perfect
        int maxLeft = 0;
        if (i == 0) { maxLeft = B[j-1]; }
        else if (j == 0) { maxLeft = A[i-1]; }
        else { maxLeft = Math.max(A[i-1], B[j-1]); }
        if ( (m + n) % 2 == 1 ) { return maxLeft; }

        int minRight = 0;
        if (i == m) { minRight = B[j]; }
        else if (j == n) { minRight = A[i]; }
        else { minRight = Math.min(B[j], A[i]); }

        return (maxLeft + minRight) / 2.0;
      }
    }
    return 0.0;
  }

  public static void main(String[] args) {
    Random random = ThreadLocalRandom.current();
    int[] num1 = IntStream.range(0, 10).map(i -> random.nextInt(100)).toArray();
    int[] num2 = IntStream.range(0, 20).map(i -> random.nextInt(100) + 100).toArray();
    Arrays.sort(num1);
    Arrays.sort(num2);
    P0004_H_FindMedianSortedArrays instance = new P0004_H_FindMedianSortedArrays();
    System.out.println(Arrays.toString(num1));
    System.out.println(Arrays.toString(num2));
    System.out.println(instance.findMedianSortedArrays(num1, num2));
  }
}
