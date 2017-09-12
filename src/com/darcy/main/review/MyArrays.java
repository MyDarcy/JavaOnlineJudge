package com.darcy.main.review;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Author by darcy
 * Date on 17-9-9 下午8:25.
 * Description:}
 * <p>
 * 数组相关.
 */
public class MyArrays {

  /**
   * 连续子数组的最大和.
   * array[n-1]与最大子数组的关系:
   * 1. 最大子数组包含arr[n-1], 即以array[n-1]结尾,
   * 2. array[n-1]单独构成最大子数组.
   * 3. 最大子数组不包括array[n-1], 那么求array[1...n-1]的最大子数组可以转为求array[1...n-2]的最大子数组.
   * <p>
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
   *
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
   * <p>
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
   *
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
   *
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
   * <p>
   * 1. 求和-sum(1...N-1)
   * 2. 亦或.
   *
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
   *
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
   * <p>
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

  /**
   * 求已序的整数序列中绝对值最小的数。
   * 二分法求值:
   * 1. 如果array[middle] = 0, 那么直接返回0;
   * 2. array[middle] > 0, 如果array[middle-1] < 0, 那么就找到了分界点, 通过比较array[middle]和
   * array[middle-1]就能求值.如果array[middle - 1]=0,那么array[middle-1]就是要求的值.
   * 否则就在左半部分中找.
   * 3. array[middle] < 0, 如果array[middle + 1] > 0, 那么就找到了分界点, 通过比较array[middle]和array[middle]
   * 和array[middle + 1]就能求值.如果array[middle + 1] ==0, 那么就是0, 如果array[middle + 1] < 0,
   * 那么就在右半部分中中找.
   *
   * @param array
   * @return
   */
  public static int absMin(int[] array) {
    if (array == null) {
      return Integer.MIN_VALUE;
    }

    int length = array.length;
    if (length < 1) {
      return Integer.MIN_VALUE;
    }

    if (array[0] > 0) {
      return array[0];
    }

    if (array[length - 1] < 0) {
      return array[length - 1];
    }

    int start = 0;
    int end = length - 1;
    int middle = 0;
    while (start < end) { // true.
      middle = (start + end) / 2;
      if (array[middle] == 0) {
        return 0;
      } else if (array[middle] > 0) {
        if (array[middle - 1] > 0) {
          end = middle - 1;
        } else if (array[middle - 1] == 0) {
          return 0;
          // 后一个数字大于0, 前一个数字小于0, 分界点.
        } else {
          break;
        }
      } else {
        if (array[middle + 1] < 0) {
          start = middle + 1;
        } else if (array[middle + 1] == 0) {
          return 0;
          // 找到分界点.
        } else {
          break;
        }
      }
    }

    int result = 0;
    if (array[middle] > 0) {
      result = Math.min(Math.abs(array[middle - 1]), array[middle]);
    } else {
      result = Math.min(Math.abs(array[middle]), Math.abs(array[middle + 1]));
    }

    return result;
  }


  /**
   * 数组中指定两个数字的最小距离.
   *
   * @param array
   * @param number1
   * @param number2
   * @return
   */
  public static int minDistanceOfTwoNumberInArray(int[] array, int number1, int number2) {
    int number1Index = -1;
    int number2Index = -1;
    int min = Integer.MAX_VALUE;
    for (int i = 0; i < array.length; i++) {
      if (array[i] == number1) {
        number1Index = i;
        if (number2Index != -1) {
          int temp = number1Index - number2Index;
          if (temp < min) {
            min = temp;
          }
        }
      }

      if (array[i] == number2) {
        number2Index = i;
        if (number1Index != -1) {
          int temp = number2Index - number1Index;
          if (temp < min) {
            min = temp;
          }
        }
      }
    }
    return min;
  }

  /**
   * 数组中指定数字第一次出现的位置.
   * 附加条件: 相邻元素的绝对值之差为1.
   * <p>
   * 1. O(n)的遍历
   * 2. 利用相邻元素绝对值之差为1的条件.
   *
   * @param array
   * @return
   */
  public static int firstOccrrence(int[] array, int number) {
    for (int i = 0; i < array.length; ) {
      if (array[i] == number) {
        return i;
      } else {
        i += (number - array[i]);
      }
    }

    return -1;
  }

