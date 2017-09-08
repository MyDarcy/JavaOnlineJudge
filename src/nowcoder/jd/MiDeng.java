package nowcoder.jd;

import java.util.Scanner;

/**
 * Created by hzq19
 * Date on 2017/9/8 20:47.
 * Description:
 */
public class MiDeng {

  public static void main(String[] args) {
    Scanner input = new Scanner(System.in);
    int number = input.nextInt();
    int total = 0;
    for (int i = 1; i <= number; i++) {
      if (i == 1) {
        total += (number * number);
        continue;
      }

      for (int j = 2; j <= number; j++) {
        if (j % 2 == 0) {
          int temp = getJ(i, j, number);
          // System.out.println(temp);
          total += temp;
        } else {
          total++;
        }

      }
    }
    System.out.println(total);
  }

  private static int getJ(int i, int j, int number) {
    int count = 0;
    for (int k = 1; k <= j; k *= 2) {

      int temp = j / k;
      if (temp * k == j) {
        count++;
      }
    }
    return count;

    /*return (int) Math.log(j);*/
  }

}
