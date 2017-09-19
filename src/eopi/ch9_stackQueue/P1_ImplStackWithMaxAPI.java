package eopi.ch9_stackQueue;

import java.util.Deque;
import java.util.LinkedList;

/**
 * Author by darcy
 * Date on 17-9-18 下午9:32.
 * Description:
 *
 * 实现一个栈, 支持max API, 即取出最大的max值.
 *
 */
public class P1_ImplStackWithMaxAPI {

  public static class CacheWithMax {
    public Integer element;
    public Integer max;

    public CacheWithMax(Integer element, Integer max) {
      this.element = element;
      this.max = max;
    }
  }

  public static class Stack {
    private Deque<CacheWithMax> stack = new LinkedList<>();

    public boolean empty() {
      return stack.isEmpty();
    }

    public Integer max() {
      if (empty()) {
        throw new RuntimeException("empty stack.");
      }

      return stack.peek().max;
    }

    public Integer pop() {
      if (empty()) {
        throw new RuntimeException("Empty stack");
      }
      return stack.removeFirst().element;
    }

    // 每个元素放入都需要维护一个 number-max pair.
    public void push(Integer number) {
      stack.push(new CacheWithMax(number, Math.max(number, empty() ? number : max())));
    }
  }
}
