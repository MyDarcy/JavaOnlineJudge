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
   * L[i] -- length of the longest non-decreasing subsequence　of A that ends at and includes Index i.
   * L[i] is either 1 (if A[i] is less than all previous entries),
   * or 1 + max{L[j]|j < i and A[j] <= A[i]}
   *
   * L[i] -- 以第i个元素结尾的最长非递减子序列长度.
   * L[i] -- 可能是１也可能是 1 + 前面递增子序列的最大值.
   *
   *
   *
   * Generalizing, define L[i] to be the length of the longest nondecreasingsubsequence
   * of A that ends at and includes Index i. As an example, for the array in Figure 17.15,
   * L[6] = 3. The longest nondecreasing subsequence that ends at Index i is of length 1
   * (if A[i] is smaller than all preceding entries) or has some element, say at Index j, as
   * its penultimate entry, in which case the subsequence restricted to A[0 :;] must be the
   * longest subsequence ending at Index j. Based on this, L[i] is either 1 (if A[i] is less
   * than all previous entries), or 1 + max{L[j]|j < i and A[j] <= A[i]}.
   *
   * We can use this relationship to compute L, recursively or iteratively.
   * If we want the sequence as well, for each i,in addition to **storing** the length of the nondecreasing
   * sequence ending at i, we store the index of the last element of the subsequence that
   * we extended to get the value assigned to L[i]; 存储最长长度和上一次最长时的index;
   *
   * array = [0, 8, 4, 12, 2, 10, 6, 14, 1, 9]
   * 1. L[0] = 1 (since there are no entries before Entry 0)
   * 2. L[1] = 1 + max(L[0]) = 2 (since A[0] < A[l])
   * 3. L[2] = 1 + max(L[0]) = 2 (since A[0] < A[2] and A[1] > A[2])
   * 4. L[3] = 1 + max(L[0],L[1],L[2]) = 3 (since A[0], A[1], A[2] < A[3])
   * 5. L[4] = 1 + max(L[0]) = 2 (since A[0] < A[4], A[1],A[2] and A[3] > A[4])
   * 6. L[5] = 1 + max(L[0],L[l],L[2],L[4]) = 3 (since A[0],A[1],A[2],A[4] < A[5] and
   *  A[3] > A[5])
   * 7. L[6] = 1 + max(L[0],L[2],L[4]) = 3 (since A[0], A[2], A[4] < A[6] and A[1], A[3],
   *  A[5] > A[6])
   * 8. L[7] = l+max(L[0],L[1],L[2],L[3],L[4],L[5],L[6]) = 4(sinceA[0],A[1],A[2],A[3],
   *  A[4],A[5],A[6]<A[7])
   * 9. L[8] = 1 + max(L[0]) = 2 (since A[0] < A[8] and A[1],A[2], A[3], A[4], A[5], A[6],
   *  A[7] > A[8])
   * 10. L[9] = 1 + max(L[0],L[1],L[2],L[4],L[6],L[8]) = 4 (since A[0], A[1], A[2], A[4],
   * A[6], A[8] < A[9] and A[3], A[5], A[7] > A[9])
   *
   *
   * The time complexity is O(n2) (each L[i] takes O(n) time to compute), and the space
   * complexity is O(n) (to store L).
   *
   * @param A
   * @return
   */
  public static int dpSolution(List<Integer> A) {
    int[] maxLength = new int[A.size()];
    List<List<Integer>> lists = new ArrayList<>(A.size());
    Arrays.fill(maxLength, 1);
    for (int i = 1; i < A.size(); i++) {
      List<Integer> list = new ArrayList<>();
      for (int j = 0; j < i; j++) {
        if (A.get(i) > A.get(j)) {
          maxLength[i] = Math.max(maxLength[i], maxLength[j] + 1);
          /*if (maxLength[i] > (maxLength[j] + 1)) {
            continue;
          } else if (maxLength[i] < (maxLength[j] + 1)) {
            list = new ArrayList<>(lists.get(j));
          } else {
            continue;
          }*/

        }
      }
      /*list.add(i);
      lists.add(list);*/
    }


    for (int i = 0; i < lists.size(); i++) {
      System.out.println(lists.get(i));
    }

    return Arrays.stream(maxLength).max().getAsInt();
  }

  public static void main(String[] args) {
    /*String[] split = "0 8 4 12 2 10 6 14 1 9".split("\\s+");
    Integer[] integers = (Integer[]) Arrays.stream(split).map((s) -> new Integer(Integer.parseInt(s))).toArray();
    List<Integer> list = new ArrayList<>(Arrays.asList(integers));*/

    List<Integer> list1 = new ArrayList<>();
    list1.add(0);
    list1.add(8);
    list1.add(4);
    list1.add(12);
    list1.add(2);
    list1.add(10);
    list1.add(6);
    list1.add(14);
    list1.add(1);
    list1.add(9);
    System.out.println(list1);
    System.out.println(dpSolution(list1));

  }

}

/*
Variant:Write a program that takesas input an array of numbers and returns a longest
nondecreasing subsequence in the array.

Variant: Define a sequence of numbers <a0,a1...a[n-1]> to be alternating if a[i] <
a[i+1] for even i and a[i] > a[i+1] for odd i. Given an array of numbers A of length n, find a
longest subsequence {i0,...,i(k-1)) such that (A[i0], A[i1],...,A[i(k-1)]} is alternating.

Variant: Define a sequence of numbers <a0,a1...a[n-1]> to be weakly alternating if no
three consecutive terms in the sequence are increasing or decreasing. Given an
array of numbers A of length n, find a longest subsequence <i0, i1...i(k-1)> such that
(A[i0], A[i1],... ,A[i(k-1)]) is weakly alternating.

Variant: Define a sequence of numbers <a0,a1...a[n-1]> to be convex if a[i] < (a[i-1] + a[i+1])/2 , for
1 <= i <= n — 2. Given an array of numbers A of length n, find a longest subsequence
<i0, i1...i(k-1)> such that (A[i0], A[i1],... ,A[i(k-1)]) is convex.

Variant:Define a sequenceof numbers <a0,a1...a[n-1]> tobe bitonic if there exists k such
that a[i] < a[i+1] for 0 <= i < k and ai > a[i+1], for k <= i < n-1. Given an array of numbers A
of length n, find a longest subsequence <i0, i1...i(k-1)> such that (A[i0], A[i1],... ,A[i(k-1)]) is bitonic.

Variant: Define a sequence of points in the plane to be ascending if each point is above
and to the right of the previous point. How would you find a maximum ascending
subset of a set of points in the plane?

Variant: Compute the longest nondecreasing subsequence in O(nlog n) time.
 */