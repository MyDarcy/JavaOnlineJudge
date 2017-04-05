package nowcoder.toutiao;

import java.util.Arrays;
import java.util.Scanner;

/**
 * Created by csqiang on 2017/3/30.
 *
 * @Author mr.darcy
 * Description:
时间限制：C/C++语言 2000MS；其他语言 4000MS
内存限制：C/C++语言 131072KB；其他语言 655360KB
题目描述：
按数组的形式给出函数f(x)的取值，即数组A的A[0]元素为f(0)的取值，数组的取值都为整数，
函数在每个点都是严格单调递增或者严格递减（即A[i-1] != A[i] != A[i+1]），要求找出
最宽的先上升后下降的区间（这个区间内函数的值必须先上升到一个点然后下降，区间的上升段和下降段长度必须都大于0）。
1. 如果找到符合条件的最大区间输出数组对应的左右下标（有多个最大区间时，输出最左边的那个”）
2. 找不到那么输出-1 -1
 */
public class MaxIncreasingAndDecreasingRange {
    /*public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        int number = input.nextInt();
        int[] array = new int[number];
        int i = 0;
        while (input.hasNextInt()) {
            array[i++] = input.nextInt();
        }

//        System.out.println(Arrays.toString(array));

        int[] result = getMaxIncreasingAndDecreasingRange(array);

    }

    private static int[] getMaxIncreasingAndDecreasingRange(int[] array) {
        int[] result = {-1, -1};
        return result;
    }*/

    public static void main(String[] args) {
        // TODO Auto-generated method stub
        int[] nums = { 1, 2, 1, 2, 1, 2, 3, 4, 3, 2 };
        for (Integer num : solve(nums)) {
            System.out.print(num + " ");
        }

    }

    public static int[] solve(int[] nums) {
        int max = Integer.MIN_VALUE;
        int left = 0;
        int right = 0;
        //dp[i][j]表示i~j之间上升下降的状态值的和，上升一次记为1，下降一次记为-1，貌似没啥用
        Integer[][] dp = new Integer[nums.length][nums.length];
        for (int i = 0; i < nums.length; i++) {
            int j = i;
            dp[i][j] = 0;
        }
        //找出一段合法的后，i直接移到right位置
        for (int i = right; i < nums.length; i++) {
            for (int j = i + 1; j < nums.length; j++) {
                //如果已经有值，则不再计算
                if (dp[i][j] != null) {
                    continue;
                }
                //如果相邻元素上升，则记为1，反之，记为-1
                dp[j - 1][j] = nums[j - 1] < nums[j] ? 1 : -1;

                //如果出现一开始就是下降的情况 或者 先下降后上升的情况，则直接break
                if ((j - 1 == i && dp[j - 1][j] == -1) ||
                        (j - 1 > i && dp[j - 2][j - 1] == -1 && dp[j - 1][j] == 1)) {
                    break;
                }
                //因为之前必须得有上升的，但是一直上升也不行，所以记录结果的时候，必须是在下降的状态
                if (dp[j - 1][j] < 0 && j - i > max) {
                    left = i;
                    right = j;
                    max = j - i;
                }
            }
        }
        if (max <= 0) {
            return new int[] { -1, -1 };
        } else {
            return new int[] { left, right };
        }

    }
}
