package eopi.ch17_dp;

import java.util.Arrays;
import java.util.List;

/**
 * Author by darcy
 * Date on 17-8-25 下午6:40.
 * Description:
 *
 * 在捡硬币游戏中，给出一排硬币,选手只能从两端选择硬币, 那么先玩的选手如何能使得自己所获得的硬币总价值最大.
 *
 * Design an efficient algorithm for computing the maximum total value for the starting
 * player in the pick-up-coins game.
 *
 * Hint: Relate the best play for the first player to the best play for the second player.
 *
 *
 */
public class P11_Coins {

  /**
   * 简单的贪心算法;
   *
   * greedily selecting the maximum of the two end coins does not yield the best solution.
   *
   * The drawback of greedy selection is that it does not consider the opportunities
   * created for the second player.
   *
   * 贪心算法的问题在于它没有考虑到它的选择给随后的选手创造的机会.
   *
   * @param coins
   * @return
   */
  public static int bruteForceSolution(List<Integer> coins) {


    return 0;
  }

  /**
   * dp方法
   *
   * a, b -- 桌子上还有的硬币的index.
   * R(a, b) --  the maximum revenue a player can get when it is his turn to play
   * C[i] -- 每个硬币的面额大小.
   *  S(a,b) -- sum of the coins from positions a to b
   *
   * 递归解法:
   * 1. 当第一个选手选择a处的硬币的时候, 那么第二个选手的选择也是最优化的, 所以选手1的最后的最大值是
   * C[a] + S(a + 1, b) - R(a + 1, b)
   *
   * 2. 当第一个选择b处的硬币的时候，那么第二个选手的选择也是最优化的，所以选手1最后的最大值是
   * C[b] + S(a, b - 1) - R(a, b - 1)
   *
   * 此时选手1的最大化收益就是max(C[a] + S(a + 1, b) - R(a + 1, b), C[b] + S(a, b - 1) - R(a, b - 1))
   *
   *
   * 改进版本: 最小化对手的最大收益.无须计算S(a－１, b)的和
   * 因为第二个选手的选择也倾向于使得自己的利益最大化, 但是此时总的收益是固定的,因为就只有那么多硬币。
   * 所以结果等价于第二个选手的选择会使得第一个选手的再次选择的利益最小化.
   * 所以R(a, b) = max (
   *                    C[a] + min(R(a + 2, b), R(a + 1, b  1)),
   *                    C[b] + min(R(a, b - 2), R(a + 1, b - 1))) a <= b;
   *              0; otherwise;
   *
   *
   * Since the second player seeks to maximize his revenue, and the total
   * revenue is a constant, it is equivalent for the the second player to
   * move so as to minimize the first player's revenue.
   *
   *  O(n2) possible arguments for R(a,b), where n is the number of coins, and the
   *  time spent to compute R from previously computed values is O(1). Hence, R can
   *  be computed in O(n2) time
   *
   * @param coins
   * @return
   */
  public static int dpSolution(List<Integer> coins) {
    int[][] maxinumRevenueForAB = new int[coins.size()][coins.size()];
    return dpSolution(coins, 0, coins.size() - 1, maxinumRevenueForAB);
  }

  private static int dpSolution(List<Integer> coins, int a, int b, int[][] maxinumRevenueForAB) {
    // 已经没有硬币可以选了.
    if (a > b) {
      return 0;
    }

    if (maxinumRevenueForAB[a][b] == 0) {
      int maxRevenueA =
          coins.get(a)
              + Math.min(dpSolution(coins, a + 2, b, maxinumRevenueForAB),
                          dpSolution(coins, a + 1, b - 1, maxinumRevenueForAB));

      int maxRevenueB =
          coins.get(b)
              + Math.min(dpSolution(coins, a + 1, b - 1, maxinumRevenueForAB),
                          dpSolution(coins, a, b - 2, maxinumRevenueForAB));

      maxinumRevenueForAB[a][b] = Math.max(maxRevenueA, maxRevenueB);
    }

    return maxinumRevenueForAB[a][b];
  }

  public static void main(String[] args) {
    List<Integer> coins = Arrays.asList(25, 5, 10, 5, 10, 5, 10, 25, 1, 25, 1, 25, 1, 25, 5, 10);
    System.out.println(dpSolution(coins));
  }

}
