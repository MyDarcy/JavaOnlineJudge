package nowcoder.shangtang;

import java.util.Scanner;

/**
 * Created by hzq19
 * Date on 2017/10/12 19:59.
 * Description:
 * a -> 1
 * b -> 2
 * ...
 * z -> 26
 *
 * 给定编码后的数字字符串，求有多少种原始可能的字母序列组成。
 */
public class Main1 {

  private static int count = 0;
  public static void main(String[] args) {
    Scanner input = new Scanner(System.in);
    String str = input.nextLine().trim();
    if (str == null || str.length() == 0) {
      System.out.println(0);
    }
    /*if (str.charAt(0) < '2') {
      solution(str, 0, true);
    } else {
      solution(str, 0, false);
    }*/

    solution(str, 0);
    System.out.println(count);

  }

  private static void solution(String str, int start/*, boolean canBeContinue*/) {
    if (start == str.length()) {
      count++;
      return;
    } else {
      if (start < str.length() - 1 && str.charAt(start) == '1') {
        // 当前位置后面是0
        if (str.charAt(start + 1) == '0') {
          // 只能认为是两个字符的编码.
          solution(str, start + 2);
        } else {
          // 可以认为是一个字符的编码
          solution(str, start + 1);

          // 可以认为是编码了两个字符.
          solution(str, start + 2);
        }


      } else if (start < str.length() - 1 && str.charAt(start) == '2') {
        char c = str.charAt(start + 1);
        if (c == '0') {
          solution(str, start + 2);
        } else if (c >= '1' && c <= '6') {
          solution(str, start + 1);

          solution(str, start + 2);
        } else {
          solution(str, start + 1);
        }

        //当前位置是 0
      } else if (str.charAt(start) == '0') {
        return;

        // 当前位置是3-9, 必须单独拆分.
      } else {
        solution(str, start + 1);
      }
    }
  }

}
/*
12

31717126241541717
 */