package eopi.ch14_sorting;

import com.sun.org.apache.bcel.internal.generic.INEG;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Author by darcy
 * Date on 17-9-29 下午3:08.
 * Description:
 * 两个有序集合的并集.
 */
public class P14_1_ComputeIntersectOfTwoSortedArray {

  /**
   * @param list1
   * @param list2
   * @return
   */
  public static List<Integer> solution(List<Integer> list1, List<Integer> list2) {
    int i = 0;
    int j = 0;
    List<Integer> result = new ArrayList<>();
    while (i < list1.size() && j < list2.size()) {
      if (list1.get(i) == list2.get(j)) {
        result.add(list1.get(i));
        i++;
        j++;
      } else if (list1.get(i) < list2.get(j)) {
        i++;
      } else if (list1.get(i) > list2.get(j)) {
        j++;
      }
    }

    return result;
  }

  public static void main(String[] args) {
    List<Integer> list1 = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9);
    List<Integer> list2 = Arrays.asList(1, 3, 5, 7, 8, 9, 11, 17);
    List<Integer> result = solution(list1, list2);
    System.out.println(result);
  }

}
