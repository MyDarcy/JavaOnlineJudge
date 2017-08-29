package eopi.ch6_arrays;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Author by darcy
 * Date on 17-8-29 下午11:04.
 * Description:
 */
public class P10_ArrayPermutation {

  public static List<List<Integer>> solution(List<Integer> array) {
    List<List<Integer>> result = new ArrayList<>();
    solution(array, 0, result);
    return result;
  }

  private static void solution(List<Integer> array, int index,  List<List<Integer>> result) {
    if (index == array.size()) {
      result.add(new ArrayList<Integer>(array));
      return;
      // 注意循环的范围.
    } else {
      for (int i = index; i < array.size(); i++) {
        Collections.swap(array, i, index);
        solution(array, index + 1, result);
        Collections.swap(array, i, index);
      }
    }
  }

  public static void main(String[] args) {
    List<Integer> list = new ArrayList<>();
    list.add(1);
    list.add(2);
    list.add(3);
    list.add(4);
    List<List<Integer>> result = solution(list);
    System.out.println(result.size());
    for (List<Integer> a : result) {
      System.out.println(a);
    }
  }
}