  /**
   * 子数组的合并. array[0, mid-1], array[mid, n -1]各自有序(升序).
   * 要求，空间复杂度O(1).
   * <p>
   * 前半部分和mid进行比较并交换, 交换后, 后半部分前后compareAndSwap.
   *
   * @param array
   * @param mid
   */
  public static void mergeSubSortedArray(int[] array, int mid) {
    for (int i = 0; i < mid; i++) {
      if (array[i] > array[mid]) {
        swap(array, i, mid);
        adjustify(array, mid);
      }
    }
  }

  private static void adjustify(int[] array, int mid) {
    for (int i = mid; i < array.length - 1; i++) {
      if (array[i] > array[i + 1]) {
        swap(array, i, i + 1);
      } else {
        break;
      }
    }
  }



  /**
   * 两个有序的整型数组的交集.
   *
   * 散列.
   *
   * @param array1
   * @param array2
   * @return
   */
  public static List<Integer> interResult(int[] array1, int[] array2) {
    int index1 = 0;
    int index2 = 0;
    List<Integer> result = new ArrayList<>();
    while (index1 < array1.length && index2 < array2.length) {
      if (array1[index1] == array2[index2]) {
//        System.out.println("equal.");
        if (result.size() == 0 || (result.size() > 0 && result.get(result.size() - 1) != array1[index1])) {
          result.add(array1[index1]);
        }
        index1++;
        index2++;
      } else if (array1[index1] < array2[index2]) {
        index1++;
      } else if (array1[index1] > array2[index2]) {
        index2++;
      }
    }
    return result;
  }
  /**
   * 数组序列, 元素取值的可能是0～65535中的任意一个数字, 相同数字不会重复出现. 0可以反复出现.
   * 设计一种算法, 从该数组序列中任意取5个数字, 判断这5个数字是否连续.
   * . 5个数字是可以乱序的.
   * . 0可以匹配任意数值
   * . 0可以出现多次.
   * . 全0算连续, 只有一个非0算连续.
   * @param array
   */
  public static void numberSequentialAdjacent(int[] array) {
    int min = Integer.MAX_VALUE;
    int max = Integer.MIN_VALUE;
    for (int i = 0; i < array.length; i++) {
      if (array[i] != 0) {
        if (min > array[i]) {
          min = array[i];
        } else if (max < array[i]) {
          max = array[i];
        }
      }
    }
    if (max - min <= array.length - 1) {
      System.out.println("true");
    } else {
      System.out.println("false");

    }
  }

  /**
   * 反序对的个数
   * 如果a[i] > a[j](i < j) 那么称为一个反序对.
   * <p>
   * 1. 暴力法.
   * 2. 分治法.
   *
   * @param array
   * @return
   */
  private static int count = 0;
  public static int reverseCount(int[] array) {

    mergeSort(array, 0, array.length - 1);

    return count;
  }

  private static void mergeSort(int[] array, int start, int end) {
    if (start < end) {
      int middle = (start + end) / 2;
      mergeSort(array, start, middle);
      mergeSort(array, middle + 1, end);
      mergeSort(array, start, middle, end);
    }
  }

  private static void mergeSort(int[] array, int start, int middle, int end) {
    int[] left = new int[middle - start + 1];
    int[] right = new int[end - middle];
    for (int i = 0, j = start; /*i < middle - start + 1*/ j <= middle; i++, j++) {
      left[i] = array[j];
    }

    for (int i = 0, j = middle + 1; /*i < end - middle*/ j <= end; i++, j++) {
      right[i] = array[j];
    }

    int i = 0, j = 0, k = start;
    for (/*int i = 0, j = 0, k = start*/; i < middle - start + 1 && j < end - middle; k++) {
      if (left[i] < right[j]) {
        array[k] = left[i++];
      } else {
        // 计数.
        // 因为左半部分是已经有序的了, 当前位置的元素比半部分的元素大，那么左半部分的其余元素也是比右半部分的元素大的.
        // 所以是 + (middle - i + 1)
        count += middle - i + 1;
        array[k] = right[j++];
      }
    }

    if (i < middle - start + 1) {
      for (int l = i; l < middle - start + 1; l++, k++) {
        array[k] = left[l];
      }
    }

    if (j < end - middle) {
      for (int l = j; l < end - middle; l++, k++) {
        array[k] = right[l];
      }
    }
  }

