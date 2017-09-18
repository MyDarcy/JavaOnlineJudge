package eopi.ch5_primitive;

import java.util.Random;

/**
 * Author by darcy
 * Date on 17-9-18 下午1:53.
 * Description:
 *
 * bit交换.
 *
 *  x & (x - 1) clears the lowest set bit in x,
 *  x & ~(x- 1) extracts the lowest set bit of x.
 *
 *  A 64-bit integer can be viewed as an array of 64 bits, with the bit at
 *  index 0 corresponding to the least significant bit (LSB), and the bit
 *  at index 63 corresponding to the most significant bit (MSB).
 *
 *
 */
public class P2_SwapBits {

  /**
   * 1. 普通方法, 检测是否都是1或者0,那么就不用替换了; 一个1一个0, 那么各自位flip即可.
   *
   * 2. 一个1,一个0, 那么与两个位都为1的数字亦或就能进行求反运算.
   * O(1)的时间复杂度.
   *
   * @param number
   * @param x
   * @param y
   * @return
   */
  public static long solution(long number, int x, int y) {
    if (((number >>> x) & 1) != ((number >>> y & 1))) {
      long mask = (1L << x) | (1L << y);
      number ^= mask;
    }

    return number;
  }

  public static void main(String[] args) {
    System.out.println(12 & (12 - 1));
    System.out.println(12 & ~(12 - 1));

    Random random = new Random();
    for (int i = 0; i < 10; i++) {
      long number = Math.abs(random.nextLong());
      System.out.println("------" + number + "-----");
      System.out.println(number);
      int x = random.nextInt(64);
      int y = random.nextInt(64);
      System.out.println("x=" + x + ", y=" + y);
      long result = solution(number, x, y);
      if (result != number) {
        System.out.println(result);
      }
    }
  }
}
