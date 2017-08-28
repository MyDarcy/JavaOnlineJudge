package eopi.ch6_arrays;

import java.util.ArrayList;
import java.util.List;

/**
 * Author by darcy
 * Date on 17-8-28 下午4:18.
 * Description:
 *
 * list表示的整数+1
 *
 * Write a program which takes as input an array of digits encoding a decimal number
 * D and updates the array to represent the number D + 1. For example, if the input
 * is (1,2,9) then you should update the array to (1,3,0). Your algorithm should work
 * even if it is implemented in a language that has finite-precision arithmetic.
 *
 */
public class P3_AddOne {


  /**
   *
   * 简单的判断特定的位是否是10即可.
   * @param list
   * @return
   */
  public static List<Integer> solution(List<Integer> list) {
    int n = list.size() - 1;
    list.set(n, list.get(n) + 1);
    for (int i = n; i > 0; i--) {
      if (list.get(i) == 10) {
        list.set(i, 0);
        list.set(i - 1, list.get(i - 1) + 1);
      }
    }

    if (list.get(0) == 10) {
      list.set(0, 0);
      list.add(0, 1);
    }
    return list;
  }

  public static void main(String[] args) {
    List<Integer> list = new ArrayList<>();
    list.add(9);
    list.add(9);
    solution(list);
    System.out.println(list);
  }
}
