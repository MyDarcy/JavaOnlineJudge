package eopi.ch6_arrays;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * Author by darcy
 * Date on 17-8-28 下午4:29.
 * Description:
 *
 * 两个任意精度的整数相乘
 *
 * Write a program that takes two arrays representing integers, and returns
 * an integer representing their product. For example, since
 * 193707721 X -761838257287 = -147573952589676412927, if the inputs are
 * (1,9,3, 7,0,7,7, 2,1) and (-7,6,1,8,3,8, 2,5,7, 2,8,7), your function should return
 * (-1,4,7,5,7,3,9,5,2,5,8,9,6,7,6,4,1,2,9, 2,7).
 *
 */
public class P4_IntegerMultiply {

  /**
   *
   * 每一位数据的相乘都能确定最终其作用的位.
   *
   * @param number1
   * @param number2
   * @return
   */
  public static List<Integer> solution(List<Integer> number1, List<Integer> number2) {
//    int sign = number1.get(0) * number2.get(0) < 0 ? -1 : 1;
    int sign = number1.get(0) < 0 ^ number2.get(0) < 0 ? -1 : 1;
    number1.set(0, Math.abs(number1.get(0)));
    number2.set(0, Math.abs(number2.get(0)));

    List<Integer> result = new ArrayList<>(Collections.nCopies(number1.size() + number2.size(), 0));

    for (int i = number1.size() - 1; i >= 0; i--) {
      for (int j = number2.size() - 1; j >= 0; j--) {
        result.set(i + j + 1, number1.get(i) * number2.get(j) + result.get(i + j + 1));
        result.set(i + j, result.get(i + j + 1) / 10 + result.get(i + j));
        result.set(i + j + 1, result.get(i + j + 1) % 10);
      }
    }

    int firstNotZero = 0;
    for (int i = 0; i < result.size(); i++) {
      if (result.get(i) != 0) {
        firstNotZero = i;
        break;
      }
    }

    result = result.subList(firstNotZero, result.size());
    result.set(0, sign * result.get(0));
    return result;
  }

  public static void main(String[] args) {
    List<Integer> list1 = Arrays.asList(new Integer[]{1, 9, 3, 7, 0, 7, 7, 2, 1});
    List<Integer> list2 = Arrays.asList(new Integer[]{-7, 6, 1, 8, 3, 8, 2, 5, 7, 2, 8, 7});
    List<Integer> result = solution(list1, list2);
    System.out.println(result);
  }

}
