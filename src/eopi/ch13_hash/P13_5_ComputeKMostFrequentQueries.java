package eopi.ch13_hash;

import java.util.List;

/**
 * Author by darcy
 * Date on 17-9-26 下午3:28.
 * Description:
 *
 * 计算k个最频繁出现的字符串.
 *
 * You are given an array of strings. Compute the k strings that appear most frequently
 * in the array.
 *
 * Hint: Consider extreme values for k, as well as scenarios where there are a relatively small
 * number of distinct strings.
 *
 * 思路: 统计各个字符串出现的次数.
 * StringWithFrequency
 *
 */
public class P13_5_ComputeKMostFrequentQueries {

  public class StringWithFrequency{
    String str;
    Integer frequency;

    public StringWithFrequency(String str, Integer frequency) {
      this.str = str;
      this.frequency = frequency;
    }
  }

  /**
   * Map<String, StringWithFrequency>记录字符串出现的次数.
   * minHeap统计topK
   *
   * @param list
   * @return
   */
  public static List<String> solution(List<String> list) {

    return null;
  }

}
