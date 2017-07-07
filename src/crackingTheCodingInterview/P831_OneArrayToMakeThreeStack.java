package crackingTheCodingInterview;

/**
 * Author by darcy
 * Date on 17-7-6 下午10:06.
 * Description:
 * 使用一个数组来实现三个栈.
 *
 * 思路: 均分数组, 同时每个栈都维持一个指针记录栈顶位置.
 */
public class P831_OneArrayToMakeThreeStack {

    private static final int stackSize = 100;
    private final int[] array = new int[stackSize * 3];
    private final int[] stackPointers = {-1, -1, -1};

    /**
     * 注意最开始的index都是-1;
     * @param stackNumber
     * @param value
     */
    public void push(int stackNumber, int value) {
        if (stackPointers[stackNumber] + 1 == stackSize) {
            throw new RuntimeException("Out of space.");
        }
        stackPointers[stackNumber]++;
        array[position(stackNumber)] = value;
    }

    public Integer pop(int stackNumber) {
        if (stackPointers[stackNumber] == -1) {
            throw new RuntimeException("No Space.");
        }
        // 获取栈顶元素, 栈顶元素清0, 栈指针自减;
        Integer value = array[position(stackNumber)];
        array[position(stackNumber)] = 0;
        stackPointers[stackNumber]--;
        return value;
    }


    public Integer peek(Integer stackNumber) {
        Integer value = array[position(stackNumber)];
        return value;
    }

    private int position(int stackNumber) {
        return stackNumber * stackSize + stackPointers[stackNumber];
    }

}
