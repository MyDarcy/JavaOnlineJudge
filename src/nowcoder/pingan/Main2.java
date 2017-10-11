package nowcoder.pingan;

import java.math.BigInteger;
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

    System.out.println(count);
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
