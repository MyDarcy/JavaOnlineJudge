package com.darcy.main.cleancode_v1_0_3.ArrayString;

/**
 * Author by darcy
 * Date on 17-9-1 上午10:08.
 * Description:
 *
 * Implement atoi to convert a string to an integer.
 * 字符串所代表的数字转为整型
 * 字符串可能包含空格，可能为负数也可能溢出.
 * 能组成整型数据的后面也可能包含一些额外的字符.忽略这部分字符。
 *
 * 溢出的时候分别范围Integer的最大值或者最值.
 *
 */
public class P8_StrToInt {

  public static final int MAX_DIV_10 = Integer.MAX_VALUE / 10;

  /**
   *
   * 1. 去掉leading zero.
   * 2. 确定符号.
   * 3. 关键是处理overflow:
   *     这里是采用简单的做法: Integer.MAX_VALUE = 2147483647; Integer.MIN_VALUE=-2147483648;
   *     除10 = 214748364, 用于比较; 当前位是一个数字,并且之前累计的和已经是 > 214748364了。那么这次 ×10
   *     一定溢出.或者累计和等于214748364,但是当前位是>=8的，所以也会移除.(分析一下最大最小值就可以断定了)
   * @param str
   * @return
   */
  public static Integer atoi(String str) {
    int i = 0, n = str.length();
    int flag = 1;

    while (i < n && Character.isSpaceChar(str.charAt(i))) {
      i++;
    }

    if (i < n && str.charAt(i) == '+') {
      flag = 1;
      i++;
    }

    if (i < n && str.charAt(i) == '-') {
      flag = -1;
      i++;
    }

    int number = 0;
    while (i < n && Character.isDigit(str.charAt(i))) {
      int temp = Character.getNumericValue(str.charAt(i));
      if (number > MAX_DIV_10 || (number == MAX_DIV_10 && temp >= 8)) {
        return flag == 1 ? Integer.MAX_VALUE : Integer.MIN_VALUE;
      }
      number = number * 10 + temp;
      i++;
    }
    return number;
  }

  public static void main(String[] args) {
    String str = "    +12345689e10";
    System.out.println(atoi(str));

  }

}


