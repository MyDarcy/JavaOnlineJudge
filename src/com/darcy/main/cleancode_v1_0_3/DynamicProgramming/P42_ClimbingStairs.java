package com.darcy.main.cleancode_v1_0_3.DynamicProgramming;

import java.util.HashMap;
import java.util.Map;

/**
 * Author by darcy
 * Date on 17-9-4 上午8:35.
 * Description:
 *
 * You are climbing a staircase. It takes n steps to reach to the top.
 * Each time you can either climb 1 or 2 steps. In how many distinct ways can you climb to
 * the top?
 *
 */
public class P42_ClimbingStairs {

  /**
   * 缓存中间结果.
   * @param n
   * @return
   */
  public static int solution(int n) {
    Map<Integer, Integer> map = new HashMap<>();
    solution(n, map);
    /*System.out.println(map.get(n));*/
    return map.get(n);
  }

  private static void solution(int n, Map<Integer, Integer> map) {
    if (n <= 1) {
      map.put(n, 1);
    } else if (!map.containsKey(n)) {
      map.put(n, solution(n - 1) + solution(n - 2));
    }
  }

  /**
   * int[]来缓存结果.
   * @param n
   * @return
   */
  public static int solution2(int n) {
    int[] cache = new int[n + 1];
    return solution2(n, 2, cache);
  }

  private static int solution2(int n, int k, int[] cache) {
    if (n <= 1) {
      return 1;
    }

    if (cache[n] == 0) {
      for (int i = 1; i <= k && (n - i) >= 0; i++) {
        cache[n] += solution2(n - i, k, cache);
      }
    }

    return cache[n];
  }

  /**
   * 迭代计算结果.
   * @param n
   * @return
   */
  public static int solution3(int n) {
    if (n <= 1) {
      return 1;
    }

    int pre = 1;
    int curr = 1;
    for (int i = 2; i <= n; i++) {
      int temp = pre;
      pre = curr;
      curr = temp + curr;
    }
    return curr;
  }

  public static void main(String[] args) {
    for (int i = 0; i < 20; i++) {
      System.out.println(solution3(i));
    }
  }

}
