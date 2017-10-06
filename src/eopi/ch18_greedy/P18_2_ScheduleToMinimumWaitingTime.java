package eopi.ch18_greedy;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * Author by darcy
 * Date on 17-10-6 上午10:24.
 * Description:
 *
 * 合理的进行任务调度,使得等待时间最小。
 * 每个数据库查询都是需要花费一定的时间, 而每个query花费的时间事先已知.数据库每次只能处理一条查询.
 * 一个query在处理之前的时间称为等待时间.
 *
 * Given service timesfor a set of queries,compute a schedulefor processing the queries
 * that minimizes the total waiting time. Return the minimum waiting time. For
 * example, if the service times are (2,5,1,3), if we schedule in the given order, the total
 * waiting time is0+(2)+(2+5)+(2+5+l) = 17. If however, weschedule queries in order
 * of decreasing service times, the total waiting time is 0 + (5) + (5 + 3) + (5 + 3 + 2) = 23.
 * As we will see, for this example, the minimum waiting time is 10
 *
 */
public class P18_2_ScheduleToMinimumWaitingTime {

  /**
   * 按照升序排列,那么每个任务的等待时间耗时最少.
   *
   * @param tasks
   * @return
   */
  public static int solution(List<Integer> tasks) {
    Collections.sort(tasks);

    int totalWaitingTime = 0;
    for (int i = 0; i < tasks.size(); i++) {
      int numberRemainingQueries = tasks.size() - (i + 1);
      totalWaitingTime += numberRemainingQueries * tasks.get(i);
    }

    return totalWaitingTime;
  }

  public static void main(String[] args) {
    List<Integer> tasks = Arrays.asList(2, 5, 1, 3);
    int totalWaitingTime = solution(tasks);
    System.out.println(totalWaitingTime);
  }

}
