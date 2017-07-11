package ctci5th.chapter8.section3;

import java.util.Stack;

/**
 * Author by darcy
 * Date on 17-7-7 上午8:56.
 * Description:
 *
 * 实现一个栈, 支持Push, Pop, min, 并且都是O(1)的时间复杂度.
 *
 * 可以添加一个字段记录最小值，但是如果这个最小值被移除了，那么如何找到次最小值.
 * 解决思路: 每次添加一个元素, 都记录一个当前的最小值;可以创建一个复合结构.
 */
public class P832_StackPopPushMinO_oneTimeComplexcity extends Stack<NodeWithMin> {

    public void pushElement(Integer value) {
        int min = Math.min(value, minElement());
        NodeWithMin node = new NodeWithMin(value, min);
        push(node);
    }

    public Integer popElement() {
        NodeWithMin node = super.pop();
        return node.value;
    }

    public Integer minElement() {
        if (isEmpty()) {
            return Integer.MAX_VALUE;
        }
        return peek().min;
    }


}
class NodeWithMin{
    public int value;
    public int min;

    public NodeWithMin(int value, int min) {
        this.value = value;
        this.min = min;
    }
}