package eopi.ch18_greedy;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * Author by darcy
 * Date on 17-10-6 上午11:16.
 * Description:
 *
 * three-sum问题.
 *
 */
public class P18_4_TheThreeSumProblems {

  /**
   * three-sum问题转为two-sum问题.
   * 时间复杂度O(n^2)
   *
   * @param numbers
   * @param target
   * @return
   */
  public static boolean hasThreeSum(List<Integer> numbers, int target) {
    Collections.sort(numbers);

    // 但是如果一个数字只能选择一次呢, 而这种解决方案可能会导致选择两次.
    // 使用hash表统计各个number出现的次数.
    // 获取传入外层遍历的index, 内层不能使用此index的数字.

    for (Integer number : numbers) {
      if (hasTwoSum(numbers, target - number)) {
        System.out.println(number);
        return true;
      }
    }

    return false;
  }

  /**
   *
   * @param numbers
   * @param target
   * @return
   */
  public static boolean hasTwoSum(List<Integer> numbers, int target) {
    int i = 0;
    int j = numbers.size() - 1;
    while (i <= j) {
      if (numbers.get(i) + numbers.get(j) == target) {
        System.out.println(numbers.get(i) + "\t" + numbers.get(j) + "\t");
        return true;
      } else if (numbers.get(i) + numbers.get(j) < target) {
        ++i;
      } else {
        --j;
      }
    }
    return false;
  }

  public static void main(String[] args) {
    List<Integer> list = Arrays.asList(11, 2, 5, 7, 3);
    boolean result = hasThreeSum(list, 21);
    System.out.println(result);
  }

}
