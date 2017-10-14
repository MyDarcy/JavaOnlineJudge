package nowcoder.pingan;

import java.math.BigInteger;
import java.util.Random;
import java.util.Scanner;

/**
 * Created by hzq19
 * Date on 2017/10/11 21:35.
 * Description:
 *
 * n的阶乘中末位0的个数.
 *
 */
public class Main2 {

  public static void main(String[] args) {

    /*Scanner input = new Scanner(System.in);
    String str = input.nextLine().trim();*/

    Scanner input = new Scanner(System.in);
    long number = input.nextLong();
    number = Math.abs(number);
    long count = solution(number);

    System.out.println(count);

    System.out.println("----");
    test();
  }

  private static void test() {
    Random random = new Random(31);
    int right = 0;
    for (int i = 0; i < 100; i++) {
      long number = random.nextInt(1200);
      long result1 = solution(number);
      long result2 = solution2(number);
      System.out.println(number + ":" + result1 + "\t" + result2);
      right += (result1 == result2) ? 1 : 0;
    }
    System.out.println("test:" + right);
  }


  /**
   * 解法1.
   * @param number
   * @return
   */
  private static long solution(long number) {
    long count = 0;
    long div = 5;

    long MAXDIV5 = Long.MAX_VALUE / 5;

    while (div < number) {
      count += number / div;
      if (div > MAXDIV5) {
        break;
      }
      div *= 5;
    }
    return count;
  }

  /**
   * 解法2.
   * @param number
   * @return
   */
  private static long solution2(long number) {
    long count = 0;
    while (number > 0) {
      count += number / 5;
      number /= 5;
    }

    return count;
  }

  /**
   *
   * @param number
   * @return
   */
  public static BigInteger solution(BigInteger number) {
    return null;
  }

}
