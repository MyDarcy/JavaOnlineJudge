package eopi.ch7_strings;

import java.util.HashMap;
import java.util.Map;

import static javax.swing.UIManager.put;

/**
 * Author by darcy
 * Date on 17-9-18 下午3:52.
 * Description:
 * <p>
 * 罗马文到数字.
 * <p>
 * The Roman numeral representation of positive integers uses the symbol
 * I,V, X,L,C,D,M. Each symbol represents a value, with I being 1, V being 5, X being
 * 10, L being 50, C being 100, D being 500, and M being 1000
 * <p>
 * • I can immediately precede V and X.
 * • X can immediately precede L and C.
 * • C can immediately precede D and M.
 */
public class P9_RomanToDecimal {

  /**
   * @param s
   * @return
   */
  public static int solution(String s) {
    Map<Character, Integer> T = new HashMap<Character, Integer>() {
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

    int sum = T.get(s.charAt(s.length() - 1));
    for (int i = s.length() - 2; i >= 0; --i) {
      if (T.get(s.charAt(i)) < T.get(s.charAt(i + 1))) {
        sum -= T.get(s.charAt(i));
      } else {
        sum += T.get(s.charAt(i));
      }
    }
    return sum;

  }

  public static void main(String[] args) {

  }

}
