package jianzhioffer;

import java.util.ArrayList;

/**
 * Created by darcy
 * 2017/5/12--0:16
 * Description:
 * 输入一个递增排序的数组和一个数字S，在数组中查找两个数，
 * 使得他们的和正好是S，如果有多对数字的和等于S，输出两个数的乘积最小的
 */
public class P41_TwoSumProblem {

    /**
     * 找到第一个直接停止就可以了;
     * @param array
     * @param sum
     * @return
     */
    public ArrayList<Integer> FindNumbersWithSum(int [] array, int sum) {
        if (array == null || array.length < 2) {
            return new ArrayList<>();
        }
        int pre = 0;
        int rear = array.length - 1;

        int result = 0;
        ArrayList<Integer> list = new ArrayList<>();
        while (pre < rear) {
            result = array[pre] + array[rear];
            if (result == sum) {
                list.add(array[pre]);
                list.add(array[rear]);
                break;
            }
            if (result < sum) {
                pre++;
            } else {
                rear--;
            }
        }

        return list;
    }

    public static void main(String[] args) {
        int[] array = {1, 2, 3, 4, 7, 11, 12, 17};
        P41_TwoSumProblem demo = new P41_TwoSumProblem();
        ArrayList<Integer> list = demo.FindNumbersWithSum(array, 222);
        System.out.println(list);
    }

}
