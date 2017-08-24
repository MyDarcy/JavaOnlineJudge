package eopi.ch17_dp;

import java.util.Arrays;

/**
 * Author by darcy
 * Date on 17-8-24 下午9:12.
 * Description:
 * 计算编辑距离:
 * a最少经过多少次变换(插入, 删除, 替换)转为b;
 * Write a program that takes two strings and computes the minimum number of
 * edits needed to transform the first string into the second string.(edit means
 * insertion, deletion, or substitution of a single character.)
 *
 */
public class P4_Distance {

  /**
   * 暴力破解算法:
   * distance 1,2,3,4,,,的尝试下去.
   * @param s1
   * @param s2
   * @return
   */
  public static int bruteForceSolution(String s1, String s2) {

    return 0;
  }

  /**
   * 字符串A,B的长度分别是a, b
   * E(A[0 : a-1],B[0 : b — 1])代表A,B 之间的编辑距离.
   *
   * 1. 当A的最后一个字符 == B的最后一个字符, 那么 E(A[0, a-1,B[0 : b-1]) = E(A[0 : a-2],B[0 : b-2])
   * 2. 当A的最后一个字符 != B的最后一个字符, 那么 E(A[0, a-1,B[0 : b-1]) =
   *
   *    1 + min(E(A[0:a-2],B[0:b-2], E(A[0:a-1],B[0:b-2], E(A[0:a-2],B[0:b-1])
   *
   *    A[0, a-1] 转为 B[0 : b-1]的三种方式
   *        1. A[0, a-2]转为B[0, b-2], 然后把A的最后一个字符转为B的最后一个字符.
   *        2. A[0, a-1]转为B[0, b-2], 然后在A的末尾加上B的最后一个字符.
   *        3. A[0, a-2]转为B[0, b-1], 然后把A的最后一个字符删除掉.
   *
   * DP is a great way to solve this recurrence relation: cache intermediate results on
   * the way to computing E(A[0 : a - 1], B[0 : b - 1])
   *
   * The value E(A[0 : a-1],B[0 : b—1]) takes time O(1) to compute once E(A[0 : k],B[0 : l])
   * is known for all k < a and l < b. This implies O(ab) time complexity for the algorithm.
   * Our implementation uses O(ab) space.
   *
   * @param s1
   * @param s2
   * @return
   */
  public static int dpSolution(String s1, String s2) {
    int[][] distanceOfPrefix = new int[s1.length()][s2.length()];
    for (int[] row : distanceOfPrefix) {
      Arrays.fill(row, -1);
    }

    return dpSolution(s1, s1.length() - 1, s2, s2.length() - 1, distanceOfPrefix);

  }

  private static int dpSolution(String s1, int index1, String s2, int index2, int[][] distanceOfPrefix) {

    // A已经为空了, 所以需要添加B所有的元素到A, 也就是要改变index2 + 1次;
    if (index1 < 0) {
      return index2 + 1;

      // 同理.
    } else if (index2 < 0) {
      return index1 + 1;
    }

    // 都还有元素
    if (distanceOfPrefix[index1][index2] == -1) {
      if (s1.charAt(index1) == s2.charAt(index2)) {
        distanceOfPrefix[index1][index2]
            = dpSolution(s1, index1 - 1, s2, index2 - 1, distanceOfPrefix);
      } else {
        // 替换A的最后一个字符为B的最后一个字符.
        int substitueLast = dpSolution(s1, index1 - 1, s2, index2 - 1, distanceOfPrefix);

        // s1转为s2[0:b-1], 然后在s1的末尾添加s2的最后一个字符.因为s2末尾此时还有一个字符.
        int addLast = dpSolution(s1, index1, s2, index2 - 1, distanceOfPrefix);

        // s1[0:a-1]转为s2, 然后删除s1的最后一个字符.
        int deleteLast = dpSolution(s1, index1 - 1, s2, index2, distanceOfPrefix);

        distanceOfPrefix[index1][index2]
            = 1 + Math.min(substitueLast, Math.min(addLast, deleteLast)); // １代表转换前缀后还需要进行的一次操作。

      }
    }

    return distanceOfPrefix[index1][index2];
  }


  public static void main(String[] args) {
    String s1 = "Carthorse";
    String s2 = "Orchestra";
    System.out.println(dpSolution(s1, s2));

    s1 = "aabb";
    s2 = "baa";
    System.out.println(dpSolution(s1, s2));

    // variant3, 只能是删除操作.
    s1 = "abcdbbb";
    s2 = new StringBuilder(s1).reverse().toString();
    System.out.println(dpSolution(s1, s2));

  }
}


/*
Variant: Compute the Levenshtein distance using O(min(a, b)) space and O(ab) time.

Variant: Given A and B as above, compute a longest sequence of characters that is a
subsequence of A and of B. For example, the longest subsequence which is present
in both strings in Figure 17.4 is {r,h,s).(非连续的)

Variant: Given a string A, compute the minimum number of characters you need to
delete from A to make the resulting string a palindrome.(回文)

Variant: Given a string A and a regular expression r,what is the string in the language
of the regular expression r that is closest to A? The distance between strings is the
Levenshtein distance specified above.(正则表达式的哪一种可能形式最接近a字符串)

Variant: Define a string t to be an interleaving(交错；交叉) of strings s１ and S2 if there is a way to
interleave the characters of s1 and s2, keeping the left-to-right order of each, to obtain
t. For example, if s1 is "gtaa" and s2 is "atc", then "gattaca" and "gtataac" can be
formed by interleaving s1 and s2 but "gatacta" cannot. Design an algorithm that takes
as input strings s1, s2 and t,and determines if t is an interleaving of s1 and s2.
难道不是只有两种情况????
 */