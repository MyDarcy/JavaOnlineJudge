package eopi.ch18_greedy;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * Author by darcy
 * Date on 17-10-6 上午10:12.
 * Description:
 *
 * 计算最优的任务分配.
 * 每个人需要分配两个任务且只能分配两个任务. 而任务之间是相互独立的,
 * 计算如何分配使得任务的总花费时间最少.
 *
 * Design an algorithm that takes as input a set of tasks and returns an optimum assignment.
 * Hint: What additional task should be assigned to the worker who is assigned the longest task?
 *
 */
public class P18_1_ComputeAnOptimumAssignmentsOfTasks {

  public static class PairedTasks{
    public Integer task1;
    public Integer task2;

    public PairedTasks(Integer task1, Integer task2) {
      this.task1 = task1;
      this.task2 = task2;
    }

    @Override
    public String toString() {
      return "PairedTasks{" +
          "task1=" + task1 +
          ", task2=" + task2 +
          '}';
    }
  }

  /**
   * 要使的总的花费时间最少,那么就需要将最长耗时的任务和最短耗时的任务搭配.
   * 时间复杂度O(NlogN)
   *
   * @param tasks
   * @return
   */
  public static List<PairedTasks> solution(List<Integer> tasks) {
    Collections.sort(tasks);
    List<PairedTasks> result = new ArrayList<>();
    for (int i = 0, j = tasks.size() - 1; i < j; i++, j--) {
      result.add(new PairedTasks(tasks.get(i), tasks.get(j)));
    }

    return result;
  }

  public static void main(String[] args) {
    List<Integer> tasks = Arrays.asList(5, 2, 1, 6, 4, 4);
    List<PairedTasks> result = solution(tasks);
    for (PairedTasks task : result) {
      System.out.println(task);
    }
  }

}
