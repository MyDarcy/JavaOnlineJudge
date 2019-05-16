package leetcode;

import java.util.*;

/**
 * 求给定字符串中最长非重复子串;
 * Given a string, find the length of the longest substring without repeating characters.
 *
 * Example 1:
 *
 * Input: "abcabcbb"
 * Output: 3
 * Explanation: The answer is "abc", with the length of 3.
 * Example 2:
 *
 * Input: "bbbbb"
 * Output: 1
 * Explanation: The answer is "b", with the length of 1.
 * Example 3:
 *
 * Input: "pwwkew"
 * Output: 3
 * Explanation: The answer is "wke", with the length of 3.
 *              Note that the answer must be a substring, "pwke" is a subsequence and not a substring.
 */
public class P0003_M_LengthOfLongestSubstring {

  /**
   * 两个指针，指针之间的元素不重复；right位置前的元素一定是都处理过的；
   */

  public int lengthOfLongestSubstring2(String s) {
    Set<Character> characters = new HashSet<>();
    LinkedList<Character> stack = new LinkedList<>();
    int max = 0;
    // 问题在于i会一直++; 这种思路会把重复元素之间的元素出栈，而这些元素跟后面的元素是可能构成最长无重复元素的子序列的；
//    for (int i = 0; i < s.length(); i++) {
//      char toAdd = s.charAt(i);
//      if (!stack.isEmpty() && characters.contains(toAdd)) {
//
//      }
//    }

    return max;
  }

  /**
   * The idea is use a hash set to track the longest substring without repeating characters so far,
   * use a fast pointer j to see if character j is in the hash set or not, if not, great, add it
   * to the hash set, move j forward and update the max length, otherwise, delete from the head by
   * using a slow pointer i until we can put character j to the hash set.
   * left元素被移除，一定是因为后边出现了重复的元素；前进到set中无重读元素；任何时候[left, right)之间一定没有重复元素；
   * @param s
   * @return
   */
  public int lengthOfLongestSubstring3(String s) {
    int left = 0;
    int right = 0;
    int maxLength = 0;
    Set<Character> set = new HashSet<>();
    // left 到 right 之间没有重复的元素时候才会向set中添加新的元素;
    while (right < s.length()) {
      // 没有遇到重复的char
      if (!set.contains(s.charAt(right))) {
        set.add(s.charAt(right));
        right++;
        maxLength = Integer.max(maxLength, set.size());
      } else {
        set.remove(s.charAt(left)); // left代表一直删除已经处理的元素知道没有重复元素可以继续放入了；
        left++;
      }
    }
    return maxLength;
  }

  /**
   * keep a hashmap which stores the characters in string as keys and their positions as values,
   * and keep two pointers which define the max substring. move the right pointer to scan through
   * the string , and meanwhile update the hashmap. If the character is already in the hashmap,
   * then move the left pointer to the right of the same character last found. Note that the two
   * pointers can only move forward.
   *
   * 两个指针之间的元素无重复；
   * @param s
   * @return
   */
  public int lengthOfLongestSubstring(String s) {
    Map<Character, Integer> map = new HashMap<>();
    int i = 0;
    int j = 0;
    int max = 0;
    // j指向的是最左边的不重复元素的指针; 而且一定是之前的出现的多个元素x的最大索引；
    for (; i < s.length(); i++) {
      if (map.containsKey(s.charAt(i))) {
        // 当前序列中重复元素的最右边的index; 比较j是因为序列中有其他元素可能重复出现；（）
        j = Integer.max(j, map.get(s.charAt(i)) + 1); // 当前元素已经出现过的最右边的位置：
      }
      map.put(s.charAt(i), i);
      max = Integer.max(max, i - j + 1);
    }
    return max;
  }

  public int lengthOfLongestSubstring4(String s) {
    if(s== null) {
      return 0;
    }
    int result = 0;
    int start = 0;
    Set<Character> uniTest = new HashSet();
    for(int end = 0; end<s.length(); end++){
      while(uniTest.contains(s.charAt(end))) { // 直到不出现重复元素;
        uniTest.remove(s.charAt(start));
        start++;
      }
      result = Math.max(result, end-start+1);
      uniTest.add(s.charAt(end));
    }
    return result;
  }

  public static void main(String[] args) {
    P0003_M_LengthOfLongestSubstring instance = new P0003_M_LengthOfLongestSubstring();
    System.out.println(instance.lengthOfLongestSubstring("abcabcbb"));
    System.out.println(instance.lengthOfLongestSubstring("bbbbb"));
    System.out.println(instance.lengthOfLongestSubstring("pwwkew"));
    System.out.println(instance.lengthOfLongestSubstring("abcdccbaed"));

  }

}
