package eopi.ch7_strings;

/**
 * Author by darcy
 * Date on 17-9-18 下午3:18.
 * Description:
 * <p>
 * 序列起始为1, 后面的每个元素都是根据之前的元素生成的.
 * 就是读之前的序列, 1个1, 2个2, 3个3, 四个4 => 11223344.
 * <p>
 * 1 is read off as "one 1" or 11.
 * 11 is read off as "two 1s" or 21.
 * 21 is read off as "one 2, then one 1" or 1211.
 * 1211 is read off as "one 1, one 2, then two 1s" or 111221.
 * 111221 is read off as "three 1s, two 2s, then one 1" or 312211.
 * <p>
 * https://en.wikipedia.org/wiki/Look-and-say_sequence
 * <p>
 * <p>
 * The look-and-say sequence starts with 1. Subsequent numbers are derived by describing
 * the previous number in terms of consecutive(连贯的；连续不断的) digits. Specifically, to generate an
 * entry of the sequence from the previous entry, read off the digits of the previous
 * entry, counting the number of digits in groups of the same digit. For example,
 * 1; one 1; two 1s; one 2 then one 1; one 1, then one 2, then two 1s; three 1s,
 * then two 2s, then one 1. The first eight numbers in the look-and-say sequence are
 * <1,11, 21,1211,111221,312211,13112221,1113213211>.
 * <p>
 * Write a program that takes as input an integer n and returns the nth integer
 * in the look-and-say sequence. Return the result as a string.
 * <p>
 * Hint: You need to return the result as a string.
 */
public class P8_LookAndSayProblems {


  public static String lookAndSay(int n) {
    String s = "1";
    for (int i = 1; i < n; ++i) {
      s = nextNumber(s);
    }
    return s;
  }

  public static String lookAndSay2(int n) {
    String s = "1";
    for (int i = 1; i < n; ++i) {
      s = solution(s);
    }
    return s;
  }

  private static String nextNumber(String s) {
    StringBuilder result = new StringBuilder();
    for (int i = 0; i < s.length(); ++i) {
      int count = 1;
      while (i + 1 < s.length() && s.charAt(i) == s.charAt(i + 1)) {
        ++i;
        ++count;
      }
      result.append(count);
      result.append(s.charAt(i));
    }
    return result.toString();
  }

  /**
   * 13112221
   *
   * @param string
   * @return
   */
  private static String solution(String string) {
    int i = 0;
    StringBuilder sb = new StringBuilder();
    while (i < string.length()) {
      int count = 1;
      while (i < string.length() - 1 && string.charAt(i) == string.charAt(i + 1)) {
        i++;
        count++;
      }
      sb.append(count);
      sb.append(string.charAt(i));

      i++;
    }

    return sb.toString();
  }

  public static void main(String[] args) {
    for (int i = 0; i < 10; i++) {
      System.out.println(lookAndSay(i + 1));
    }

    System.out.println("----------");
    for (int i = 0; i < 10; i++) {
      System.out.println(lookAndSay2(i + 1));
    }
  }

}
