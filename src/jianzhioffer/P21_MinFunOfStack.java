package jianzhioffer;

import java.util.Deque;
import java.util.LinkedList;

/**
 * Created by darcy
 * 2017/4/16--0:19
 * Description:
 * 定义栈的数据结构，请在该类型中实现一个能够得到栈最小元素的min函数。
 */
public class P21_MinFunOfStack {

    private Deque<Integer> dataStack = new LinkedList<>();
    private Deque<Integer> minStack = new LinkedList<>();

    public void push(int node) {

        dataStack.addLast(node);
        if (minStack.size() == 0) {
            minStack.addLast(node);
        } else if (minStack.size() != 0 && node > minStack.getLast()) {
            minStack.addLast(minStack.getLast());
        } else {
            minStack.add(node);
        }
    }

    public void pop() {
        if (dataStack.size() == 0) {
            throw new RuntimeException("empty stack");
        }
        dataStack.removeLast();
        minStack.removeLast();
    }

    public int top() {
        if (dataStack.size() == 0) {
            throw new RuntimeException("empty stack");
        }
        return dataStack.peekLast();

    }

    public int min() {
        if (minStack.size() == 0) {
            throw new RuntimeException("empty stack");
        }
        return minStack.peekLast();
    }

}
