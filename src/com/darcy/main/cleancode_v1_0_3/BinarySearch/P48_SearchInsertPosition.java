package com.darcy.main.cleancode_v1_0_3.BinarySearch;

/**
 * Author by darcy
 * Date on 17-9-4 下午4:56.
 * Description:
 *
 * 搜索插入位置。
 *
 * Given a sorted array and a target value, return the index if the target is found. If not,
 * return the index where it would be if it were inserted in order.
 * You may assume no duplicates in the array.
 * Here are few examples.
 * [1,3,5,6], 5 → 2
 * [1,3,5,6], 2 → 1
 * [1,3,5,6], 7 → 4
 * [1,3,5,6], 0 → 0
 *
 */
public class P48_SearchInsertPosition {

  /**
   *
   * @param array
   * @return
   */
  public static int solution(int[] array, int target) {
    int result = solution(array, 0, array.length - 1, target);
    return result;
  }

  private static int solution(int[] array, int start, int end, int target) {
    while (start <= end) {
      int middle = start + (end - start) / 2;
      if (array[middle] == target) {
        return middle;
      } else if (array[middle] < target) {
        start = middle + 1; // 此时array[start]的元素就可能比target大了.
      } else {
        end = middle - 1; // 此时array[end]的就可能比target小了.
      }
    }
    return start;
  }

  /**
   *
   * When the while loop ends, L must be equal to R and it
   * is a valid index. Obviously, if A[L] is equal to target, we return L. If A[L] is greater than
   * target, that means we are inserting target before A[L], so we return L. If A[L] is less than
   * target, that means we insert target after A[L], so we return L + 1.
   *
   * @param A
   * @param target
   * @return
   */
  public static int searchInsert(int[] A, int target) {
    int L = 0, R = A.length - 1;
    while (L < R) {
      int M = (L + R) / 2;
      if (A[M] < target) {
        L = M + 1;
      } else {
        R = M;
      }
    }
    return (A[L] < target) ? L + 1 : L;
  }

  public static void main(String[] args) {
    int[] array1 = {1, 3, 5, 6};
    int target1 = 5;
    System.out.println(solution(array1, target1));
    System.out.println(searchInsert(array1, target1));
    System.out.println("----");

    int[] array2 = {1, 3, 5, 6};
    int target2 = 2;
    System.out.println(solution(array2, target2));
    System.out.println(searchInsert(array2, target2));
    System.out.println("----");

    int[] array3 = {1, 3, 5, 6};
    int target3 = 7;
    System.out.println(solution(array3, target3));
    System.out.println(searchInsert(array3, target3));
    System.out.println("----");

    int[] array4 = {1, 3, 5, 6};
    int target4 = 0;
    System.out.println(solution(array4, target4));
    System.out.println(searchInsert(array4, target4));
    System.out.println("----");

  }

}
