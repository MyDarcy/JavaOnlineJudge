package com.darcy.main.review;

import java.util.Arrays;

/**
 * Author by darcy
 * Date on 17-9-9 下午8:25.
 * Description:}
 *
 * 数组相关.
 */
public class MyArrays {

  /**
   * 连续子数组的最大和.
   * array[n-1]与最大子数组的关系:
   * 1. 最大子数组包含arr[n-1], 即以array[n-1]结尾,
   * 2. array[n-1]单独构成最大子数组.
   * 3. 最大子数组不包括array[n-1], 那么求array[1...n-1]的最大子数组可以转为求array[1...n-2]的最大子数组.
   *
   * time:O(n), space: O(n)
   *
   * @param array
   * @return
   */
  public static int maxSubArrayC1(int[] array) {
    int length = array.length;
    int[] all = new int[length];
    int[] end = new int[length];
    all[length - 1] = end[length - 1] = array[length - 1];
    all[0] = end[0] = array[0];
    for (int i = 1; i < array.length; i++) {
      // 上面三句话的体现。
      end[i] = Math.max(end[i - 1] + array[i], array[i]);
      all[i] = Math.max(end[i], all[i - 1]);
    }
    return all[length - 1];
  }

  /**
   * 最大连续子数组的和.
   * time:O(n), 空间复杂度:O(1)
   * @param array
   * @return
   */
  public static int maxSubArray(int[] array) {
    int nAll = array[0];

    int nEnd = array[0];
    for (int i = 1; i < array.length; i++) {
      nEnd = Math.max(nEnd + array[i], array[i]);
      nAll = Math.max(nAll, nEnd);
    }
    return nAll;
  }

  /**
   * 当End[i-1] < 0的时候, End[i] = array[i] // 这里End[i]表示包含array[i]的子数组和.
   * 一旦End[i]小于0, End[i]重新开始.
   *
   * 获取最大子树组和的起始和结束index.
   */
  private static int start = 0;
  private static int end = 0;
  public static int maxSubArray2(int[] array) {
    int maxSum = Integer.MIN_VALUE;
    int nSum = 0; // 包含最后一位的最大值.
    int nStart = 0;

    for (int i = 0; i < array.length; i++) {
      if (nSum < 0) {
        nSum = array[i];
        // 重新开始时候记录start位置.
        nStart = i;
      } else {
        nSum += array[i];
      }
      if (nSum > maxSum) {
        maxSum = nSum;
        start = nStart;
        end = i;
      }
    }
    return maxSum;
  }


  /**
   * 12345678 ->(2) 78123456
   * 数组的移位.
   *
   * @param array
   */
  public static void rotate(int[] array, int k) {
    int length = array.length;
    k = k % length;
    reverse(array, length - k, length - 1);
    reverse(array, 0, length - k - 1);
    reverse(array, 0, length - 1);

  }

  private static void reverse(int[] array, int start, int end) {
    for (; start < end; start++, end--) {
      swap(array, start, end);
    }
  }

  private static void swap(int[] array, int start, int end) {
    int temp = array[end];
    array[end] = array[start];
    array[start] = temp;
  }


  /**
   * 找第k小的数据.
   * 1. 利用快速排序的思想.
   * 2. 利用堆的思想.
   * @param array
   */
  public static int kthMin(int[] array, int k) {
    if (array == null || array.length < k) {
      return Integer.MIN_VALUE;
    }

    return kthMin(array, 0, array.length - 1, k);
  }

  private static int kthMin(int[] array, int start, int end, int k) {
    if (start > end) {
      return Integer.MIN_VALUE;
    }

    int current = array[start];
    int i = start;
    int j = end;
    // i, j遍历.
    while (i < j) {
      while (array[j] >= current && i < j) {
        j--;
      }
      if (i < j) {
        array[i++] = array[j];
      }

      while (array[i] < current && i < j) {
        i++;
      }

      if (i < j) {
        array[j--] = array[i];
      }
    }

    // i == j且是目标值的时候也会求出来.
    array[i] = current;
    if (i + 1 == k) {
      return current;
    } else if (i + 1 > k) {
      return kthMin(array, start, i - 1, k);
    } else {
      return kthMin(array, i + 1, end, k);
    }
  }

  /**
   * 只出现一次的数字.
   * 1. 如果是其他数组出现的次数>1且不一致,那么简单的排序或者map即可。
   * 2. 其他出现两次, 亦或.
   * 3. 其他出现三次,统计每一位出现的次数.
   * @param array
   * @return
   */
  public static int findNumberOccrrenceOnce(int[] array) {
    int[] bitCounts = new int[32];
    for (int i = 0; i < array.length; i++) {
      for (int j = 0; j < 32; j++) {
        bitCounts[j] += (array[i] >> j) & 1;
      }
    }

    int result = 0;
    for (int i = 0; i < bitCounts.length; i++) {
      if (bitCounts[i] % 3 == 1) {
        result += 1 << i;

      }
    }
    return result;
  }