  /**
   * 三个升序数组中各选一个元素组成的三元组距离最小，距离定义为
   * max = min(a[i] - b[i], b[i] - c[i], a[i] - c[i])都是绝对值.
   * 1. 暴力.
   * 2. 分析.
   * ai < bj < ak
   * 此时的最大距离就是ak - ai = Di
   * 求ai, bj, c[k+1]的距离, 那么最大距离更新为c[k+1] - a[i], 此时结果比Di大.
   * 求ai, b[j+1], ck的距离， 那么b[j+1] < ck的时候, 结果还是D[i], 当b[j+1]>ck的时候, 此时结果比Di大.
   * 求a[i+1], bj, ck的距离, 此时的ck - a[i+1]是比Di小的.
   *
   * @return
   */
  public static int minDistanceOfTriple(int[] a1, int[] a2, int[] a3) {

    int i = 0, j = 0, k = 0;
    int minResult = Integer.MAX_VALUE;
    int temp = 0;
    while (true) {
      temp = Math.max(Math.abs(a1[i] - a2[j]), Math.max(Math.abs(a1[i] - a3[k]), Math.abs(a2[j] - a3[k])));
      if (temp < minResult) {
        minResult = temp;
      }
      int min = Math.min(a1[i], Math.min(a2[j], a3[k]));
      if (min == a1[i]) {
        i++;
        if (i == a1.length) {
          break;
        }
      }

      if (min == a2[j]) {
        j++;
        if (j == a2.length) {
          break;
        }
      }

      if (min == a3[k]) {
        k++;
        if (k == a3.length) {
          break;
        }
      }
    }

    return minResult;
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

    System.out.println("\n----absMin-----");
    int[] absMinArray = {-9, -5, -2, -1, 3, 8, 9};
    int absMinResult = absMin(absMinArray);
    System.out.println(absMinResult);

    System.out.println("\n----minDistanceOfTwoNumberInArray-----");
    int[] minDistanceArray = {4, 5, 6, 4, 7, 4, 6, 4, 7, 8, 5, 6, 4, 3, 10, 8};
    int minDisntaceResult = minDistanceOfTwoNumberInArray(minDistanceArray, 4, 8);
    System.out.println(minDisntaceResult);


    System.out.println("\n----firstOccrenceNumber----");
    int[] firstOccrenceArray = {3, 4, 5, 6, 5, 6, 7, 8, 9, 8};
    int firstOccrrenceResult = firstOccrrence(firstOccrenceArray, 5);
    System.out.println(firstOccrrenceResult);


    System.out.println("\n ---mergeSortedArray---");
    int[] mergeSortedArray = {1, 5, 6, 7, 9, 2, 4, 8, 10, 14};
    mergeSubSortedArray(mergeSortedArray, 5);
    System.out.println(Arrays.toString(mergeSortedArray));

    System.out.println("\n ----interResult----");
    int[] interArray1 = {0, 1, 1, 2, 2, 3, 3, 4, 5};
    int[] interArray2 = {1, 1, 2, 3, 3, 4, 4, 5, 6, 7, 8};
    List<Integer> interResult = interResult(interArray1, interArray2);
    System.out.println(interResult);

    System.out.println("\n----seuentialAdjacent-----");
    int[] adjacentArray = {1, 2, 3, 6, 7, 8, 0, 0, 9, 11, 0};
    numberSequentialAdjacent(adjacentArray);

    System.out.println("\n ----reverseCount----");
    int[] reverseCountArray = {1, 5, 3, 2, 6};
    int reverseCount = reverseCount(reverseCountArray);
    System.out.println(reverseCount);

    System.out.println("\n ----minDistanceTriple----");
    int[] tripleArray1 = {3, 4, 5, 7};
    int[] tripleArray2 = {10, 12, 14, 16, 17};
    int[] tripleArray3 = {20, 21, 23, 24, 30, 37};
    int tripleMinResult = minDistanceOfTriple(tripleArray1, tripleArray2, tripleArray3);
    System.out.println(tripleMinResult);
  }

}
