package jianzhioffer;

import java.util.Random;

/**
 * Created by darcy
 * 2017/4/4--0:06
 * Description:
 * 大量0~200以内的数排序。
 */
public class P8_AgeSort {

    private static Random random = new Random(31);
    private static int MAX_AGE = 121;

    public static void main(String[] args) {
        int[] ints = new int[10000];
        for (int i = 0; i < ints.length; i++) {
            // not include max_age;
            ints[i] = random.nextInt(MAX_AGE);
        }

        int[] ages = ageSort(ints);
        int count = 0;
        /*for (int i = 0; i < ages.length; i++) {
            count += ages[i];
            System.out.println(count);
        }*/
        int index = 0;
        for (int i = 0; i < MAX_AGE; i++) {
            for (int j = 0; j < ages[i]; j++) {
                ints[index] = i;
                index++;
            }
        }

        for (int i = 0; i < ints.length - 1; i++) {
            if (ints[i] > ints[i + 1]) {
                throw new RuntimeException("not sorted.");
            }
        }
        System.out.println("ok...");


    }

    private static int[] ageSort(int[] ints) {
        int[] ages = new int[MAX_AGE];
        for (int i = 0; i < ints.length; i++) {
            ages[ints[i]]++;
        }
        return ages;
    }
}
