package jianzhioffer;

import java.lang.reflect.Array;
import java.util.Arrays;

/**
 * Created by darcy
 * 2017/5/7--14:41
 * Description:
 * 把只包含素因子2、3和5的数称作丑数（Ugly Number）。
 * 例如6、8都是丑数，但14不是，因为它包含因子7。
 * 习惯上我们把1当做是第一个丑数。求按从小到大的顺序的第N个丑数。
 */
public class P34_UgleNumber {

    /**
     * 从前往后遍历;
     * 暴力的方式牛客网过不了;;;
     * @param index
     * @return
     */
    public int GetUglyNumber_Solution(int index) {

        if (index <= 0) {
            throw new RuntimeException("Wrong parameters.");
        }

        if (index == 1) {
            return 1;
        }
        int count = 1;
        int target = 0;
        for (int i = 2; true; i++) {
            if (isUglyNumber(i)) {
                count++;
                if (index == count) {
                    target = i;
                    break;
                }
            }
        }
        return target;
    }

    private boolean isUglyNumber(int number) {
        while (number % 2 == 0) {
            number /= 2;
        }

        while (number % 3 == 0) {
            number /= 3;
        }

        while (number % 5 == 0) {
            number /= 5;
        }
        return number == 1 ? true : false;
    }

    /**
     *
     * 思路: 丑数必定是前面的丑数*2, *3, * 5的结果，只是需要确定往数组中放入当前最小的满足要求的丑数;
     * @param index
     * @return
     */
    public int GetUglyNumber_Solution2(int index) {
        if (index <= 0) {
            return 0;
        }

        int[] array = new int[index];
        array[0] = 1;
        int nextIndex = 1;
        int twoIndex = 0;
        int threeIndex = 0;
        int fiveIndex = 0;

        while (nextIndex < index) {
            int min = Math.min(Math.min(array[twoIndex] * 2, array[threeIndex] * 3), array[fiveIndex] * 5);
            array[nextIndex] = min;

            // 这里处理的结果就是下一个可能的最小值; * 2 , * 3, * 5的比当前最小值大;
            // 注意是 <= 号; 不然下次放入的还是等于的那个数;
            while (array[twoIndex] * 2 <= array[nextIndex]) {
                twoIndex++;
            }
            while (array[threeIndex] * 3 <= array[nextIndex]) {
                threeIndex++;
            }
            while (array[fiveIndex] * 5 <= array[nextIndex]) {
                fiveIndex++;
            }
            ++nextIndex;
        }
        System.out.println(Arrays.toString(array));
        return array[nextIndex - 1];
    }


    public static void main(String[] args) {
        P34_UgleNumber demo = new P34_UgleNumber();
        long time1 = System.currentTimeMillis();
        int target = demo.GetUglyNumber_Solution(30);
        long time2 = System.currentTimeMillis();
        System.out.println(target + "\t time:" + (time2 - time1));
        int target2 = demo.GetUglyNumber_Solution2(30);
        long time3 = System.currentTimeMillis();
        System.out.println(target2 + "\t time:" + (time3 - time2));

    }
}

/*
测试用例:
1500

对应输出应该为:

859963392

你的输出为:

7185
 */