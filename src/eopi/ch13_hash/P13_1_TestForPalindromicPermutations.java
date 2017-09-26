package eopi.ch13_hash;

import java.util.HashMap;
import java.util.Map;

/**
 * Author by darcy
 * Date on 17-9-26 下午2:52.
 * Description:
 *
 * 测试一个字符串能够通过permutation成为一个回文字符串.
 *
 * A palindrome is a string that reads the same forwards and backwards, e.g., "level",
 * "rotator", and "foobaraboof".
 *
 * Write a program to test whether the letters forming a string can be permuted to for
 * a palindrome. For example, "edified" can be permuted to form "deified".
 *
 * Hint: Find a simple characterization of strings that can be permuted to form a palindrome.
 *
 */
public class P13_1_TestForPalindromicPermutations {

  /**
   * 1.直接进行permutation然后看特定的字符串是否是回文字符串.但是时间复杂度是2^N次方.
   * 2.统计各个字符出现的次数.
   *
   * 时间复杂度是O(N), 空间复杂度是O(N)
   *
   * @param str
   * @return
   */
  public static boolean solution(String str) {
    Map<Character, Integer> map = new HashMap<>();
    for (int i = 0; i < str.length(); i++) {
      char ch = str.charAt(i);
      if (!map.containsKey(ch)) {
        map.put(ch, 1);
      } else {
        map.put(ch, map.get(ch) + 1);
      }
    }

    // 出现了奇数次的字符最多只能出现一次.
    int oddCount = 0;
    for (Map.Entry<Character, Integer> entry : map.entrySet()) {
      if ((entry.getValue() & 1) != 0 && ++oddCount > 1) {
        return false;
      }
    }
    return true;
  }
}
