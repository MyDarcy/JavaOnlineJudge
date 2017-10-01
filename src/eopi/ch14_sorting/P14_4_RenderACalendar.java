package eopi.ch14_sorting;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * Author by darcy
 * Date on 17-9-29 下午9:26.
 * Description:
 * <p>
 * 给定一组任务, 都有发生的起始时间和结束时间,
 * 计算同时处于发生状态的任务最多有多少个.
 * <p>
 * Write a program that takes a set of events, and determines the maximum number of
 * events that take place concurrently.
 * <p>
 * Hint: Focus on endpoints.
 */
public class P14_4_RenderACalendar {

  public static class Event implements Comparable<Event> {
    public int start;
    public int end;

    public Event(int start, int end) {
      this.start = start;
      this.end = end;
    }

    @Override
    public int compareTo(Event event) {
      int r1 = Integer.compare(start, event.start);
      if (r1 != 0) {
        return r1;
      }

      return Integer.compare(end, event.end);
    }

    @Override
    public String toString() {
      return "Event{" +
          "start=" + start +
          ", end=" + end +
          '}';
    }
  }

  public static class EndPoint implements Comparable<EndPoint> {

    public int time;
    public boolean isStart;

    public EndPoint(int time, boolean isStart) {
      this.time = time;
      this.isStart = isStart;
    }

    @Override
    public String toString() {
      return "EndPoint{" +
          "time=" + time +
          ", isStart=" + isStart +
          '}';
    }

    @Override
    public int compareTo(EndPoint endPoint) {
      if (time != endPoint.time) {
        return Integer.compare(time, endPoint.time);
      }

      // 先开始 -> 后开始: 从小到大排序.
      // If times are equal, an endpoint that starts an interval comes first.
      return isStart && !endPoint.isStart ? -1 : !isStart && endPoint.isStart ? 1 : 0;
    }
  }


  /**
   * 对于每一个端节点,计算包含该端节点的event个数.
   * n个interval, 那么端节点的个数是2N.
   * <p>
   * For each endpoint, compute the number of events that contain it. The maximum number
   * of concurrent events is the maximum of this quantity over all endpoints. If there are
   * n intervals, the total number of endpoints is 2n. Computing the number of events
   * containing an endpoint takes O(n) time, since checking whether an interval contains
   * a point takes O(1) time. Therefore, the overall time complexity is O(2n X n) = O(n^2).
   *
   * @param events
   * @return
   */
  public static int bruteSolution(List<Event> events) {

    return 0;
  }


  /**
   * 首先对排序所有的端节点(增序). 先开始的在前面. 如果运行事件相同并且同时开始,那么哪一个在前面无所谓.
   * 按照这种顺序排完序以后, 遇到一个端节点是一段时间的起始节点,那么+1, 一个端节点是一段时间的结束节点, 那么
   * -1. 每次操作后的max进行比较更新.
   * <p>
   * Intuitively, we can improve the run time by sorting the set of all the endpoints in
   * ascending order. (If two endpoints have equal times, and one is a start time and the
   * other is an end time, the one corresponding to a start time comes first. If both are
   * start or finish times, we break ties arbitrarily.)
   * <p>
   * Now as we proceed through endpoints we can incrementally track the number of events
   * taking place at that endpoint using a counter. For each endpoint that is the start
   * of an interval, we increment the counter by 1, and for each endpoint that is the end
   * of an interval, we decrement the counter by 1. The maximum value attained by the counter
   * is maximum number of overlapping intervals.
   * <p>
   * 复杂度分析:
   * Sorting the endpoint array takes O(nlog n) time; iterating through the sorted array
   * takes O(n) time, yielding an O(nlog n) time complexity The space complexity is O(n),
   * which is the size of the endpoint array.
   *
   * @param events
   * @return
   */
  public static int solution(List<Event> events) {
    List<EndPoint> endPoints = new ArrayList<>();
    for (Event event : events) {
      endPoints.add(new EndPoint(event.start, true));
      endPoints.add(new EndPoint(event.end, false));
    }

    Collections.sort(endPoints);
    for (EndPoint e : endPoints) {
      System.out.println(e);
    }

    int maxNumSimultaneousEvents = 0, numSimultaneousEvents = 0;
    for (EndPoint endPoint : endPoints) {
      if (endPoint.isStart) {
        ++numSimultaneousEvents;
        maxNumSimultaneousEvents = Math.max(numSimultaneousEvents, maxNumSimultaneousEvents);
      } else {
        --numSimultaneousEvents;
      }
    }

    return maxNumSimultaneousEvents;
  }

  public static void main(String[] args) {
    Event e1 = new Event(1, 5);
    Event e2 = new Event(6, 10);
    Event e3 = new Event(11, 13);
    Event e4 = new Event(14, 15);
    Event e5 = new Event(2, 7);
    Event e6 = new Event(8, 9);
    Event e7 = new Event(12, 15);
    Event e8 = new Event(4, 5);
    Event e9 = new Event(9, 17);

    List<Event> events = Arrays.asList(e1, e2, e3, e4, e5, e6, e7, e8, e9);
    Collections.sort(events);
    for (Event event : events) {
      System.out.println(event);
    }
    System.out.println();

    int result = solution(events);
    System.out.println(result);
  }
}

/*
Event{start=1, end=5}
Event{start=2, end=7}
Event{start=4, end=5}
Event{start=6, end=10}
Event{start=8, end=9}
Event{start=9, end=17}
Event{start=11, end=13}
Event{start=12, end=15}
Event{start=14, end=15}

0 1 2 3 4 5 6 7 8 9 10 11 12 13 14 15 16 17 18
  1       5
    2         7
        4 5
            6       10
                8 9
                  9                      17
                       11    13
                          12       15
                                14 15

 */