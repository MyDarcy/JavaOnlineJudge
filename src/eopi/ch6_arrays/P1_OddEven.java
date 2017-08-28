package eopi.ch6_arrays;

import java.util.Arrays;
import java.util.Random;

/**
 * Author by darcy
 * Date on 17-8-28 下午2:33.
 * Description:
 *
 * 数组的奇数和偶数分开, 偶数位于数组前面,奇数位于数组后面.
 *
 */
public class P1_OddEven {

  /**
   *
   * 数组分为三个部分, 前面是even部分,后面是odd部分,中间是未分类部分.
   *
   * @param array
   */
  public static void solution(int[] array) {
    int even = 0;
    int odd = array.length - 1;
    while (even < odd) {
      if (array[even] % 2 == 0) {
        even++;
      } else {
        int temp = array[even];
        array[even] = array[odd];
        array[odd--] = temp;
      }
    }
  }

  public static void main(String[] args) {
    int arrayNumber = 12;
    Random random = new Random(13);
    int[] array = new int[arrayNumber];
    for (int i = 0; i < arrayNumber; i++) {
      array[i] = random.nextInt(100);
    }
    System.out.println(Arrays.toString(array));
    solution(array);
    System.out.println(Arrays.toString(array));
  }
}
