package eopi.ch6_arrays;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Author by darcy
 * Date on 17-8-29 下午10:38.
 * Description:
 *
 * 求1...n之间的所有质数.
 *
 * Write a program that takes an integer argument and returns all the primes between
 * 1 and that integer. For example, if the input is 18, you should return (2,3,5,7,11,13,17).
 *
 * Hint: Exclude the multiples of primes
 */
public class P9_PrimesToN {

  /**
   * 求1...n之间的所有质数.
   * 暴力方法.
   * 迭代所有元素，检测每个元素。
   *
   * 时间复杂度O(n^(3/2))
   *
   * @param n
   * @return
   */
  public static List<Integer> bruteForceSolution(int n) {

    return null;
  }

  /**
   *
   * 当一个数字是质数的时候,就移除所有后续的倍数index,因为这些index肯定不是质数.
   *
   * 时间复杂度nloglogn -- (n/2 + n/3 + n/5 + n/7 + ...)
   * 空间复杂度O(n)
   * @param n
   * @return
   */
  public static List<Integer> solution(int n) {
    boolean[] isPrime = new boolean[n + 1];
    Arrays.fill(isPrime, true);
    List<Integer> result = new ArrayList<>();
    isPrime[0] = false;
    isPrime[1] = false;
    for (int i = 2; i <= n; i++) {
      if (isPrime[i]) {
        result.add(i);
        for (int j = i; j <= n; j += i) {
          isPrime[j] = false;
        }
      }
    }

    return result;
  }

  public static void main(String[] args) {
    List<Integer> result = solution(10);
    System.out.println(result);
  }

}
