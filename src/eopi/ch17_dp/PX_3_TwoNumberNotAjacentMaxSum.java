package eopi.ch17_dp;

import java.util.Random;

/**
 * Created by hzq19
 * Date on 2017/9/27 21:17.
 * Description:
 *
 * 给定数组求不相邻的数组成的最大值.
 *
 */
public class PX_3_TwoNumberNotAjacentMaxSum {


  /**
   * 暴力.
   * @param array
   * @return
   */
  public static int bruteForceSolution(int[] array) {
    int sum = Integer.MIN_VALUE;
    for (int i = 0; i < array.length; i++) {
      for (int j = i + 2; j < array.length; j++) {
        int temp = array[i] + array[j];
        if (temp > sum) {
          sum = temp;
        }
      }
    }
    return sum;
  }

  /**
   * 记录跟当前元素相邻的max和跟当前元素不相邻的max.
   * 到下一个元素的时候，要更新不相邻的max.
   *
   * @param array
   * @return
   */
  public static int solution(int[] array) {
    if (array == null || array.length <= 2) {
      return Integer.MIN_VALUE;
    }

    int previousNumberMax = array[0];
    int previousNumberMaxIndex = 0;
    int adjacentNumberMax = array[1];
    int adjacentNumberMaxIndex = 1;
    int resultMax = Integer.MIN_VALUE;
    for (int i = 2; i < array.length; i++) {

      int tempSum = previousNumberMax + array[i];
      if (tempSum > resultMax) {
        resultMax = tempSum;
      }

      if (adjacentNumberMaxIndex + 1 == i) {
        if (adjacentNumberMax > previousNumberMax) {
          previousNumberMax = adjacentNumberMax;
          previousNumberMaxIndex = adjacentNumberMaxIndex;
        }

        adjacentNumberMax = array[i];
        adjacentNumberMaxIndex = i;
      }
    }
    return resultMax;
  }

  public static void main(String[] args) {
//    int[] array = {5, 7, 9, 5, 2};
    int[] array = {3, 7, 9, 6, 2};
    int result = solution(array);
    System.out.println(result + "\n");

    array = new int[]{5, 7, 9, 5, 6};
    result = solution(array);
    System.out.println(result + "\n");

    Random random = new Random(31);
    for (int times = 0; times < 20; times++) {
      int number = random.nextInt(1000);

      int[] array2 = new int[number];
      for (int i = 0; i < number; i++) {
        array2[i] = random.nextInt(1000000);
      }

      int r1 = solution(array2);
      int r2 = bruteForceSolution(array2);

      System.out.println(r1);
      System.out.println(r2);
      System.out.println();
    }


  }

}
