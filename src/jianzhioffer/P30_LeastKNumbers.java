package jianzhioffer;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

/**
 * Created by darcy
 * 2017/5/6--15:50
 * Description:
 * 输入n个整数，找出其中最小的K个数。例如输入4,5,1,6,2,7,3,8这8个数字，
 * 则最小的4个数字是1,2,3,4,。
 */
public class P30_LeastKNumbers {
    /**
     * partition : 比第k个数小的都在数组的左边; 比第k个数大的都在第k个数右边;
     *
     * @param input
     * @param k
     */


    private static Random random = new Random(31);

    public ArrayList<Integer> GetLeastNumbers_Solution(int[] input, int k) {

        /*
          条件检查;
         */
        if (input == null || input.length == 0 || k > input.length || k == 0) {
            return new ArrayList<>();
        }


        int length = input.length;
        int start = 0;
        int end = length - 1;
        int index = partition(input, k, start, end);
        while (index != k - 1) {
            if (index > k - 1) {
                index = partition(input, k,0, index - 1);
            } else {
                index = partition(input, k, index + 1, end);
            }
        }

        ArrayList<Integer> list = new ArrayList<>();
        for (int i = 0; i < k; i++) {
            list.add(input[i]);
        }
        return list;
    }

    private int partition(int[] input, int k,  int start, int end) {

        // 有点问题;
       /* int small = start - 1;
        for (int i = start; i < end; i++) {
            if (input[i] < input[end]) {
                small++;
                if (small != i) {
                    swap(input, small, i);
                }
            }
        }
        small++;
        swap(input, small, end);
        return small;*/

        /*链接：https://www.nowcoder.com/questionTerminal/6a296eb82cf844ca8539b57c23e6e9bf*/

        int pivotkey = input[k - 1];
        // start是k-1处的元素，k-1是start处的元素;
        swap(input, k - 1, start);
        while (start < end) {
            // 从后面找一个小于pivotKey的元素;
            while (start < end && input[end] >= pivotkey)
                end--;
            // 小于pivotKey的元素移到前面;但是start处的元素不一定是大于pivotkey的(但是此时它交换到了end处)，
            // 不过不用担心，下一轮循环中上面的那个循环它会直接退出，把这个元素移到前面去;
            swap(input, start, end);
            // 从前面找一个大于pivotKey的元素;
            while (start < end && input[start] <= pivotkey)
                start++;
            swap(input, start, end);
        }
        return start;
    }

    private void swap(int[] input, int start, int end) {
        int temp = input[start];
        input[start] = input[end];
        input[end] = temp;
    }

    public static void main(String[] args) {
        int[] ints = new int[30];
        for (int i = 0; i < ints.length; i++) {
            ints[i] = random.nextInt(ints.length);
        }
//        ints = new int[]{4,5,1,6,2,7,3,8};
        P30_LeastKNumbers demo = new P30_LeastKNumbers();
        System.out.println(Arrays.toString(ints));
        ArrayList<Integer> list = demo.GetLeastNumbers_Solution(ints, 10);

        System.out.println(list);
    }
}

/*
[4,5,1,6,2,7,3,8],10
{4, 5, 1, 6, 2, 7, 3, 8}, 8
[4,5,1,6,2,7,3,8],0
 */
