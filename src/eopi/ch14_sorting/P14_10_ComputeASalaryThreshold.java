package eopi.ch14_sorting;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * Author by darcy
 * Date on 17-10-1 下午4:03.
 * Description:
 * <p>
 * 计算薪水的极限值: 公司为了节省开销, 设定了今年总的支出, 需要求出一个合理的阈值, 使得去年工资高于阈值的都只发阈值那么多工资,
 * 去年工资小于阈值的都是照旧发放.
 * 求阈值.
 * <p>
 * You are working in the finance office for ABC corporation. ABC needs to cut payroll
 * expenses to a specified target. The chief executive officer wants to do this by putting
 * a cap on last year's salaries. Every employee who earned more than the cap last year
 * will be paid the cap this year; employees who earned no more than the cap will see
 * no change in their salary.
 * <p>
 * For example, if there were five employees with salaries last year were
 * $90,$30,$100,$40, and $20, and the target payroll this year is $210, then 60 is a
 * suitable salary cap, since 60 + 30 + 60 + 40 + 20 = 210.
 * Design an algorithm for computing the salary cap, given existing salaries and the
 * target payroll.
 * <p>
 * Hint: How does the payroll vary with the cap?
 */
public class P14_10_ComputeASalaryThreshold {

  /**
   * @param lastYear 去年的薪资情况
   * @param target   今年的支出目标
   * @return 待求出的阈值
   */
  public static int solution(List<Integer> lastYear, int target) {
    Collections.sort(lastYear);
    double sum = 0;
    for (int i = 0; i < lastYear.size(); i++) {
      int adjustedSum = lastYear.get(i) * (lastYear.size() - i);
      if (sum + adjustedSum >= target) {
        return (int) ((target - sum) / (lastYear.size() - i));
      }

      sum += lastYear.get(i);
    }

    return Integer.MIN_VALUE;
  }

  public static void main(String[] args) {
    List<Integer> list = Arrays.asList(20, 30, 40, 90, 100);
    int result = solution(list, 210);
    System.out.println(result);

  }

}
