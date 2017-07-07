package crackingTheCodingInterview;

import java.util.Stack;

/**
 * Author by darcy
 * Date on 17-7-7 上午9:25.
 * Description:
 * 上一种方案中, 每放入一个元素, 都要存储当前的最小值. 而且构造复合对象的事件开销和空间开销都要稍微大一点．
 *
 * 改进: 使用一个额外的栈来记录当前的最小值.
 */
public class P832_StackPopPushMinO_oneTimeComplexity2 extends Stack<Integer> {
    private Stack<Integer> min = new Stack<>();

    public void pushElement(Integer element) {
        if (element < minElement()) {
            min.push(element);
        }
        push(element);
    }

    public Integer popElement() {
        Integer value = pop();
        if (value == minElement()) {
            min.pop();
        }
        return value;
    }

    public Integer minElement() {
        if (min.isEmpty()) {
            return Integer.MAX_VALUE;
        }
        return min.peek();
    }
}
