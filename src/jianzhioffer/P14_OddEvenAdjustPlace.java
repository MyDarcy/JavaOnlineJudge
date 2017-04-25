package jianzhioffer;

import java.util.ArrayList;

/**
 * Created by darcy
 * 2017/4/9--23:59
 * Description:
 *
 * nowcoder: 输入一个整数数组，实现一个函数来调整该数组中数字的顺序，使得所有的**奇数**位于数组的前半部分，
 * 所有的偶数位于位于数组的后半部分，并保证奇数和奇数，偶数和偶数之间的相对位置不变。
 */
public class P14_OddEvenAdjustPlace {

    /*
    时间开销和空间开销都是O(N)
     */
    public void reOrderArray(int [] array) {
        ArrayList<Integer> odd = new ArrayList<>(array.length);
        ArrayList<Integer> even = new ArrayList<>(array.length);

        for (int i = 0; i < array.length; i++) {
            // 奇数;
            if ((array[i] & 1) == 1) {
                odd.add(array[i]);
            } else {
                even.add(array[i]);
            }
        }

        for (int i = 0; i < odd.size(); i++) {
            array[i] = odd.get(i);
        }

        int offset = odd.size();
        for (int i = 0; i < even.size(); i++) {
            array[offset + i] = even.get(i);
        }
    }

    /*

     */
}
