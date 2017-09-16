package eopi.ch6_arrays;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * Author by darcy
 * Date on 17-8-29 下午10:38.
 * Description:
 * <p>
 * 求1...n之间的所有质数.
 * <p>
 * Write a program that takes an integer argument and returns all the primes between
 * 1 and that integer. For example, if the input is 18, you should return (2,3,5,7,11,13,17).
 * <p>
 * Hint: Exclude the multiples of primes
 */
public class P9_PrimesToN {

  /**
   * 求1...n之间的所有质数.
   * 暴力方法.
   * 迭代所有元素，检测每个元素。
   * <p>
   * 时间复杂度O(n^(3/2)),可以通过sqrt(n)来使得时间复杂度降为O(n^(3/2) / (log(n) ^ 2) )
   *
   * @param n
   * @return
   */
  public static List<Integer> bruteForceSolution(int n) {

    return null;
  }

  /**
   * 当一个数字是质数的时候,就移除所有后续的倍数index,因为这些index肯定不是质数.
   * <p>
   * 时间复杂度nloglogn -- (n/2 + n/3 + n/5 + n/7 + ...)
   * 空间复杂度O(n)
   *
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

  /**
   * Given n, return all primes up to and including n.
   * <p>
   * Book solution.
   * <p>
   * 复杂度分析:
   * 时间复杂度: O(n /2 + n / 3 + n / 5 + n / 5 + ....) == nloglogn
   * 空间复杂度: O(n)
   *
   * @param n
   * @return
   */
  public static List<Integer> generatePrimes(int n) {
    List<Integer> primes = new ArrayList<>();
    // isPrime.get(p) represents if p is prime or not. Initially , set each
    // to true, excepting ® and 1. Then use sieving to eliminate nonprimes.
    List<Boolean> isPrime = new ArrayList<>(Collections.nCopies(n + 1, true));
    isPrime.set(0, false);
    isPrime.set(1, false);
    for (int p = 2; p <= n; ++p) {
      if (isPrime.get(p)) {
        primes.add(p);
        // Sieve p’s multiples.
        for (int j = p; j <= n; j += p) {
          isPrime.set(j, false);
        }
      }
    }
    return primes;

  }

  /**
   * Given n, return all primes up to and including n.
   * @param n
   * @return
   */
  public static List<Integer> generatePrimes3(int n) {
    final int size = (int) Math.floor(0.5 * (n - 3)) + 1;
    List<Integer> primes = new ArrayList<>();
    primes.add(2);
    // isPrime.get(i) represents whether (2i + 3) is prime or not.
    // Initially, set each to true. Then use sieving to eliminate nonprimes.
    List<Boolean> isPrime = new ArrayList<>(Collections.nCopies(size, true));
    for (int i = 0; i < size; ++i) {
      if (isPrime.get(i)) {
        int p = ((i * 2) + 3);
        primes.add(p);
        // Sieving from p^2, whose value is (4i^2 + 12i + 9). The index of this
        // value in isPrime is (2i^2 + 6i + 3) because isPrime.get(i) represents
        // 2i + 3.
        //
        // Note that we need to use long type for j because pA2 might overflow.
        for (long j = ((i * i) * 2) + 6 * i + 3; j < size; j += p) {
          isPrime.set((int) j, false);
        }
      }
    }
    return primes;
  }

  public static void main(String[] args) {
    List<Integer> result = solution(10);
    System.out.println(result);
  }

}
