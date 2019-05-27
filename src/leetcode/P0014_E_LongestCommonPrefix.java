package leetcode;

import java.util.Arrays;
import java.util.Optional;

/**
 * Write a function to find the longest common prefix string amongst an array of strings.
 *
 * If there is no common prefix, return an empty string "".
 *
 * Example 1:
 *
 * Input: ["flower","flow","flight"]
 * Output: "fl"
 * Example 2:
 *
 * Input: ["dog","racecar","car"]
 * Output: ""
 * Explanation: There is no common prefix among the input strings.
 * Note:
 *
 * All given inputs are in lowercase letters a-z.
 *
 */
public class P0014_E_LongestCommonPrefix {
  public String longestCommonPrefix(String[] strs) {
    if (strs == null || strs.length == 0) {
      return null;
    }
    Integer minLength = Arrays.stream(strs).map(String::length).min(Integer::compareTo).get();
    if (minLength == 0) {
      return "";
    }
    int i = 0;
    for (; i < minLength; i++) {
      char currentChar = strs[0].charAt(i);
      final int  charIndex = i;
      boolean result = Arrays.stream(strs).map(s -> s.charAt(charIndex)).allMatch(character -> character == currentChar);
      if (!result) break;
    }
    return strs[0].substring(0, i);
  }

  /**
   * 将其中一个作为前缀字符串, indexOf(subString)
   * @param strs
   * @return
   */
  public String longestCommonPrefix2(String[] strs) {
    if (strs.length == 0) return "";
    String pre = strs[0];
    for (int i = 1; i < strs.length; i++)
      while(strs[i].indexOf(pre) != 0)
        pre = pre.substring(0,pre.length()-1);
    return pre;
  }

  /**
   * official solution
   * 1. 水平找；两两没有找到，长度-1， 继续跟下面比较：
   * LCP(S1, S2, ..., Sn) = LCP(LCP(LCP(S1, S2), S3), Sn)
   * @param strs
   * @return
   */
  public String longestCommonPrefix3(String[] strs) {
    if (strs.length == 0) return "";
    String prefix = strs[0];
    for (int i = 1; i < strs.length; i++)
      while (strs[i].indexOf(prefix) != 0) { // 没有找到，就是-1；
        prefix = prefix.substring(0, prefix.length() - 1);
        if (prefix.isEmpty()) return "";
      }
    return prefix;
  }

  /**
   * 垂直找
   * @param strs
   * @return
   */
  public String longestCommonPrefix4(String[] strs) {
    if (strs == null || strs.length == 0) return "";
    for (int i = 0; i < strs[0].length() ; i++){
      char c = strs[0].charAt(i);
      for (int j = 1; j < strs.length; j ++) {
        if (i == strs[j].length() || strs[j].charAt(i) != c)
          return strs[0].substring(0, i);
      }
    }
    return strs[0];
  }

  /**
   * 分治法
   * 最坏情况下，时间复杂度O(S), S = m * n;
   * 空间复杂度O(m * logn)
   * @param strs
   * @return
   */
  public String longestCommonPrefix5(String[] strs) {
    if (strs == null || strs.length == 0) return "";
    return longestCommonPrefix(strs, 0 , strs.length - 1);
  }

  private String longestCommonPrefix(String[] strs, int l, int r) {
    if (l == r) {
      return strs[l];
    }
    else {
      int mid = (l + r)/2;
      String lcpLeft =   longestCommonPrefix(strs, l , mid);
      String lcpRight =  longestCommonPrefix(strs, mid + 1,r);
      return commonPrefix(lcpLeft, lcpRight);
    }
  }

  String commonPrefix(String left,String right) {
    int min = Math.min(left.length(), right.length());
    for (int i = 0; i < min; i++) {
      if ( left.charAt(i) != right.charAt(i) )
        return left.substring(0, i);
    }
    return left.substring(0, min);
  }

  /**
   * 二叉搜索树;
   * 搜指定的长度是否是公共子串：
   * @param strs
   * @return
   */
  public String longestCommonPrefix6(String[] strs) {
    if (strs == null || strs.length == 0)
      return "";
    int minLen = Integer.MAX_VALUE;
    for (String str : strs)
      minLen = Math.min(minLen, str.length());
    int low = 1;
    int high = minLen;
    while (low <= high) {
      int middle = (low + high) / 2;
      if (isCommonPrefix(strs, middle))
        low = middle + 1;
      else
        high = middle - 1;
    }
    return strs[0].substring(0, (low + high) / 2);
  }

  private boolean isCommonPrefix(String[] strs, int len){
    String str1 = strs[0].substring(0,len);
    for (int i = 1; i < strs.length; i++)
      if (!strs[i].startsWith(str1))
        return false;
    return true;
  }

  public static void main(String[] args) {
    P0014_E_LongestCommonPrefix instance = new P0014_E_LongestCommonPrefix();
    String[] strings = {"flower", "flow", "flight"};
    System.out.println(instance.longestCommonPrefix(strings));
  }
}
