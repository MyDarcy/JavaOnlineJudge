package com.darcy.main.cleancode_v1_0_3.Misc;

import java.util.HashMap;
import java.util.Map;


/**
 * Author by darcy
 * Date on 17-9-3 下午9:00.
 * Description:
 */
public class P37_RomanToInteger {

  private static Map<Character, Integer> map =
      new HashMap<Character, Integer>() {{
        put('I', 1);
        put('V', 5);
        put('X', 10);
        put('L', 50);
        put('C', 100);
        put('D', 500);
        put('M', 1000);
      }};

  /**
   * 抓住下面一个公式即可. 避免处理2个字符代表一个数字的情况.
   * <p>
   * XL = 40 = 10 + (50 - 2*10) = X + (X - 2*L)
   *
   * @param roman
   * @return
   */
  public static Integer solution(String roman) {
    int result = 0;
    int prev = 0;
    for (char c : roman.toCharArray()) {
      int value = map.get(c);
      result += (value > prev) ? (value - 2 * prev) : value;
      prev = value;
    }

    return result;
  }

  public static void main(String[] args) {
    String[] strings = {"MMMCMXCIX", "DLXVII", "MMMCXXXIV", "CCCI"};
    for (String str : strings) {
      System.out.println(str + " -> " + solution(str));
    }
  }

}
