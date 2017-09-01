package com.darcy.main.cleancode_v1_0_3.ArrayString;

/**
 * Author by darcy
 * Date on 17-9-1 上午8:43.
 * Description:
 *
 * 实现strstr()函数:
 *
 * 找字符串中子字串第一次出现的位置.
 * 大海捞针.
 *
 */
public class P5_ImplStrStr {

  /**
   *
   * 暴力破解算法。
   * 其他还有 Rabin-Karp algorithm, KMP algorithm, or the Boyer-Moore algorithm
   * @param str
   * @param target
   * @return
   */
  public static int solution(String str, String target) {
    for (int i = 0; i < str.length(); i++) {
      for (int j = i + 1; j <= str.length(); j++) {
        if (str.substring(i, j).equals(target)) {
          return i;
        }
      }
    }
    return -1;
  }

  /**
   *
   * i. needle or haystack is empty. If needle is empty, always return 0. If haystack is
   * empty, then there will always be no match (return –1) unless needle is also
   * empty which 0 is returned.
   *
   * ii. needle’s length is greater than haystack’s length. Should always return –1.
   *
   * iii. needle is located at the end of haystack. For example, “aaaba” and “ba”. Catch
   * possible off-by-one errors.
   *
   * iv. needle occur multiple times in haystack. For example, “mississippi” and
   * “issi”. It should return index 2 as the first match of “issi”.
   *
   * v. Imagine two very long strings of equal lengths = n, haystack = “aaa…aa” and
   * needle = “aaa…ab”. You should not do more than n character comparisons, or
   * else your code will get Time Limit Exceeded in OJ.
   *
   * @param str
   * @param target
   * @return
   */
  public static int solution2(String str, String target) { // haystack, needle
    for (int i = 0; i < str.length(); i++) {
      for (int j = 0; ; j++) {
        if (j == target.length()) {
          return i;
        }
        // 到了末尾.
        if (i + j == str.length()) {
          return -1;
        }
        // 其中一个不相等，立即停止.
        if (str.charAt(i + j) != target.charAt(j)) {
          break;
        }
      }
    }
    return -1;
  }

  public static void main(String[] args) {
    String str = "string java";
    String target = "ing";
    System.out.println(solution(str, target));

  }

}
