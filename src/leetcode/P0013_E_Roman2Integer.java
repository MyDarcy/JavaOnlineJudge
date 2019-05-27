package leetcode;

import java.util.HashMap;
import java.util.Map;

/**
 * Roman numerals are represented by seven different symbols: I, V, X, L, C, D and M.
 *
 * Symbol       Value
 * I             1
 * V             5
 * X             10
 * L             50
 * C             100
 * D             500
 * M             1000
 * For example, two is written as II in Roman numeral, just two one's added together.
 * Twelve is written as, XII, which is simply X + II. The number twenty seven is written as XXVII,
 * which is XX + V + II.
 *
 * Roman numerals are usually written largest to smallest from left to right.
 * However, the numeral for four is not IIII. Instead, the number four is written as IV.
 * Because the one is before the five we subtract it making four.
 * The same principle applies to the number nine, which is written as IX.
 * There are six instances where subtraction is used:
 *
 * I can be placed before V (5) and X (10) to make 4 and 9.
 * X can be placed before L (50) and C (100) to make 40 and 90.
 * C can be placed before D (500) and M (1000) to make 400 and 900.
 * Given a roman numeral, convert it to an integer. Input is guaranteed to be within the range from 1 to 3999.
 *
 * Example 1:
 *
 * Input: "III"
 * Output: 3
 * Example 2:
 *
 * Input: "IV"
 * Output: 4
 * Example 3:
 *
 * Input: "IX"
 * Output: 9
 * Example 4:
 *
 * Input: "LVIII"
 * Output: 58
 * Explanation: L = 50, V= 5, III = 3.
 * Example 5:
 *
 * Input: "MCMXCIV"
 * Output: 1994
 * Explanation: M = 1000, CM = 900, XC = 90 and IV = 4.
 *
 * Given a roman numeral, convert it to an integer.
 * Input is guaranteed to be within the range from 1 to 3999.
 * 罗马数字 -> 十进制数字
 */
public class P0013_E_Roman2Integer {

  /**
   * 应该不会出现 MCCCD这种有歧义的解释；
   * @param s
   * @return
   */
  public int romanToInt(String s) {

    Map<Character, Integer> map = new HashMap<Character, Integer>() {

      {
        put('I', 1);
        put('V', 5);
        put('X', 10);
        put('L', 50);
        put('C', 100);
        put('D', 500);
        put('M', 1000);
      }

    };

    int result = 0;
    Character previous = null;
    for (int i = 0; i < s.length(); i++) {
      char currentChar = s.charAt(i);
      result += map.get(currentChar);
      if (previous != null && map.get(previous) < map.get(currentChar)) {
        int samePrevious = 1;
        int sameCharIndex = i - 2;
        while (sameCharIndex >= 0 && s.charAt(sameCharIndex) == previous) {
          samePrevious += 1;
        }
        // 加了两遍;
        result -= 2 * map.get(previous);
      }
      previous = s.charAt(i);
    }

    return result;
  }

  /**
   *
   * @param s
   * @return
   */
  public int romanToInt2(String s) {
    int sum=0;
    if(s.indexOf("IV")!=-1){sum-=2;} // 多算了两次I;
    if(s.indexOf("IX")!=-1){sum-=2;} // 同理；
    if(s.indexOf("XL")!=-1){sum-=20;}
    if(s.indexOf("XC")!=-1){sum-=20;}
    if(s.indexOf("CD")!=-1){sum-=200;}
    if(s.indexOf("CM")!=-1){sum-=200;}

    char c[]=s.toCharArray();
    int count=0;

    for(;count<=s.length()-1;count++){
      if(c[count]=='M') sum+=1000;
      if(c[count]=='D') sum+=500;
      if(c[count]=='C') sum+=100;
      if(c[count]=='L') sum+=50;
      if(c[count]=='X') sum+=10;
      if(c[count]=='V') sum+=5;
      if(c[count]=='I') sum+=1;

    }

    return sum;

  }

  /**
   * 拼接而成的只能是 -1， -10， -100， 不会是-2， -20，-200这种吗？？
   * @param s
   * @return
   */
  public int romanToInt3(String s) {
    Map<Character, Integer> map = new HashMap<>();
    map.put('I', 1);
    map.put('V', 5);
    map.put('X', 10);
    map.put('L', 50);
    map.put('C', 100);
    map.put('D', 500);
    map.put('M', 1000);
    char[] chars = s.toCharArray();
    int result = 0;
    int i = 0, j = 1;
    for(; j < chars.length; i++, j++) {
      if (map.get(chars[i]) >= map.get(chars[j])) {
        result += map.get(chars[i]);
      } else {
        result -= map.get(chars[i]);
      }
    }
    result += map.get(chars[i]);
    return result;
  }

  public static void main(String[] args) {
    P0013_E_Roman2Integer instance = new P0013_E_Roman2Integer();
    System.out.println(instance.romanToInt3("III"));
    System.out.println(instance.romanToInt3("IV"));
    System.out.println(instance.romanToInt3("IX"));
    System.out.println(instance.romanToInt3("LVIII"));
    System.out.println(instance.romanToInt3("MCMXCIV"));
  }

}
