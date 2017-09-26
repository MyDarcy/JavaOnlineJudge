package eopi.ch12_search;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Random;

/**
 * Author by darcy
 * Date on 17-9-24 下午9:06.
 * Description:
 * <p>
 * 数组中找第k大的数字.
 * <p>
 * Design an algorithm for computing the kth largest element in an array. Assume
 * entries are distinct.
 * <p>
 * Hint: Use divide and conquer in conjunction with randomization.
 */
public class P12_8_FindKthLargetNumber {

  /**
   * 找第k大的数字.
   * 1. 排序. 然后index = k-1的就是. 做了很多不必要的工作.
   * 2. size = k的最小堆. 遍历一遍元素, 堆顶的就是所需要的值.
   * 3. 利用快排partition的思想.
   * 如果多于k-1个元素比pivot元素大,那么k大的元素在大的那一边.
   * 如果少于k-1个元素比pivot元素大,那么k大的元素在小的那一边.
   * <p>
   * T(N) = O(N) + T(N/2);
   * 所以时间复杂度是O(N), 空间复杂度是O(1)
   * <p>
   * Intuitively, this is a good approach because on average we reduce by
   * half the number of entries to be considered.
   *
   * @param list
   * @param k
   * @return
   */
  public static int findKth(List<Integer> list, int k) {
    int left = 0;
    int right = list.size() - 1;
    Random random = new Random(31);
    while (left <= right) {
      // pivotIndex的选取.
      int pivotIndex = random.nextInt(right - left + 1) + left;
      int newPivotIndex = partitionFindIndex(left, right, pivotIndex, list);
      if (newPivotIndex == k - 1) {
        return list.get(newPivotIndex);
      } else if (newPivotIndex > k - 1) {
        right = newPivotIndex - 1;
      } else {
        left = newPivotIndex + 1;
      }
    }
    return Integer.MIN_VALUE;
  }

  /**
   * 比array[pivotIndex]值大的元素都移动到左边.
   * 比array[pivotIndex]值小的元素都移动到右边.
   *
   * @param left
   * @param right
   * @param pivotIndex
   * @param list
   * @return
   */
  private static int partitionFindIndex(int left, int right, int pivotIndex, List<Integer> list) {
    int pivotValue = list.get(pivotIndex);
    int newPivotIndex = left;

    // pivotValue现在在最右边.
    Collections.swap(list, pivotIndex, right);

    for (int i = left; i < right; i++) {

      // 大的元素都移动到左边. newPivotIndex现在的值就表示有多少个value比pivotValue大.
      if (Integer.compare(list.get(i), pivotValue) > 0) {
        Collections.swap(list, i, newPivotIndex++);
      }
    }

    Collections.swap(list, right, newPivotIndex);
    return newPivotIndex;
  }

  public static void main(String[] args) {
    List<Integer> list =
        Arrays.asList(1, 4, 9, 10, 20, 28, 4, 9, 40, 10, 15, 30, 9, 19, 100, 191);

    int number = findKth(list, 3);

//    list.sort((a, b) -> a - b);
    System.out.println(list);
    System.out.println(number);
  }

}
/*
Variant: Design an algorithm for finding the median of an array.

Variant: Design an algorithm for finding the kth largest element of A in the presence
of duplicates. The kth largest element is defined to be A[k-1] after A has been sorted
in a stable manner, i.e., if A[i] = A[j] and i < j then A[i] must appear before A[j] after
stable sorting


 */