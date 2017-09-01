package com.darcy.main.cleancode_v1_0_3.Math;

/**
 * Created by hzq19
 * Date on 2017/9/2 0:13.
 * Description:
 *
 * 回文数字.
 *
 * Determine whether an integer is a palindrome. Do this without extra space.
 *
 */
public class P19_PalindromeNumber {

  public static boolean solution(Integer integer) {

    if (integer < 0) {
      return false;
    }

    int div = 1;
    // 找到和integer同位数的 10...0数字.
    while (integer / div >= 10) {
      div *= 10;
    }

    while (integer != 0) {

      int l = integer / div;
      int r = integer % 10;
      if (l != r) {
        return false;
      }
      // 去掉刚放比较的两位.
      integer = (integer % div) / 10;
      // 因为已经去掉了两位，现在的integer是原来的近 1/100；
      div /= 100;
    }

    return true;
  }

  /**
   *
   * 逆序一个数.
   *
   * 然后判断一下两个数字是否相等。但是单纯的逆序可能导致溢出问题.
   *
   * @param integer
   * @return
   */
  public static int reverse(Integer integer) {
    int result = 0;
    while (integer != 0) {
      result = result * 10 + integer % 10;
      integer /= 10;
    }
    return result;
  }

  public static void main(String[] args) {
    Integer i = 100012;
    System.out.println(reverse(i));

    System.out.println(solution(10001));
    System.out.println(solution(123454321));

  }

}
