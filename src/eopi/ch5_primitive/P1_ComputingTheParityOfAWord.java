package eopi.ch5_primitive;

import java.util.Random;

/**
 * Author by darcy
 * Date on 17-9-18 上午11:10.
 * Description:
 *
 * 数字的二进制表示中1的个数是奇数个,那么返回odd(返回1).
 * 偶数个,返回even(返回0).
 *
 */
public class P1_ComputingTheParityOfAWord {

  public static int solution1(long x) {
    int result = 0;
    while (x != 0) {
      result ^= (x & 1);
      x >>>= 1;
    }

    return result;
  }


  public static int solution2(long x) {
    int result = 0;
    while (x != 0) {
      result ^= 1;
      x = x & (x - 1);
    }

    return result;
  }

  /**
   * 相同位置都为1,结果直接置为0;
   * 时间复杂度O(logN)
   *
   * @param x
   * @return
   */
  public static int solution3(long x) {
    x ^= x >>> 32;
    x ^= x >>> 16;
    x ^= x >>> 8;
    x ^= x >>> 4;
    x ^= x >>> 2;
    x ^= x >>> 1;
    return (int) (x & 1);
  }

  public static void main(String[] args) {
    Random random = new Random();
    for (int i = 0; i < 10; i++) {
      long number = Math.abs(random.nextLong());
      System.out.println("-----" + number + "-----");
      System.out.println(solution1(number));;
      System.out.println(solution2(number));;
      System.out.println(solution3(number));;
    }
  }

}
