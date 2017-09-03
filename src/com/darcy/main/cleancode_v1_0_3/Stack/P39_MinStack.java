package com.darcy.main.cleancode_v1_0_3.Stack;

import java.util.Stack;

/**
 * Author by darcy
 * Date on 17-9-3 下午10:42.
 * Description:
 *
 * 设计一个stack, push, pop, top, getmin都是常数时间.
 *
 * Design a stack that supports push, pop, top, and retrieving the minimum element in
 * constant time.
 *  push(x) – Push element x onto stack.
 *  pop() – Removes the element on top of the stack.
 *  top() – Get the top element.
 *  getMin() – Retrieve the minimum element in the stack
 *
 */
public class P39_MinStack {

  static class MinStack {
    private Stack<Integer> stack = new Stack<>();
    private Stack<Integer> minStack = new Stack<>();

    public void push(Integer x) {
      stack.push(x);
      // 小于等于栈顶的才放入元素才放入栈顶.连续放入相同大小的元素. 那么最小栈也是连续放入了多个值. 3,2,2,2,2
      if (minStack.isEmpty() || x <= minStack.peek()) {
        minStack.push(x);
      }
    }


    public Integer pop() {
      if (!stack.isEmpty()) {
        Integer value = stack.pop();
        if (value.equals(minStack.peek())) {
          minStack.pop();
        }
        return value;
      }

      throw new RuntimeException("empty stack.");
    }

    public Integer top() {
      return stack.peek();
    }

    public Integer getMin() {
      if (minStack != null) {
        return minStack.peek();
      }
      throw new RuntimeException("empty stack");
    }
  }

}
