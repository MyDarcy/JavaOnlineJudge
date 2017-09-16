package com.darcy.main.cleancode_v1_0_3.ArrayString;

import java.util.Arrays;

/**
 * Author by darcy
 * Date on 17-9-1 下午5:11.
 * Description:
 * <p>
 * 给定字符串S, 找S中的最长回文子串.可以假设字符串长度<1000,
 * 并且一定存在这样的回文子串.
 */
public class P13_LongestPalindromicSubstring {

  /**
   * 在原字符串和原字符串的逆序中找common str.
   * 有的时候成功: S = “caba”, S’ = “abac”. common = "aba"
   * 有的时候失败: S = “abacdfgdcaba”, S’ = “abacdgfdcaba”, common = "abacd",
   * 因为原字符串的前面和后面存在相同的子字符串部分。
   *
   * @param string
   * @return
   */
  public static String errorSolution(String string) {

    return null;
  }

  /**
   * TODO
   * need to fix it.
   * <p>
   * O(n^2)的时间复杂度,O(n^2)空间复杂度.
   *
   * @param str
   * @return
   */
  public static String solution(String str) {
    boolean[][] P = new boolean[str.length()][str.length()];
    dpSolution(str, 0, str.length() - 1, P);

    for (boolean[] item : P) {
      System.out.println(Arrays.toString(item));
    }

    int maxLength = 0;
    String maxLengthString = null;
    for (int i = 0; i < P.length; i++) {
      for (int j = 0; j < P[i].length; j++) {
        if (P[i][j]) {
          if (j - i + 1 > maxLength) {
            maxLength = j - i + 1;
            maxLengthString = str.substring(i, j + 1);
          }
        }
      }
    }
    return maxLengthString;
  }

  private static boolean dpSolution(String str, int start, int end, boolean[][] P) {
    if (start == end) {
      /*P[start][end] = true;*/
      return true;
    } else if (start + 1 == end) {
      /*P[start][end] = str.charAt(start) == str.charAt(end);*/
      return str.charAt(start) == str.charAt(end);
    } else {
      /*for (int i = 0; i < str.length(); i++) {
        for (int j = i + 1; j < str.length(); j++) {
          P[i][j] = dpSolution(str, i + 1, j - 1, P)
              && str.charAt(i) == str.charAt(j);
        }
      }*/
      return P[start][end];
    }
  }

  /**
   * @param s
   * @return
   */
  public static String solutionDP(String s) {
    int n = s.length();
    int longestBegin = 0;
    int maxLen = 1;
    // i到j之间是否是回文.
    boolean[][] table = new boolean[1000][1000];
    // 长度为1的回文.
    for (int i = 0; i < n; i++) {
      table[i][i] = true;
    }
    // 长度为2的回文.
    for (int i = 0; i < n - 1; i++) {
      if (s.charAt(i) == s.charAt(i + 1)) {
        table[i][i + 1] = true;
        longestBegin = i;
        maxLen = 2;
      }
    }
    // 长度 >=3 的回文.
    for (int len = 3; len <= n; len++) {
      for (int i = 0; i < n - len + 1; i++) {
        int j = i + len - 1;
        if (s.charAt(i) == s.charAt(j) && table[i + 1][j - 1]) {
          table[i][j] = true;
          longestBegin = i;
          maxLen = len;
        }
      }
    }
    return s.substring(longestBegin, maxLen);
  }

  /**
   * 以index为中心,向两边拓展,拓展的过程中保证回文属性.
   * O(n^2)的时间复杂度,O(1)的空间复杂度.
   * 每一个index都可以进行拓展,单个拓展的时间复杂度 O(n),总的时间复杂度O(n^2);
   * <p>
   * other solution:
   * http://articles.leetcode.com/longest-palindromic-substring-part-ii
   *
   * @param str
   * @return
   */
  public static String solution2(String str) {
    int start = 0;
    int end = 0;
    for (int i = 0; i < str.length(); i++) {
      int length1 = expandAroundCenter(str, i, i);
      int length2 = expandAroundCenter(str, i, i + 1);
      int maxLength = Math.max(length1, length2);
      // 由index处的元素获得的回文串.
      // 新的最长回文.
      if (maxLength > end - start) {
        start = i - (maxLength - 1) / 2;
        end = i + maxLength / 2;
      }
    }
    // 获取新的最长回文串.
    return str.substring(start, end + 1);
  }

  private static int expandAroundCenter(String str, int left, int right) {
    int L = left;
    int R = right;
    while (L >= 0 && R < str.length() && str.charAt(L) == str.charAt(R)) {
      L--;
      R++;
    }
    // 此时两处的char不相等.
    // 那么相等的回文部分的长度是R-L-1;
    return R - L - 1;
  }


  public static void main(String[] args) {
    String str = "abcdcbaaaa";
    System.out.println(solutionDP(str));
    System.out.println(solution2(str));

  }

}
