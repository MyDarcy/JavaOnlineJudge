package ctci5th.chapter8.section3;

import java.util.Stack;

/**
 * Author by darcy
 * Date on 17-7-7 上午11:15.
 * Description:
 * 两个stack实现一个队列.
 */
public class P835_TwoStackMakeQueue1 {

    /**
     * 思路: stack1 存放所有新放入的节点. stack2 主要用于移除节点.
     *
     * 问题是: 每次数据操作都涉及大量的数据移动.
     */
    Stack<Integer> stack1 = new Stack<Integer>();
    Stack<Integer> stack2 = new Stack<Integer>();

    public void push(int node) {
        while(!stack2.isEmpty()) {
            stack1.push(stack2.pop());
        }
        stack1.push(node);
    }

    /**
     * 移除之前,先把stack1中的所有元素移动到stack2中, 然后从stack2中移除栈顶元素,　此时栈顶元素就是放入最久的元素.
     * @return
     */
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
