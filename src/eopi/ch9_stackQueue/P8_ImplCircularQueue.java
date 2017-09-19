package eopi.ch9_stackQueue;

import java.util.Arrays;
import java.util.Collections;
import java.util.NoSuchElementException;

/**
 * Author by darcy
 * Date on 17-9-19 下午3:37.
 * Description:
 * <p>
 * 实现循环队列.
 */
public class P8_ImplCircularQueue {

  public static class Queue {
    private int head = 0, tail = 0, numQueueElements = 0;
    private static final int SCALE_FACTOR = 2;
    private Integer[] entries;

    public Queue(int capacity) {
      entries = new Integer[capacity];
    }

    public void enqueue(Integer x) {
      if (numQueueElements == entries.length) { // Need to resize.
        // Makes the queue elements appear consecutively.
        // 不是很懂这里是什么意思.
        // 应该是 index head -> index 0;
        Collections.rotate(Arrays.asList(entries), -head);
        // Resets head and tail.
        head = 0;
        tail = numQueueElements;
        // newLength: newLength the length of the copy to be returned
        // a copy of the original array, truncated or padded with nulls
        // to obtain the specified length.
        entries = Arrays.copyOf(entries, numQueueElements * SCALE_FACTOR);
      }
      entries[tail] = x;
      tail = (tail + 1) % entries.length;
      ++numQueueElements;
    }

    public Integer dequeue() {
      if (numQueueElements != 0) {
        --numQueueElements;
        Integer ret = entries[head];
        head = (head + 1) % entries.length;
        return ret;
      }
      throw new NoSuchElementException("Dequeue called on an empty queue.");
    }

    public int size() {
      return numQueueElements;
    }
  }

  public static void main(String[] args) {
    int[] array = new int[]{0, 0, 1, 1, 1, 0, 0, 0};
    array = Arrays.copyOf(array, array.length * 2);
    System.out.println(Arrays.toString(array));
  }
}
