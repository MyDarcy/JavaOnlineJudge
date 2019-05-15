package leetcode;

import java.util.HashMap;
import java.util.Map;

/**
 * Author by: darcy
 * Created on 17-3-4-上午11:39.
 * Description:
 * Given an array of integers, return indices of the two numbers such that they add up to a specific target.
   You may assume that each input would have exactly one solution, and you may not use the same element twice.

 Example:
 Given nums = [2, 7, 11, 15], target = 9,

 Because nums[0] + nums[1] = 2 + 7 = 9,
 return [0, 1].

 思路： 从前往后遍历一遍, 如果target - current不在map中, 那么将(current, index)添加到map中;
       在的话, 则返回target-current的index和current的index;
 */
public class P1_E_TwoSum {

    /**
     * 目前最优解
     * @param nums
     * @param target
     * @return
     */
    public int[] twoSum(int[] nums, int target) {
        HashMap<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            int x = target - nums[i];
            if (map.containsKey(x)) {
                return new int[] {map.get(x), i};
            }
            map.put(nums[i], i);
        }
        return null;
    }

    /**
     * 暴力解法
     */
    public int[] twoSumBruceForce(int[] nums, int target) {
        for (int i = 0; i < nums.length; i++) {
            for (int j = 0; j < nums.length; j++) {
                if (nums[i] + nums[j] == target) {
                    return new int[]{i, j};
                }
            }
        }
        return null;
    }

    /**
     * 两遍hash
     */
    public int[] twoSumTwoHash(int[] nums, int target) {
        Map<Integer, Integer> maps = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            maps.put(nums[i], i);
        }

        for (int i = 0; i < nums.length; i++) {
            int subTarget = target - nums[i];
            // 把自己算了两遍的情况;
            if (maps.containsKey(subTarget) && maps.get(subTarget) != i) {
                return new int[]{i, maps.get(subTarget)};
            }
        }

        return null;
    }
}
