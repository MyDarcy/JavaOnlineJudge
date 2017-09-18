package eopi.ch6_arrays;

import java.util.ArrayList;
import java.util.List;

/**
 * Author by darcy
 * Date on 17-9-18 上午9:37.
 * Description:
 *
 * 计算Pascal三角形的行.
 *          1
 *         1 1
 *        1 2 1
 *       1 3 3 1
 *      1 4 6 4 1
 */
public class P19_ComputeLineInPascalTriangle {

  /**
   * n行Pascal三角形.
   *
   * 时间复杂度O(n^2), 空间复杂度O(n^2);
   * @param n
   * @return
   */
  public static List<List<Integer>> solution(int n) {
    List<Integer> line = null;
    List<List<Integer>> result = new ArrayList<>();
    for (int i = 1; i <= n; i++) {
      line = new ArrayList<>(i);
      // 第一行.
      if (i == 1) {
        line.add(1);

        // 第二行.
      } else if (i == 2) {
        line.add(1);
        line.add(1);
        // >= 3行.
      } else {
        for (int j = 0; j < i; j++) {
          if (j == 0) {
            line.add(1);
          } else if (j == i - 1) {
            line.add(1);
          } else {
            line.add(result.get(i - 2).get(j - 1) + result.get(i - 2).get(j));
          }
        }
      }
      result.add(line);
    }

    return result;
  }


  public static void main(String[] args) {
    List<List<Integer>> result = solution(5);
    for (List<Integer> line : result) {
      System.out.println(line);
    }

  }

}
