package leetcode;

import java.util.Arrays;
import java.util.List;

/**
 * Given a string s, find the longest palindromic substring in s. You may assume that the maximum length of s is 1000.
 *
 * Example 1:
 *
 * Input: "babad"
 * Output: "bab"
 * Note: "aba" is also a valid answer.
 * Example 2:
 *
 * Input: "cbbd"
 * Output: "bb"
 *
 * 给定字符串，求其最长回文子串
 *
 * 1.暴力的方法：
 * 一共有n(n-1)/2个子字符串(选择两个位置就可以得到一个子串)，每个耗时O(n), 总的时间复杂度O(n^3)；
 *
 * 2.动态规划：
 * define P(i,j) as following:
 *
 * P(i,j) = true if Si to Sj is palindrome, false otherwise;
 * P(i,j)=(P(i+1,j−1) and Si == Sj
 *
 * 可以确定值的情况是
 * P(i,i)=true
 * P(i, i+1) = (Si == Si+1)
 * 时间复杂度O(n^2), 空间复杂度O(n^2)
 * ​
 */
public class P0005_M_LongestPalindrome {

  /**
   * self:
   * 思路： 遍历到某个位置，继续从左右两边继续遍历, 不太行; 没考虑奇数和偶数，导致边界条件控制复杂;
   * @param s
   * @return
   */
  public String longestPalindrome(String s) {
    if (s == null) {
      return null;
    }
    if (s.length() == 0) {
      return "";
    }

    String result = "";
    return result;
  }

  /**
   * 动态规划的算法;
   * dp(i, j) represents whether s(i ... j) can form a palindromic substring,
   * dp(i, j) is true when s(i) equals to s(j) and s(i+1 ... j-1) is a palindromic substring.
   * When we found a palindrome, check if it's the longest one. Time complexity O(n^2).
   *
   * i从后往前的原因
   * because for dp[i][j], you first need to know dp[i+1][j-1].
   * so for i, you should start from the end(i+1 is greater than i, calculate i+1 befor i).
   *
   * @param s
   * @return
   */
  public String longestPalindrome2(String s) {
    int n = s.length();
    String res = null;

    boolean[][] dp = new boolean[n][n];
    // 可以初始化基本情况；

    for (int i = n - 1; i >= 0; i--) {
      for (int j = i; j < n; j++) {
        // j - i = 0, 相当于默认为true; j - i = 1， 相邻两个元素相等即为true； j - i = 2, 里面只有一个字符，所有也是只要外面两个边界字符相等即可。
        dp[i][j] = s.charAt(i) == s.charAt(j) && (j - i < 3 || dp[i + 1][j - 1]);

        if (dp[i][j] && (res == null || j - i + 1 > res.length())) {
          res = s.substring(i, j + 1);
        }
      }
    }

    return res;
  }

  int left;
  int maxLength;
  /**
   * 分 奇偶 从两边拓展;
   * @param s
   * @return
   */
  public String longestPalindrome3(String s) {
    if (s == null) {
      return null;
    }
    if (s.length() == 0) {
      return "";
    }

    int length = s.length();
    for (int i = 0; i < length; i++) {
      extend2Palindrome(s, i, i); // 奇数长度
      extend2Palindrome(s, i, i + 1); // 偶数长度
    }
    return s.substring(left, left + maxLength);
  }

  public void extend2Palindrome(String str, int i, int j) {
    while (i >= 0 && j < str.length() && str.charAt(i) == str.charAt(j)) {
      i--;
      j++;
    }
    if (maxLength < j - i - 1) {
      left = i + 1;
      maxLength = j - i - 1;
    }
  }

  public String longestPalindrome4(String s) {
    if (s == null || s.trim().equals("")) {
      return s;
    }
    int len = s.length();
    int begin = 0;
    int maxLen = 0;
    // 因为 后续 要找到最大的回文串的的话，最大的可满足的拓展点就是 len-maxLen/2;
    for (int i = 0; i < len - maxLen / 2; i++) {
      int j = i;
      int k = i;
      // 需要向左边拓展的情况也是包含了
      while (k < len - 1 && s.charAt(k) == s.charAt(k + 1)) { // Skip duplicated characters to the right
        k++;
      }
      while (j > 0 && k < len - 1 && s.charAt(j-1) == s.charAt(k+1)) { // Expand both left and right
        j--;
        k++;
      }
      int newLen = k - j + 1;
      if (newLen > maxLen) {
        begin = j;
        maxLen = newLen;
      }
    }
    return s.substring(begin, begin + maxLen);
  }

  /**
   * office solutions
   * @param s
   * @return
   */
  public String longestPalindrome5(String s) {
    if (s == null || s.length() < 1) return "";
    int start = 0, end = 0;
    for (int i = 0; i < s.length(); i++) {
      int len1 = expandAroundCenter(s, i, i);
      int len2 = expandAroundCenter(s, i, i + 1);
      int len = Math.max(len1, len2);
      if (len > end - start) {
        start = i - (len - 1) / 2;
        end = i + len / 2;
      }
    }
    return s.substring(start, end + 1);
  }

  private int expandAroundCenter(String s, int left, int right) {
    int L = left, R = right;
    while (L >= 0 && R < s.length() && s.charAt(L) == s.charAt(R)) {
      L--;
      R++;
    }
    return R - L - 1;
  }


  public static void main(String[] args) {
    List<String> list = Arrays.asList("babad", "cbbd", "aabbb");
    for (int i = 0; i < list.size(); i++) {
      P0005_M_LongestPalindrome instance = new P0005_M_LongestPalindrome();
      System.out.println(instance.longestPalindrome4(list.get(i)));
    }
  }

}
