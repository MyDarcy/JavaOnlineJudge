package eopi.ch16_recursion;

/**
 * Author by darcy
 * Date on 17-8-27 上午9:56.
 * Description:
 *
 *  Euclidean algorithm for calculating the greatest common divisor (GCD) of two numbers
 *
 */
public class P1_GCD {
  /**
   *
   * 经典递归求解问题.
   *
   * Since with each recursive step one of the arguments is at least halved,
   * it means that the time complexity is O(logmax(x, y)).
   *
   * @param m
   * @param n
   * @return
   */
  public static int solution(int m, int n) {
    return n == 0 ? m : solution(n, m % n);
  }

  /**
   *
   * 取模事件开销较大,用－来代替.
   * gcd(m, n) = p; m = xp, n = yp, m -n = (x-y)p同样是p的倍数.
   * @param m
   * @param n
   * @return
   */
  public static int solution2(int m, int n) {
    return n == 0 ? m : m - n > n ? solution2(m - n, n) : solution2(n, m - n);
  }

  public static void main(String[] args) {
    System.out.println(solution(360, 155));
    System.out.println(solution2(360, 155));

  }
}
