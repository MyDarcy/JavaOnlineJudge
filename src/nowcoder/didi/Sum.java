package nowcoder.didi;

import java.util.Scanner;

/**
 * Created by hzq19
 * Date on 2017/8/26 15:59.
 * Description:
 *
 * 给定整数序列，求连续子串最大和
 *
 * input:
 * -23 17 -7 11 -2 1 -34
 *
 * output
 * 21
 */
public class Sum {

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);

        String line = input.nextLine().trim();
        /*System.out.println(line);*/

        /*String line = "-23 17 -7 11 -2 1 -34";*/

        String[] strings = line.split("\\s+");
        int[] array = new int[strings.length];
//        long[] array2 = new long[strings.length];
        for (int i = 0; i < strings.length; i++) {
            array[i] = Integer.parseInt(strings[i]);
//            array2[i] = Long.parseLong(strings[i]);
        }
        int result = dpSolution(array);
//        long result2 = dpSolution(array2);
        System.out.println(result);
//        System.out.println(result2);
    }

    private static long dpSolution(long[] array2) {
        if (array2 == null || array2.length == 0) {
            return 0;
        }

        long sum = 0;
        long min = 0;
        long max = 0;

        /*int sum = array[0];
        int min = array[0];
        int max = Integer.MIN_VALUE;*/

        for (int i = 0; i < array2.length; i++) {
            sum += array2[i];
            // 记录到目前为止的最小子数组的和.
            if (sum < min) {
                min = sum;
            }

            // 求目前为止的最大子数组的和.
            if ((sum - min) > max) {
                max = sum - min;
            }
        }
        return max;
    }

    /**
     * 当前总和 - 前k个元素组成的最小子数组的和.
     * @param array
     * @return
     */
    public static int dpSolution(int[] array) {

        if (array == null || array.length == 0) {
            return 0;
        }

        int sum = 0;
        int min = 0;
        int max = 0;

        /*int sum = array[0];
        int min = array[0];
        int max = Integer.MIN_VALUE;*/

        for (int i = 0; i < array.length; i++) {
            sum += array[i];
            // 记录到目前为止的最小子数组的和.
            if (sum < min) {
                min = sum;
            }

            // 求目前为止的最大子数组的和.
            if ((sum - min) > max) {
                max = sum - min;
            }
        }
        return max;
    }

}
/*
nowcoder上面通过率83.3%; 不是溢出问题。
 */