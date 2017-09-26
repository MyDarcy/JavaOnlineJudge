package eopi.ch13_hash;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Author by darcy
 * Date on 17-9-26 下午3:42.
 * Description:
 *
 * 输入一个字符串数字, 计算任意两个相同字符串的最近距离.
 *
 * Write a program which takes as input an array and finds the distance between a
 * closest pair of equal entries. For example, if s = ("All", "work", "and", "no", "play",
 * "makes", "for", "no", "work", "no", "fun", "and", "no", "results"), then the second
 * and third occurrences of "no" is the closest pair.
 *
 * Hint: Each entry in the array is a candidate.
 */
public class P13_6_FindTheRepeatedEntriesInArray {

  /**
   *
   * @param list
   * @return
   */
  public static int solution(List<String> list) {
    Map<String, Integer> strIndex = new HashMap<>();

    int min = Integer.MAX_VALUE;
    for (int i = 0; i < list.size(); i++) {
      if (strIndex.containsKey(list.get(i))) {
        int distance = i - strIndex.get(list.get(i));
        if (distance < min) {
          min = distance;
        }
      }
      strIndex.put(list.get(i), i);
    }

    return min;
  }

  public static void main(String[] args) {
    List<String> list = Arrays.asList("ALL", "work", "and",
        "so", "play", "makes", "for", "no", "work", "no", "fun", "and", "no", "result");

    int result = solution(list);
    System.out.println(result);
  }
}
