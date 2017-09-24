package eopi.ch11_heap;

import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

/**
 * Author by darcy
 * Date on 17-9-24 下午3:55.
 * Description:
 *
 * 一个数组中的元素是按照递增递减递增递减...组织的.k次这样的递增递减part.
 *
 */
public class P11_2_KIncreasing_Decreasing {

  static enum Type {
    INCREASING,
    DECREASING
  }

  /**
   *
   * 将降序的subList逆序一下, 然后所有的子序列都是increasing的, 然后就是k个递增子序列的合并问题.
   * @param list
   * @return
   */
  public static List<Integer> solution(List<Integer> list) {
    Type type = Type.INCREASING;
    int startIndex = 0;
    List<List<Integer>> parts = new LinkedList<>();
    for (int i = 1; i < list.size(); i++) {
      if (i == list.size() || (list.get(i - 1) <= list.get(i) && type == Type.DECREASING)
          || (list.get(i - 1) >= list.get(i) && type == Type.INCREASING)) {
        List<Integer> subList = list.subList(startIndex, i);
        if (type == Type.DECREASING) {
          Collections.reverse(subList);
        }
        parts.add(subList);
        startIndex = i;

        type = (type == Type.INCREASING) ? Type.DECREASING : Type.INCREASING;
      }
    }

    return P11_1_MergeSortedFiles.solution(parts);
  }

  public static void main(String[] args) {
    List<Integer> list = Arrays.asList(1, 4, 7, 10, 8, 2, 10, 15, 20, 18, 12, 100, 200, 50, 1, 87);
    List<Integer> result = solution(list);
    System.out.println(result);
  }

}
