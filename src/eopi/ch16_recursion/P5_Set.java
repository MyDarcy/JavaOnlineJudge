package eopi.ch16_recursion;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * Author by darcy
 * Date on 17-8-27 下午2:44.
 * Description:
 *
 * 生成集合S的所有子集合;
 *
 * The power set of a set S is the set of all subsets of S, including both the empty set
 * and S itself.
 *
 * Write a function that takes as input a set and returns its power set.
 * Hint: There are 2^n subsets for a given set S of size n. There are 2^k k-bit words.
 *
 */

public class P5_Set {

  /**
   * S的所有子集合包括两类: 所有包含特定元素ele的子集合和所有不包含特定元素ele的子集合.
   *
   * compute all subsets U that do not include a particular element (which could be any single element).
   * Then we compute all subsets V which do include that element. Each subset set must appear in II or
   * in V,so the final result is just U u V.
   *
   *
   * 复杂度:
   * The number of recursive calls, C(n) satisfies the recurrence C(n) = 2C(n- 1), which
   * solves to C(n) = O(2^n). Since we spend O(n) time within a call, the time complexity
   * is O(n2^n). The space complexity is O(n2^n),since there are 2^n subsets, and the average
   * subset size is n/2. If we just want to print the subsets, rather than returning all of
   * them, we simply perform a print instead of adding the subset to the result, which
   * reduces the space complexity to 0(n) -- the time complexity remains the same
   *
   * @param input
   * @return
   */
  public static List<List<Integer>> bruteForceSolution(List<Integer> input) {
    List<List<Integer>> result = new ArrayList<>();
    bruteForceSolution(input, 0, new ArrayList<Integer>(), result);
    return result;
  }

  private static void bruteForceSolution(List<Integer> input, int tobeSelected, ArrayList<Integer> beenSelected, List<List<Integer>> result) {
    if (tobeSelected == input.size()) {
      result.add(new ArrayList<>(beenSelected));
//      System.out.println(beenSelected);
      return;
    } else {
      // 总共会有input.size()个数字,每次都二分,所以最终的子集合的个数也是2^n个.

      // 生成包含tobeSelected元素的所有的子集
      beenSelected.add(input.get(tobeSelected));
      bruteForceSolution(input, tobeSelected + 1, beenSelected, result);
      // 生成不包含tobeSelected元素的所有的子集
      beenSelected.remove(beenSelected.size() - 1);
      bruteForceSolution(input, tobeSelected + 1, beenSelected, result);
    }
  }

  /**
   *
   * 首先换底公式 log2(x) = ln(x) / ln(2)
   * Math.log(x) 表示的是 Returns the natural logarithm of a;
   *
   * [0:2^n - 1]共有2^n个数字, 代表的是n bit 的取值0或者1的情况.
   * 刚好可以和n个数的组成的集合的所有子集的元素选择情况一一对应。
   *
   *
   *
   * Since each set takes O(n) time to compute, the time complexity is0(n2^n). In practice,
   * this approach is very fast. Furthermore, its space complexity is O(n) when we want
   * to just enumerate subsets, e.g., to print them, rather that to return all the subsets.
   *
   * @param target
   * @return
   */
  public static List<List<Integer>> solution(List<Integer> target) {
    int n = target.size();
    double log2 = Math.log(2); // ln2
    List<List<Integer>> result = new ArrayList<>();
    for (int i = 0; i < (1 << n); i++) {
      int bitArray = i;
      List<Integer> list = new ArrayList<>();

      while (bitArray != 0) {
        // 换底公式求出特定数字最低位为1是第多少位, 相当于是对集合S中的元素的选择情况的判定.
        list.add(target.get((int) (Math.log(bitArray & ~(bitArray - 1)) / log2)));
        bitArray &= (bitArray - 1);
      }

      result.add(list);
    }

    return result;
  }

  public static void main(String[] args) {
    long start = System.currentTimeMillis();
    List<Integer> input = IntStream.rangeClosed(1, 20).mapToObj(Integer::valueOf).collect(Collectors.toList());
    List<List<Integer>> result = bruteForceSolution(input);
    /*for (List<Integer> item : result) {
      System.out.println(item);
    }*/
    System.out.println(System.currentTimeMillis() - start + "ms");

    start = System.currentTimeMillis();
    List<Integer> input2 = IntStream.rangeClosed(1, 20).mapToObj(Integer::valueOf).collect(Collectors.toList());
    List<List<Integer>> solution = solution(input2);
    /*for (int i = 0; i < solution.size(); i++) {
      System.out.println(solution.get(i));
    }*/
    System.out.println(System.currentTimeMillis() - start + "ms");

  }

}

/*
Variant: Solve this problem when the input array may have duplicates, i.e., denotes
a multiset. You should not repeat any multiset. For example, if A =
(1,2,3, 2>, then you should return ((),(1), (2),(3),(1,2), <1,3>,(2,2>,<2,3>,<1,2,2>,
<1, 2,3), <2, 2,3), <1,2, 2,3>).
 */