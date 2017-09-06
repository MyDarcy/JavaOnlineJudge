package eopi.ch17_dp;

import java.util.Arrays;
import java.util.stream.IntStream;

/**
 * Author by darcy
 * Date on 17-9-6 下午8:36.
 * Description:
 *
 * 最长递增自序列
 *
 */
public class PX1_LIS {

  /**
   * 从后往前的dp.
   * dp[i]表示以i为起始元素的最长递增子序列
   * @param array
   * @return
   */
  public static int solution(int[] array) {
    if (array == null) {
      return 0;
    }

    int[] dp = new int[array.length];
    dp[array.length - 1] = 1;

    for (int i = array.length - 2; i >= 0; i--) {
      int max = 0;
      for (int j = i + 1; j < array.length; j++) {
        if (array[j] > array[i]) {
          if (max < dp[j]) { // d[j]和d[i]又构成了递增的关系.
            max = dp[j];
            /*continue;*/
          }
        }
      }
      dp[i] = max + 1;
    }

    int result = Arrays.stream(dp).max().getAsInt();
    return result;
  }

  public static void main(String[] args) {
    int[] array = {1, -1, 2, -2, 3, -1, 4, -2, 5, -1, 6, 0, 7};
    int result = solution(array);
    System.out.println(result);

  }

}
