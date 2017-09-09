package com.darcy.main.review;

/**
 * Author by darcy
 * Date on 17-9-9 下午8:25.
 * Description:}
 *
 * 数组相关.
 */
public class Arrays {

  /**
   * 最大连续子数组的和.
   * @param array
   * @return
   */
  public static int maxSubArray(int[] array) {
    int max = array[0];

    int end = array[0];
    for (int i = 1; i < array.length; i++) {
      end = Math.max(end + array[i], array[i]);
      max = Math.max(max, end);
    }
    return max;

  }

  public static void main(String[] args) {
    int[] array1 = new int[]{1, -2, 4, 8, -4, 7, -1, -5};
    int result = maxSubArray(array1);
    System.out.println(result);
  }

}
