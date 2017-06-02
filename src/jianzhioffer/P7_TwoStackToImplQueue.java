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
        while(!stack2.isEmpty()) {
            stack1.push(stack2.pop());
        }
        stack1.push(node);
    }

    public int pop() {
        while(!stack1.isEmpty()) {
            stack2.push(stack1.pop());
        }
        if(stack2.isEmpty()) {
            throw new RuntimeException("No Element");
        }
        return stack2.pop();
    }
}
