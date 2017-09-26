package eopi.ch13_hash;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Author by darcy
 * Date on 17-9-26 下午6:46.
 * Description:
 *
 *
 *
 */
public class P13_10_LongestContainedRange {

  /**
   * 有个巧妙的地方在于:
   * 从set中取出一个数字, 然后往两遍拓展.看拓展的元素是否在待处理的集合中, 在->删除该元素并继续处理.一旦不在,
   * 本次处理就结束了.重点在于:一旦上一次拓展结束, 其删除的元素一定不会在下一次拓展的范围内.因为能连续的话上一
   * 次已经能够拓展了. 所以每轮拓展都能取得包含当前元素的最大范围.
   *
   * @param list
   * @return
   */
  public static int solution(List<Integer> list) {
    Set<Integer> unprocessed = new HashSet<>(list);

    int maxIntervalSize = 0;
    while (!unprocessed.isEmpty()) {
      Integer toBeProcessed = unprocessed.iterator().next();
      unprocessed.remove(toBeProcessed);

      // 左拓展.
      int lower = toBeProcessed - 1;
      while (unprocessed.contains(lower)) {
        unprocessed.remove(lower);
        lower--;
      }

      // 右拓展.
      int high = toBeProcessed + 1;
      while (unprocessed.contains(high)) {
        unprocessed.remove(high);
        high++;
      }

      maxIntervalSize = Math.max(maxIntervalSize, high - lower - 1);
    }
    return maxIntervalSize;
  }

  public static void main(String[] args) {
    List<Integer> list = Arrays.asList(10, 5, 3, 11, 6, 100, 4);
    int result = solution(list);
    System.out.println(result);
  }

}
