package eopi.ch17_dp;

import java.util.HashMap;
import java.util.Map;

/**
 * Author by darcy
 * Date on 17-8-24 下午2:47.
 * Description:
 * Mathematically, the nth Fibonacci number F(n) is given by the equation F(n) =
 * F(n - 1) + F(n - 2), with F(0) = 0 and F(l) = 1. A function to compute F(n) that
 * recursively invokes itself has a <B>time complexity</B> that is exponential in n.
 * 递归的计算第n个fibonacci　number的时间复杂度是O(2^n), 因为一些Fibonacci　number会被重复计算.
 *
 * 0,1,1,2,3,5,8,13, 21
 */
public class P1_Fibnacci {

  private static Map<Integer, Integer> cache = new HashMap<>();


  /**
   * 递归的获取结果
   * 事件复杂度O(2^n)
   * @param n
   * @return
   */
  public static int recursiveSolution(int n) {
    if (n <= 1) {
      return n;
    }

    return recursiveSolution(n - 1) + recursiveSolution(n - 2);
  }

  /**
   * 缓存中间结果时间复杂度 O(n)
   * @param n
   * @return
   */
  public static int solution(int n) {
    if (n <= 1) {
      return n;
    } else if (!cache.containsKey(n)) {
      cache.put(n, solution(n - 1) + solution(n - 2));
    }

    return cache.get(n);
  }

  /**
   * O(n)时间复杂度, O(1)的空间复杂度;
   * bottom-up迭代的方式填充缓存。
   *
   * 官方吐槽:
   * this program iteratively fills in the cache in a bottom-up fashion, which
   * allows it to reuse cache storage to reduce the space complexity of the cache
   * @param n
   * @return
   */
  public static int solustionMinSpaceCost(int n) {
    if (n <= 1) {
      return n;
    }
    
    int first = 0;
    int second = 1;
    int temp = 0;
    for (int i = 1; i < n; i++) {
      temp = first + second;
      first = second;
      second = temp;
    }
    return second;
  }

  public static void main(String[] args) {

    long start = System.currentTimeMillis();

    int number = 40;
    for (int i = 0; i < number; i++) {
      solution(i);
//      System.out.println(solution(i));
    }
    System.out.println("time cost:" + (System.currentTimeMillis() - start));

    start = System.currentTimeMillis();
    for (int i = 0; i < number; i++) {
      recursiveSolution(i);
//      System.out.println(recursiveSolution(i));
    }
    System.out.println("time cost:" + (System.currentTimeMillis() - start));

    start = System.currentTimeMillis();
    for (int i = 0; i < number; i++) {
      solustionMinSpaceCost(i);
      System.out.println(solustionMinSpaceCost(i));
    }
    System.out.println("time cost:" + (System.currentTimeMillis() - start));

  }

}
