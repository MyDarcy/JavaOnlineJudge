package eopi.ch12_search;

/**
 * Author by darcy
 * Date on 17-9-24 下午8:16.
 * Description:
 * <p>
 * Write a program which takes a nonnegative integer and returns the largest integer
 * whose square is less than or equal to the given integer. For example, if the input is
 * 16, return 4; if the input is 300, return 17, since 17^2 = 289 < 300 and 18^2 = 324 > 300.
 */
public class P12_4_ComputeSquareRoot {

  /**
   * if x^2 < k, 那么比x小的都不可能是.
   * if x^2 > k, 那么比x大的都不可能是.
   * <p>
   * 而二叉搜索能极大的缩小搜索区间.
   *
   * @param k
   * @return
   */
  public static int squareRoot(int k) {
    long left = 0, right = k;

    // Candidate interval [left, right] where everything before left has
    // square <= k, and everything after right has square > k.
    while (left <= right) {
      long mid = left + ((right - left) / 2);
      long midSquared = mid * mid;
      if (midSquared <= k) {
        left = mid + 1;
      } else {
        right = mid - 1;
      }
    }
    return (int) left - 1;
  }

  public static void main(String[] args) {
    int result = squareRoot(99898111);
    System.out.println(result + " \t" + result * result);
    System.out.println(Math.pow(result + 1, 2));

  }

}
