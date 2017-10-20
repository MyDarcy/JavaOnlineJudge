package eopi.ch25_honors;

import java.util.Arrays;

/**
 * Author by darcy
 * Date on 17-10-20 下午7:30.
 * Description:
 *
 * Let A be an array of length n. Design an algorithm to find the smallest positive integer
 * which is not present in A. You do not need to preserve the contents of A. For example,
 * if A = (3,5,4, —1,5,1,-1), the smallest positive integer not present in A is 2.
 *
 * Hint: First, find an upper bound for x.
 *
 * 找给定数组中最小的正整数.
 *
 */
public class P2_FindTheFirstMissingPositiveNumber {

  /**
   * 方法1: 排序并且迭代查找. O(NlogN)的时间复杂度.
   * 方法2: hashset存储数据, 然后从1开始迭代, 时间和空间复杂度都是O(N)
   * 方法3: 用数组本身来实现类似hashset的作用.
   *      for k in 1...n, A[k-1] = k;
   *
   * For example, let A = (3,4,0,2), n = 4. we begin by recording the presence of 3
   * by writing it in A[3-1]; we save the current entry at index 2 by writing it to A[0].
   * Now A = (0,4,3, 2). Since 0 is outside the range of interest, we advance to A[1], i.e.,
   * 4, which is within the range of interest. We write 4 in A[4- 1], and save the value at
   * that location to index 1, and A becomes (0,2,3,4). The value at A[l] already indicates
   * that a 2 is present, so we advance. The same holds for A[2] and A[3],
   *
   * 时间复杂度是O(N), 空间复杂度是O(N)
   *
   * @param array
   * @return
   */
  public static int solution(int[] array) {
    int n = array.length;
    int i = 0;
    while (i < n) {
      // array[i]在[1,n]之间;
      // 要把元素k存放到数组的array[k-1]位置处.
      if (array[i] > 0 && array[i] <= n
          && array[array[i] - 1] != array[i]) {
        swap(array, array[i] - 1, i);

        // array[i]为负数或者 > n, 那么i++;
      } else {
        i++;
      }
    }

    for (int j = 0; j < n; j++) {
      if (array[j] != j + 1) {
        return j + 1;
      }
    }
    return n + 1;
  }

  private static void swap(int[] array, int i, int j) {
    int temp = array[j];
    array[j] = array[i];
    array[i] = temp;
  }

  public static void main(String[] args) {
    int[] array = {3, 4, 0, 2};
    int result = solution(array);
    System.out.println(result);
  }

}
