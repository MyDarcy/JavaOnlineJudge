package com.darcy.main.cleancode_v1_0_3.Bit;

/**
 * Author by darcy
 * Date on 17-9-3 下午7:26.
 * Description:
 * 给定一组数字,除了一个数字出现一次外,其他数字都出现了三次,那么找出这个只出现一次的数字.
 *
 * Given an array of integers, every element appears three times except for one.
 * Find that single one.
 *
 * Note:
 * Your algorithm should have a linear runtime complexity. Could you implement it without
 * using extra memory
 */
public class P34_SingleNumberII {

  /**
   * 1. 排序. 然后找一遍即可.
   * 2. map方法.
   */


  /**
   * 统计第 i bit出现的次数, 该位置出现的次数要么是3k次,要么是3k+1次.
   *
   * 总的事件复杂度O(n), 不过是系数是32。 空间复杂度常量.
   *
   *
   * @param array
   * @return
   */
  public static int solution(int[] array) {
    int[] count = new int[32];
    int result = 0;
    for (int i = 0; i < 32; i++) {
      for (int j = 0; j < array.length; j++) {
        // 统计所有数字的bit i出现过多少次.
        if (((array[j] >> i) & 1) > 0) {
          count[i]++;
        }
      }
      // 出现过一次那个如果在该位出现过,那么总的出现次数是 3k + 1.
      // 否则就是3k次.
      // 然后该位代表的具体值.
      result |= (count[i] % 3) << i;
    }

    return result;
  }

  /**
   * 1. ones as a bitmask to represent the ith bit had appeared once.
   * 2. twos as a bitmask to represent the ith bit had appeared twice.
   * 3. threes as a bitmask to represent the ith bit had appeared three times.
   *
   * When the ith bit had appeared for the third time, clear the ith bit of both ones and twos to 0.
   * The final answer will be the value of ones.
   *
   *
   *
   * @param A
   * @param n
   * @return
   */
  public static int singleNumber(int A[], int n) {
    int ones = 0, twos = 0, threes = 0;
    for (int i = 0; i < n; i++) {
      // ones & A[i] --> 如果特定的位再次出现, 那么该位还是为1;
      twos |= ones & A[i];
      ones ^= A[i]; // 出现了一次(三次)的都会统计在ones.
      threes = ones & twos; // 出现了三次.
      ones &= ~threes; // 真的只出现1次.
      twos &= ~threes; // 真的只出现2次.
    }
    return ones;
  }

  public static void main(String[] args) {
    int[] array = {100101, 222, 222, 2000, 2000, 2000, 222, 1111, 1111, 1111};
    int result = solution(array);
    System.out.println(result);

    int result2 = singleNumber(array, array.length);
    System.out.println(result2);
  }



}
