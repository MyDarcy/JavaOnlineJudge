package eopi.ch14_sorting;

import java.util.Arrays;

/**
 * Author by darcy
 * Date on 17-9-29 下午3:49.
 * Description:
 *
 * 第一个数组是排序的, 实际元素的个数比数组的空间要小. 第二个数组也是有序的, 那么将第二个数组中的元素合并到第一个数组中.
 *
 * Write a program which takes as input two sorted arrays of integers, and updates the
 * first to the combined entries of the two arrays in sorted order. Assume the first array
 * has enough empty entries at its end to hold the result.
 *
 * Hint: Avoid repeatedly moving entries.
 */
public class P14_2_MergeTwoSortedArray {

  /**
   * 将第二个array合并到第一个array中.
   * 两个数组从末尾开始比较,得到max的, 放到第一个数组"末尾" (m + n -1).
   *
   * @param array1
   * @param array2
   */
  public static void solution(int[] array1, int m, int[] array2, int n) {
    int i = m - 1;
    int j = n - 1;
    int writableIndex = m + n - 1;
    while (i >= 0 && j >= 0) {
      array1[writableIndex--] = array1[i] > array2[j] ? array1[i--] : array2[j--];
    }

    // m < 0说明a中所有的元素已经后移动了.
    while (j >= 0) {
      array1[writableIndex--] = array2[j--];
    }
  }

  public static void main(String[] args) {
    int[] array1 = new int[]{5, 13, 17, 0, 0, 0, 0, 0};
    int[] array2 = new int[]{3, 7, 11, 19};
    solution(array1, 3, array2, 4);

    System.out.println(Arrays.toString(array1));

  }

}
