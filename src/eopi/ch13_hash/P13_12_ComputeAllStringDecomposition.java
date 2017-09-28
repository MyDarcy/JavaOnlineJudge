package eopi.ch13_hash;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Author by darcy
 * Date on 17-9-28 下午8:52.
 * Description:
 *
 * 给定一个sentance, 和个单词集合, 在sentence中找包含集合中所有单词的任意可能顺序的连接(concatenation).
 * 集合中的每个单词只能出现且仅出现一次, 而且集合中的个单词都是相同的长度的.
 *
 * Write a program which takes as input a string (the "sentence") and an array of strings
 * (the "words"), and returns the starting indices of substrings of the sentence string
 * which are the concatenation of all the strings in the words array. Each string must
 * appear exactly once, and their ordering is immaterial. Assume all strings in the words
 * array have equal length. It is possible for the words array to contain duplicates.
 *
 * Hint: Exploit the fact that the words have the same length
 *
 */
public class P13_12_ComputeAllStringDecomposition {

  /**
   *
   * 总的时间复杂度是O(Nmn),
   * @param sentance
   * @param words
   * @return
   */
  public static List<Integer> solution(String sentance, List<String> words) {

    Map<String, Integer> wordFrequency = new HashMap<>();
    for (int i = 0; i < words.size(); i++) {
      Integer count = wordFrequency.get(words.get(i));
      if (count == null) {
        count = 0;
      }
      count++;
      wordFrequency.put(words.get(i), count);
    }

    int wordLength = words.get(0).length();
    List<Integer> list = new ArrayList<>();
    for (int i = 0; i + words.size() * wordLength < sentance.length(); i++) {
      if (match(sentance, i, wordLength, words.size(), wordFrequency)) {
        list.add(i);
      }
    }

    return list;
  }

  /**
   * 总共有size个单词.
   * @param sentance
   * @param start
   * @param wordLength
   * @param size
   * @param wordFrequency
   * @return
   */
  private static boolean match(String sentance, int start, int wordLength, int size, Map<String, Integer> wordFrequency) {

    Map<String, Integer> currentFrequencyMap = new HashMap<>();
    // size个单词.
    for (int i = 0; i < size; i++) {
      String subString = sentance.substring(start + wordLength * i, start + wordLength * (i + 1));

      // 没有出现过这个单词, 本次查找结束.
      Integer count = wordFrequency.get(subString);
      if (count == null) {
        return false;
      }

      Integer currentCount = currentFrequencyMap.get(subString);
      if (currentCount == null) {
        currentCount = 0;
      }
      currentCount++;
      currentFrequencyMap.put(subString, currentCount);

      // 更新后的统计信息的特定单词出现的次数能超过count次.
      if (currentFrequencyMap.get(subString) > count) {
        return false;
      }
    }

    return true;
  }

  public static void main(String[] args) {
    String sentance = "amanaplanacanal";
    List<String> list = Stream.of("can", "apl", "ana").collect(Collectors.toList());

    List<Integer> result = solution(sentance, list);
    System.out.println(result);
  }

}
