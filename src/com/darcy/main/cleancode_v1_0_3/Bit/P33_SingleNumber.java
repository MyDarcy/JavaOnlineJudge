package com.darcy.main.cleancode_v1_0_3.Bit;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * Author by darcy
 * Date on 17-9-3 下午7:05.
 * Description:
 *
 * 给定数组中只出现过一次的数字.
 *
 * Given an array of integers, every element appears twice except for one. Find that single
 * one.
 *
 */
public class P33_SingleNumber {

  /**
   * 时间复杂度和空间复杂度都是O(n);
   * @param array
   * @return
   */
  public static int mapSolution(int[] array) {
    Map<Integer, Integer> map = new HashMap<>();
    for (int i = 0; i < array.length; i++) {
      map.put(array[i], map.getOrDefault(array[i], 0) + 1);
    }

    for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
      if (entry.getValue() == 1) {
        return entry.getKey();
      }
    }

    return -1;
  }

  /**
   * The set is pretty efficient and runs in one pass. However, it uses extra space of O(n).
   * @param A
   * @return
   */
  public int singleNumber(int[] A) {
    Set<Integer> set = new HashSet<>();
    for (int x : A) {
      if (set.contains(x)) {
        set.remove(x);
      } else {
        set.add(x);
      }
    }
    return set.iterator().next();
  }

  /**
   * 亦或操作
   * @param array
   * @return
   */
  public static int solution(int[] array) {
    int result = 0;
    for (int i = 0; i < array.length; i++) {
      result ^= array[i];
    }
    return result;
  }

  public static void main(String[] args) {
    int[] array = {1, 2, 3, 4, 5, 6, 1, 2, 3, 4, 5};
    int result = mapSolution(array);
    System.out.println(result);

    int result2 = solution(array);
    System.out.println(result2);
  }

}
