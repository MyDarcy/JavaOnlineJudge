package nowcoder.pingduoduo;

import java.util.Scanner;

/**
 * Created by hzq19 on 2017/8/1.
 * 给定一个无序数组，包括正数，负数和0， 要求从其中找出三个数的乘机，
 * 使得乘机最大，要求时间复杂度O(n),空间复杂度O(1)
 *
 * input
 * 4
 * 3 4 1 2
 *
 * ouput 24
 */
public class MaxSum {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int numbers = Integer.parseInt(scanner.nextLine().trim());
        String[] intStringArray = scanner.nextLine().trim().split("\\s+");
        int[] ints = new int[intStringArray.length];

        for (int i = 0; i < numbers; i++) {
            ints[i] = Integer.parseInt(intStringArray[i]);
        }

        int twoMaxSumPositive = 1;
        int twoMaxSumNegative = 1;

    }
}
