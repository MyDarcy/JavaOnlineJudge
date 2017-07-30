package ctci5th.chapter8.section9;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

/**
 * Author by darcy
 * Date on 17-7-30 上午11:08.
 * Description:
 * 某字符串的所有可能组合.
 */
public class P905_StringPermutation {

  /**
   * 同样，后面的n-1个字符构成的所有字串　+ 首字符, 就得到了新的字符串.
   * @param str
   * @return
   */
  public static ArrayList<String> getPermutations(String str) {
    if (str == null) {
      return null;
    }

    ArrayList<String> list = new ArrayList<>();
    if (str.length() == 0) {
      list.add("");
      return list;
    }

    char firstChar = str.charAt(0);
    // 获取所有的子结果.
    String substring = str.substring(1);
    ArrayList<String> subPermutations = getPermutations(substring);
    for (String word : subPermutations) {
      for (int i = 0; i <= word.length(); i++) {
        // 直接利用原字符串，构造新的可能的字符串.
        list.add(buildString(word, i, firstChar));
      }
    }

    return list;
  }

  private static String buildString(String word, int index, char firstChar) {
    return word.substring(0, index) + firstChar + word.substring(index);
  }


  /**
   * 方法2:
   * 参见 jianzhioffer P12 的全排列问题.
   */

  public static void main(String[] args) {
    String str = "abcd";
    ArrayList<String> permutations = getPermutations(str);
    System.out.println(permutations);
    Set<String> set = new HashSet<>(permutations);
    System.out.println(set);
  }
}
