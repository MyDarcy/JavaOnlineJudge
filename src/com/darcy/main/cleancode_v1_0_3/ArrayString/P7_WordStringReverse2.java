package com.darcy.main.cleancode_v1_0_3.ArrayString;

/**
 * Author by darcy
 * Date on 17-9-1 上午9:01.
 * Description:
 * <p>
 * 单词组成的字符串逆序. 不占用额外的空间.
 * <p>
 * For example, given s = "the sky is blue", return "blue is sky the".
 * <p>
 * 不包含除了空格以外的其他空格类型.
 * <p>
 */
public class P7_WordStringReverse2 {

  /**
   *
   * 字符串经过两次反转,得到自身.
   *
   * @param str
   * @return
   */
  public static void solution(char[] str) {
    // 先逆序.
    reverse(str, 0, str.length);
    System.out.println(new String(str));
    // j就是代表一个字符串的末尾字符的下一个字符.
    for (int i = 0, j = 0; j <= str.length; j++) {
      // 空格不参与reverse.
      if (j == str.length || str[j] == ' ') {
        reverse(str, i, j);
        i = j + 1;
      }
    }
  }

  /**
   *
   * @param str
   * @param start
   * @param end 逆序的最后一个字符的下一个位置.
   */
  private static void reverse(char[] str, int start, int end) {
    for (int i = 0; i < (end - start) / 2; i++) {
      /*char temp = str[end - i - 1];
      str[end - i - 1] = str[start + i];
      str[start + i] = temp;*/
      char temp = str[start + i];
      str[start + i] = str[end - i - 1];
      str[end - i - 1] = temp;
    }
  }


  public static void main(String[] args) {
    char[] str = "the sky is blue".toCharArray();
    System.out.println(str.length);
    solution(str);
    System.out.println(str);
  }
}
