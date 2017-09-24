package eopi.ch11_heap;

import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.PriorityQueue;

/**
 * Author by darcy
 * Date on 17-9-24 下午4:15.
 * Description:
 * <p>
 * 数组几乎已经是有序的, 那么怎样高效的实现排序????
 * 几乎有序: 数组中的每个元素距离sorted-array中位置最远是k.
 * <p>
 * Write a program which takes as input a very long sequence of numbers and prints
 * the numbers in sorted order. Each number is at most k away from its correctly sorted
 * position. (Such an array is sometimes referred to as being For example, no
 * number in the sequence (3,-1,2,6,4,5,8} is more than 2 away from its final sorted
 * position
 */
public class P11_3_SortAnAlmostSortedArray {

  /**
   * 因为数组已经基本有序了, 元素距离结果位置最远距离是k, 那么读取了k+1个元素一定能找到当前最小值.
   * 时间复杂度O(NLogK), 空间复杂度O(K)
   *
   * after we have read k +1 numbers, the smallest number in that group must be smaller
   * than all following numbers.
   *
   * @param iterator
   * @param k        当前元素现在的位置距离最终排序结果的位置最远是k.
   * @return
   */
  public static void solution(Iterator<Integer> iterator, int k) {
    PriorityQueue<Integer> minHeap = new PriorityQueue<>();

    // 先入k个元素到堆中.
    for (int i = 0; i < k && iterator.hasNext(); i++) {
      minHeap.add(iterator.next());
    }

    // 再入1个, 那么就一定可以找出这k个元素的最小值.
    // poll后在再加入一个, 所以heap中的元素达到k+1个以后就出堆small.
    while (iterator.hasNext()) {
      minHeap.add(iterator.next());
      Integer small = minHeap.poll();
      System.out.print(small + "\t");
    }

    while (!minHeap.isEmpty()) {
      Integer small = minHeap.poll();
      System.out.print(small + "\t");
    }
  }

  public static void main(String[] args) {
    List<Integer> list = Arrays.asList(3, -1, 2, 6, 4, 5, 8);
    solution(list.iterator(), 2);
  }
}
