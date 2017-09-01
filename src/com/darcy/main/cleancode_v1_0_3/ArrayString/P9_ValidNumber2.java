package com.darcy.main.cleancode_v1_0_3.ArrayString;

/**
 * Author by darcy
 * Date on 17-9-1 上午10:41.
 * Description:
 *
 * 验证给定的字符串是否是数值的.这次可能是包含指数的.
 *
 * "0" : true
 * "0.1" : true
 * "abc" : false
 *
 * Q: How to account for whitespaces in the string?
 * A: When deciding if a string is numeric, ignore both leading and trailing whitespaces.
 * Q: Should I ignore spaces in between numbers – such as “1 1”?
 * A: No, only ignore leading and trailing whitespaces. “1 1” is not numeric.
 * Q: If the string contains additional characters after a number, is it considered valid?
 * A: No. If the string contains any non-numeric characters (excluding whitespaces and decimal
 * point), it is not numeric.
 * Q: Is it valid if a plus or minus sign appear before the number?
 * A: Yes. “+1” and “-1” are both numeric.
 * Q: Should I consider only numbers in decimal? How about numbers in other bases such as
 * hexadecimal (0xFF)?
 * A: Only consider decimal numbers. “0xFF” is not numeric.
 * Q: Should I consider exponent such as “1e10” as numeric?
 * A: No. But feel free to work on the challenge that takes exponent into consideration. (The Online
 * Judge problem does take exponent into account.)
 */
public class P9_ValidNumber2 {
  
  /**
   *
   *
   * @param str
   * @return
   *
   *
   */
  public static boolean solution(String str) {
    int i = 0, n = str.length();
    // 忽略leading spaces.
    while (i < n && Character.isSpaceChar(str.charAt(i))) {
      i++;
    }
    // 正负号不影响结果.
    if (i < n && (str.charAt(i) == '+' || str.charAt(i) == '-')) {
      i++;
    }
    boolean isNumberic = false;
    while (i < n && Character.isDigit(str.charAt(i))) {
      i++;
      // 到目前为止为true.
      isNumberic = true;
    }

    // 数值部分必须是符合"dddd.ddd"这样的结构的;

    if (str.charAt(i) == '.') {
      i++;
      while (i < n && Character.isDigit(str.charAt(i))) {
        i++;
        // 到目前为止是true.
        isNumberic = true;
      }
    }

    // while退出循环的时候呢要么到了末尾，要么遇到了非数字字符.
    // 但是e前面呢必须也要有数字才行,所以首先必须判定isNumberic.
    if (isNumberic && i < n && Character.toLowerCase(str.charAt(i)) == 'e') {
      i++;
      // 而如果只有e而后面没有数字字符的化也是error的。
      // 即e10, 10e都是不合法的.
      isNumberic = false;

      // e后面可以接 +、 -号，但是只能直接相邻.
      if (i < n && (str.charAt(i) == '+' || str.charAt(i) == '-')) {
        i++;
      }
      while (i < n && Character.isDigit(str.charAt(i))) {
        isNumberic = true;
        i++;
      }
    }

    // rear spaces..
    while (i < n && Character.isSpaceChar(str.charAt(i))) {
      i++;
    }

    // 前面判定是数字并且即使后面有后导空格，但是没有其他的字符.
    return isNumberic && i == n;

  }

  public static void main(String[] args) {
    String str = "   +12345  ";
    System.out.println(solution(str));

    System.out.println(solution(" e100"));

    System.out.println(solution(" 309e+100 "));

    System.out.println(solution(" 20e 10 "));
    /*System.out.println(e10);
    System.out.println(10e);*/
  }

}
