package com.darcy.main.review;

import java.util.ArrayList;
import java.util.List;

/**
 * Author by darcy
 * Date on 17-9-13 上午9:07.
 * Description:
 * <p>
 * 字符串相关.
 */
public class MyStrings {

  /**
   * 字符串的逆序.
   *
   * @param str
   * @return
   */
  public static String reverse(String str) {
    StringBuilder sb = new StringBuilder(str);
    sb.reverse();
    int start = 0;
    for (int i = 0; i < str.length(); i++) {
      if (sb.charAt(i) == ' ') {
        swap(sb, start, i - 1);
      }
      start = i + 1;
    }
    return sb.toString();
  }

  private static void swap(StringBuilder sb, int start, int end) {
    while (start < end) {
      char tem = sb.charAt(start);
      sb.setCharAt(start, sb.charAt(end));
      sb.setCharAt(end, tem);
      start++;
      end--;
    }
  }

  /**
   * 两个字符串是否是由相同的字符组成.
   * 字母以及字母的个数是否相同.
   * <p>
   * 1. 排序.
   * 2. 统计个数.
   * 点: getBytes, 256.
   *
   * @param s1
   * @param s2
   * @return
   */
  public static boolean isTwoStrOfSameChars(String s1, String s2) {

    if (s1 == null || s2 == null) {
      return false;
    }

    if (s1.length() != s2.length()) {
      return false;
    }

    int[] number1 = new int[128];
    int[] number2 = new int[128];

    for (int i = 0; i < s1.length(); i++) {
      number1[s1.charAt(i)]++;
      number2[s2.charAt(i)]++;
    }

    for (int i = 0; i < number1.length; i++) {
      if (number1[i] != number2[i]) {
        return false;
      }
    }

    return true;

  }

  /**
   * 移除字符串中重复的非第一个字符.
   * <p>
   * 1.统计各个字符出现的个数.
   * char[256], 第一次出现, 置1, 非第一次出现, 置\0然后遍历一遍把这些字符去掉, 或者直接把非重复的字符添加
   * 到StringBuilder中.
   * <p>
   * 2. regex
   * replaceAll去掉的是前面的字符, 但是我们要的是去掉后面的字符,所以是reverse以后再replaceAll
   *
   * @param str
   * @return
   */
  public static String removeDuplicateChar(String str) {
    StringBuilder sb = new StringBuilder(str).reverse();
    String replaceString = sb.toString().replaceAll("(?s)(.)(?=.*\\1)", "");
    StringBuilder result = new StringBuilder(replaceString).reverse();
    return result.toString();
  }

  /**
   * 获取所有的组合情况,但是要满足特定的约束
   * 1.给定[1,2,3,4,5,6], 3,5不能相邻, 4不能在三号位上.
   * <p>
   * 思路: 先构造图(满足约束), 然后图深度遍历的算法, 遍历的结果使用remove去掉.
   *
   * @param ints
   * @return
   */
  public static List<String> getAllPermutationWithConstraints(int[] ints) {
    int length = ints.length;
    boolean[] visited = new boolean[length];
    int[][] graph = new int[length][length];
    List<String> list = new ArrayList<>();

    buildGraph(graph, ints);

    for (int i = 0; i < ints.length; i++) {
      depthFirstTranverse(i, visited, "", graph, ints, list);
    }

    return list;
  }

  private static void buildGraph(int[][] graph, int[] ints) {
    for (int i = 0; i < ints.length; i++) {
      for (int j = 0; j < ints.length; j++) {
        if (i == j) {
          graph[i][j] = 0;
        } else {
          graph[i][j] = 1;
        }
      }
    }
    // index 3,5跟数值3,5不一样吧.
    // 但是构造的ints是这样的构造的.
    graph[3][5] = 0;
    graph[5][3] = 0;
  }

  private static void depthFirstTranverse(int start, boolean[] visited, String combination, int[][] graph, int[] ints, List<String> list) {
    visited[start] = true;
    // str加了一个char.
    combination += ints[start];
    if (combination.length() == ints.length) {
      if (combination.charAt(2) != '4') {
        list.add(combination);
      }
    }
    for (int i = 0; i < ints.length; i++) {
      if (graph[start][i] == 1 && !visited[i]) {
        depthFirstTranverse(i, visited, combination, graph, ints, list);
      }
    }
    // 去掉加的char, 然后设置为未访问.
    combination = combination.substring(0, combination.length() - 1);
    visited[start] = false;
  }


  /**
   * 解法有问题.
   *
   * @param str
   * @return
   */
  public static List<String> combination(String str) {
    char[] charArray = str.toCharArray();
    List<String> result = new ArrayList<>();
    StringBuilder sb = new StringBuilder("");
    for (int i = 1; i <= charArray.length; i++) {
      combination(charArray, 0, i, sb, result);
    }

    return result;
  }

  private static void combination(char[] charArray, int start, int length, StringBuilder sb, List<String> result) {
    if (length == 0) {
      result.add(sb.toString());
      return;
    }

    if (start >= length) {
      return;
    }

    sb.append(charArray[start]);
    combination(charArray, start + 1, length - 1, sb, result);
    sb.deleteCharAt(sb.length() - 1);
    combination(charArray, start + 1, length, sb, result);

  }


  /**
   * 所有可能的组合情况.
   * @param array
   * @return
   */
  public static List<String> combinations2(char[] array) {
    int length = array.length;
    char[] cached = new char[length];
    boolean[] visited = new boolean[length];
    int result = length;
    List<String> list = new ArrayList<>();

    outer:
    while (true) {
      int index = 0;
      while (visited[index]) {
        visited[index] = false;
        ++result;
        if (++index == length) {
          break outer;
        }
      }
      visited[index] = true;
      cached[--result] = array[index];
      list.add(new String(cached).substring(result));
    }
    return list;
  }

  public static void main(String[] args) {
    System.out.println("\n----reverseString----");
    String str = "how are you!";
    String reverseString = reverse(str);
    System.out.println(reverseString);

    System.out.println("\n----isTwoStringOfSameChars----");
    String sameChars1 = "AABBCCDDEabcd";
    String sameChars2 = "DDCCBBAAabcdE";
    boolean sameResult = isTwoStrOfSameChars(sameChars1, sameChars2);
    System.out.println(sameResult);

    System.out.println("\n----removeDuplicatedChars----");
    String removeDuplcateString = "system.out.println(java+python+go)";
//    String removeDuplcateString = "abcaabcd";
    String removeDuplcateResult = removeDuplicateChar(removeDuplcateString);
    System.out.println(removeDuplcateResult);

    System.out.println("\n----Combinations-----");
    int[] combinationsInts = {1, 2, 2, 3, 4, 5};
    List<String> result = getAllPermutationWithConstraints(combinationsInts);
    System.out.println(result);

    System.out.println("\n----allCombinations---");
    String allCombinationStr = "123";
    List<String> allCombinationResult = combination(allCombinationStr);
    System.out.println(allCombinationResult);

    System.out.println("\n----allCombinations2---");
    String allCombinationStr2 = "123";
    List<String> allCombinationResult2 = combinations2(allCombinationStr.toCharArray());
    System.out.println(allCombinationResult2);
  }

}
