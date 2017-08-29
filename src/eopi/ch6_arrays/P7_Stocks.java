package eopi.ch6_arrays;

/**
 * Author by darcy
 * Date on 17-8-29 下午9:28.
 * Description:
 *
 * 给定一个价格序列, 什么时候买入卖出获利最大.
 *
 * Write a program that takes an array denoting the daily stock price, and returns the
 * maximum profit that could be made by buying and then selling one share of that
 * stock.
 *
 * Hint: Identifying the minimum and maximum is not enough since the minimum may appear
 * after the maximum height. Focus on valid differences.
 */
public class P7_Stocks {


  /**
   *
   * @param array
   * @return
   */
  public static int solution(int[] array) {
    int maxProfit = 0;
    int minPrice = Integer.MAX_VALUE;
    for (int a : array) {
      maxProfit = Math.max(maxProfit, a - minPrice);
      minPrice = Math.min(minPrice, a);
    }
    return maxProfit;
  }

  public static void main(String[] args) {
    int[] array = {310,315, 275, 295, 260, 270, 290, 230, 255, 250};
    int maxProfit = solution(array);
    System.out.println(maxProfit);

  }

}
