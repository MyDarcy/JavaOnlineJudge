package jianzhioffer;

import java.util.Stack;

/**
 * Author by darcy
 * Date on 17-9-7 下午4:02.
 * Description:
 *
 * 两个栈实现一个队列, 一个只入，一个只出.
 *
 */
public class P7_TwoStackToImplQueue2 {
  Stack<Integer> stack1 = new Stack<Integer>();
  Stack<Integer> stack2 = new Stack<Integer>();

  public void push(int node) {
    stack1.push(node);
  }

  public int pop() {
    if (stack2.size() <= 0) {
      while (stack1.size() > 0) {
        /*int data = stack1.peek();//查看栈顶元素，但不移除它
        stack1.pop();//弹出栈顶元素
        stack2.push(data);//压入
         */
        stack2.push(stack1.pop());
      }
    }

    if (stack2.isEmpty()) {
      try {
        throw new Exception("queue is empty.");
      } catch (Exception e) {
      }
    }
    /**
     * int head = stack2.peek();
     * stack2.pop();
     */
    int head = stack2.pop();
    return head;

  }
}
