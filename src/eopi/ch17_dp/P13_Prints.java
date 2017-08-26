package eopi.ch17_dp;

import java.util.Arrays;
import java.util.List;

/**
 * Author by darcy
 * Date on 17-8-26 上午10:27.
 * Description:
 *
 * 一行字符串的混乱度: 一行如果最后一个字符到末尾有b个空格,那么该行的混乱度是 b^2;
 *
 * Define the messiness of the end-of-line whitespace as follows. The messiness of a
 * single line ending with b blank characters is b^2. The total messiness of a sequence of
 * lines is the sum of the messinesses of all the lines.
 *
 * Given text, i.e., a string of words separated by single blanks, decompose the text into
 * lines such that no word is split across lines and the messiness of the decomposition
 * is minimized. Each line can hold no more than a specified number of characters.
 *
 * Hint: Focus on the last word and the last line.
 *
 *
 */
public class P13_Prints {


  /**
   * 贪心算法: 总是试图在一行中放置尽可能多的单词，但是这样不一定是最优的结果.
   *  array = "a", "b", "c", "d",  length = 5;
   *  "a_b_c" 　"a_b__"
   *  "d____"   "c_d__"
   *   方法1(16)     方法2(4+4=8)
   *
   * @param words
   * @param lineLength
   * @return
   */
  public static int bruteForceSolution(List<String> words, int lineLength) {

    return 0;
  }

  /**
   *
   * i个单词的最优放置中移除第i个单词不一定是i-1个单词的最优放置方式.
   * 但是最优放置i个单词的最后一行由j,,,i这些单词组成,这种放置方式中,　前j-1个单词的放置也是最优的。
   *
   * 前i个单词的混乱程度的最小值M[i] = min{M[j] + f(j, i)}
   * f(j, i)表示从j到i的单词组成的行的混乱度.
   *
   * 几个例子以及最优的放置方式.
   * array = [aaa, bbb, c, d, ee],  length = 11
   * "aaa_bbb____"
   * "c_d_ee_____"
   *
   * array = [aaa, bbb, c, d, ,ee, ff], length =11
   * "aaa_bbb____"
   * "c_d_ee_ff__"
   *
   * array = [aaa, bbb, c, d, ee, ff, ggggggg], length = 11
   * "aaa_bbb____"  "aaa_bbb____"
   * "c_d_ee_____"  "c_d_ee_ff__"
   * "ggggg______"  "ff_ggggggg_" (最后一行的放置方式)
   *
   * Suppose we want to find the optimum placement for the ith word. As we have just
   * seen, we cannot trivially extend the optimum placement for the first i-1words to an
   * optimum placement for the ith word. Put another way, the placement that results by
   * removing the ith word from an optimum placement for the first i words is not always
   * an optimum placement for the first i-1 words. However, what we can say is that if
   * in the optimum placement for the ith word the last line consists of words j, j+1,...,i,
   * then in this placement, the first j — 1 words must be placed optimally
   *
   * since the first i words are assumed to be optimally placed, the placement of
   * words on the lines prior to the last one must be optimum. Therefore, we can write
   * a recursive formula for the minimum messiness, M(i), when placing the first i words.
   * Specifically, M(i),equals min[f(j, i) + M(j-1) j<=i], where f(j,i) is the messiness
   * of a single line consisting of words j to i inclusive.
   *
   *
   * Let L be the line length. Then there can certainly be no more than L words on a line,
   * so the amount of time spent processing each word is 0(L). Therefore, if there are n
   * words, the time complexity is O(nL). The space complexity is O(n) for the cache.
   * @param words
   * @param lineLength
   * @return
   */
  public static int dpSolution(List<String> words, int lineLength) {
    int[] minMessiness = new int[words.size()];
    Arrays.fill(minMessiness, Integer.MAX_VALUE);
    int numberRemaingBlanks = lineLength - words.get(0).length();
    minMessiness[0] = numberRemaingBlanks * numberRemaingBlanks;
    for (int i = 1; i < words.size(); i++) {
      numberRemaingBlanks = lineLength - words.get(i).length();
      minMessiness[i] = minMessiness[i - 1] + numberRemaingBlanks * numberRemaingBlanks;

      for (int j = i - 1; j >= 0; j--) {
        // 本行又加了新的单词.
        numberRemaingBlanks -= (words.get(j).length() + 1);
        // 放不下更多新的单词了.
        if (numberRemaingBlanks < 0) {
          break;
        }

        int firstJWordsMessiness = j - 1 < 0 ? 0 : minMessiness[j - 1];
        int currentLineMessiness = numberRemaingBlanks * numberRemaingBlanks;
        minMessiness[i] = Math.min(minMessiness[i], firstJWordsMessiness + currentLineMessiness);


      }
    }

    return minMessiness[words.size() - 1];
  }

  public static void main(String[] args) {
    List<String> words = Arrays.asList("aaa bbb c d ee ff ggggggg".split("\\s+"));
    System.out.println(words);
    System.out.println(dpSolution(words, 11));

    words = Arrays.asList("aaa bbb c d ee".split("\\s+"));
    System.out.println(words);
    System.out.println(dpSolution(words, 11));
  }

}

/*

Variant: Solve the same problem when the messiness is the sum of the messinesses
of all but the last line.

Variant: Suppose the messiness of a line ending with b blank characters is defined
to be b. Can you solve the messiness minimization problem in O(n) time and O(1)
space?

 */
