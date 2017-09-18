package eopi.ch9_stackQueue;

import java.util.Deque;
import java.util.LinkedList;

/**
 * Author by darcy
 * Date on 17-9-18 下午9:51.
 * Description:
 */
public class P1_ImplStackWithMaxAPI2 {

  static class MaxWithCount {
    Integer max;
    Integer count;

    public MaxWithCount(Integer max, Integer count) {
      this.max = max;
      this.count = count;
    }
  }

  static class Stack {
    // 注意deque模拟stack只能是在一端操作.
    private Deque<Integer> stack = new LinkedList<>();
    private Deque<MaxWithCount> cacheMaxWithCount = new LinkedList<>();

    public boolean empty() {
      return stack.isEmpty();
    }

    public Integer max() {
      if (empty()) {
        throw new RuntimeException("empty stack");
      }

      return cacheMaxWithCount.peek().max;
    }

    public Integer pop() {
      if (empty()) {
        throw new RuntimeException("empty stack");
      }
      Integer element = stack.pollFirst();
      if (!cacheMaxWithCount.isEmpty()) {

        // 小于max的都不记录.
        if (Integer.compare(element, cacheMaxWithCount.peekFirst().max) == 0) {
          cacheMaxWithCount.peekFirst().count
              = cacheMaxWithCount.peekFirst().count - 1;
          if (cacheMaxWithCount.peekFirst().count.equals(0)){
            cacheMaxWithCount.removeFirst();
          }
        }
      }
      return element;
    }

    /**
     *
     * @param number
     * @return
     */
    public void push(Integer number) {
      stack.addFirst(number);
      if (!cacheMaxWithCount.isEmpty()) {
        if (Integer.compare(number, cacheMaxWithCount.peekFirst().max) == 0) {
          cacheMaxWithCount.peekFirst().count += 1;
        } else if (Integer.compare(number, cacheMaxWithCount.peekFirst().max) > 0) {
          cacheMaxWithCount.addFirst(new MaxWithCount(number, 1));
        }
        // 小于当前max的不计录.
      } else {
        cacheMaxWithCount.addFirst(new MaxWithCount(number, 1));
      }
    }
  }



}
