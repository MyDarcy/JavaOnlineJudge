package eopi.ch25_honors;

import java.util.Random;

/**
 * Author by darcy
 * Date on 17-10-20 下午5:34.
 * Description:
 *
 * 设计一个高效的算法, 不使用乘法, 除法和取模操作求GCD.
 *
 * Design an efficient algorithm for computing the GCD of two numbers without using
 * multiplication, division or the modulus operators.
 *
 * Hint: Use case analysis: both even; both odd; one even and one odd.
 *
 */
public class P1_GCD {

  /**
   * 求i,j的最大公约数.
   *
   * An example is illustrative. Suppose we were to compute the GCD of 24 and 300.
   * Instead of repeatedly subtracting 24 from 300, we can observe that since both are
   * even, the result is 2 X GCD(12,150). Dividing by 2 is a right shift by 1, so we do not
   * need a general division operation. Since 12 and 150 are both even, GCD(12,150) =
   * 2 X GCD(6,75). Since 75 is odd, the GCD of 6 and 75 is the same as the GCD of 3
   * and 75, since 2 cannot divide 75. The GCD of 3 and 75 is the GCD of 3 and 75- 3 =
   * 72. Repeatedly applying the same logic, GCD(3,72) = GCD(3,36) = GCD(3,18) =
   * GCD(3,9) = GCD(3,6) = GCD(3,3) = 3. This implies GCD(24,300) = 2 X 2 X 3 = 12.
   *
   * @param i
   * @param j
   * @return
   */
  public static int solution(int i, int j) {
    if (i == j) {
      return i;
    // 都是偶数.
    } else if ((i & 1) == 0 && (j & 1) == 0) {
      return solution(i >>> 1, j >>> 1) << 1;
      // j是偶数.
    } else if ((i & 1) != 0 && (j & 1) == 0) {
      return solution(i, j >>> 1);
      // i是偶数.
    } else if ((i & 1) == 0 && (j & 1) != 0) {
      return solution(i >>> 1, j);
      // 都是奇数并且i > j.
    } else if (i > j) {
      return solution(i - j, j);
    }
    // 都是奇数并且 j >= i
    return solution(i, j - i);
  }

  /**
   * @param i
   * @param j
   * @return
   */
  public static int solution2(int i, int j) {
    if (i < j) {
      return solution(j, i);
    }
    if (j == i) {
      return i;
    } else {
      return solution2(i - j, j);
    }
  }

  /**
   *
   * @param i
   * @param j
   * @return
   */
  public static int solution3(int i, int j) {
    if (j == 0) {
      return i;
    } else {
      return solution3(j, i % j);
    }
  }

  public static void main(String[] args) {
    System.out.println(solution(2 ^ 20, 2));
    System.out.println(solution2(2 ^ 20, 2));
    System.out.println(solution3(2 ^ 20, 2));

    Random random = new Random(31);
    int result = 0;
    for (int i = 0; i < 1000; i++) {
      int number1 = random.nextInt(2 ^ 5);
      int number2 = random.nextInt(2 ^ 5);
      int r1 = solution(number1, number2);
      int r2 = solution2(number1, number2);
      int r3 = solution3(number1, number2);
      /*result += (r1 == r2 ? r2 == r3 ? 1 : 0 : 0);*/
    }

    System.out.println(result);
  }

}
