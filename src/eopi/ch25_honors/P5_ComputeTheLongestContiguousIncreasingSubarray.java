package eopi.ch25_honors;

import java.util.Arrays;
import java.util.List;
import java.util.Random;

import static java.util.stream.Collectors.toList;

/**
 * Author by darcy
 * Date on 17-10-23 上午10:22.
 * Description:
 * <p>
 * 计算最长的连续递增子数组.
 * <p>
 * Implement an algorithm that takes as input an array A of n elements, and returns the
 * beginning and ending indices of a longest increasing subarray of A. For example, if
 * A = (2,11,3,5,13, 7,19,17, 23), the longest increasing subarray is (3,5,13), and you
 * should return (2,4)
 */
public class P5_ComputeTheLongestContiguousIncreasingSubarray {

  public static class SubArray {
    int start;
    int end;

    public SubArray(int start, int end) {
      this.start = start;
      this.end = end;
    }
  }

  /**
   * 思路: 当处理到位置i的时候, 此时不满足递增关系, 但是已经处理的前面的最长递增子序列长度为L,
   * 那么要使得后面的最长的递增子序列的长度大于L, 那么可以 i+L, 然后从后往前迭代(最差的情况是迭代到位置i,
   * 其他情况都是相当于某种程度的减枝);
   *
   * given <2,11,3,5,13, 7,19,17, 23>
   * We can heuristically improve upon the0(n)algorithm by observing that if A[i— 1] >=
   * A[i] (i.e., we are starting to look for a new subarray starting at i) and the longest
   * contiguous subarray seen up to index i has length L, we can move on to index i + L
   * and work backwards(往前迭代) towards i. Specifically, if for any j,i < j < i + L we have
   * A[j- 1] >= A[j], we can skip the earlier indices. For example, after processing 13, we
   * work our way back from the entry at index 4 + 3 = 7, i.e., 13's index plus the length
   * of the longest increasing subarray seen so far (3). Since A[7] = 17 < A[6] = 19, we do
   * not need to examine prior entries—no increasing array ending at A[7] can be longer
   * than the current best.
   * @param A
   * @return
   */
  public static SubArray solution(List<Integer> A) {
    int maxLength = 1;
    SubArray ans = new SubArray(0, 0);
    int i = 0;
    while (i < A.size() - maxLength) {
      // Backward check and skip if A[j - 1] >= A[j].
      boolean isSkippable = false;
      for (int j = i + maxLength; j > i; --j) {
        // 到i处时, 最长的递增子序列长度为maxLength;
        // 那么就从i+maxLength处往后迭代, 看是否满足前后递增的关系.不满足, 那么更新下一次迭代的位置.
        // 最差的情况是位置i处开始的最长递增子序列长度也是L, 即[i, i + L]也是满足递增关系的. 但是之前i和i-1
        // 不满足递增关系, 所以需要重新计算.
        if (A.get(j - 1) >= A.get(j)) {
          i = j;
          isSkippable = true;
          break;
        }
      }
      // Forward check if it is not skippable.
      if (!isSkippable) {
        // 一定是构造最长递增子序列长度>L的
        i += maxLength;
        // 一般情况下不满足退出的时候 A[i - 1] >= A[i]
        // 所以是从i处开始查找.
        while (i < A.size() && A.get(i - 1) < A.get(i)) {
          ++i;
          ++maxLength;
        }
        ans = new SubArray(i - maxLength, i - 1);
      }
    }
    return ans;
  }

  public static void main(String[] args) {
    int number = 20;
    int[] array = new int[number];
    Random random = new Random(System.currentTimeMillis());
    for (int i = 0; i < number; i++) {
      array[i] = random.nextInt(100);
    }
    List<Integer> list = Arrays.stream(array).boxed().collect(toList());
    System.out.println(list);
    SubArray result = solution(list);
    System.out.println(list.subList(result.start, result.end + 1));

  }

}
