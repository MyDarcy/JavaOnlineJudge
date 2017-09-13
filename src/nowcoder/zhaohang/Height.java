package nowcoder.zhaohang;

import java.util.Scanner;

/**
 * Created by hzq19
 * Date on 2017/9/13 19:44.
 * Description:
 *
 * 每次从队列左边选择，选择结果总和大的人胜利.
 */
public class Height {

  public static void main(String[] args) {
    Scanner input = new Scanner(System.in);
    int number = input.nextInt();
    int[] heights = new int[number];
    for (int i = 0; i < number; i++) {
      heights[i] = input.nextInt();
    }

    int sum = 0;
    for (int i = 0; i < number; i++) {
      sum += heights[i];
    }

    int[][] result = new int[heights.length][heights.length];
    int total = solution(heights, 0, heights.length - 1, result);
    if (total > sum / 2) {
      System.out.println(true);
    } else {
      System.out.println(false);
    }
  }

  /**
   * 类似博弈问题.
   * @param heights
   * @param start
   * @param end
   * @param result
   * @return
   */
  private static int solution(int[] heights, int start, int end, int[][] result) {
    if (start > end) {
      return 0;
    }

    // start <= end
    if (result[start][end] == 0) {
      int t1 = heights[start]
          // b选1个，b选2个。
          + Math.min(solution(heights, start + 2, end, result), solution(heights, start + 3, end, result));
      int t2 = 0;

      if (start < end) {
        t2  = heights[start] + heights[start + 1]
          + Math.min(solution(heights, start + 3, end, result), solution(heights, start + 4, end, result));
      }
      result[start][end] = Math.max(t1, t2);
    }
    return result[start][end];
  }
}
