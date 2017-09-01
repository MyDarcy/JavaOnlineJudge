package com.darcy.main.cleancode_v1_0_3.ArrayString;

/**
 * Author by darcy
 * Date on 17-9-1 上午8:34.
 * Description:
 *
 * 判断一个字符串是否是回文，只考虑其中的字母和数字部分.
 *
 */
public class P4_ValidPalindrome {

  /**
   * Character API的熟悉.
   * @see     Character#isDigit(char)
   * @see     Character#isJavaIdentifierPart(char)
   * @see     Character#isJavaLetter(char)
   * @see     Character#isJavaLetterOrDigit(char)
   * @see     Character#isLetter(char)
   * @see     Character#isUnicodeIdentifierPart(char)
   *
   * @param string
   * @return
   */
  public static boolean solution(String string) {
    if (string == null || string.length() == 0) {
      return false;
    }

    int low = 0;
    int high = string.length() - 1;
    while (low < high) {
      while (low < high && !Character.isLetterOrDigit(string.charAt(low))) {
        low++;
      }

      while (low < high && !Character.isLetterOrDigit(string.charAt(high))) {
        high--;
      }

      // 一直要循环完毕. 直到low == high
      if (Character.toLowerCase(string.charAt(low)) != Character.toLowerCase(string.charAt(high))) {
        return false;
      }

      low++;
      high--;
    }

    return true;
  }


  public static void main(String[] args) {
    String str1 = "A man, a plan, a canal: Panama";
    System.out.println(solution(str1));

    String str2 = "race a car";
    System.out.println(solution(str2));

  }

}
