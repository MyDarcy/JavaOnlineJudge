package nowcoder.souhu;

import java.util.Scanner;

/**
 * Created by hzq19
 * Date on 2017/9/17 10:45.
 * Description:
 * <p>
 * 某游戏公司刚创立时只有一名员工，每名员工有3个月试用期，过试用期后转为正式员工，
 * 每名正式员工每个月都会推荐一名新员工进入公司，新员工经过3个月试用期后转为正式
 * 员工后每个月又会推荐一名新员工进入公司， 假如公司创立时的第一名员工也需要试用期，
 * 并且所有员工都不会离职。 据此，请并书写main方法打印出公司成立前a个月总员工数量。
 * 输入描述:
 * 输入月数a
 * 输出描述:
 * 输出公司成立a月对应的员工数量
 * 示例1
 * 输入
 * 5
 * 输出
 * 3
 */
public class P3_Numbers {

  /**
   * month=1, formal=0, month1=1, month2=0, month3=0
   * month=2, formal=0, month1=0, month2=1, month3=0
   * month=3, formal=0, month1=0, month2=0, month3=1
   * month=4, formal=1, month1=1, month2=0, month3=0
   * month=5, formal=1, month1=1, month2=1, month3=0
   * month=6, formal=1, month1=1, month2=1, month3=1
   * month=7, formal=2, month1=2, month2=1, month3=1
   * month=8, formal=3, month1=3, month2=2, month3=1
   * month=9, formal=4, month1=4, month2=3, month3=2
   * month=10, formal=6, month1=6, month2=4, month3=3
   * month=11, formal=9, month1=9, month2=6, month3=4
   * month=12, formal=13, month1=13, month2=9, month3=6
   *
   months	formal month1 month2 month3
   1           0      1      0      1
   2           0      0      1      0
   3           0      0      0      1
   4           1      1      0      0
   5           1      1      1      0
   6           1      1      1      1
   7           2      2      1      1
   8           3      3      2      1
   9           4      4      3      2
   10          6      6      4      3
   11          9      9      6      4
   12         13     13      9      6
   *
   * @param args
   */
  public static void main(String[] args) {
    Scanner input = new Scanner(System.in);
    int months = input.nextInt();
    int result = solution(months);
    System.out.println(result);
  }

  private static int solution(int months) {
    if (months < 0) {
      return 0;
    }

    if (months <= 1) {
      return months;
    }

    if (months <= 3) {
      return 1;
    }

    int month1 = 0;
    int month2 = 0;
    int month3 = 0;
    int formal = 1;
     System.out.printf("%6s\t%6s %6s %6s %6s\n", "months", "formal", "month1", "month2", "month3");
    //System.out.println("months" + " formal" + " month1" + " month2" + " month3");
    for (int i = 4; i <= months; i++) {
      formal += month3;
      month3 = month2;
      month2 = month1;
      month1 = formal;

      System.out.printf("%-6d %6d %6d %6d %6d\n", i, formal, month1, month2, month3);
      // System.out.println("month="+ i + ", formal=" + formal + ", month1=" + month1 + ", month2=" + month2 + ", month3=" + month3);
    }

    int total = formal + month1 + month2 + month3;
    return total;
  }

}
