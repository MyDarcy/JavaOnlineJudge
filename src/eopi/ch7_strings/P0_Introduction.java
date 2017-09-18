package eopi.ch7_strings;

/**
 * Author by darcy
 * Date on 17-9-18 下午2:33.
 * Description:
 */
public class P0_Introduction {

  /**
   * The time complexity is O(n) and the space complexity is O(1)
   *
   * @param s
   * @return
   */
  public static boolean isPalindromic(String s) {
    for (int i = 0, j = s.length() - 1; i < j; ++i, --j) {
      if (s.charAt(i) != s.charAt(j)) {
        return false;
      }
    }
    return true;
  }

}
