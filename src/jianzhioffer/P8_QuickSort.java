package jianzhioffer;

import java.util.Arrays;
import java.util.Random;

/**
 * Created by darcy
 * 2017/4/2--0:01
 * Description:
 */
public class P8_QuickSort {

    private static Random random = new Random(31);

    public static void main(String[] args) {
        int[] ints = new int[100];
        for (int i = 0; i < ints.length; i++) {
            ints[i] = random.nextInt(1000);
        }
        quickSort(ints);

        System.out.println(Arrays.toString(ints));;

        for (int i = 0; i < ints.length - 1; i++) {
            if (ints[i] > ints[i + 1]) {
                throw new RuntimeException("not sorted.");
            }
        }
        System.out.println("ok...");
    }

    private static void quickSort(int[] ints) {
        quickSort(ints, ints.length, 0, ints.length - 1);
    }

    private static void quickSort(int[] ints, int length, int start, int end) {
        // one elment left, no need to sort...
        if (start == end) {
            return;
        }

        int index = partition(ints, length, start, end);
        if (index > start)
            quickSort(ints, length, start, index - 1);
        if (index < end)
            quickSort(ints, length, index + 1, end);
    }

    private static int partition(int[] ints, int length, int start, int end) {
        if (ints == null || length <= 0 || start < 0 || end >= length) {
            throw new RuntimeException("wrong parameters");
        }

        // upper exclusive...
        int randomIndex = random.nextInt(end - start + 1) + start;
        swap(ints, randomIndex, end);
        int small = start - 1;
        for (int i = start; i < end; i++) {
            if (ints[i] < ints[end]) {
                ++small;
                if (small != i) {
                    swap(ints, i, small);
                }
            }
        }
        ++small;
        swap(ints, small, end);
        return small;
    }

    private static void swap(int[] ints, int randomIndex, int end) {
        int temp = ints[randomIndex];
        ints[randomIndex] = ints[end];
        ints[end] = temp;
    }

}
