package nowcoder.zhaohang;

import java.util.Scanner;

/**
 * Created by hzq19
 * Date on 2017/9/13 19:32.
 * Description:
 *
 * a, b不大于n的公倍数的个数.
 */
public class GongBeiShu {

  public static void main(String[] args) {
    Scanner input = new Scanner(System.in);
    int a = input.nextInt();
    int b = input.nextInt();
    int n = input.nextInt();

    int temp = 0;
    // a, b之间数据交换.
    if (a > b) {
      temp = a;
      a = b;
      b = temp;
    }

    /*System.out.println(gcd(18, 6));
    System.out.println(gcd(5, 3));*/

    // 互质的.
    if (gcd(a, b) == 1) {
      temp = a * b;
      System.out.println(n / temp);

      // 非互质的.
    } else {
      System.out.println(n / b);
    }
  }

  private static int gcd(int a, int b) {
    if (b != 0) {
      return gcd(b, a % b);
    } else {
      return a;
    }
  }

}
