package jianzhioffer;

import java.util.Arrays;

/**
 * Created by darcy
 * 2017/5/4--0:33
 * Description:
 * 在8×8的国际象棋上摆放八个皇后，使其不能相互攻击，
 * 即任意两个皇后不得处在同一行、同一列或者同一对角斜线上。
 * 下图中的每个黑色格子表示一个皇后，这就是一种符合条件的摆放方法。请求出总共有多少种摆法。
 */
public class P28_EightQueen {

    private static int count;

    /**
     * 由于八个皇后的任意两个不能处在同一行，那么这肯定是每一个皇后占据一行。
     * 于是我们可以定义一个数组ColumnIndex[8]，数组中第i个数字表示位于第i行的皇后的列号。
     * 先把ColumnIndex的八个数字分别用0-7初始化，接下来我们要做的事情就是对数组ColumnIndex做全排列。
     * 由于我们是用不同的数字初始化数组中的数字，因此任意两个皇后肯定不同列。
     * 我们只需要判断得到的每一个排列对应的八个皇后是不是在同一对角斜线上，也就是数组的两个下标i和j，
     * 是不是i-j==ColumnIndex[i]-Column[j]或者j-i==ColumnIndex[i]-ColumnIndex[j]。
     * @param ints
     */
    public static void solution(int[] ints) {
        if (ints == null) {
            throw new RuntimeException("array is null");
        }

        solution(ints, 0, ints.length);
    }

    private static void solution(int[] ints, int start, int length) {
        if (start == length - 1) {
            if (check(ints)) {
                count++;
                /*System.out.println(Arrays.toString(ints));*/
                printQueue(ints);
            }
        } else {
            for (int i = start; i < length; i++) {
                swap(ints, i, start);
                solution(ints, start + 1, length);
                swap(ints, i, start);
            }
        }

    }

    private static void printQueue(int[] ints) {
        for (int i = 0; i < ints.length; i++) {

            for (int j = 0; j < ints.length; j++) {
                if (j != ints[i]) {
                    System.out.print(" |");
                } else {
                    System.out.print(ints[i] + "|");
                }
            }
            System.out.println();

        }
        System.out.println("\n");
    }

    private static void swap(int[] ints, int i, int j) {
        int temp = ints[i];
        ints[i] =ints[j];
        ints[j] = temp;
    }

    private static boolean check(int[] ints) {
        for (int i = 0; i < ints.length; i++) {
            for (int j = i + 1; j < ints.length; j++) {
                if (Math.abs(i - j) == Math.abs(ints[i] - ints[j])) {
                    return false;
                }

            }
        }
        return true;
    }

    public static void main(String[] args) {
        int[] ints = new int[8];
        for (int i = 0; i < 8; i++) {
            ints[i] = i;
        }
        solution(ints);

        System.out.println(count);
    }

}
