package eopi.ch17_dp;

import java.util.*;

/**
 * Author by darcy
 * Date on 17-8-25 下午4:09.
 * Description:
 *
 * 给定的字符串是否可能是字典单词的连接(可以重复使用同一个单词). 如果这样的连接存在.
 * 那么返回这样的构成连接的单词.
 *
 *
 *
 *
 * Given a dictionary i.e., a set of strings, and a name, design an efficient algorithm that
 * checks whether the name is the concatenation of a sequence of dictionary words. If
 * such a concatenation exists, return it. A dictionary word may appear more than once
 * in the sequence, e.g., "a", "man", "a", "plan", "a", "canal" is a valid sequence for
 * "amanaplanacanal".
 *
 * Hint:Solve the generalized problem, i.e., determine for each prefix of the name whether
 * it is the concatenation of dictionary words.(前缀是否是单词的连接)
 */
public class P9_Words {

  /**
   * 1.暴力破解的方法:
   * 找到dict中是name的prefix的单词,然后继续继续在name的后缀中搜索,其实就是一个子问题.
   *
   * 吐槽:
   * Implemented naively, this approach has very high time complexity on some input
   * cases, e.g., if the name is N repetitions of "AB" followed by "C" and the dictionary
   * words are "A", "B", and "AB" the time complexity is exponential in N. For example,
   * for the string "ABABC", then we recurse on the substring "ABC" twice (once from
   * the sequence "A", "B" and once from "AB").
   * @param name
   * @param dict
   * @return
   */
  public static List<String> bruteForceSolution(String name, Set<String> dict) {

    return new ArrayList<>();
  }

  /**
   * 吐槽:
   * The solution is straightforward—cache intermediate results. The cache keys are
   * prefixes of the string. The corresponding value is a Boolean denoting whether the
   * prefix can be decomposed into a sequence of valid words
   * (key, key可否被分解)
   *
   * It's easy to determine if a string is a valid word—we simply store the dictionary
   * in a hash table. A prefix of the given string can be decomposed into a sequence of
   * dictionary words exactly if it is a dictionary word, or there exists a shorter prefix
   * which can be decomposed into a sequence of dictionary words and the difference of
   * the shorter prefix and the current prefix is a dictionary word.
   *
   * 很容易确定一个字符串是否是一个有效的单词，我们只需将字典存储在一个哈希表中。
   * 给定字符串的前缀可以被精确地分解为字典单词序列，如果它是字典中的单词，
   * 或者存在可以分解成字典字单词序列的较短前缀，以及较短前缀和当前前缀的差值也是一个字典单词。
   *
   * For example, for "amanaplanacanal":
   * 1. the prefix "a" has a valid decomposition (since "a" is a dictionary word),
   * 2. the prefix "am" has a valid decomposition (since "am" is a dictionary word),
   * 3. the prefix"ama"hasa valid decomposition (since"a"hasa valid decomposition,
   *    and "am" is a dictionary word), and
   * 4. "aman" has a valid decomposition (since "am" has a valid decomposition and
   *   "an" is a dictionary word).Skipping ahead,
   * 5. "amanapl" does not have a valid decomposition (since none of "1", "pi", apl",
   *   etc. are dictionary words), and
   * 6. "amanapla" does not have a valid decomposition (since the only dictionary
   *   word ending the string is "a" and "amanapl" does not have a valid decomposition).
   *
   * ×××××××××
   * if a prefix has a valid decomposition, we record the length of the last dictionary
   * word in the decomposition.
   * ×××××××××
   *
   * 开销:
   * Let n be the length of the input string s. For each k < n we check for each j < k
   * whether the substring s[j +1: k] is a dictionary word, and each such check requires
   * O(k - j) time. This implies the time complexity is 0(n^3).
   * 注意判定一个string是否在hashset实际上equals的实现是逐个字符判断.
   *
   * 提升版本:
   * Let W be the length of the longest dictionary word. We can restrict
   * j to range from k - W to k -1 without losing any decompositions, so the time
   * complexity improves to 0(nnW).本质上复杂度并没有降低.
   *
   * @param name
   * @param dict
   * @return
   */
  public static List<String> dpSolution(String name, Set<String> dict) {

    /*
    算法结束时, lastLength[i] != -1 表明name.substring(0, i + 1)是一个有效的分解.
    并且分解中的最后一个字符串的长度是lastLength[i]
     */
    int[] lastLength = new int[name.length()];
    Arrays.fill(lastLength, -1);

    for (int i = 0; i < name.length(); i++) {

      // 如果name.substring(0,i + 1)是一个有效的单词,那么把lastLength[i] = 有效单词的长度.
      if (dict.contains(name.substring(0, i + 1))) {
        lastLength[i] = i + 1;
      }

      // 如果lastLength[i] == -1, 那么找合适的j(j < i)使得domain.substring(0, j + 1)是一个有效的分解.
      // 并且domain.substring(j + 1, i + 1)是一个字典单词.同时在lastLength[i]中记录此字典单词的长度
      if (lastLength[i] == -1) {
        for (int j = 0; j < i; j++) {
          if (lastLength[j] != -1 && dict.contains(name.substring(j + 1, i + 1))) {
            lastLength[i] = i - j;
            break;
          }
        }
      }
    }

    List<String> decomposition = new ArrayList<>();
    // 1. 能分解成字典单词的连接
    // 2. 其记录的长度是分解后的最后一个字典单词的长度
    if (lastLength[name.length() - 1] != -1) {
      int index = name.length() - 1;
      while (index >= 0) {
        decomposition.add(name.substring(index + 1 - lastLength[index], index + 1));
        // 找记录上一个单词的长度的位置.
        index -= lastLength[index];
      }
      Collections.reverse(decomposition);

    }
    return decomposition;
  }

  public static void main(String[] args) {
    String name = "bedbathandbeyond";
    Set<String> dict = new HashSet<>();
    dict.add("bed");
    dict.add("bath");
    dict.add("hand");
    dict.add("bat");
    dict.add("beyond");
    dict.add("and");
    System.out.println(dpSolution(name, dict));

  }



}

/*
回文分解.
Variant: Palindromic decompositions were described in Problem 16.7 on Page 293.
Observe every string s hasat least one palindromic decomposition, which is the trivial
one consisting of the individual characters. For example, if s is "0204451881" then
"0", "2",“0”, "4", "4", "5", "1", "8", "8", "1" is such a trivial decomposition. The
minimum decomposition of s is "020", "44", "5", "1881". How would you compute a
palindromic decomposition of a string s that uses a minimum number of substrings?
 */