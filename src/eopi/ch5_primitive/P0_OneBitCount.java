package eopi.ch5_primitive;

import java.util.Random;

/**
 * Author by darcy
 * Date on 17-9-18 上午10:32.
 * Description:
 *
 * int型数字中1出现的个数.
 *
 */
public class P0_OneBitCount {

  public static int solution(int number) {
    int copy = number;
    int oneBitCount = 0;
    while (copy != 0) {
      oneBitCount++;
      copy = copy & (copy - 1);
    }

    return oneBitCount;
  }

  /**
   *
   * @param number
   * @return
   */
  public static int solution2(int number) {
    int oneBitCount = 0;
    while (number != 0) {
      oneBitCount += (number & 1);
      number >>>= 1;
    }
    return oneBitCount;
  }

  public static void main(String[] args) {
    Random random = new Random();
    for (int i = 0; i < 10; i++) {
      int number = random.nextInt(10000000);
      System.out.println("------" + number + "------");
      System.out.println(solution(number));
      System.out.println(solution2(number));

    }
  }

}
