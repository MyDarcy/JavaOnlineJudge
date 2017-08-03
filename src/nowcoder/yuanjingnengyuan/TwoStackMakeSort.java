package nowcoder.yuanjingnengyuan;

import java.util.ArrayList;
import java.util.Collections;

/**
 * Created by hzq19 on 2017/8/3.
 */
public class TwoStackMakeSort {

    /**
     * 将结果栈中小于临时栈栈顶的元素都移动到临时栈，然后将之前保存的临时栈栈顶元素移动到结果栈即可.
     * 将大元素放到结果栈的最前面(因为是用ArrayList来模拟栈)
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
            while (!result.isEmpty() && result.get(result.size() - 1) < integer) {
                original.add(result.remove(result.size() - 1));
            }
            result.add(integer);
        }
        return result;
    }

    public static void main(String[] args) {
        int[] numbers = new int[]{1, 2, 3, 4, 5};
        TwoStackMakeSort demo = new TwoStackMakeSort();
        ArrayList<Integer> list = demo.twoStacksSort(numbers);
        System.out.println(list);

    }

}
