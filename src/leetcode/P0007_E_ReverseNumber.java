package leetcode;

/**
 * Given a 32-bit signed integer, reverse digits of an integer.
 *
 * Example 1:
 *
 * Input: 123
 * Output: 321
 * Example 2:
 *
 * Input: -123
 * Output: -321
 * Example 3:
 *
 * Input: 120
 * Output: 21
 *
 * Note:
 * Assume we are dealing with an environment which could only store integers within the 32-bit signed integer range: [−2^31,  2^31 − 1].
 * For the purpose of this problem, assume that your function returns 0 when the reversed integer overflows.
 */
public class P0007_E_ReverseNumber {


  /**
   * 需要考虑是否溢出:
   * 这里溢出的考虑比较巧妙
   * @param x
   * @return
   */
  public int reverse(int x) {
    int result = 0;
    while (x != 0) {
      int remain = x % 10;
      x = x  / 10;
      int temp = result * 10 + remain;
      if ((temp - remain) / 10 != result) { // 检查是否溢出;
        return 0;
      }
      result = temp;
    }

    return result;
  }

  /**
   * If temp=rev⋅10+pop causes overflow, then it must be that rev≥ INTMAX / 10;
   * If rev> INTMAX / 10, then temp=rev⋅10+pop is guaranteed to overflow.
   * If rev== INTMAX / 10 , then temp=rev⋅10+pop will overflow if and only if pop>7
   * @param x
   * @return
   */
  public int reverse2(int x) {
    int rev = 0;
    while (x != 0) {
      int pop = x % 10;
      x /= 10;
      if (rev > Integer.MAX_VALUE/10 || (rev == Integer.MAX_VALUE / 10 && pop > 7)) return 0;
      if (rev < Integer.MIN_VALUE/10 || (rev == Integer.MIN_VALUE / 10 && pop < -8)) return 0;
      rev = rev * 10 + pop;
    }
    return rev;
  }

  public static void main(String[] args) {
    P0007_E_ReverseNumber instance = new P0007_E_ReverseNumber();
    System.out.println(instance.reverse(Integer.MIN_VALUE / 10));
    System.out.println(instance.reverse(Integer.MAX_VALUE  / 10));
    System.out.println(instance.reverse(100));
    System.out.println(instance.reverse(-12345));
    System.out.println(instance.reverse(12345));
  }

}
