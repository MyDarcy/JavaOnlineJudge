package jianzhioffer;

import java.util.Stack;

/**
 * Created by darcy
 * 2017/3/28--23:54
 * Description:
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
