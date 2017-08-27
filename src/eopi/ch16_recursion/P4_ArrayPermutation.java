package eopi.ch16_recursion;

import java.util.*;
import java.util.stream.IntStream;

import static java.util.stream.Collectors.toList;

/**
 * Author by darcy
 * Date on 17-8-27 下午12:06.
 * Description:
 * 数组元素所有可能的置换情况。
 *
 * Write a program which takes as input an array of distinct integers and generates all
 * permutations of that array. No permutation of the array may appear more than once.
 *
 * Hint: How many possible values are there for the first element?
 *
 * if the array is (2,3,5, 7) one output could be (2,3,5, 7), (2,3,7,5), (2,5,3, 7), (2,5, 7,3),
 * <2,7,3,5>, <2,7,5,3>, <3,2,5,7>, <3,2,7,5>, <3,5,2,7>, <3,5,7,2>, <3,7,2,5>, <3,7,5,2>,
 * <5, 2,3, 7>, (5, 2, 7,3}, <5,3,2,7>, <5,3,7,2>, <5,7,2,3>, <5,7,2,3>, <7,2,3,5>, <7,2,5,3>,
 * <7,3, 2,5), <7,3,5, 2), <7,5, 2,3), <7,5,3, 2).
 */
public class P4_ArrayPermutation {

  /**
   * 暴力方法
   * 每个位置放置的可能是n,总的情况是n^n; 从剩余元素中选择, n!
   *
   * @param A
   * @return
   */
  public static List<List<Integer>> bruteSolution(List<Integer> A) {

    return null;
  }


  /**
   * 简单来讲: 以A[0]起始的所有permutation其实就是A[1:n-1]的所有permutation.
   * 然后交换A[0]和A[1],那么就能得到以A[1]起始的所有置换. 然后这样swap下去，遍历所有情况就可以得到结果。
   *
   * A better approach is to recognize that once a value has been chosen for an entry,
   * we do not want to repeat it. Specifically, every permutation of A begins with one of
   * A[0], A[1],... ,A[n - 1], The idea is to generate all permutations that begin with A[0],
   * then all permutations that begin with A[1], and so on. Computing all permutations
   * beginning with A[0] entails computing all permutations of A[1: n—1],which suggests
   * the use of recursion. To compute all permutations beginning with A[1] we swap A[0]
   * with A[1] and compute all permutations of the updated A[1 : n- 1]. We then restore
   * the original state before embarking(着手) on computing all permutations beginning with
   * A[2], and so on.
   *
   * 时间复杂度分析:
   * The time complexity is determined by the number of recursive calls, since within each
   * function the time spent is O(1), not including the time in the subcalls. The number
   * of function calls, C(n) satisfies the recurrence C(n) = 1 + nC(n - 1) for n > 1, with
   * C(0) = 1. Expanding this, we see C(n) = 1 + n + n(n - 1) + n(n - 1)(n - 2) + ... + n! =
   * n!(1/n! + 1/(n-1)! + 1/(n-2)! + ... + 1/1!), since the sum (1 + 1/1! + ... + 1/n!) --> e, 所以
   * C(n) --> (e-1)n!, ie, O(n!). The time complexity of T(n) is O(n*n!),  since we do O(n)
   * computation per call outside of the recursive calls.
   * @param A
   * @return
   */
  public static List<List<Integer>> solution(List<Integer> A) {
    List<List<Integer>> result = new ArrayList<>();
    solution(0, A, result);
    return result;
  }

  /**
   * 以index为i起始的所有置换.
   * @param i
   * @param A
   * @param result
   */
  private static void solution(int i, List<Integer> A, List<List<Integer>> result) {
    if (i == A.size() - 1) {
      result.add(new ArrayList<>(A));
    } else {
      for (int j = i; j < A.size(); j++) {
        // 所有元素都会机会成为第一个元素。
        Collections.swap(A, i, j);
        solution(i + 1, A, result);
        Collections.swap(A, i, j);
      }
    }
  }

  /**
   *
   *
   * 复杂度
   * The time complexity is O(n * n!),since there are n! permutations and we spend O(n)
   * time to store each one.
   * @param A
   * @return
   */
  public static List<List<Integer>> otherSolution(List<Integer> A) {
    List<List<Integer>> result = new ArrayList<>();
    A.sort(new Comparator<Integer>() {
      @Override
      public int compare(Integer o1, Integer o2) {
        if (o1 > o2) {
          return 1;
        } else if (o1 < o2) {
          return -1;
        } else {
          return 0;
        }
      }
    });

    do {
      result.add(new ArrayList<>(A));
      A = nextPermutation(A);
    } while (!A.isEmpty());

    return result;
  }

  /**
   * The general algorithm for computing the next permutation is as follows:
   * (1.) Find k such that p[k] < p[k + 1] and entries after index k appear in decreasing
   * order.
   * (2.) Find the smallest p[l] such that p[l] > p[k] (such an l must exist since p[k] <
   * p[k+1]).
   * (3.) Swap p[l] and p[k] (note that the sequence after position k remains in decreasing
   * order).
   * (4.) Reverse the sequence after position k.
   * @param a
   * @return
   */
  private static List<Integer> nextPermutation(List<Integer> a) {
    int k = a.size() - 2;
    // 找到第一个前面比后面小的元素。同时意味者该元素后面的元素都是逆序的。
    while (k >= 0 && a.get(k) >= a.get(k + 1)) {
      k--;
    }

    if (k == -1) {
      return Collections.emptyList();
    }

    // k后面的元素都是逆序的.
    // 实际上找到的就是上面退出while循环的元素.
    for (int i = a.size() - 1; i > k; i--) {
      // 临界的大小两个元素进行交换.
      if (a.get(i) > a.get(k)) {
        Collections.swap(a, k, i);
        break;
      }
    }

    // k之后的元素逆序,
    Collections.reverse(a.subList(k + 1, a.size()));
    return a;
  }

  public static void main(String[] args) {
    List<Integer> A = IntStream.range(1, 5).mapToObj((i) -> Integer.valueOf(i)).collect(toList());
    List<Integer> A1 = IntStream.rangeClosed(1, 5).mapToObj(Integer::valueOf).collect(toList());
//    List<List<Integer>> result = solution(A);
    List<List<Integer>> result = otherSolution(A);
    for (int i = 0; i < result.size(); i++) {
      System.out.println(result.get(i));
    }

    System.out.println();
    /*List<List<Integer>> result2 = solution(A1);
    for (int i = 0; i < result2.size(); i++) {
      System.out.println(result2.get(i));
    }
*/
  }

}

/*
Variant: Solve Problem 16.3 on Page 287 when the input array may have duplicates.
You should not repeat any permutations. For example, if A = (2, 2,3, 0} then the out¬
putshould be <2, 2,0,3), <2, 2,3,0>, <2,0, 2,3>, <2,0,3, 2>, <2,3, 2, 0>, <2,3,0, 2),<0, 2, 2,3>,
<0, 2,3, 2), <0,3, 2, 2), <3, 2, 2,0>, <3, 2, 0, 2), <3, 0, 2, 2>.
 */