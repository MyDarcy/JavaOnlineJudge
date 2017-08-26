package eopi.ch17_dp;

import java.util.*;

/**
 * Author by darcy
 * Date on 17-8-26 下午2:23.
 * Description:
 *
 * 找数组的最长的非递减子序列.(子序列不一定是要连续的)
 *
 * Write a program that takes as input an array of numbers and returns the length of a
 * longest nondecreasing subsequence in the array.
 *
 * Hint: Express the longest nondecreasing subsequence ending at an entry in terms of the longest
 * nondecreasing subsequence appearing in the subarray consisting of preceding elements.
 *
 */
public class P14_NonDecreasing {

  /**
   * 暴力破解的方法.
   * 迭代所有的子序列, 但是总的子序列的个数是2^n次方.所以时间复杂度非常高.
   * 当然也可以使用一些启发式prune的方法。但是代码也不够简洁
   *
   *
   * @param A
   * @return
   */
  public static int bruteForceSolution(List<Integer> A) {

    return 0;
  }

  /**
   * dp方法
   *
   *
   *
   * @param A
   * @return
   */
  public static int dpSolution(List<Integer> A) {
    int[] maxLength = new int[A.size()];
    Arrays.fill(maxLength, 1);
    for (int i = 1; i < A.size(); i++) {
      for (int j = 0; j < i; j++) {
        if (A.get(i) > A.get(j)) {
          maxLength[i] = Math.max(maxLength[i], maxLength[j] + 1);
        }
      }
    }

    return Arrays.stream(maxLength).max().getAsInt();
  }

  public static void main(String[] args) {
    /*String[] split = "0 8 4 12 2 10 6 14 1 9".split("\\s+");
    Integer[] integers = (Integer[]) Arrays.stream(split).map((s) -> new Integer(Integer.parseInt(s))).toArray();
    List<Integer> list = new ArrayList<>(Arrays.asList(integers));*/

    List<Integer> list = new ArrayList<>();
    list.add(0);
    list.add(8);
    list.add(4);
    list.add(12);
    list.add(2);
    list.add(10);
    list.add(6);
    list.add(14);
    list.add(1);
    list.add(9);
    System.out.println(dpSolution(list));

  }

}
