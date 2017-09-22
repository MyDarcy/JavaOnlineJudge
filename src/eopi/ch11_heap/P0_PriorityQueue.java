package eopi.ch11_heap;

import java.util.*;

/**
 * Author by darcy
 * Date on 17-9-22 下午3:12.
 * Description:
 */
public class P0_PriorityQueue {

  /**
   * 最小堆找topK个最大值.
   * 每个字符串处理的时间是logK. 总的处理时间是O(NlogK)
   *
   * @param k
   * @param iterator
   * @return
   */
  public static List<String> topK(int k, Iterator<String > iterator) {
    // 选择长度最长的字符串.
    PriorityQueue<String> queue = new PriorityQueue<>(
        (a, b) -> Integer.compare(a.length(), b.length()));

    // 超过一定的数目, 就是移除最小的值.
    while (iterator.hasNext()) {
      queue.add(iterator.next());
      if (queue.size() > k) {
        queue.poll();
      }
    }

    return new ArrayList<>(queue);
  }

  public static void main(String[] args) {
    Random random = new Random(31);
    int number = 10;
    List<String> list = new LinkedList<>();
    for (int i = 0; i < number * 3; i++) {
      int length = 1 + random.nextInt(12);
      String str = getString(length);
      list.add(str);
    }

    System.out.println(list);

    List<String> result = topK(number, list.iterator());

    System.out.println(result);
  }

  private static String getString(int length) {
    StringBuilder sb = new StringBuilder();
    Random random = new Random();
    for (int i = 0; i < length; i++) {
      int incr = random.nextInt(26);
      sb.append((char) ('a' + incr));
    }
    return sb.toString();
  }

}