  /**
   * 只重复一次的数,
   * 1...N-1这n-1个数存放与N的数组中.
   *
   * 1. 求和-sum(1...N-1)
   * 2. 亦或.
   * @param array
   * @return
   */
  public static int findNumberDuplicateOnce(int[] array) {
    /*int result = 0;
    for (int i = 0; i < array.length; i++) {
      result ^= array[i];
    }

    for (int i = 1; i < array.length; i++) {
      result ^= i;
    }
    return result;*/

    // 求反.
    // 重复的数字会两次求反.
    for (int i = 0; i < array.length; i++) {
      if (array[i] > 0) {
        array[array[i]] = -array[array[i]];
      } else {
        array[-array[i]] = -array[-array[i]];
      }
    }

    int result = Integer.MIN_VALUE;
    for (int i = 1; i < array.length; i++) {
      if (array[i] > 0) {
        result = i;
      } else {
        array[i] = -array[i];
      }
    }
    return result;
  }

  /**
   * 递归求最大值.
   * @param array
   * @return
   */
  public static int recursiveGetMax(int[] array) {
    return recursiveGetMax(array, 0);
  }

  private static int recursiveGetMax(int[] array, int start) {
    int l = array.length - start;
    if (l == 1) {
      return array[start];
    }

    return Math.max(array[start], recursiveGetMax(array, start + 1));
  }


  /**
   * 一个数组中的一个数字减去右边子数组中的一个数字可以得到一个差值. 求此差值的最大值.
   *
   * dp思路:
   * diff[i]是以 array[i]为减数(-后面的那一个)的差值的最大值. array[i]则表示到i时的最大值.
   * 所以:
   * diff[i+1] = max(diff[i], max[i] - array[i+1])
   * max[i+1] = max(max[i], array[i+1])
   *
   * @param array
   * @return
   */
  public static int maxDiff(int[] array) {

    if (array == null || array.length <= 1) {
      return Integer.MIN_VALUE;
    }

    int length = array.length;
    int[] max = new int[length];
    int[] diff = new int[length];
    // 可以只用一个diff和max变量替代.
    max[0] = array[0];
    diff[0] = 0;
    for (int i = 1; i < length; i++) {
      diff[i] = Math.max(diff[i - 1], max[i - 1] - array[i]);
      max[i] = Math.max(max[i - 1], array[i]);
    }

    return diff[length - 1];

  }

  public static void main(String[] args) {

    System.out.println("---auxarray---");
    int[] array0 = new int[]{1, -2, 4, 8, -4, 7, -1, -5};
    int result0 = maxSubArray(array0);
    System.out.println(result0);

    System.out.println("----subArrayMax----");
    int[] array1 = new int[]{1, -2, 4, 8, -4, 7, -1, -5};
    int result = maxSubArray(array1);
    System.out.println(result);

    System.out.println("-----get index---");
    int[] array2 = new int[]{1, -2, 4, 8, -4, 7, -1, -5};
    int result2 = maxSubArray2(array2);
    System.out.println(result2);
    System.out.println("start=" + start + ", end=" + end);

    System.out.println("\n---rotateArray----");
    int[] rotateArray = {1, 2, 3, 4, 5, 6, 7, 8};
    rotate(rotateArray, 2);
    System.out.println(Arrays.toString(rotateArray));

    System.out.println("\n----kthMin---");
    int[] kthArray = {4, 5, 6, 1, 2, 3, 8, 9};
    int kthResult = kthMin(kthArray, 5);
    System.out.println(kthResult);


    System.out.println("\n----find Number Occrrence Once----");
    int[] onceArray = {4, 5, 4, 5, 6, 4, 5, 6, 9, 6};
    int onceResult = findNumberOccrrenceOnce(onceArray);
    System.out.println(onceResult);

    System.out.println("\n ----find Number Duplicate Once");
    int[] duplicateAray = {1, 2, 3, 4, 5, 6, 6, 7};
    int duplicateResult = findNumberDuplicateOnce(duplicateAray);
    System.out.println(duplicateResult);

    System.out.println("\n recursiveGetMax");
    int[] recursiveArray = {4, 5, 6, 9, 10, 21, 11, 28, 10};
    int recuresivemax = recursiveGetMax(recursiveArray);
    System.out.println(recuresivemax);
  }

}
