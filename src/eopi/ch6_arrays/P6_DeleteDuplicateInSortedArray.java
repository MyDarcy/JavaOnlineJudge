package eopi.ch6_arrays;

import java.util.Arrays;

/**
 * Author by darcy
 * Date on 17-8-29 下午9:08.
 * Description:
 *
 * 从已序数组中删除重复元素.
 * O(n)的时间开销和O(1)的空间开销
 *
 * Write a program which takes as input a sorted array and updates it so that all
 * duplicates have been removed and the remaining elements have been shifted left to fill the
 * emptied indices. Return the number of valid elements.
 *
 */
public class P6_DeleteDuplicateInSortedArray {

  /**
   * 1. hashmap表明元素是否是新元素.
   * 2. 重复元素左移动
   * @param array
   * @return
   */
  public static int bruteForceSolution(int[] array) {

    return 0;
  }

  /**
   *
   * O(n)时间复杂度,O(1)的空间复杂度.
   * @param array
   * @return
   */
  public static int solution(int[] array) {

    if (array == null || array.length == 0) {
      return 0;
    }

    int nonDuplicateIndex = 1;
    for (int i = 1; i < array.length; i++) {
      if (array[i] != array[i - 1]) {
        array[nonDuplicateIndex++] = array[i];
      }
    }
    return nonDuplicateIndex;
  }

  public static void main(String[] args) {
    int[] array = {1, 2, 2, 2, 2, 4, 4, 6, 7, 8, 9};
    System.out.println(Arrays.toString(array));
    int result = solution(array);
    System.out.println(Arrays.toString(array));
    System.out.println(result);

  }
}
