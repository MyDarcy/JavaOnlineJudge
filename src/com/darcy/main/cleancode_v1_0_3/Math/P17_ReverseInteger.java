package com.darcy.main.cleancode_v1_0_3.Math;

/**
 * Author by darcy
 * Date on 17-9-1 下午9:27.
 * Description:
 * <p>
 * Reverse digits of an integer. For example: x = 123, return 321
 * <p>
 * Q: What about negative integers?
 * A: For input x = –123, you should return –321.
 * <p>
 * Q: What if the integer’s last digit is 0? For example, x = 10, 100, …
 * A: Ignore the leading 0 digits of the reversed integer. 10 and 100 are both reversed as 1.
 * <p>
 * Q: What if the reversed integer overflows? For example, input x = 1000000003.
 * A: In this case, your function should return 0.
 */
public class P17_ReverseInteger {

  public static final int MAX_DIV_10 = Integer.MAX_VALUE / 10;

  public static Integer solution(Integer number) {
    int result = 0;
    while (number != 0) {
      if (Math.abs(result) > MAX_DIV_10) {
        return 0;
      }
      // 不需要显式的处理 + - 号.
      result = result * 10 + number % 10; // 符号在取模的时候能得到保持.
      number /= 10;
    }

    return result;
  }

  public static void main(String[] args) {
    System.out.println(Integer.MAX_VALUE);
    System.out.println(-10 % 4);

    System.out.println(solution(-10202020));

    System.out.println(solution(876543219));
  }
}
