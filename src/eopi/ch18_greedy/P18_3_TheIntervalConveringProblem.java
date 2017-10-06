package eopi.ch18_greedy;

import java.util.*;

/**
 * Author by darcy
 * Date on 17-10-6 上午10:37.
 * Description:
 *
 * 给定一个任务序列.每个任务都抽象为一个时间区间[start, end]
 * 如何最小化检查次数. 使得所有任务都能被检查到并且任务被检查时候是运行态.
 *
 * Consider a foreman responsible for a number of tasks on the factory floor. Each task
 * starts at a fixed time and ends at a fixed time. The foreman wants to visit the floor to
 * check on the tasks. Your job is to help him minimize the number of visits he makes.
 * In each visit, he can check on all the tasks taking place at the time of the visit. A visit
 * takes place at a fixed time, and he can only check on tasks taking place at exactly that
 * time. For example, if there are tasks at times [0,3],[2,6],[3,4],[6,9], then visit times
 * 0, 2,3,6 cover all tasks. A smaller set of visit times that also cover all tasks is 3,6. In
 * the abstract, you are to solve the following problem.
 *
 *
 * You are given a set of closed intervals. Design an efficient algorithm for finding a
 * minimum sized set of numbers that covers all the intervals.
 *
 * Hint: Think about extremal points.
 */
public class P18_3_TheIntervalConveringProblem {

  public static class Interval {
    int left;
    int right;

    public Interval(int left, int right) {
      this.left = left;
      this.right = right;
    }
  }

  /**
   * 每次都通过右端点尽可能的覆盖多的区间.
   * 时间复杂度O(NlogN)
   *
   * Sort all the intervals,
   * comparing on right endpoints. Select the first interval's right endpoint. Iterate
   * through the intervals, looking for the first one not covered by this right endpoint. As
   * soon as such an interval is found, select its right endpoint and continue the iteration.
   *
   * @param tasks
   * @return
   */
  public static List<Integer> solution(List<Interval> tasks) {
    if (tasks.isEmpty()) {
      return Collections.EMPTY_LIST;
    }

    Collections.sort(tasks, new Comparator<Interval>() {
      @Override
      public int compare(Interval o1, Interval o2) {
        return Integer.compare(o1.right, o2.right);
      }
    });

    List<Integer> visits = new ArrayList<>();
    Integer lastVisitTime = tasks.get(0).right;
    visits.add(lastVisitTime);
    for (Interval interval : tasks) {

      // 找第一个当前访问时间不能覆盖的区间(即该区间的左端点>当前访问时间)
      // 然后更新当前访问时间.
      if (interval.left > lastVisitTime) {
        lastVisitTime = interval.right;
        visits.add(lastVisitTime);
      }
    }
    return visits;
  }

  public static void main(String[] args) {
    List<Interval> tasks = Arrays.asList(new Interval(1, 2),
        new Interval(2, 3),
        new Interval(3, 4),
        new Interval(2, 3),
        new Interval(3, 4),
        new Interval(4, 5));

    List<Integer> result = solution(tasks);
    System.out.println(result);
  }

}
