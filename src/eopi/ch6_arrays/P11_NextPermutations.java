package eopi.ch6_arrays;

import java.util.List;

/**
 * Author by darcy
 * Date on 17-9-16 下午10:49.
 * Description:
 *
 * 返回一个permutation的字典序的下一个permutation.
 *
 * Write a program that takes as input a permutation, and returns the next permutation
 * under dictionary ordering. If the permutation is the last permutation, return the
 * empty array. For example, if the input is (1,0,3, 2) your function should return
 * (1, 2,0,3). If the input is (3, 2,1,0), return ().
 *
 * Hint:Study concrete examples
 */
public class P11_NextPermutations {

  /**
   * 暴力破解算法.
   * A brute-force approach might be to find all permutations whose length
   * equals that of the input array, sort them according to the dictionary order, then find
   * the successor of the input permutation in that ordering.
   *
   * @param permutation
   * @return
   */
  public static List<Integer> bruteForceSolution(List<Integer> permutation) {
    return null;
  }

}
