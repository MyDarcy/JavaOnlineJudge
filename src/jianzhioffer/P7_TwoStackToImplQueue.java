package jianzhioffer;

import java.util.Stack;

/**
 * Created by darcy
 * 2017/3/28--23:54
 * Description:
 * 用两个栈来实现一个队列，完成队列的Push和Pop操作。 队列中的元素为int类型。
 */
public class P7_TwoStackToImplQueue {
  Stack<Integer> stack1 = new Stack<Integer>();
  Stack<Integer> stack2 = new Stack<Integer>();

  public void push(int node) {
    while (!stack2.isEmpty()) {
      stack1.push(stack2.pop());
    }
    stack1.push(node);
  }

  /**
   * 反复的在栈之间倒腾数据性能并不好.
   * @return
   */
  public int pop() {
    while (!stack1.isEmpty()) {
      stack2.push(stack1.pop());
    }
    if (stack2.isEmpty()) {
      throw new RuntimeException("No Element");
    }
    return stack2.pop();
  }

  /**
   * 真正性能较高的，其实是另一个变种。即：
   * 入队时，将元素压入s1。
   * 出队时，判断s2是否为空，如不为空，则直接弹出顶元素；如为空，则将s1的元素逐个“倒入”s2，把最后一个元素弹出并出队。
   * 这个思路，避免了反复“倒”栈，仅在需要时才“倒”一次。但在实际面试中很少有人说出，可能是时间较少的缘故吧。
   */
  public int pop2() {
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
