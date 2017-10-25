package com.darcy.main.review;

import java.util.List;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import static java.util.stream.Collectors.toList;

/**
 * Author by darcy
 * Date on 17-10-25 上午10:02.
 * Description:
 *
 * 约瑟夫环问题, 给定一个n大小的队列, 从0开始数, 数到m的出队列.
 * 输出出队列顺序.
 */
public class JosephRing {

  /**
   * 0, 1, 2,,, M-3, M-2, [M-1], M, M+1,,, N-2, N-1 // [M-1]表示要出队列的数据.
   * 那么下一次报数序列是
   * M, M+1,,, N-2, N-1, 0, 1, 2,,,M-3, M-2
   * 重新排序
   * 0, 1, 2,,, M-3, M-2, M-1,,, N-3, N-2;
   * 所以N个人报数问题可以分解为N-1个人报数的问题, 继续分解为N-2个人报数的问题,
   * 所以F(N, M) = (F(N-1, M) + M) % N
   *
   * 求最后一个出队列的人z在队列中的index.
   *
   * @param n 总人数
   * @param m 每数多数个人出一个.
   * @Return 最后一个出队的人的index.
   */
  public static int solution(int n, int m) {
    if (n == 1) {
      return 0;
    } else {
      int i = (solution(n - 1, m) + m) % n;
      return i;
    }
  }

  /**
   *
   * @param n
   * @param m
   */
  public static void solution2(int n, int m) {
    List<Integer> data = IntStream.rangeClosed(1, n).boxed().collect(toList());
    System.out.println(data);
    int k = 0;
    while (data.size() > 0) {
      k += m;
      k = k % data.size() - 1;
      if (k < 0) {
        System.out.println(data.get(data.size() - 1));
        data.remove(data.size() - 1);
        k = 0;
      } else {
        System.out.println(data.get(k));
        data.remove(k);
      }
    }
  }

  public static void main(String[] args) {
    int result = solution(10, 3);
    System.out.println(result);

    System.out.println();

    solution2(10, 3);
  }

}
