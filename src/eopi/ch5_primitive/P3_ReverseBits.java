package eopi.ch5_primitive;

import java.util.Random;

/**
 * Author by darcy
 * Date on 17-9-18 下午2:14.
 * Description:
 * <p>
 * bit reverse.
 * <p>
 * Write a program that takes a 64-bit word and returns the 64-bit word consisting of the
 * bits of the input word in reverse order. For example, if the input is alternating Is
 * and Os, i.e., (1010...10), the output should be alternating Os and Is, i.e., (0101...01).
 */
public class P3_ReverseBits {

  /**
   * @param word
   * @return
   */
  public static String solution1(long word) {
    String s = Long.toBinaryString(word);
    return new StringBuilder(s).reverse().toString();
  }

  /*public static long reverseBits(long x) {
    final int WORD_SIZE = 16;
    final int BIT_MASK = 0xFFFF;
    return precomputedReverse[(int) (x & BIT_MASK)] << (3 * WORD_SIZE)
        | precomputedReverse[(int) ((x >>> WORD_SIZE) & BIT_MASK)] << (2 * WORD_SIZE)
        | precomputedReverse[(int) ((x >>> (2 * WORD_SIZE)) & BIT_MASK)] << WORD_SIZE
        | precomputedReverse[(int) ((x >>> (3 * WORD_SIZE)) & BIT_MASK)];
  }*/

  public static void main(String[] args) {

    Random random = new Random();
    for (int i = 0; i < 10; i++) {
      long number = Math.abs(random.nextLong());
      System.out.println(number);

      System.out.println(solution1(number));
    }

  }
}
