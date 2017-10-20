package eopi.ch25_honors;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;
import java.util.stream.Stream;

/**
 * Author by darcy
 * Date on 17-10-20 下午8:09.
 * Description:
 *
 * 股票股价序列,可以买卖k次.
 *
 * Write a program to compute the maximum profit that can be made by buying and
 * selling a share k times over a given day range. Your program takes k and an array of
 * daily stock prices as input.
 *
 *
 */
public class P3_BuyAndSellStocksKTimes {

  /**
   *
   * Define Bij to be the most money you can have if you must make j-1 buy-sell transactions
   * prior to i and buy at i. Define Sij to be the maximum profit achievable with jbuys and
   * sells with the jth sell taking place at i.
   *
   * Bij表示在第i次买入之前, 已经进行了j-1次的买-卖操作所能获取的最大值.
   * Sij表示在第i次买入时, 已经进行了j次买入和卖出所能获得的最大利润.
   *
   * @param A
   * @param k
   * @return
   */
  public static double solution(List<Double> A, int k) {
    List<Double> kSum = new ArrayList<>(k * 2);
    for (int i = 0; i < k * 2; i++) {
      kSum.add(Double.NEGATIVE_INFINITY);
    }

    for (int i = 0; i < A.size(); i++) {
      List<Double> preSum = new ArrayList<>(kSum);
      for (int j = 0, sign = -1; j < kSum.size() && j <= i; j++, sign *= -1) {
        double diff = sign * A.get(i) + (j == 0 ? 0 : preSum.get(j - 1));
        kSum.set(j, Math.max(diff, preSum.get(j)));
      }
    }

    // 最后一次卖所获得的收益.
    return kSum.get(kSum.size() - 1);
  }

  public static void main(String[] args) {
    List<Double> list = new LinkedList<>();
    Random random = new Random(31);
    for (int i = 0; i < 12; i++) {
      list.add((double) random.nextInt(31));
    }
    System.out.println(list);
    System.out.println(solution(list, 1));
    System.out.println(solution(list, 2));
    System.out.println(solution(list, 3));
    System.out.println(solution(list, 4));
    System.out.println(solution(list, 5));
    System.out.println(solution(list, 6));

  }

}
