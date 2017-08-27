package eopi.ch16_recursion;

import java.util.ArrayList;
import java.util.List;

/**
 * Author by darcy
 * Date on 17-8-27 下午7:40.
 * Description:
 *
 * 计算字符串所有的回文分解，即分解出的每个部分都是回文。
 *
 * Compute all palindromic decompositions of a given string. For example, if the string
 * is "0204451881", then the decomposition "020", "44", "5", "1881" is palindromic, as
 * is "020", "44", "5", "1", "88", "1". However, "02044, "5", "1881" is not a palindromic
 * decomposition
 *
 * Hint: Focus on the first palindromic string in a palindromic decomposition
 *
 */
public class P8_PalindromicDecomposition {

  /**
   *
   * We can brute-force compute all palindromic decompositions by first computing
   * all decompositions, and then checking which ones are palindromic. To compute
   * all decompositions, we use prefixes of length 1,2... for the first string in the
   * decomposition, and recursively compute the decomposition of the corresponding
   * suffix. The number of such decompositions is 2"_1, where n is the length of the string.
   * (One way to understand this is from the fact that every «-bit vector corresponds
   * to a unique decomposition—the Is in the bit vector denote the starting point of a substring.)
   *
   * @param input
   * @return
   */
  public static List<List<String>> bruteForceSolution(String input) {

    return null;
  }

  /**
   * 要使的分解后的各个部分都是回文的，那么需要所有的前缀部分都必须是回文的，这样往后查找才是有意义的。
   *
   * we should enumerate decompositions that begin with a palindrome.
   *
   * The worst-case time complexity is still O(n * 2^n), e.g., if the input string consists of
   * n repetitions of a single character.
   *
   * @param input
   * @return
   */
  public static List<List<String>> solution(String input) {
    List<List<String>> result = new ArrayList<>();
    solution(input, 0, new ArrayList<String>(), result);
    return result;
  }

  private static void solution(String input, int offset, List<String> partialResult, List<List<String>> result) {
    if (offset == input.length()) {
      result.add(new ArrayList<>(partialResult));
      return;
    }

    for (int i = offset + 1; i <= input.length(); i++) {
      // 要找到符合规则的分解，那么任意分解的前缀都必须是回文的。
      String preffix = input.substring(offset, i);
      if (isPalindromic(preffix)) {
        partialResult.add(preffix);
        solution(input, i, partialResult, result);
        partialResult.remove(partialResult.size() - 1);
      }
    }
  }

  private static boolean isPalindromic(String preffix) {
    for (int i = 0, j = preffix.length() - 1; i < j; i++, j--) {
      if (preffix.charAt(i) != preffix.charAt(j)) {
        return false;
      }
    }
    return true;
  }

  public static void main(String[] args) {
    String input = "0204451881";
    List<List<String>> result = solution(input);
    for (List<String> item : result) {
      System.out.println(item);
    }

    System.out.println();

    String input2 = "1111111111";
    List<List<String>> result2 = solution(input2);
    for (List<String> item : result2) {
      System.out.println(item);
    }
  }

}
