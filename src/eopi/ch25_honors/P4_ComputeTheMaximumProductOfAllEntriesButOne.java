package eopi.ch25_honors;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static java.util.stream.Collectors.toList;

/**
 * Author by darcy
 * Date on 17-10-20 下午10:09.
 * Description:
 * <p>
 * 给定n个元素的整数数组, 那么任意n-1个树相乘的最大值.
 * <p>
 * Given an array A of length n whose entries are integers, compute the largest product
 * that can be made using n-1 entries in A. You cannot use an entry more than once.
 * Array entries may be positive, negative, or 0. Your algorithm cannot use the division
 * operator, explicitly or implicitly.
 * <p>
 * Hint: Consider the products of the first i-1and the last n- i elements. Alternatively, count the
 * number of negative entries and zero entries.
 */
public class P4_ComputeTheMaximumProductOfAllEntriesButOne {

  /**
   * 思路: i元素左右两个部分的乘积组成n-1个元素的乘积.
   * 而prefixProduct[i] = prefixProduct[i - 1] * array[i]
   * 所以可以复用上一步的乘积;
   * <p>
   * 后缀乘积可以预先计算出来,使用O(N)的数组的存储. 也可以使用计算后n-1个元素的乘积然后迭代中不断除去当前迭代元素.
   * 此时的空间复杂度是O(1)
   * <p>
   * 总的时间复杂度是O(N), 空间复杂度是O(N)
   *
   * @param array
   * @return
   */
  public static int solution(int[] array) {
    int product = 1;
    int[] suffixProducts = new int[array.length];
    Arrays.fill(suffixProducts, 0);
    // new ArrayList<Integer>(Collections.nCopies(n, 0));
    for (int i = array.length - 1; i >= 0; i--) {
      product *= array[i];
      suffixProducts[i] = product;
    }

    int prefixProduct = 1;
    int result = Integer.MIN_VALUE;
    for (int i = 0; i < array.length; i++) {
      int suffixProduct = (i == array.length - 1 ? 1 : suffixProducts[i + 1]);
      result = Integer.max(result, prefixProduct * suffixProduct);
      prefixProduct *= array[i];
    }

    return result;
  }

  /**
   * 1. The insight comes from the fact that if there are no negative entries,
   * the maximum product comes from using all but the smallest element
   * 2. If the number of negative entries is odd, regardless of how many 0 entries and
   * positive entries, the maximum product uses all entries except for the negative entry
   * with the smallest absolute value, i.e., the greatest negative entry.
   * 3. if the number of negative entries is even, the maximum product again uses all
   * but the smallest nonnegative element, assuming the number of nonnegative entries
   * is greater than zero.
   * 4. If the number of negative entries is even, and there are no nonnegative entries, the
   * result must be negative. Since we want the largest product, we leave out the entry whose
   * magnitude is largest, i.e., the least negative entry.
   *
   * 两阶段算法.
   * @param A
   * @return
   */
  public static int findBiggestNMinusOneProduct(List<Integer> A) {
    int leastNonnegativeIndex = -1; // 最小的非负整数index.
    int numberOfNegatives = 0;
    int greatestNegativeIndex = -1; // 绝对值最大的负数index.
    int  leastNegativeIndex = -1; // 绝对值最小的负数index
    // Identify the least negative , greatest negative , and least nonnegative
    // entries.
    for (int i = 0; i < A.size(); ++i) {
      if (A.get(i) < 0) {
        ++numberOfNegatives;
        if (leastNegativeIndex == -1 || A.get(leastNegativeIndex) < A.get(i)) {
          leastNegativeIndex = i;
        }
        if (greatestNegativeIndex == -1
            || A.get(i) < A.get(greatestNegativeIndex)) {
          greatestNegativeIndex = i;
        }
      } else if (A.get(i) >= 0) {
        if (leastNonnegativeIndex == -1
            || A.get(i) < A.get(leastNonnegativeIndex)) {
          leastNonnegativeIndex = i;
        }
      }
    }
    int product = 1;
    int IdxToSkip = (numberOfNegatives % 2) != 0 ? leastNegativeIndex
        // Check if there are any nonnegative entry.
        : // 偶数个负数. 那么正数值最小的去掉.
        (leastNonnegativeIndex != -1 ? leastNonnegativeIndex
            // 全部都是负数, 那么负数绝对值最大的去掉.
            : greatestNegativeIndex);
    for (int i = 0; i < A.size(); ++i) {
      if (i != IdxToSkip) {
        product *= A.get(i);
      }
    }
    return product;
  }

  public static void main(String[] args) {
    int[] array1 = {3, 2, 5, 4};
    System.out.println(solution(array1));
    System.out.println(findBiggestNMinusOneProduct(Arrays.stream(array1).boxed().collect(toList())));

    int[] array2 = {3, 2, -1, 4};
    System.out.println(solution(array2));
    System.out.println(findBiggestNMinusOneProduct(Arrays.stream(array2).boxed().collect(toList())));

    int[] array3 = {3, 2, -1, 4, -1, 6};
    System.out.println(solution(array3));
    System.out.println(findBiggestNMinusOneProduct(Arrays.stream(array3).boxed().collect(toList())));
  }

}
