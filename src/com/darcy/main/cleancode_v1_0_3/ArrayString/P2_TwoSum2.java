package com.darcy.main.cleancode_v1_0_3.ArrayString;

import java.util.Arrays;

/**
 * Created by hzq19
 * Date on 2017/8/31 23:50.
 * Description:
 * <p>
 * 同样是Two-sum问题，如果输入数组是已经有序的。
 */
public class P2_TwoSum2 {

  /**
   * 遍历数组，然后二分搜素第二个数字。
   * <p>
   * 时间复杂度O(nlogn),空间复杂度O(1)
   *
   * @param array
   * @param target
   * @return
   */
  public static int[] solution(int[] array, int target) {
    for (int i = 0; i < array.length; i++) {
      int j = /*Arrays.binarySearch(array, target - array[i]);*/
          binarySearch(array, target - array[i], i);
      if (j > 0) { // j != -1;
        return new int[]{i, j};
      }
    }
    throw new RuntimeException("no pair found");
  }

  private static int binarySearch(int[] array, int number, int start) {
    int L = start;
    int R = array.length - 1;
    while (L < R) {
      int M = (L + R) / 2;
      if (array[M] < number) {
        L = M + 1;
      } else {
        R = M;
      }
    }
    return (L == R && array[L] == number) ? L : -1;
  }

  /**
   * 大小元素相加,跟目标元素相比,调整索引的位置.
   * O(n)的时间， O(1)的空间.
   *
   * @param array
   * @param target
   * @return
   */
  public static int[] solution2(int[] array, int target) {
    if (array == null || array.length < 2) {
      throw new RuntimeException("no enough elements");
    }

    int low = 0;
    int high = array.length - 1;
    while (low < high) {
      int result = array[low] + array[high];
      if (result == target) {
        return new int[]{low, high};
      } else if (result > target) {
        high--;
      } else {
        low++;
      }
    }
    throw new RuntimeException("no pair found");
  }

  public static void main(String[] args) {
    int[] array = {1, 2, 3, 8, 9, 11, 15, 17, 19, 21};
    int[] result = solution(array, 23);
    System.out.println(Arrays.toString(result));


    int[] result2 = solution2(array, 32);
    System.out.println(Arrays.toString(result2));
  }

}
