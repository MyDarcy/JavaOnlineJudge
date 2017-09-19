package eopi.ch9_stackQueue;

import java.util.Deque;
import java.util.LinkedList;
import java.util.NoSuchElementException;
import java.util.Queue;

/**
 * Author by darcy
 * Date on 17-9-19 下午3:59.
 * Description:
 * <p>
 * 实现一个带max()的栈.
 * <p>
 * 实现思想:
 * 入queue的时候, 从尾部入queue; 同时缓存max的队列也要不断的出队列, 直到当前max队列末尾的值比当前元素大.
 * 出queue的时候, 从头部出queue, 如果queue头部的元素刚好等于max队列头部的元素. 那么从max读列头部移除该元素.不等, 直接移除这个元素.
 * <p>
 * We now briefly describe how to update the deque on queue updates. If the queue
 * is dequeued, and if the element just dequeued is at the deque's head, we pop the
 * deque from its head; otherwise the deque remains unchanged. When we add an
 * entry to the queue, we iteratively evict from the deque's tail until the element at the
 * tail is greater than or equal to the entry being enqueued, and then add the new entry
 * to the deque's tail.
 */
public class P10_ImplQueueWithMaxAPI {

  /**
   * 实现1:
   * 实现思想:
   * 入queue的时候, 从尾部入queue; 同时缓存max的队列也要不断的出队列, 直到当前max队列末尾的值比当前元素大.
   * 出queue的时候, 从头部出queue, 如果queue头部的元素刚好等于max队列头部的元素. 那么从max读列头部移除该元素.
   * 不等, 直接移除这个元素.
   * <p>
   * @param <T>
   */
  public static class QueueWithMax<T extends Comparable<T>> {
    private Queue<T> entries = new LinkedList<>();
    private Deque<T> candidatesForMax = new LinkedList<>();

    /**
     * O(n)
     *
     * @param x
     */
    public void enqueue(T x) {
      entries.add(x);
      while (!candidatesForMax.isEmpty()) {
        // Eliminate dominated elements in candidatesForMax.
        if (candidatesForMax.getLast().compareTo(x) >= 0) {
          break;
        }
        candidatesForMax.removeLast();
      }
      candidatesForMax.addLast(x);
    }

    /**
     * O(1)
     *
     * @return
     */
    public T dequeue() {
      if (!entries.isEmpty()) {
        T result = entries.remove();
        if (result.equals(candidatesForMax.getFirst())) {
          candidatesForMax.removeFirst();
        }
        return result;
      }
      throw new NoSuchElementException("Called dequeue() on empty queue.");
    }

    public T max() {
      if (!candidatesForMax.isEmpty()) {
        return candidatesForMax.getFirst();
      }
      throw new NoSuchElementException("empty queue");
    }
  }


  /**
   * 实现2:
   *
   * An alternate solution that is often presented is to use reduction. Specifically, we
   know how to solve the stack-with-max problem efficiently (Solution 9.1 on Page 132)
   and we also know how to efficiently model a queue with two stacks (Solution 9.9 on
   Page 146), so we can solve the queue-with-max design by modeling a queue with
   two stacks-with-max. This approach feels unnatural compared to the one presented
   above.
   */
  public class QueueWithMax2 {
    private P1_ImplStackWithMaxAPI.Stack enqueue = new P1_ImplStackWithMaxAPI.Stack();
    private P1_ImplStackWithMaxAPI.Stack dequeue = new P1_ImplStackWithMaxAPI.Stack();

    public void enqueue(Integer x) {
      enqueue.push(x);
    }

    public Integer dequeue() {
      if (dequeue.empty()) {
        while (!enqueue.empty()) {
          dequeue.push(enqueue.pop());
        }
      }
      if (!dequeue.empty()) {
        return dequeue.pop();
      }
      throw new NoSuchElementException("Cannot get dequeue() on empty queue.");
    }

    public Integer max() {
      if (!enqueue.empty()) {
        return dequeue.empty() ? enqueue.max()
            : Math.max(enqueue.max(), dequeue.max());
      }
      if (!dequeue.empty()) {
        return dequeue.max();
      }
      throw new NoSuchElementException("Cannot get max() on empty queue.");
    }
  }

}
