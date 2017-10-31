package eopi.ch25_honors;

import java.util.Collections;
import java.util.List;
import java.util.stream.IntStream;

import static java.util.stream.Collectors.toList;
import static jianzhioffer.P12_PermutateNumber.swap;

/**
 * Author by darcy
 * Date on 17-10-25 上午9:30.
 * Description:
 *
 * 数组的rotate.
 *
 * Design an algorithm for rotating an array A of n elements to the right by i positions.
 * Do not use library functions implementing rotate.
 *
 * Hint: Use concrete examples to form a hypothesis relating n,i,and the number of cycles
 */
public class P6_RotateAnArray {

  /**
   * 方法1:perform shift-by-one i times, which has O(ni) time complexity and O(1)space complexity.
   * 方法2:use an additional array of length i asa buffer to move elements i at a time.
   * This has O(n) time complexity and O(i) space complexity.
   *
   * @param array
   */
  public static void solution(List<Integer> array, int k) {
    k %= array.size();
    reverse(0, array.size(), array);
    reverse(0, k, array);
    reverse(k, array.size(), array);
  }

  private static void reverse(int start, int end, List<Integer> array) {
    for (int i = start, j = end - 1; i < j; i++, j--) {
      Collections.swap(array, i, j);
    }
  }

  public static void main(String[] args) {
    List<Integer> data = IntStream.rangeClosed(1, 10).boxed().collect(toList());
    System.out.println(data);
    solution(data, 2);
    System.out.println(data);
  }

}
