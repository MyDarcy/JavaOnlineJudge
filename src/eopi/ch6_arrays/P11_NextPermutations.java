package eopi.ch6_arrays;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * Author by darcy
 * Date on 17-9-16 下午10:49.
 * Description:
 * <p>
 * 返回一个permutation的字典序的下一个permutation.
 *
 * 字典序.
 *
 * <p>
 * Write a program that takes as input a permutation, and returns the next permutation
 * under dictionary ordering. If the permutation is the last permutation, return the
 * empty array. For example, if the input is (1,0,3, 2) your function should return
 * (1, 2,0,3). If the input is (3, 2,1,0), return ().
 * <p>
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

  /**
   * 在一个permutation中从后往前找第一个不构成逆序的数字,其位置计为k,那么k之后的元素都是逆序的, a[k] < a[k+1],
   * 然后在[k+1, length-1]从后往前找第一个比a[k]大元素和a[k]进行互换. 因为后面本来就是逆序的, 而a[k]又比交换后的
   * 元素小.那么[k+1, length-1]仍然是逆序的. 再reverse一下就是顺序的了.
   *
   * 意思就是构造一个比之前序列字典序大"1"的数, 就是prefix和suffix部分都尽可能的改变小.
   *
   * 整个完全已经逆序,那么结束查找.
   *
   * 时间复杂度O(N)
   * 空间复杂度O(1)
   *
   * Intuitively, we should swap e with the smallest entrys in the suffix which is
   * larger than e so as to minimize the change to the prefix (which is defined to
   * be the part of the sequence that appears before the suffix).
   *
   * The general algorithm for computing the next permutation is as follows:
   * (1.) Find k such that p[k] < p[k + 1] and entries after index k appear in decreasing
   * order.
   * (2.) Find the smallest p[l] such that p[l] > p[k] (such an l must exist since p[k] <
   * p[k+l]).
   * (3.) Swap p[l] and p[k] (note that the sequence after position k remains in decreasing
   * order).
   * (4.) Reverse the sequence after position k.
   * @param perm
   * @return
   */
  public static List<Integer> nextPermutation(List<Integer> perm) {
    int k = perm.size() - 2;
    // 直到找到第一个数不能构成逆序,即前后顺序的。
    while (k >= 0 && perm.get(k) >= perm.get(k + 1)) {
      --k;
    }
    // 全部逆序,那么所有的permutation就找完了.
    if (k == -1) {
      return Collections.emptyList(); // perm is the last permutation.
    }
    // Swap the smallest entry after index k that is greater than perm[k] . We
    // exploit the fact that perm.subList (k + 1, perm.size()) is decreasing so
    // if we search in reverse order, the first entry that is greater than
    // perm[k] is the smallest such entry.
    // k之后的元素从尾部开始找第一个比perm[k]大的元素. 然后跟k互换位置.
    // 因为k之后的元素都是逆序的. 而k又比 k+1处的元素小.
    // 所以这里swap以后, k+1之后的元素仍然都是逆序的.
    for (int i = perm.size() - 1; i > k; --i) {
      if (perm.get(i) > perm.get(k)) {
        Collections.swap(perm, k, i);
        break;
      }
    }
    // Since perm.subList[k + 1, perm.size()) is in decreasing order, we can
    // build the smallest dictionary ordering of this subarray by reversing it.
    // 再逆序, k+1之后的元素都是顺序的.
    Collections.reverse(perm.subList(k + 1, perm.size()));
    return perm;
  }

  public static void main(String[] args) {
    /**
     * 3, 4, 5, 1, 2, 6
     */
    List<Integer> list = new ArrayList<>(Arrays.asList(1, 2, 3, 4, 5, 6));
    System.out.println("init:" + list);

    List<Integer> result = null;
    int count = 0;
    while ((result = nextPermutation(list)).size() != 0) {
      count++;
      System.out.println(result);
    }
    System.out.println(count);

    /*
    init: [1, 2, 3, 4, 5, 6]
          [1, 2, 3, 4, 6, 5]
          [1, 2, 3, 5, 4, 6]
          [1, 2, 3, 5, 6, 4]
          [1, 2, 3, 6, 4, 5]
          [1, 2, 3, 6, 5, 4]
          [1, 2, 4, 3, 5, 6]
     */

    /*List<Integer> result1 = nextPermutation(list);
    System.out.println(result1);

    List<Integer> result2 = nextPermutation(list);
    System.out.println(result2);*/
  }
}

/*
Variant: Compute the fcth permutation under dictionary ordering, starting from the
identity permutation (which is the first permutation in dictionary ordering).


Variant: Given a permutation p, return the permutation corresponding to the previous
permutation of p under dictionary ordering.
 */