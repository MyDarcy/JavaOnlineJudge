package leetcode;

import java.util.HashMap;

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
public class Number_1_TwoSum {
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
}
