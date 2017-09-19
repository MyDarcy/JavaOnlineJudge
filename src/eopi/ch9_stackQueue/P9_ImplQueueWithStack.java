package eopi.ch9_stackQueue;

import java.util.Deque;
import java.util.LinkedList;
import java.util.NoSuchElementException;

/**
 * Author by darcy
 * Date on 17-9-19 下午3:57.
 * Description:
 *
 * 用栈实现队列.
 *
 * The intuition for improving the time complexity of dequeue is that after we move
 elements from the first stack to the second stack, any further dequeues are trivial,
 until the second stack is empty. This is true even if we need to enqueue, as long as we
 enqueue onto the first stack. When the second stack becomes empty, and we need to
 perform a dequeue, we simply repeat the process of transferring from the first stack to
 the second stack. In essence, we are using the first stack for enqueue and the second
 for dequeue.
 *
 */
public class P9_ImplQueueWithStack {

  // 两个栈.
  private Deque<Integer> enq = new LinkedList<>();
  private Deque <Integer> deq = new LinkedList<>();

  public void enqueue(Integer x) { enq.addFirst(x); }

  public Integer dequeue() {
    if (deq.isEmpty()){
      // Transfers the elements from enq to deq.
      while (!enq.isEmpty()){
        deq.addFirst(enq.removeFirst());
      }
    }
    if (!deq.isEmpty()){
      return deq.removeFirst();
    }
    throw new NoSuchElementException("Cannot pop empty queue");
  }

}
