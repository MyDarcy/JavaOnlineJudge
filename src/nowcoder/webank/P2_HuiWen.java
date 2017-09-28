package nowcoder.webank;

import java.util.Scanner;

/**
 * Created by hzq19
 * Date on 2017/9/28 16:54.
 * Description:
 * 回文串是无论正着读还是反着读都一样的字符串，比如“level”或者“noon”就是回文串。
 若将某个十进制非负整数N，转换成二进制后得到的 01 序列具有回文串的性质，则称该数为回文数，比如十进制非负整数 9
 表示成二进制后得到 1001，“1001”具有回文串的性质，则称十进制整数 9 为回文数。
 现给你一个十进制整数N，请计算小于等于N的回文数的数量。
 输入
 第一行包含一个整数N, 1 ≤N≤10^18。
 输出
 输出一个整数M，表示小于等于 N 的回文数的数量

 样例输入
 6
 样例输出
 4
 */
public class P2_HuiWen {

  public static void main(String[] args) {
    // System.out.println(String.valueOf(Long.MAX_VALUE).length());

    Scanner input = new Scanner(System.in);
    long number = input.nextLong();
    input.close();

    int count = 0;
    String s = null;
    while (number >= 0) {
      s = Long.toBinaryString(number);
      if (isPandronic(s)) {
        count++;
      }
      number--;
    }

    System.out.println(count);
  }

  private static boolean isPandronic(String s) {
    int length = s.length();
    for (int i = 0; i < length / 2; i++) {
      if (s.charAt(i) != s.charAt(length - 1 - i)) {
        return false;
      }
    }

    return true;
  }

}
