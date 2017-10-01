package eopi.ch14_sorting;

import java.util.*;

/**
 * Author by darcy
 * Date on 17-9-30 上午11:09.
 * Description:
 * <p>
 * 计算所有interval的union集合.
 * <p>
 * Design an algorithm that takes as input a set of intervals, and outputs their union
 * expressed as a set of disjoint intervals.
 * <p>
 * Hint: Do a case analysis.
 */
public class P14_6_ComputeTheUnionOfIntervals {

  public static class Interval implements Comparable<Interval> {
    public EndPoint left;
    public EndPoint right;

    public Interval(EndPoint left, EndPoint right) {
      this.left = left;
      this.right = right;
    }

    @Override
    public String toString() {
      StringBuilder sb = new StringBuilder();
      if (left.isClosed) {
        sb.append("[");
      } else {
        sb.append("(");
      }
      sb.append(left.val + ", ");
      sb.append(right.val);

      if (right.isClosed) {
        sb.append("]");
      } else {
        sb.append(")");
      }

      return sb.toString();
    }

    /**
     * 使用Objects工具类来计算hashCode.
     *
     * @return
     */
    @Override
    public int hashCode() {
      return Objects.hash(left.val, left.isClosed);
    }

    /**
     * 不用考虑右边界值.
     *
     * @param obj
     * @return
     */
    @Override
    public boolean equals(Object obj) {
      if (obj == null || !(obj instanceof Interval)) {
        return false;
      }

      if (this == obj) {
        return true;
      }

      Interval that = (Interval) obj;
      return left.val == that.left.val && left.isClosed == that.left.isClosed;
    }

    /**
     * 按照左边界进行排序.
     * 如果左边界相等,那么闭区间在前,开区间在后.
     *
     * @param that
     * @return
     */
    @Override
    public int compareTo(Interval that) {
      if (Integer.compare(left.val, that.left.val) != 0) {
        return left.val - that.left.val;
      }

      return left.isClosed && !that.left.isClosed
          ? -1 : !left.isClosed && that.left.isClosed ? 1 : 0;
    }
  }

  public static class EndPoint {
    /**
     * isClosed表示区间是否是闭区间.
     * val表示一维坐标系上的点.
     */
    public boolean isClosed;
    public int val;

    public EndPoint(boolean isClosed, int val) {
      this.isClosed = isClosed;
      this.val = val;
    }
  }

  /**
   * 思路: 首先对intervalList进行排序.然后遍历排序后的interval列表, 根据开闭规则进行区间的合并.
   * 具体的过程代码几乎是自解释的, 所以不细说了.
   * <p>
   * As we iterate through the sorted array of intervals, we have the following cases:
   * • The interval most recently added to the result does not intersect the current
   * interval, nor does its right endpoint equal the left endpoint of the current
   * interval. In this case, we simply add the current interval to the end of the result
   * array as a new interval.
   * <p>
   * • The interval most recently added to the result intersects the current interval. In
   * this case, we update the most recently added interval to the union of it with the
   * current interval.
   * <p>
   * • The interval most recently added to the result has its right endpoint equal to
   * the left endpoint of the current interval, and one (or both) of these endpoints
   * are closed. In this case too, we update the most recently added interval to the
   * union of it with the current interval.
   *
   * @param intervals
   * @return
   */
  public static List<Interval> solution(List<Interval> intervals) {

    if (intervals.isEmpty()) {
      return Collections.EMPTY_LIST;
    }

    // 首先区间按照规则进行排序.
    Collections.sort(intervals);

    Interval current = intervals.get(0);
    List<Interval> result = new ArrayList<>();
    for (int i = 1; i < intervals.size(); i++) {

      // 遍历的区间和当前的区间重叠.
      if (intervals.get(i).left.val < current.right.val ||
          (intervals.get(i).left.val == current.right.val
              // 注意有一个是闭的就可以合并.
              && (intervals.get(i).left.isClosed || current.right.isClosed))) {

        // 然后更新右边的区间.
        if (intervals.get(i).right.val > current.right.val ||
            (intervals.get(i).right.val == current.right.val && intervals.get(i).right.isClosed)) {
          current.right = intervals.get(i).right;
          // current.right.isClosed = intervals.get(i).right.isClosed; // 可有可无.
        }
      } else {
        result.add(current);
        current = intervals.get(i);
      }
    }
    result.add(current);
    return result;
  }

  public static void main(String[] args) {
    Interval i1 = new Interval(new EndPoint(false, 0), new EndPoint(false, 3));
    Interval i2 = new Interval(new EndPoint(true, 1), new EndPoint(true, 1));
    Interval i3 = new Interval(new EndPoint(true, 2), new EndPoint(true, 4));
    Interval i4 = new Interval(new EndPoint(true, 3), new EndPoint(false, 4));
    Interval i5 = new Interval(new EndPoint(true, 5), new EndPoint(false, 7));
    Interval i6 = new Interval(new EndPoint(true, 7), new EndPoint(false, 8));
    Interval i7 = new Interval(new EndPoint(true, 8), new EndPoint(false, 11));
    Interval i8 = new Interval(new EndPoint(false, 9), new EndPoint(true, 11));
    Interval i9 = new Interval(new EndPoint(true, 12), new EndPoint(true, 14));
    Interval i10 = new Interval(new EndPoint(false, 12), new EndPoint(true, 16));
    Interval i11 = new Interval(new EndPoint(false, 13), new EndPoint(false, 15));
    Interval i12 = new Interval(new EndPoint(false, 16), new EndPoint(false, 17));

    List<Interval> intervals = Arrays.asList(i1, i2, i3, i4, i5, i6, i7, i8, i9, i10, i11, i12);

    List<Interval> result = solution(intervals);

    for (int i = 0; i < result.size(); i++) {
      System.out.println(result.get(i));
    }
  }
}
