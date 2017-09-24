package eopi.ch12_search;

import java.util.Arrays;
import java.util.List;

/**
 * Author by darcy
 * Date on 17-9-24 下午7:56.
 * Description:
 * <p>
 * 在循环移位排序数组中搜索最小的元素.
 * 　相当于就是一个排序的数组进行rotate然后在其中搜索特定的元素.
 * Design an O(log n) algorithm for finding the position of the smallest element in a
 * cyclically sorted array. Assume all elements are distinct. For example, for the array
 * in Figure 12.2 on the next page, your algorithm should return 4.
 * <p>
 * [378, 478, 550, 631, 103, 203, 220, 234, 279, 368]
 * <p>
 * Hint: Use the divide and conquer principle.
 */

public class P12_3_SearchARotateSortedArray {

  /**
   * 参考:
   * com.darcy.main.cleancode_v1_0_3.BinarySearch.P49_FindMinimuminSortedRotatedArray
   * 考虑数组元素部分有序的性质.
   * 并且前半部分比后半部分大. 并且前半部分, 后半部分都是有序的.
   * 时间复杂度是O(logN), 空间复杂度是O(logN)
   *
   * @param list
   * @return
   */
  public static int solution(List<Integer> list) {
    int L = 0;
    int R = list.size() - 1;
    while (L < R) { // while(L <　R && list.get(L) >= list.get(R)) // 重复元素的情况.
      int M = L + (R - L) / 2;
      // 比右边元素大, 那么当前是在左边较大的部分.
      if (list.get(M) > list.get(R)) {
        L = M + 1;

        // 比右边元素小, 那么当前可能是最小的.
      } else {
        R = M;
      }

      /*else if (list.get(M) < list.get(R)) {
        R = M;
        //
      } else {
        L = L + 1;
      }*/
    }

    // 结束的时候L == R
    return L;
  }

  public static void main(String[] args) {
    List<Integer> list = Arrays.asList(378, 478, 550, 631, 103, 203, 220, 234, 279, 368);
    int result = solution(list);
    System.out.println(result);
  }

}

/*
Variant: A sequence is strictly ascending if each element is greater than its prede¬
cessor. Suppose it is known that an array A consists of a strictly ascending sequence
followed by a strictly a strictly descending sequence. Design an algorithm for finding
the maximum element in A.

Variant: Design an O(log n) algorithm for finding the position of an element k in a
cyclically sorted array of distinct elements.
 */