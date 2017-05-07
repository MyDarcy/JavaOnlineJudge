package jianzhioffer;

/**
 * Created by darcy
 * 2017/5/6--17:51
 * Description:
 */
public class P30_PartialContinuousArrayMaxSum {

    /*
        遍历到一个元素的时候，如果此前的最大连续子数组和小于0，那么最大连续子数组和重新计数(设置为当前值);
        否则就和当前元素累加;
     */
    public int FindGreatestSumOfSubArray(int[] array) {
        if (array == null || array.length == 0) {
            throw new RuntimeException("wrong parameters");
        }

        int currentMax = 0;
        int target = Integer.MIN_VALUE;
        for (int i = 0; i < array.length; i++) {
            if (currentMax <= 0) {
                currentMax = array[i];
            } else {
                currentMax += array[i];
            }

            if (target < currentMax) {
                target = currentMax;
            }

        }
        return target;
    }

    /**
     * DP来做;
     * f[i]表示以第i个数结尾的子数组的最大和;
     * @param array
     * @return
     */

    public int FindGreatestSumOfSubArray2(int[] array) {

        int[] f = new int[array.length];

        for (int i = 0; i < array.length; i++) {
            if (i == 0 || f[i - 1] <= 0) {
                f[i] = array[i];
            } else if (i != 0 && f[i - 1] > 0) {
                f[i] = f[i - 1] + array[i];
            }

        }

        int max = Integer.MIN_VALUE;
        for (int i = 0; i < f.length; i++) {
            if (f[i] > max) {
                max = f[i];
            }
        }

        return max;

    }
}