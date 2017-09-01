package com.darcy.main.cleancode_v1_0_3.ArrayString;

/**
 * Author by darcy
 * Date on 17-9-1 下午3:11.
 * Description:
 * <p>
 * 给定一个字符串, 找最多包含两个不同字符的最长子串.
 */
public class P11_LongestSubStringWithAtMostTwoRepeatedChar {

  /**
   * The trick is to maintain a sliding window that always satisfies the invariant where there
   * are always at most two distinct characters in it. When we add a new character that breaks、
   * this invariant, how can we move the begin pointer to satisfy the invariant? Using the
   * above example, our first window is the substring “abba”. When we add the character ‘c’
   * into the sliding window, it breaks the invariant. Therefore, we have to readjust the
   * window to satisfy the invariant again. The question is which starting point to choose so
   * the invariant is satisfied
   *
   * @param str
   * @return
   */
  public static int solution(String str) {
    int i = 0; // 记录两个字符中组成的字符串的起始字符的位置。
    int j = -1; // 记录两个字符中的第二个。如果遇到第三个字符，其位置为k，那么可以直接减去起始字符的位置.
    int maxLen = 0;
    for (int k = 1; k < str.length(); k++) {
      if (str.charAt(k) == str.charAt(k - 1)) continue;
      // maxLength可能一直没有机会获得更新.
      if (j >= 0 && str.charAt(j) != str.charAt(k)) {
        maxLen = Math.max(k - i, maxLen);
        i = j + 1;
      }
      // k == 1, j = 0;
      // k == 2, j = 1;
      j = k - 1;
    }
    // 所以这里
    return Math.max(str.length() - i, maxLen);
  }

  /**
   *
   * @param str
   * @return
   */
  public static int solution2(String str) {

    int[] count = new int[256];
    int i = 0; // 两个字符组成字符串的起始位置.
    int numDistinct = 0;
    int maxLen = 0;
    for (int j = 0; j < str.length(); j++) {
      // 遇到一个新出现的字符.
      if (count[str.charAt(j)] == 0) numDistinct++;
      count[str.charAt(j)]++;
      // 已经有三个字符了,那么需要减少到两个字符.
      while (numDistinct > 2) {
        count[str.charAt(i)]--;
        if (count[str.charAt(i)] == 0) numDistinct--;
        i++;
      }
      maxLen = Math.max(j - i + 1, maxLen);
      System.out.println(maxLen);
    }

    return maxLen;

  }

  public static void main(String[] args) {
    String str = "abaac";
    System.out.println(solution(str));

    System.out.println("***");
    System.out.println("result:" + solution2("ababcabaabb"));
  }

}
