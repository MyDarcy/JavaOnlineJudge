package eopi.ch14_sorting;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/**
 * Author by darcy
 * Date on 17-9-30 上午9:18.
 * Description:
 * <p>
 * 给定一组区间, 合并这些区间. 最终得到的区间的左端点互不相交.
 * <p>
 * Write a program which takes as input an array of disjoint closed intervals with integer
 * endpoints, sorted by increasing order of left endpoint, and an interval to be added,
 * and returns the union of the intervals in the array and the added interval. Your result
 * should be expressed as a union of disjoint intervals sorted by left endpoint.
 * <p>
 * Hint: What is the union of two closed intervals?
 * <p>
 * 例子:
 * if the initial set of intervals is [-4,-1],[0, 2], [3,6],[7,9], [11,12], [14,17],
 * and the added interval is [1,8], the result is [-4,-1],[0, 9], [11,12],[14,17].
 */
public class P14_5_MergeInterval {

  public static class Interval {
    public int left;
    public int right;

    public Interval(int left, int right) {
      this.left = left;
      this.right = right;
    }

    @Override
    public String toString() {
      return "Interval{" +
          "left=" + left +
          ", right=" + right +
          '}';
    }
  }

  /**
   * Solution: A brute-force approach is to find the smallest left endpoint and the largest
   * right endpoint in the set of intervals in the array and the added interval. We then
   * form the result by testing every integer between these two values for membership in
   * an interval. The time complexity is0(Dn),where D is the difference between the two
   * extreme values and n is the number of intervals. Note that D may be much larger
   *
   * @param list
   * @return
   */
  public static List<Interval> bruteForceSolution(List<Interval> list) {

    return null;
  }

  /**
   * 思路: 因为区间集合已经是按照left排序的(如果不是),那么先List.sort()一下也可以. 并且此中的interval完全是无重叠的.
   * 那么遍历原区间集合中的区间直到找到和待加入的区间存在重合的区间.
   * <p>
   * 继续遍历区间集合, 对于和待加入的区间重叠的区间, 更新待加入的interval的left = min(left1, left2).
   * 更新待加入的interval的 right = max(right1, right2)
   * <p>
   * Specifically, processing an interval in the array takes place in three stages:
   * (1.) First, we iterate through intervals which appear completely before the interval
   * to be added—all these intervals are added directly to the result.
   * <p>
   * (2.) As soon as we encounter an interval that intersects the interval to be added,
   * we compute its union with the interval to be added. This union is itself an
   * interval. We iterate through subsequent intervals, as long as they intersect with
   * the union we are forming. This single union is added to the result.
   * <p>
   * (3.) Finally, we iterate through the remaining intervals. Because the array was
   * originally sorted, none of these can intersect with the interval to be added, so
   * we add these intervals to the result.
   *
   * @param originalIntervals
   * @param tobeAddInterval
   * @return
   */
  public static List<Interval> solution(List<Interval> originalIntervals, Interval tobeAddInterval) {
    List<Interval> result = new LinkedList<>();
    int i = 0;

    // 直到要被加入的interval和原区间集合中的元素重合overlapping.
    while (i < originalIntervals.size() && tobeAddInterval.left > originalIntervals.get(i).right) {
      result.add(originalIntervals.get(i++));
    }

    // 右边和左边又冲突了.
    while (i < originalIntervals.size() && tobeAddInterval.right > originalIntervals.get(i).left) {
      tobeAddInterval = new Interval(
          Math.min(tobeAddInterval.left, originalIntervals.get(i).left),
          Math.max(tobeAddInterval.right, originalIntervals.get(i).right)
      );
      i++;
    }

    result.add(tobeAddInterval);
    result.addAll(originalIntervals.subList(i, originalIntervals.size()));

    return result;
  }

  public static void main(String[] args) {
    List<Interval> intervals = Arrays.asList(
        new Interval(-4, -1),
        new Interval(0, 2),
        new Interval(3, 6),
        new Interval(7, 9),
        new Interval(11, 12),
        new Interval(14, 17)
    );
    /*
    Interval{left=-4, right=-1}
    Interval{left=0, right=9}
    Interval{left=11, right=12}
    Interval{left=14, right=17}
     */

    Interval toBeAdd = new Interval(1, 8);
    List<Interval> result = solution(intervals, toBeAdd);
    for (Interval i : result) {
      System.out.println(i);
    }
  }
}
