package nowcoder.pingan;

import java.util.Scanner;

/**
 * Created by hzq19
 * Date on 2017/10/11 21:22.
 * Description:
 * 首位为3的四位数, 有且仅有一个数字出现了两次.
 */
public class Main1 {

  public static void main(String[] args) {
    Scanner input = new Scanner(System.in);
    int inputNumber = input.nextInt();
    if (inputNumber <= 3000) {
      System.out.println(0);
    } else if (inputNumber >= 4000) {
      int i = 9 * 3 * 8 + 3 * 9 * 8;
      System.out.println(i);
    } /*else {
      for (int i = 0; i <= 9; i++) {
        // 三个位置.
        for (int j = 0; j < 3; j++) {
          solution1(j);
          } else {

          }
        }
      }
    }*/
    System.out.println();
  }

  /**
   *
   * @param i
   * @return
   */
  public static String solution1(int i) {
    /*if (i == 0) {
      for (int j = 0; j <= 9; j++) {
        new StringBuilder("3").append()
      }
    } else if (i == 1) {

    } else {

    } */

    return null;

  }

}
