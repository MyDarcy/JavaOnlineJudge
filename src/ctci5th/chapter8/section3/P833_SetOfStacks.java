package ctci5th.chapter8.section3;

import java.util.ArrayList;

/**
 * Author by darcy
 * Date on 17-7-7 上午9:39.
 * Description:
 * 请实现一种数据结构SetOfStacks，由多个栈组成，其中每个栈的大小为size，
 * 当前一个栈填满时，新建一个栈。该数据结构应支持与普通栈相同的push和pop操作。
 * <p>
 * nowcoder要求: 给定一个操作序列int[][2] ope(C++为vector<vector<int>>)，
 * 每个操作的第一个数代表操作类型，若为1，则为push操作，后一个数为应push的数字；若为2，
 * 则为pop操作，后一个数无意义。请返回一个int[][](C++为vector&ltvector&ltint>>)，
 * 为完成所有操作后的SetOfStacks，顺序应为从下到上，默认初始的SetOfStacks为空。保证数据合法。
 */
public class P833_SetOfStacks {

    private final ArrayList<ArrayList<Integer>> stacks = new ArrayList<>();
    private final int PER_STACK_SIZE;

    public P833_SetOfStacks(int size) {
        PER_STACK_SIZE = size;
    }

    public void push(Integer element) {
        ArrayList<Integer> lastStack = getLastStack();
        if (lastStack == null) {
            lastStack = new ArrayList<>();
            lastStack.add(element);
        } else if (lastStack.size() == PER_STACK_SIZE) {
            ArrayList<Integer> stack = new ArrayList<>();
            stack.add(element);
            stacks.add(stack);
        } else {
            lastStack.add(element);
        }
    }

    public Integer pop() {
        ArrayList<Integer> lastStack = getLastStack();
        if (lastStack == null) {
            throw new RuntimeException("no stack available...");
        } else {
            Integer integer = lastStack.remove(lastStack.size() - 1);
            if (lastStack.size() == 0) {
                stacks.remove(stacks.size() - 1);
            }
            return integer;
        }
    }

    public ArrayList<Integer> getLastStack() {

        if (stacks.isEmpty()) {
            return null;
        }

        return stacks.get(stacks.size() - 1);
    }


    /**
     * 链接：https://www.nowcoder.com/questionTerminal/69f0ffed01c741c5ae5594a23f7cd739
     *
     * 题目没看清.
     *
     * @param ope　代表了放入元素的个数和操作的类型.
     * @param size
     * @return
     */
    public ArrayList<ArrayList<Integer>> setOfStacks(int[][] ope, int size) {
        // write code here
        ArrayList<ArrayList<Integer>> list = new ArrayList<ArrayList<Integer>>();
        // 记录当前push和pop的stack.
        ArrayList<Integer> curArray = new ArrayList<Integer>(size);
        list.add(curArray);
        for (int i = 0; i < ope.length; i++) {
            switch (ope[i][0]) {
                //1:push
                case 1:
                    //当前数组未满
                    if (curArray.size() != size) {
                        curArray.add(ope[i][1]);
                    } else {
                        curArray = new ArrayList<Integer>(size);
                        list.add(curArray);
                        curArray.add(ope[i][1]);
                    }
                    break;
                //2:pop
                case 2:
                    //当前数组不为空
                    if (curArray.size() != 0) {
                        curArray.remove(curArray.size() - 1);
                    } else {
                        list.remove(list.size() - 1);
                        curArray = list.get(list.size() - 1);
                        curArray.remove(curArray.size() - 1);
                    }
                    break;
            }
        }
        return list;
    }


}
