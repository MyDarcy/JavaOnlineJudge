package eopi.ch13_hash;

import java.util.HashSet;
import java.util.Set;

/**
 * Author by darcy
 * Date on 17-9-28 下午9:20.
 * Description:
 *
 * 奇数, *3 + 1; 偶数, /2. 重复过程, 输入任意的数字最后得到的结果一定是1.
 *
 * The Collatz conjecture is the following: Take any natural number. If it is odd, triple
 * it and add one; if it is even, halve it. Repeat the process indefinitely. No matter what
 * number you begin with, you will eventually arrive at 1.
 * As an example, if we start with 11 we get the sequence 11,34,17,52,26,13,40,
 * 20,10,5,16,8,4,2,1. Despite intense efforts, the Collatz conjecture has not been
 * proved or disproved.
 *
 * Suppose you were given the task of checking the Collatz conjecture for the first
 * billion integers. A direct approach would be to compute the convergence sequence
 * for each number in this set.
 * Test the Collatz conjecture for the first n positive integers.
 *
 * Hint: How would you efficiently check the conjecture for n assuming it holds for all m < n?
 *
 */
public class P13_13_TestTheCollatzConjecture {

  /**
   * TODO
   *
   * remain to be understand.
   *
   *  * Reuse computation by storing all the numbers you have already proved to
   *  converge to 1; that way, as soon as you reach such a number, you can assume it
   *  would reach 1.
   *
   *  * To save time, skip even numbers (since they are immediately halved, and the
   *  resulting number must have already been checked).
   *
   *  * If you have tested every number up to k, you can stop the chain as soon as you
   *  reach a number that is less than or equal to k. You do not need to store the
   *  numbers below k in the hash table.
   *
   *  * If multiplication and division are expensive, use bit shifting and addition.
   *  Partition the search set and use many computers in parallel to explore the
   *  subsets, as show in Solution 20.9 on Page 386.
   *
   *
   *  注意:
   *  fail in two ways—a sequence returns to a previous number in the sequence, which
   *  implies it will loop forever, or a sequence goes to infinity. The latter cannot be
   *  tested with a fixed integer word length, so we simply flag overflows.
   *
   * @param n
   * @return
   *
   */
  public static boolean solution(int n) {
    Set<Long> verified = new HashSet<>();

    for (int i = 3; i <= n; i++) {
      Set<Long> sequence = new HashSet<>();
      long current = i;
      while (current >= i) {
        if (!sequence.add(current)) {
          // 之前已经添加过.形成了环状结构.
          return false;
        }

        if ((current % 2) != 0) {
          if (!verified.add(current)) {
            break;
          }

          long nextCurrent = current * 3 + 1;
          if (nextCurrent <= current) {
            throw new ArithmeticException("Overflow.");
          }

          current = nextCurrent;
        } else {
          current /= 2;
        }
      }
    }
    return true;
  }
}
