package nowcoder.webank;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Scanner;

/**
 * Created by hzq19
 * Date on 2017/9/28 16:33.
 * Description:
 * 给出三个整数a，b，c，你需要计算数2a+2b-2c在二进制表示下1的个数。
 输入
 第一行包含三个整数a,b,c。1≤c＜b＜a≤109
 输出
 输出对应的答案。
 提示：2^3+2^2-2^1=10=(110)2

 样例输入
 3 2 1
 样例输出
 2
 */
public class P1_OneCount {

  public static void main(String[] args) {
    Scanner input = new Scanner(System.in);

    int a = input.nextInt();
    int b = input.nextInt();
    int c = input.nextInt();
    input.close();

    System.out.println(1 + (b - c));

    /*int number = (int) Math.pow(2, a) + (int) Math.pow(2, b) - (int) Math.pow(2, c);
    int result = solution(number);
    System.out.println(result);*/

  }

  private static int solution(int result) {
    int count = 0;
    while (result != 0) {
      count++;
      result = result & (result - 1);
    }

    return count;
  }

}
