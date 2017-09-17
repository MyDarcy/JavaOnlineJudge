package eopi.ch6_arrays;

import java.util.*;

/**
 * Author by darcy
 * Date on 17-9-17 下午7:54.
 * Description:
 * <p>
 * 随机均匀的生成size=k的子集合
 * 返回C(n,k)的大小为k的任意子集合.
 * <p>
 * The set {0,1, 2,...,n- 1) has C(n,k) = n!/((n- k)!k!) subsets of size k. We seek to design
 * an algorithm that returns any one of these subsets with equal probability.
 * <p>
 * Write a program that takes as input a positive integer n and a size k < n,and returns
 * a size-k subset of {0,1,2,...,n-1). The subset should be represented as an array. All
 * subsets should be equally likely and, in addition, all permutations of elements of the
 * array should be equally likely. You may assume you have a function which takes as
 * input a nonnegative integer t and returns an integer in the set {0, 1, . . . , t - 1) with
 * uniform probability
 */
public class P14_ComputeARandomSubset {

  /**
   * 如同P13,迭代选择k个不同的数字. O(k)的空间复杂度.
   *
   * @return
   */
  public static List<Integer> bruteForceSolution() {

    return null;
  }


  /**
   *
   * 不是太懂这个运行机制.
   *
   * 维持一个HashMap记录随机化过程中pair之间的互换情况.
   *
   * 进行k次迭代.在[i, n -1 -i]中选择一个随机数r, i是当前迭代的次数, 从0开始计数.
   *
   *
   * Returns a random k-sized subset of {0, 1,..., n - 1}.
   * <p>
   * For example, suppose n = 100 and k = 4. In the first iteration, suppose we get the
   * random number 28. We update H to (0, 28), (28,0). This means that A[0] is 28 and
   * A[28] is 0—for all other i,A[i] = i. In the second iteration, suppose we get the random
   * number 42. We update H to (0, 28), (28,0), (1, 42), (42,1). In the third iteration,suppose
   * we get the random number 28again. We update H to (0, 28), (28, 2), (1, 42), (42,1), (2,0).
   * In the third iteration, suppose we get the random number 64. We update H to
   * (0, 28), (28, 2), (1, 42), (42,1), (2, 0), (3,64), (64,3). The random subset is the 4 elements
   * corresponding to indices 0,1, 2,3, i.e., (28, 42,0,64).
   *
   * @param n
   * @param k
   * @return
   */
  public static List<Integer> randomSubset(int n, int k) {
    Map<Integer, Integer> changedElements = new HashMap<>();
    Random randomIndexGenerator = new Random();
    for (int i = 0; i < k; ++i) {
      System.out.println(changedElements);
      // randomIndex的范围是[i, n - i]
      // randomIndex和i之间的一个互换。
      int randomIndex = i + randomIndexGenerator.nextInt(n - i);
      Integer ptr1 = changedElements.get(randomIndex);
      Integer ptr2 = changedElements.get(i);

      // randomIndex和i位置都没有被换过.那么它们两个互换.
      if (ptr1 == null && ptr2 == null) {
        changedElements.put(randomIndex, i);
        changedElements.put(i, randomIndex);

        // i互换过, randomIndex没有互换过.
        // i -> ptr2 ==> i->randomIndex, randomIndex = ptr2;
      } else if (ptr1 == null && ptr2 != null) {
        changedElements.put(randomIndex, ptr2);
        changedElements.put(i, randomIndex);

        // randomIndex互换过. 而i没有换过.
        // randomIndex->ptr1 ==> i->ptr1, randomIndex->i
      } else if (ptr1 != null && ptr2 == null) {
        changedElements.put(i, ptr1);
        changedElements.put(randomIndex, i);

        // 都换过.交叉换.
      } else {
        changedElements.put(i, ptr1);
        changedElements.put(randomIndex, ptr2);
      }
    }

    /**
     {}
     {0=63, 63=0}
     {0=63, 1=6, 6=1, 63=0}
     {0=63, 1=6, 2=15, 6=1, 63=0, 15=2}
     {0=63, 1=6, 2=15, 3=41, 6=1, 41=3, 63=0, 15=2}
     [63, 6, 15, 41]
     */
    System.out.println(changedElements);
    List<Integer> result = new ArrayList<>();

    for (int i = 0; i < k; ++i) {
      result.add(changedElements.get(i));
    }
    return result;
  }

  public static void main(String[] args) {
    List<Integer> result = randomSubset(100, 4);
    System.out.println(result);
  }

}
