package jianzhioffer;

import java.util.Arrays;
import java.util.Random;

/**
 * Created by darcy
 * 2017/5/4--22:53
 * Description:
 * 8个数放到正方形的8个顶点，使得三组对面上的4个顶点是否相等。有没有这样的一种放法;
 * 仍然是全排列的问题;
 */
public class P28_SquareSumEqual {

    public static void main(String[] args) {
        int[] ints = new int[8];
        Random random = new Random(2);
        for (int i = 0; i < ints.length; i++) {
            ints[i] = random.nextInt(20);
        }

        System.out.println(Arrays.toString(ints));
        System.out.println();
        solution(ints);
    }

    private static void solution(int[] ints) {
        if (ints == null) {
            throw new RuntimeException("array null");
        }

        solution(ints, 0, ints.length);
    }

    private static void solution(int[] ints, int start, int length) {
        if (start == length - 1) {
            if (check(ints)) {
                System.out.println(Arrays.toString(ints));
                return;
            }
        } else {
            for (int i = start; i < length; i++) {
                swap(ints, i, start);
                solution(ints, start + 1, length);
                swap(ints, i, start);
            }
        }
    }

    private static void swap(int[] ints, int i, int j) {
        int temp = ints[i];
        ints[i] =ints[j];
        ints[j] = temp;
    }

    private static boolean check(int[] ints) {
        int sum = Arrays.stream(ints).sum();
        boolean result = false;
        int partial = ints[0] + ints[1] + ints[2] + ints[3];
        result = result && (partial == sum - partial);

        partial = ints[0] + ints[2] + ints[4] + ints[6];
        result = result && (partial == sum - partial);

        partial = ints[0] + ints[1] + ints[4] + ints[5];
        result = result && (partial == sum - partial);
        return result;

    }


}
