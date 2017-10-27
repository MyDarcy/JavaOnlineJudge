package nowcoder.toutiao;

import java.util.Arrays;
import java.util.Scanner;

/**
 * Created by hzq19
 * Date on 2017/10/25 19:25.
 * Description:
 *
 * 有一个仅仅包含'a', 'b'的字符串s, 每次操作可以把一个字符串做一次转换
 * 如: a->b, b->a, 但是操作的次数有限, 那么在有限的操作数范围内, 能够得到
 * 的最大连续的相同的字符的子串的长度.
 *
 * input:
 * strLength changeTime
 * string
 * 8 1
 * aabaabaa
 *
 * output:
 * 5
 */
public class Main1 {

  public static void main(String[] args) {
    Scanner input = new Scanner(System.in);
    int n = input.nextInt();
    int m = input.nextInt();
    String str = input.next();
    input.close();

    int result = solution(n, m, str);
  }

  /**
   *
   * @param n
   * @param m
   * @param str
   * @return
   */
  private static int solution(int n, int m, String str) {
    int[] countA = new int[n];
    int[] countB = new int[n];
    for (int i = 0; i < n; i++) {
      if (str.charAt(i) == 'a') {
        countA[i] = (i == 0 ? 1 : countA[i - 1] + 1);
        countB[i] = (i == 0 ? 0 : countB[i - 1]);
      } else if (str.charAt(i) == 'b') {
        countB[i] = (i == 0 ? 1 : countB[i - 1] + 1);
        countA[i] = (i == 0 ? 0 : countA[i - 1]);
      }
    }

    int[] reverseCountA = new int[n];
    int[] reverseCountB = new int[n];
    for (int i = n - 1; i >= 0; i--) {
      if (str.charAt(i) == 'a') {
        reverseCountA[i] = (i == n - 1 ? 1 : reverseCountA[i + 1] + 1);
        reverseCountB[i] = (i == n - 1 ? 0 : reverseCountB[i + 1]);
      } else if (str.charAt(i) == 'b') {
        reverseCountB[i] = (i == n - 1 ? 1 : reverseCountB[i + 1] + 1);
        reverseCountA[i] = (i == n - 1 ? 0 : reverseCountA[i + 1]);
      }
    }
    /*System.out.println(Arrays.toString(countA));
    System.out.println(Arrays.toString(countB));
    System.out.println(Arrays.toString(reverseCountA));
    System.out.println(Arrays.toString(reverseCountB));*/

    // 消除b.
    for (int i = 0; i < n; i++) {
      if (str.charAt(i) == 'a') {
        if (i == 0) {
          int index = searchCharCount(i + 1, 'b', m, countB);
        }
      }
    }

    return 0;
  }

  private static int searchCharCount(int start, char ch, int count, int[] countB) {
    return 0;
  }

}
