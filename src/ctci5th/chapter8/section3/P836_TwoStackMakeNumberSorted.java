package ctci5th.chapter8.section3;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;
import java.util.Stack;

/**
 * Author by darcy
 * Date on 17-7-7 下午12:56.
 * Description:
 *
 * 按升序对栈进行排序(最大元素位于栈顶),最多只有一个额外的栈来存放临时数据.但是不能
 * 将数据复制到临时的数据结构中,该栈支持push, pop, peek, isEmpty等操作.
 *
 * newcoder要求:
 * 请编写一个程序，按升序对栈进行排序（即最大元素位于栈顶），要求最多只能使用一个额外的栈存放临时数据，
 * 但不得将元素复制到别的数据结构中。 给定一个int[] numbers(C++中为vector<int>)，其中第一个元素为栈顶，
 * 请返回排序后的栈。请注意这是一个栈，意味着排序过程中你只能访问到第一个元素。
 * 测试样例：
 * [1,2,3,4,5]
 * 返回：[5,4,3,2,1]
 */
public class P836_TwoStackMakeNumberSorted {


    /**
     * 思路: 传入待排序的栈,将栈顶元素出栈(保存起来),然后和结果栈的栈顶元素比较,将结果栈栈顶大于待排序栈顶元素的先移动到
     * 待排序的栈,然后将将待排序栈顶元素移动到结果栈,如此. 时间复杂度O(n2), 空间复杂度O(1)
     *
     * 如果允许使用的栈数目无限,那么也可以实现quicksort和mergesort.
     * mergesort: 再创建两个栈,将本栈分成两个部分.然后递归排序两个栈,然后将两个栈归并到一起排好序.放回原来的位置.
     * 但是这样需要每一层递归都需要创建两个栈.
     *
     * quicksort: 创建两个栈,然后根据基准元素pivot将原栈分成两个栈,然后对这两个栈进行递归排序,然后归并在一起.
     * 放回原来的栈中,同样,每一层递归都需要创建两个栈.
     *
     * @param original
     * @return
     */
    public static Stack<Integer> sort(Stack<Integer> original) {
        Stack<Integer> result = new Stack<>();
        while (!original.isEmpty()) {
            Integer value = original.pop();
            // 这一步非常巧妙,只是将当前结果栈的比原栈顶大的元素移动到原栈,然后把原栈顶元素移动到目的栈,并不急着将
            // 之前移动到原栈的大元素移动回去.因为下一轮迭代就可以完成这个事,而复杂度也不会改变.
            while (!result.isEmpty() && result.peek() > value) {
                original.push(result.pop());
            }
            result.push(value);
        }
        return result;
    }

    /**
     * nowcoder solutions
     * @param numbers
     * @return
     */
    public ArrayList<Integer> twoStacksSort(int[] numbers) {
        // write code here
        ArrayList<Integer> original = new ArrayList<>();
        ArrayList<Integer> result = new ArrayList<>();
        for (int i = 0; i < numbers.length; i++) {
            original.add(numbers[i]);
        }
        while (!original.isEmpty()) {
            Integer integer = original.remove(original.size() - 1);
            while (!result.isEmpty() && result.get(result.size() - 1) > integer) {
                original.add(result.remove(result.size() - 1));
            }
            result.add(integer);
        }
        Collections.reverse(result);
        return result;
    }

    public static void main(String[] args) {
        Stack<Integer> original = new Stack<>();
        int size = 10;
        int[] numbers = new int[size];
        Random random = new Random(31);
        for (int i = 0; i < size; i++) {
            int item = random.nextInt(100);
            System.out.print(item + "\t");
            original.push(item);
            numbers[i] = item;
        }
        System.out.println();
        Stack<Integer> result = sort(original);
        while (!result.isEmpty()) {
            System.out.print(result.pop() + "\t");
        }
        System.out.println();
        P836_TwoStackMakeNumberSorted demo = new P836_TwoStackMakeNumberSorted();
        ArrayList<Integer> list = demo.twoStacksSort(numbers);
        System.out.println(list);


    }
}
