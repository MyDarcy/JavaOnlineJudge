package com.darcy.main.cleancode_v1_0_3.ArrayString;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by hzq19
 * Date on 2017/8/31 23:39.
 * Description:
 *
 * 给定整形数组和目标值，找到两个数加起来=目标值
 *
 * 返回: 两个数在数组中的index.
 */
public class TwoSum1 {

    /**
     * 暴力的方法。遍历。
     * O(n^2)的时间复杂度，O(1)的空间复杂度
     */

    /**
     * 时间复杂度 O(n), 空间复杂度O(n)
     * @param array
     * @param target
     * @return
     */
    public static int[] solution(int[] array, int target) {
        Map<Integer, Integer> numberToIndex = new HashMap<>();
        for (int i = 0; i < array.length; i++) {
            // x + y = target, 往后遍历到y的时候，x也就找到了.
            // 第一次找到x的时候, map中并不包含y.
            if (numberToIndex.containsKey(target - array[i])) {
                return new int[]{numberToIndex.get(target - array[i]), i};
            }
            numberToIndex.put(array[i], i);
        }

        throw new RuntimeException("no pair found");
    }


    public static void main(String[] args) {
        int[] array = {1, 2, 3, 4, 5, 6, 9, 10, 19, 20};
        int[] result = solution(array, 28);
        System.out.println(Arrays.toString(result));

    }

}
