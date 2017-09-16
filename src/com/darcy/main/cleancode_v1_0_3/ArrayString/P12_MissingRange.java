package com.darcy.main.cleancode_v1_0_3.ArrayString;

import java.util.ArrayList;
import java.util.List;

/**
 * Author by darcy
 * Date on 17-9-1 下午4:23.
 * Description:
 * 给定一个排序后的数组,数组中所有元素都在给定范围内, 返回缺失的范围.
 * <p>
 * <p>
 * given [0, 1, 3, 50, 75], range[0, 99],
 * return [“2”, “4->49”, “51->74”, “76->99”]
 * <p>
 * Q: What if the given array is empty?
 * A: Then you should return [“0->99”] as those ranges are missing.
 * Q: What if the given array contains all elements from the ranges?
 * A: Return an empty list, which means no range is missing.
 */
public class P12_MissingRange {

  /**
   * 原来的边界处可以加上额外的边界，进行统一处理.
   *
   * @param array
   * @param start
   * @param end
   * @return
   */
  public static List<String> solution(int[] array, int start, int end) {
    int prev = start - 1;
    List<String> result = new ArrayList<>();
    for (int i = 0; i <= array.length; i++) {
      int current = (i == array.length) ? end + 1 : array[i];

      // 前后两个元素相隔 >= 2
      if ((current - prev) >= 2) {
        result.add(getRange(prev + 1, current - 1));
      }

      prev = current;
    }

    return result;
  }

  /**
   * 不加额外的边界的话.
   *
   * @param array
   * @param start
   * @param end
   * @return
   */
  public static List<String> solution2(int[] array, int start, int end) {

    List<String> result = new ArrayList<>();

    if (array.length == 0) {
      result.add(getRange(start, end));
      return result;
    }

    for (int i = 0; i < array.length; i++) {
      if (i == 0 && array[0] != start) {
        result.add(getRange(start, array[0] - 1));
      } else if (i == 0 && array[0] == start) {
        continue;
      }

      /*System.out.println(i);*/
      if (i < array.length && i > 0 && ((array[i] - array[i - 1]) >= 2)) {
        result.add(getRange(array[i - 1] + 1, array[i] - 1));
      }

      if (i == array.length - 1 && array[i] != end) {
        result.add(getRange(array[i] + 1, end));
      }
    }
    return result;
  }

  private static String getRange(int start, int end) {
    return start == end ? String.valueOf(start) : start + "->" + end;
  }

  public static void main(String[] args) {
    int[] array = {0, 1, 3, 50, 75};
    List<String> result = solution(array, 0, 99);
    System.out.println(result);

    array = new int[]{1, 2, 4, 8, 10, 22, 89, 98};
    List<String> result2 = solution2(array, 0, 99);
    System.out.println(result2);

  }

}
