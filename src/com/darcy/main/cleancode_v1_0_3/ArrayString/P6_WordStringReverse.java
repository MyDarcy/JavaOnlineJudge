package com.darcy.main.cleancode_v1_0_3.ArrayString;

/**
 * Author by darcy
 * Date on 17-9-1 上午9:01.
 * Description:
 * <p>
 * 单词组成的字符串逆序.
 * <p>
 * For example, given s = "the sky is blue", return "blue is sky the".
 * <p>
 * 不包含除了空格以外的其他其他空格类型.
 * <p>
 * Q: What constitutes a word?
 * A: A sequence of non-space characters constitutes a word.
 * Q: Does tab or newline character count as space characters?
 * A: Assume the input does not contain any tabs or newline characters.
 * Q: Could the input string contain leading or trailing spaces?
 * A: Yes. However, your reversed string should not contain leading or trailing spaces.
 * Q: How about multiple spaces between two words?
 * A: Reduce them to a single space in the reversed string.
 */
public class P6_WordStringReverse {

  /**
   *
   * @param str
   * @return
   */
  public static String solution(String str) {

    String[] split = str.trim().split("\\s+");

    StringBuilder sb = new StringBuilder();
    for (int i = split.length - 1; i >= 0; i--) {
      if (i != 0) {
        sb.append(split[i] + " ");
      } else {
        sb.append(split[i]);
      }
    }
    return sb.toString();
  }

  /**
   *
   * @param str
   * @return
   */
  public static String solution2(String str) {
    StringBuilder sb = new StringBuilder();
    int j = str.length();
    for (int i = str.length() - 1; i >= 0; i--) {
      // 更新j;
      if (str.charAt(i) == ' ') {
        j = i;
      }

      if (i == 0 || str.charAt(i - 1) == ' ') {
        // 第一次只加str, 后面每一次都加 " " + str;
        if (sb.length() != 0) {
          sb.append(" ");
        }
        sb.append(str.substring(i, j));
      }
    }

    return sb.toString();
  }

  public static void main(String[] args) {
    String result = solution("the sky is blue");
    System.out.println(result);

    System.out.println(solution2("the sky is blue"));

  }
}
