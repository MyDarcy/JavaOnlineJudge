package eopi.ch13_hash;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Author by darcy
 * Date on 17-9-26 下午5:24.
 * Description:
 *
 * 最长的没有重复出现的子序列.
 *
 * Write a program that takes two arrays of strings, and return the indices of the start¬
 * ing and ending index of a shortest subarray of the first array (the "paragraph"
 * array) that "sequentially covers", i.e., contains all the strings in the second array
 * (the "keywords" array), in the order in which they appear in the keywords array.
 *
 */
public class P13_9_FindLongestSubArrayWithDistinctEntries {


  /**
   * 利用之前放入map的char->index的信息.
   * 一旦出现了一个重复的字符. 那么就要更新可能的最大连续无重复数字(char).
   *
   * O(N)的时间复杂度.
   *
   * @param list
   * @return
   */
  public static int solution(List<Character> list) {
    Map<Character, Integer> mostRecentOccurrence = new HashMap<>();
    int longestSubArrayStartIndex = 0;
    int result = 0;
    for (int i = 0; i < list.size(); i++) {
      // the previous value associated with key,即上一次出现该Integer的index.
      Integer lastDuplicateIndex = mostRecentOccurrence.put(list.get(i), i);
      // 到这里已经出现了重复的数字.
      if (lastDuplicateIndex != null) {
        if (lastDuplicateIndex >= longestSubArrayStartIndex) {
          result = Math.max(result, i - longestSubArrayStartIndex);
          // 更新统计的起始位置.
          longestSubArrayStartIndex = lastDuplicateIndex + 1;
        }
      }
    }

    result = Math.max(result, list.size() - longestSubArrayStartIndex);
    return result;
  }

  public static void main(String[] args) {
    List<Character> list = new ArrayList<Character>(){
      {
        add('f');
        add('s');
        add('f');
        add('e');
        add('t');
        add('w');
        add('e');
        add('n');
        add('w');
        add('e');
      }
    };

    int result = solution(list);
    System.out.println(result);
  }

}
