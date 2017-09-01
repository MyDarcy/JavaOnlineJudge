package com.darcy.main.cleancode_v1_0_3.ArrayString;

/**
 * Author by darcy
 * Date on 17-9-1 下午8:25.
 * Description:
 *
 * 给定两个字符串S,T;判定他们是否都是一次编辑距离。
 *
 * Hint:
 * 1. If | n – m | is greater than 1, we know immediately both are not one-edit distance
 *  apart.
 * 2. It might help if you consider these cases separately, m == n and m ≠ n.
 * 3. Assume that m is always ≤ n, which greatly simplifies the conditional statements.
 *  If m > n, we could just simply swap S and T.
 * 4. If m == n, it becomes finding if there is exactly one modified operation. If m ≠ n,
 *  you do not have to consider the delete operation. Just consider the insert operation
 *  in T.
 *
 * Assume X represents the one-edit character. There are three one-edit distance operations
 * that could be applied to S.
 *
 * i. Modify operation – Modify a character to X in S.
 * S = “abcde”
 * T = “abXde”
 *
 * ii. Insert operation – X was inserted before a character in S.
 * S = “abcde”
 * T = “abcXde”
 *
 * iii. Append operation – X was appended at the end of S.
 * S = “abcde”
 * T = “abcdeX”
 *
 */
public class p14_OneEditDistance {

  /**
   * 默认str1.length < str2.length
   *
   * 当str1的字符都匹配str2的字符的时候,看末尾是否还有一个字符.
   * 如果两者长度相差1,那么遇到不相等的位置之前和之后都应该是相等,不相等的位置只会出现在str2中，只需要跳过去即可.
   * 如果两者长度相同.都跳过不匹配的字符, 而其他位置的字符都是相等的.
   *
   * @param str1
   * @param str2
   * @return
   */
  public static boolean solution(String str1, String str2) {
    int m = str1.length();
    int n = str2.length();

    if (n < m) {
      solution(str2, str1);
    }

    if (n - m > 1) {
      return false;
    }

    int i = 0;
    int diff = n - m;
    // 前面部分的匹配,也可能是匹配整个字符串.
    while (i < m && str1.charAt(i) == str2.charAt(i)) {
      i++;
    }

    // 第一个字符串已经匹配完毕,那么多余的字符是append的。
    // 但是完全相同而没有多余字符的也不少 One Edit Distance.
    if (i == m) {
      return diff > 0;
    }

    // 两者长度相同. 那么出现不一致只是说明改位置进行了变换.
    if (diff == 0) {
      i++;
    }

    // diff==0时候是两个字符串中间有一个字符进行了替换.
    // diff==1时候是两个字符串中插入了一个字符. 所以str2中(index + diff) 可以change和insert两种情况的匹配.
    while (i < m && str1.charAt(i) == str2.charAt(i+diff)) {
      i++;
    }

    return i == m;



  }

  public static void main(String[] args) {

  }
}
