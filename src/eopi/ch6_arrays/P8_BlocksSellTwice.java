package eopi.ch6_arrays;

/**
 * Author by darcy
 * Date on 17-8-29 下午9:37.
 * Description:
 *
 * 股票可以买卖2次.
 *
 * 第二次买必须是第一次卖之后.
 *
 * Write a program that computes the maximum profit that can be made by buying and
 * selling a share at most twice. The second buy must be made on another date after the
 * first sale.
 *
 * Hint:What do you need to know about the first i elements when processing the (i+ 1)th element?
 *
 */
public class P8_BlocksSellTwice {


  /**
   * 构造所有的buy-sell-buy-sell序列,然后求最大值.
   *
   * @param array
   * @return
   */
  public static int bruteForceSolution(int[] array) {

    return Integer.MAX_VALUE;
  }

  /**
   *
   * Suppose we record the best solution for A[0 : j], j between 1  and n — 1,
   * inclusive. Now we can do a reverse iteration, computing the best solution
   * for a single buy-and-sell for A[j : n — 1], j between 1 and n — 1.
   *
   *
   * For example, suppose the input array is (12,11,13,9,12,8,14,13,15). Then the
   * most profit that can be made with a single buy and sell by Day i (inclusive) is
   * F = (0,0, 2, 2,3,3,6,6,7). Working backwards, the most profit that can be made with
   * a single buy and sell on or after Day i is B = (7, 7,7,7,7, 7, 2, 2,0). To combine these
   * two, we compute M[i] = F[i-1] + B[i], where F[-l] is taken to be 0 (since the second
   * buy must happen strictly after the first sell). This yields M = (7, 7,7,9,9,10,5,8,6),
   * i.e., the maximum profit is 10
   *
   * 时间复杂度O(n), 空间复杂度O(1);
   *
   * @param array
   * @return
   */
  public static int solution(int[] array) {

    int[] iter = new int[array.length];
//    int[] reverseIter = new int[array.length];

    int maxProfit1 = 0;
    int minPrice1 = Integer.MAX_VALUE;
    // 迭代求出任意位置时卖出的最高价格.
    // 正向是之前最低时候买入, dayi卖出.
    for (int i = 0; i < array.length; i++) {
      maxProfit1 = Math.max(maxProfit1, array[i] - minPrice1);
      minPrice1 = Math.min(minPrice1, array[i]);
      if (i != 0) {
        iter[i] = maxProfit1;
      }
    }

    int maxPrice2 = 0;
    int resultProfit = 0;
    // 逆向求的是dayi买入，后面最高的价格卖出.
    for (int i = array.length - 1; i > 0; i--) {
      maxPrice2 = Math.max(maxPrice2, array[i]);
      // 记录从尾部到目前为止的最高价格. 则是当前买入, 然后最高价格卖出时候的profit.
      // 而此之前的买入卖出的profit不受本次的买入影响.
      // 因为如果当前位置是第一次卖出时候的位置, 它在第二个交易中也不起任何作用.
      /**
       * 如果当前位置的元素不是最后面元素中最小的, 那么max-它的值就不是最大的.
       * 而此时位置如果比前面所有元素都大, 那么到此处卖出的价格最高. 而后半部分的最高需要的买入也不在这个位置。
       * 而此时位置如果比前面最大元素小, 那么当前位置的获利最高也是前面已经求出的获利最高.所以需要的只是向后迭代.
       */
      resultProfit = Math.max(resultProfit, maxPrice2 - array[i] + iter[i - 1]);
    }

    return resultProfit;
  }

  public static void main(String[] args) {
    int[] array = {12, 11, 13, 9, 12, 8, 14, 13, 15};
    int result = solution(array);
    System.out.println(result);

  }

}
