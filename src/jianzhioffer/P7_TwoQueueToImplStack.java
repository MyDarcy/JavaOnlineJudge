package jianzhioffer;

import java.util.ArrayDeque;
import java.util.Queue;

/**
 * Created by darcy
 * 2017/3/28--23:54
 * Description:
 *
 * 两个队列实现一个栈.
 *
 */
public class P7_TwoQueueToImplStack {
  Queue<Integer> queue1 = new ArrayDeque<>();
  Queue<Integer> queue2 = new ArrayDeque<>();

  public void push(int node) {
    //两个栈都为空时，优先考虑queue1
    if (queue1.isEmpty()&&queue2.isEmpty()) {
      queue1.add(node);
      return;
    }

    //如果queue1为空，queue2有元素，直接放入queue2
    // queue2中仍然是按照放入的顺序排序的.
    if (queue1.isEmpty()) {
      queue2.add(node);
      return;
    }

    if (queue2.isEmpty()) {
      queue1.add(node);
      return;
    }

  }

  public int pop() {
    //两个栈都为空时，没有元素可以弹出
    if (queue1.isEmpty()&&queue2.isEmpty()) {
      try {
        throw new Exception("stack is empty");
      } catch (Exception e) {
      }
    }
    //如果queue1为空，queue2有元素， 将queue2的元素依次放入queue1中，直到最后一个元素，我们弹出。
    if (queue1.isEmpty()) {
      while (queue2.size()>1) {
        queue1.add(queue2.poll());
      }
      return queue2.poll();
    }

    if (queue2.isEmpty()) {
      while (queue1.size()>1) {
        queue2.add(queue1.poll());
      }
      return queue1.poll();
    }

    return (Integer) null;
  }

  public static void main(String[] args) {
    P7_TwoQueueToImplStack demo08 = new P7_TwoQueueToImplStack();
    demo08.push(1);
    demo08.push(2);
    demo08.push(3);
    demo08.push(4);
    System.out.println(demo08.pop());
    System.out.println(demo08.pop());
    demo08.push(5);
    System.out.println(demo08.pop());
    System.out.println(demo08.pop());
    System.out.println(demo08.pop());
  }
}
