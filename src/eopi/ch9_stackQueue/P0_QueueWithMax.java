package eopi.ch9_stackQueue;

import java.util.Collections;
import java.util.Deque;
import java.util.LinkedList;

/**
 * Author by darcy
 * Date on 17-9-19 下午3:12.
 * Description:
 *
 *
 */
public class P0_QueueWithMax {
  private Deque<Integer> queue = new LinkedList<>();

  public void enqueue(Integer number) {
    queue.addLast(number);
  }

  public Integer dequeue(Integer number) {
    if (!queue.isEmpty()) {
      return queue.removeFirst();
    }

    throw new RuntimeException("empty queue.");
  }

  public Integer max() {
    if (!queue.isEmpty()) {
      return Collections.max(queue);
    }
    throw new RuntimeException("empty queue.");
  }

}
