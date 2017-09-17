package eopi.ch6_arrays;

import java.util.*;

/**
 * Author by darcy
 * Date on 17-9-17 上午9:24.
 * Description:
 *
 * 实现一个算法, 返回size个元素的子集合, 所有的子集合都是equally likely.
 *
 * Implement an algorithm that takes as input an array of distinct elements and a size,
 * and returns a subset of the given size of the array elements. All subsets should be
 * equally likely. Return the result in input array itself.
 *
 * Hint: How would you construct a random subset of size k + 1 given a random subset of size k?
 */
public class P11_SampleOfflineData {

  /**
   * 1. 每length/size个中随机选择一个.但是这样不一定选的到size个。
   * 2. 获取所有的size个元素的子集合,然后randomly选择一个. 而且C(n,k)的复杂度.
   *
   * @param given
   * @param k
   * @return
   */
  public static List<Integer> bruteForceSolution(List<Integer> given, int k) {

    return null;
  }

  /**
   * 当生成第i个随机数的时候,就在[i, size - 1]randomly选择一个index,所代表的数字和索引为 i的元素进行交换.
   *
   * 时间复杂度O(K), 空间复杂度O(1)
   *
   *
   *
   * For k > 1, we begin by choosing one element at random as above and we now
   * repeat the same process with the n - 1 element subarray A[1 : n- 1]. Eventually, the
   * random subset occupies the slots A[0 : k — 1] and the remaining elements are in the
   * last n-k slots.
   * @param given
   * @param k
   * @return
   */
  public static List<Integer> solution(List<Integer> given, int k) {
    Random random = new Random(31);
    for (int i = 0; i < k; i++) {
      // random.nextInt(given.size() - i) + i就是 [i, size - 1]
      Collections.swap(given, i, random.nextInt(given.size() - i) + i);
    }
    return given.subList(0, k);
  }

  public static void main(String[] args) {
    List<Integer> given = new ArrayList<>(Arrays.asList(9, 5, 2, 7, 0, 3, 1, 8, 4, 6, 12));
    List<Integer> result = solution(given, 4);
    System.out.println(result);
  }

}
