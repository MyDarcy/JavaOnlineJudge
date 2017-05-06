package jianzhioffer;

import java.util.Arrays;
import java.util.Random;

/**
 * Created by darcy
 * 2017/5/5--12:49
 * Description:
 */
public class P29_AboveHalfNumber2 {

    public static void main(String[] args) {

        /**
         * {1, 2, 3, 4, 2, 2, 2, 5, 6, 8, 2, 2, 2, 2, 2, 2};
         * 这个例子貌似就是死循环...
         */
        int[] array = {1, 12, 3, 4, 12, 12, 12, 5, 6, 8, 12, 12, 12, 12, 12, 2};
        int[] copyOf = Arrays.copyOf(array, array.length);
        Arrays.sort(copyOf);
        System.out.println(Arrays.toString(copyOf));
        P29_AboveHalfNumber2 demo = new P29_AboveHalfNumber2();
        int result = demo.MoreThanHalfNum_Solution2(array);
        System.out.println(result);
    }

    /**
     * 要找的数据是比其他所有所有数据出现的次数还要多;
     *
     */
    public int MoreThanHalfNum_Solution2(int [] array) {
        int result = 0;
        int count = 1;
        for (int i = 1; i < array.length; i++) {
            if (count == 0) {
                result = array[i];
                count = 1;
            } else if (array[i] != result) {
                count--;
            } else {
                count++;
            }
        }
        if (!checkMoreThanHalf(array, result)) {
            result = 0;
        }
        return result;
    }



    /**
     * 利用QuickSort的Partition
     * index < n / 2 -- 中位数在index右部分;
     * index > n / 2 -- 中位数在index左部分;
     * index == n / 2 -- find it...
     * @param array
     * @return
     */
    public int MoreThanHalfNum_Solution(int [] array) {
        if (array == null || array.length == 0) {
            throw new RuntimeException("array null or length 0");
        }

        int length = array.length;
        int middle = length / 2;
        int start = 0;
        int end = length - 1;
        int index = partition(array, start, end);
        while (index != middle) {
            if (index > middle) {
                index = partition(array, start, index - 1);
            } else {
                index = partition(array, index + 1, end);
            }
        }

        int result = array[middle];
        if (!checkMoreThanHalf(array, result)) {
            result = 0;
        }
        return result;

    }

    private int partition(int[] array, int start, int end) {
        // small 记录小于end元素的最后一个指针;
        int small = start - 1;
        for (int i = start; i < end; i++) {
            if (array[i] < array[end]) {
                ++small;
                if (small != i) {
                    swap(array, i, small);
                }
            }
        }
        ++small;
        swap(array, small, end);
        return small;
    }

    private void swap(int[] array, int i, int j) {
        int temp = array[i];
        array[i] = array[j];
        array[j] = temp;
    }

    private boolean checkMoreThanHalf(int[] array, int result) {
        int count = 0;
        for (int i = 0; i < array.length; i++) {
            if (array[i] == result) {
                count++;
            }

        }
        if (count > array.length / 2) {
            return true;
        }
        return false;
    }
}
