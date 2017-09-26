package eopi.ch12_search;

import java.util.Arrays;
import java.util.List;

/**
 * Author by darcy
 * Date on 17-9-24 下午7:31.
 * Description:
 * <p>
 * 搜索排序数组中元素k第一次出现的位置.
 * <p>
 * Write a method that takes a sorted array and a key and returns the index of the
 * first occurrence of that key in the array. For example, when applied to the array in
 * Figure 12.1 your algorithm should return 3 if the given key is 108; if it is 285, your
 * algorithm should return 6.
 * <p>
 * Hint: What happens when every entry equals k? Don't stop when you first see k
 */
public class P12_1_SearchASortedArrayForFirstOccurrenceOfK {

  /**
   * binarySearch的过程就是逐步缩小候选集合.
   * 如果特定位置的元素 == k, 那么记录当前位置, 继续进入左子树中找数据.
   * <p>
   * Let's apply the above logic to the given example, with k = 108. We start with all
   * indices as candidates, i.e., with [0,9], The midpoint index, 4 contains k. Therefore we
   * can now update the candidate set to [0,3], and record 4 as an occurrence of k. The
   * next midpoint is1, and this index contains -10. We update the candidate set to [2,3].
   * The value at the midpoint 2 is 2, so we update the candidate set to [3,3], Since the
   * value at this midpoint is 108, we update the first seen occurrence of k to 3. Now the
   * interval is [3, 2], which is empty, terminating the search—the result is 3.
   *
   * @param A
   * @param k
   * @return
   */
  public static int searchFirstOfK(List<Integer> A, int k) {
    int left = 0, right = A.size() - 1, result = -1;
    // A.subList(left , right + 1) is the candidate set.
    while (left <= right) {
      int mid = left + ((right - left) / 2);
      if (A.get(mid) > k) {
        right = mid - 1;
      } else if (A.get(mid) == k) {
        result = mid;
        // Nothing to the right of mid can be the first occurrence of k.
        // 左边是否还有和当前元素相等的元素.
        right = mid - 1;
      } else { // A.get (mid) < k
        left = mid + 1;
      }
    }
    return result;
  }

  public static void main(String[] args) {
    List<Integer> list = Arrays.asList(-14, -10, 2, 108, 108, 243, 285, 285, 401);
    int result = searchFirstOfK(list, 108);
    System.out.println(result);
  }

}
/*
Variant: Design an efficient algorithm that takes a sorted array and a key, and finds
the index of the first occurrence of an element greater than that key. For example,
when applied to the array in Figure12.1 on the preceding page your algorithm should
return 9 if the key is 285; if it is -13, your algorithm should return 1.

Variant: Let A be an unsorted array of n integers, with A[0] > A[l] and A[n - 2] <
A[n-1]. Call an index i a local minimum if A[i\ is less than or equal to its neighbors.
How would you efficiently find a local minimum, if one exists?

Variant: Write a program which takes a sorted array A of integers, and an integer k,
and returns the interval enclosing k,i.e., the pair of integers L and U such that L is the
first occurrence of k in A and U is the last occurrence of k in A. If k does not appear
in A, return [-1,-1], For example if A = (1,2,2,4,4,4,7,11,11,13) and k = 11, you
should return [7,8].

Variant: Write a program which tests if p is a prefix of a string in an array of sorted
strings.
 */
