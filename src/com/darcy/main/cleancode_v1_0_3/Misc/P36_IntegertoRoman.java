package com.darcy.main.cleancode_v1_0_3.Misc;

/**
 * Author by darcy
 * Date on 17-9-3 下午8:46.
 * Description:
 *
 * 整形数字转为罗马数字.
 *
 * Given an integer, convert it to a roman numeral.
 * Input is guaranteed to be within the range from 1 to 3999.
 *
 * I 1
 * V 5
 * X 10
 * L 50
 * C 100
 * D 500
 * M 1000
 *
 */
public class P36_IntegertoRoman {

  private static final int[] values = {
      1000, 900, 500, 400,
      100, 90, 50, 40,
      10, 9, 5, 4,
      1
  };
  private static final String[] symbols = {
      "M", "CM", "D", "CD",
      "C", "XC", "L", "XL",
      "X", "IX", "V", "IV",
      "I"
  };

  public static String solution(int number) {
    StringBuilder sb = new StringBuilder();
    int i = 0;
    while (number > 0) {
      int k = number / values[i];
      for (int j = 0; j < k; j++) {
        sb.append(symbols[i]);
        number -= values[i];
      }
      i++;
    }
    return sb.toString();
  }

  public static void main(String[] args) {
    System.out.println(solution(3999));
    System.out.println(solution(567));
    System.out.println(solution(3134));
    System.out.println(solution(301));

  }

}
