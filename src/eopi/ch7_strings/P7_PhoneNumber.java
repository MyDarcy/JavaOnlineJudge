package eopi.ch7_strings;

import java.util.ArrayList;
import java.util.List;

/**
 * Author by darcy
 * Date on 17-9-18 下午3:00.
 * Description:
 * <p>
 * 旧时手机的number到"abc"字符串的转换.
 */
public class P7_PhoneNumber {

  /**
   * 暴力方法: length个循环嵌套.
   *
   * @return
   */
  public static String bruteForceSolution() {

    return null;
  }

  public static final String[] MAPPINGS = {"0", "1", "ABC", "DEF", "GHI", "JKL", "MNO", "PQRS", "TUV", "WXYZ"};

  /**
   * 不用上面的n层循环用递归来解决.
   * <p>
   * recursion
   * The execution path is very similar to that of the brute-force approach, but the
   * compiler handles the looping
   * <p>
   * the number of recursive calls, T(n), satisfies T(n) < 4T(n — 1), T(n) = O(4^n)
   * <p>
   * 事件复杂度O(n4^n), 因为n个字符的字符串的创建是O(n)的复杂度.
   *
   * @param phoneNumber
   * @return
   */
  public static List<String> solution(String phoneNumber) {
    List<String> result = new ArrayList<>();
    char[] partial = new char[phoneNumber.length()];
    solution(phoneNumber, 0, partial, result);
    return result;
  }

  private static void solution(String phoneNumber, int start, char[] partial, List<String> result) {
    if (start == partial.length) {
      result.add(new String(partial));
      return;
    } else {
      for (int i = 0; i < MAPPINGS[phoneNumber.charAt(start) - '0'].length(); i++) {
        char c = MAPPINGS[phoneNumber.charAt(start) - '0'].charAt(i);
        partial[start] = c;
        solution(phoneNumber, start + 1, partial, result);
      }
    }
  }

  public static void main(String[] args) {
    List<String> result = solution("0123456789");
    System.out.println(result);
  }

}
