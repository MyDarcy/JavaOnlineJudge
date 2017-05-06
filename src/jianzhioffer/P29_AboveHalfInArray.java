package jianzhioffer;

import java.util.Arrays;

/**
 * Created by darcy
 * 2017/5/5--0:08
 * Description:
 * 数组中有一个数字出现的次数超过数组长度的一半，请找出这个数字。
 * 例如输入一个长度为9的数组{1,2,3,2,2,2,5,4,2}。
 * 由于数字2在数组中出现了5次，超过数组长度的一半，因此输出2。如果不存在则输出0。
 */
public class P29_AboveHalfInArray {

    /**
     * 可能存在没有这样的数的情况; 所以不能直接取中位值;
     * @param array
     * @return
     */
    public int MoreThanHalfNum_Solution(int [] array) {
        Arrays.sort(array);
        int max = 1;
        int temp = 1;
        // 统计连续出现相同数字的最大次数;
        for (int i = 0; i < array.length - 1; i++) {
            if (array[i] == array[i + 1]) {
                temp++;
                if (temp > max) {
                    max = temp;
                }
            } else {
                temp = 1;
            }
        }
        if (max > array.length / 2) {
            return array[array.length / 2];
        } else {
            return 0;
        }
    }

}
