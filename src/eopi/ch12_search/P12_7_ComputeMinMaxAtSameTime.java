package eopi.ch12_search;

import java.util.Arrays;
import java.util.List;

/**
 * Author by darcy
 * Date on 17-9-24 下午8:53.
 * Description:
 * <p>
 * 同时计算min和max的最小值.
 */
public class P12_7_ComputeMinMaxAtSameTime {

  private static class MinMax {
    public Integer min;
    public Integer max;

    public MinMax(Integer min, Integer max) {
      this.min = min;
      this.max = max;
    }

    public static MinMax minMax(Integer a, Integer b) {
      return Integer.compare(b, a) < 0 ? new MinMax(b, a) : new MinMax(a, b);
    }
  }

  /**
   * 时间复杂度O(N), 空间复杂度O(1); 这里简单起见, new了很多临时对象.
   * 但是其实可以set值.
   *
   * @param list
   * @return
   */
  public static MinMax findMinMax(List<Integer> list) {
    if (list.size() <= 1) {
      return new MinMax(list.get(0), list.get(0));
    }

    MinMax result = new MinMax(list.get(0), list.get(0));
    for (int i = 1; i + 1 < list.size(); i += 2) {
      MinMax temp = MinMax.minMax(list.get(i), list.get(i + 1));
      result = new MinMax(Math.min(result.min, temp.min), Math.max(result.max, temp.max));
    }

    // 奇数个.
    if ((list.size() & 1) == 1) {
      result = new MinMax(Math.min(result.min, list.get(list.size() - 1)),
          Math.max(result.max, list.get(list.size() - 1)));
    }

    return result;
  }

  public static void main(String[] args) {
    List<Integer> list = Arrays.asList(3, 2, 5, 1, 2, 4);
    MinMax result = findMinMax(list);
    System.out.println(result.max + "\t" + result.min);
  }

}
