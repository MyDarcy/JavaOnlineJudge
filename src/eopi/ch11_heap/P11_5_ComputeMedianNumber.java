package eopi.ch11_heap;

import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;

/**
 * Author by darcy
 * Date on 17-9-24 下午4:51.
 * Description:
 * <p>
 * 计算一个序列的中位数字.
 */
public class P11_5_ComputeMedianNumber {


  /**
   * @return
   */
  public static void solution(List<Integer> list) {
    PriorityQueue<Integer> minHeap = new PriorityQueue<>();
    PriorityQueue<Integer> maxHeap = new PriorityQueue<>((i1, i2) -> Integer.compare(i2, i1));

    for (Integer item : list) {
      if (minHeap.isEmpty()) {
        minHeap.add(item);
      } else {
        // 最小堆中存储了大的那部分数据.
        if (item >= minHeap.peek()) {
          minHeap.add(item);
        } else {
          // 最大堆中存储小的那部分数据.
          maxHeap.add(item);
        }
      }

      // 保证两个堆中有相等数目的数据.
      // 多余的一个数据放在最小堆中,
      if (minHeap.size() > maxHeap.size() + 1) {
        maxHeap.add(minHeap.remove());
      } else if (maxHeap.size() > minHeap.size()) {
        minHeap.add(maxHeap.remove());
      }
    }

    System.out.println((minHeap.size() == maxHeap.size()) ? 0.5 * (maxHeap.peek() + minHeap.peek()) : 1.0 * minHeap.peek());
  }

  public static void main(String[] args) {
    List<Integer> list = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8);
    solution(list);
  }

}
