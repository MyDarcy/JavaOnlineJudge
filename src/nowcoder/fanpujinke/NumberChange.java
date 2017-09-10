package nowcoder.fanpujinke;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Created by hzq19
 * Date on 2017/9/10 15:59.
 * Description:
 *
 * 小明是一个间谍，现在他的任务是将一个整数n传给一个客户。但是他十分担心信号被窃取后，
 * 这个数字就暴露了。于是他决定换一种表达方式，他将n换成一个整数x，使得x减去x的每个数位上的数字后不小于n，
 * 那么最小的满足条件的x就是小明想找的。现在小明需要传送一个整数n，你能帮助他找到这个x么？
 如n=12，当x=20时，20-2-0 ≥12
 输入
 第一行一个整数n，1≤n≤10^18
 输出
 输出一个整数x，表示伪装后的数字。
 */
public class NumberChange {

  public static void main(String[] args) {
    /*Scanner input = new Scanner(System.in);
    int result = input.nextInt();
    int i = result;
    while (true) {
      i++;
      if (solution(i, result)) {
        break;
      }
    }
    System.out.println(i);*/

    Scanner input = new Scanner(System.in);
    long result = input.nextLong();
    long i = result;
    while (true) {
      i++;
      if (solution(i, result)) {
        break;
      }
    }
    System.out.println(i);

  }

  private static boolean solution(long number, long result) {

    String str = String.valueOf(number);
    int count = 0;
    for (int i = 0; i < str.length(); i++) {
      /*count += (str.charAt(i) - '0');*/
      count += Character.getNumericValue(str.charAt(i));
    }
    if ((number - count) >= result) {
      return true;
    }
    return false;
  }

  private static boolean solution(int number, int result) {

    String str = String.valueOf(number);
    int count = 0;
    for (int i = 0; i < str.length(); i++) {
      count += (str.charAt(i) - '0');
    }
    if (number - count > result) {
      return true;
    }
    return false;
  }

}
